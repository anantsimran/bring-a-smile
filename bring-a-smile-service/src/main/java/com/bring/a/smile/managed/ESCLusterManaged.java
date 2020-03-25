package com.bring.a.smile.managed;

import com.google.inject.Inject;
import io.dropwizard.lifecycle.Managed;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ESCLusterManaged implements Managed {

    private Client client=null;

    @Inject
    public ESCLusterManaged() {
        try {
            this.client = new PreBuiltTransportClient(
                    Settings.builder().put("client.transport.sniff", true)
                            .put("cluster.name", "bring-a-smile").build())
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() throws Exception {

    }

    public Client getClient(){
        return this.client;
    }

    @Override
    public void stop() throws Exception {

    }
}
