/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import RMI.Interfaces.IChat;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.utils;

/**
 *
 * @author DK
 */
public class IPHelper {

    public static void main(String[] args) {
        new IPHelper();
    }

    public IPHelper() {
        ArrayList<String> allHost = getAllHost();
        for (String string : allHost) {
            Host connectedHost = connectToServer(string);
            utils.defaultListModel.addElement(connectedHost);

        }

    }

    public static ArrayList<String> getAllHost() {
        ArrayList<String> ips = new ArrayList<>();
        ArrayList<String> ipInterfaces = new ArrayList<>();

        //tim cac interface trong may
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.isSiteLocalAddress() && isReachable(i.getHostAddress(), 80, 0)) {
                        ipInterfaces.add(i.getHostAddress());
                        System.out.println("My IP:" + i.toString());
                    }

                }
            }
        } catch (SocketException ex) {
        }

        for (String ipInterface : ipInterfaces) {
            String[] ipSplit = ipInterface.split("\\.");
            String _3octet = ipSplit[0] + "." + ipSplit[1] + "." + ipSplit[2];
            for (int i = 1; i < 255; i++) {
                String ip = _3octet + "." + i;
                System.out.println(ip);
                if (isReachable(ip, 1099, 50)) {
                    ips.add(ip);
                }
            }
        }

        return ips;
    }

    public static boolean isReachable(String addr, int openPort, int timeOutMillis) {
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static Host connectToServer(String ip) {
        Host h = new Host();
        try {
            IChat lookup = (IChat) Naming.lookup("rmi://" + ip + ":1099/Chat");
            h.setIp(ip);
            h.setName(lookup.getGameName());
        } catch (NotBoundException ex) {
            Logger.getLogger(IPHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(IPHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(IPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }

}
