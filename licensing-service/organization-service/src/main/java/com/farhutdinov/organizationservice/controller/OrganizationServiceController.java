package com.farhutdinov.organizationservice.controller;

import com.farhutdinov.organizationservice.model.Organization;
import com.farhutdinov.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "v1/organizations")
@RestController
public class OrganizationServiceController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationServiceController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable(name = "organizationId") String organizationId) {
        return organizationService.getOrganizationById(organizationId);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable(name = "organizationId") String organizationId, @RequestBody Organization org) {
        organizationService.updateOrganization(org);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable(name = "organizationId") String organizationId,
                                   @RequestBody Organization org)
    {
        organizationService.deleteOrganization(org);
    }

    @RequestMapping(name = "/{organizationId}",method = RequestMethod.POST)
    public void saveOrganization(@RequestBody Organization organization){
        organizationService.saveOrganization(organization);
    }
}
