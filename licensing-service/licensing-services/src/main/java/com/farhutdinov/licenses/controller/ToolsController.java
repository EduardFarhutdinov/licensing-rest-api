package com.farhutdinov.licenses.controller;

import com.farhutdinov.licenses.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/tools")
public class ToolsController {

    private final DiscoveryService discoveryService;

    @Autowired
    public ToolsController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @RequestMapping(value = "/eureka/services",method = RequestMethod.GET)
    public List<String> getEurekaServices(){
        return discoveryService.getEurekaService();
    }
}
