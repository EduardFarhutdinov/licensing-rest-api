package com.farhutdinov.licenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscoveryService {

    final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public DiscoveryService(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List getEurekaService() {
        List<String> service = new ArrayList<>();

        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(instance -> {
                service.add(String.format("%s:%s", serviceName, instance.getUri()));
            });
        });

        return service;
    }
}
