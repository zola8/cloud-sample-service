package com.mz.sampleservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "hello user :)";
    }

    @GetMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getServices() {
        return this.discoveryClient.getServices();
    }

    @GetMapping(value = "/service-instances/{applicationName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

}
