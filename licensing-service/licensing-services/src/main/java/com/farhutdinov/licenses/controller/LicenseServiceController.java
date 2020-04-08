package com.farhutdinov.licenses.controller;


import com.farhutdinov.licenses.model.License;
import com.farhutdinov.licenses.service.LicensingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    private final LicensingService licensingService;

    @Autowired
    public LicenseServiceController(LicensingService licensingService) {
        this.licensingService = licensingService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {

        return licensingService.getLicensesByOrg(organizationId);
    }

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        return licensingService.getLicense(organizationId, licenseId, "");
    }


    @RequestMapping(value = "/{licenseId}/{clientType}",method = RequestMethod.GET)
    public License getLicenceWithClient(@PathVariable(name = "organizationId") String organizationId,
                                        @PathVariable(name = "licenseId") String licenseId,
                                        @PathVariable(name = "clientType") String clientType) {
        return licensingService.getLicense(organizationId, licenseId, clientType);
    }

    @RequestMapping(value = "{licenseId}",method = RequestMethod.PUT)
    public void updateLicense(@PathVariable(name = "licenseId") String licenseId,@RequestBody License license){
        licensingService.updateLicense(license);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveLicenses(@RequestBody License license) {
        licensingService.saveLicense(license);
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") String licenseId,@RequestBody License license) {
        licensingService.deleteLicense(license);
    }
}
