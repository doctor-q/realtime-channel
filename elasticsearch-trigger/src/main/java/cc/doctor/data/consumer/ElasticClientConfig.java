package cc.doctor.data.consumer;

import java.net.InetSocketAddress;
import java.util.List;

public class ElasticClientConfig {
    private String clusterName;
    private List<InetSocketAddress> inetSocketAddresses;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public List<InetSocketAddress> getInetSocketAddresses() {
        return inetSocketAddresses;
    }

    public void setInetSocketAddresses(List<InetSocketAddress> inetSocketAddresses) {
        this.inetSocketAddresses = inetSocketAddresses;
    }
}
