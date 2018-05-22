package cc.doctor.data.consumer;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElasticClient {
    private static final Logger log = LoggerFactory.getLogger(ElasticClient.class);

    private Map<String, Client> clientMap = new ConcurrentHashMap<>();

    public Client getClient(ElasticClientConfig elasticClientConfig) {
        Client client = clientMap.get(elasticClientConfig.getClusterName());
        if (client == null) {
            String clusterName = elasticClientConfig.getClusterName();
            if (clusterName == null) {
                return null;
            }
            List<InetSocketAddress> inetSocketAddresses = elasticClientConfig.getInetSocketAddresses();
            TransportClient.Builder builder = new TransportClient.Builder();
            TransportClient transportClient = builder.settings(Settings.builder().put("cluster.name", clusterName)).build();
            for (InetSocketAddress transportAddress : inetSocketAddresses) {
                transportClient.addTransportAddress(new InetSocketTransportAddress(transportAddress));
            }
            client = transportClient;
        }
        return client;
    }
}
