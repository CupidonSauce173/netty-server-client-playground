package CupidonSauce173.protocols;

import java.io.Serializable;

public class ClientDisconnectProtocol implements Serializable {

    public String reason;

    public ClientDisconnectProtocol(String reason) {
        this.reason = reason;
    }
}
