/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author DK
 */
public class Host {
    private String _ip;
    private String _Name;

    public Host() {
    }

    public Host(String _ip, String _Name) {
        this._ip = _ip;
        this._Name = _Name;
    }

    public String getIp() {
        return _ip;
    }

    public void setIp(String _ip) {
        this._ip = _ip;
    }

    public String getName() {
        return _Name;
    }

    public void setName(String _Name) {
        this._Name = _Name;
    }

    @Override
    public String toString() {
        return "Game name: "+_Name+" --- IP: "+_ip;
    }
    
    
}
