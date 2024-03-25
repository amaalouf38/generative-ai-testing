package generativeai.architecture.client_server;

import java.io.Serializable;

public class File implements Serializable {

    public File(String name, long size, byte[] content) {
        this.name = name;
        this.size = size;
        this.content = content;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    // Getters and setters for name, size, and content
}
