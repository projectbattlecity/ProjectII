/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import RMI.Interface.IGame;
import ServeMap.ServerFrame;
import ServeMap.ServerPanel;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK
 */
public class ClientHelper {

    public static void main(String[] args) {
        ArrayList<String> allHost = getAllHost();
        
        for (String string : allHost) {
            System.out.println(string);
        }
    }
    public static ArrayList<String> getAllHost() {
        ArrayList<String> allHost = new ArrayList<>();
        ArrayList<String> ipInterfaces = new ArrayList<>();
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.isSiteLocalAddress() && isReachable(i.getHostAddress(), 80, 0)) {
                        ipInterfaces.add(i.getHostAddress());
                    }

                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String ipInterface : ipInterfaces) {
            String[] ips = ipInterface.split("\\.");
            String _3octet = ips[0] + "." + ips[1] + "." + ips[2];
            for (int i = 1; i < 255; i++) {
                String ip = _3octet + "." + i;
                if (isReachable(ip, 1099, 20)) {
                    allHost.add(ip);
                }
            }
        }

        return allHost;
    }

    public static boolean isReachable(String addr, int openPort, int timeOutMillis) {
        // Any Open port on other machine
        // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public Host connectToServer(String ip) {
        Host h = new Host();
        try {
            Remote lookup = Naming.lookup("rmi://" + ip + ":1099/GameControl");

            h.setIp(ip);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        return h;
    }

    public void clientStart(Host h) {
        try {
            IGame objGame = (IGame) Naming.lookup("rmi://" + h.getIp() + ":1099/GameControl");
            
            objGame.addClientTank();
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
