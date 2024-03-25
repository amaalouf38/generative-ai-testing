package generativeai.architecture.client_server;

import java.io.Serializable;

public class Request implements Serializable {
    private String filename;

    public Request(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}