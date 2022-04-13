package CupidonSauce173.events;

import java.io.Serializable;

public class ConnectEvent implements Serializable {

    public String username;

    public ConnectEvent(String username){
        this.username = username;
    }
}