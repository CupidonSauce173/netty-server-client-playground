package CupidonSauce173.protocols;

import java.io.Serializable;

public class ClientConnectProtocol implements Serializable {
    public String username;

    public ClientConnectProtocol(String username) {
        this.username = username;
    }
}
