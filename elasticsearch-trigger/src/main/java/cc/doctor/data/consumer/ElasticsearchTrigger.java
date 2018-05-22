package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

public class ElasticsearchTrigger extends AsyncBulkTrigger {
    private Client client;
    private String index;
    private String type;
    private IdGenerator idGenerator;

    @Override
    public void bulkProcessData(Iterable<Event> events) {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (Event event : events) {
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex().setIndex(index).setType(type).setSource(event);
            if (idGenerator != null) {
                String id = idGenerator.newId(event);
                if (id != null) {
                    indexRequestBuilder.setId(id);
                }
            }
            bulkRequestBuilder.add(indexRequestBuilder);
        }
        bulkRequestBuilder.get();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}
