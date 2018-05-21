package cc.doctor.data.reader;

import java.util.List;

public class CanalReaderConfig implements ReaderConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private List<String> destinations;
    private Integer entryPollSize;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public Integer getEntryPollSize() {
        return entryPollSize;
    }

    public void setEntryPollSize(Integer entryPollSize) {
        this.entryPollSize = entryPollSize;
    }
}
