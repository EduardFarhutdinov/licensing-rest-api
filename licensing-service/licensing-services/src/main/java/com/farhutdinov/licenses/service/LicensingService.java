package com.farhutdinov.licenses.service;

import com.farhutdinov.licenses.clients.OrganizationDiscoveryClient;
import com.farhutdinov.licenses.clients.OrganizationFeignClient;
import com.farhutdinov.licenses.clients.OrganizationRestTemplateClient;
import com.farhutdinov.licenses.config.ServiceConfig;
import com.farhutdinov.licenses.model.License;
import com.farhutdinov.licenses.model.Organization;
import com.farhutdinov.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicensingService {


    private final LicenseRepository licenseRepository;

    final ServiceConfig config;


//    final OrganizationFeignClient organizationFeignClient;

    final OrganizationRestTemplateClient organizationRestClient;

    final OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    public LicensingService(LicenseRepository licenseRepository, ServiceConfig config,
                         OrganizationRestTemplateClient organizationRestClient,
                            OrganizationDiscoveryClient organizationDiscoveryClient) {
        this.licenseRepository = licenseRepository;
        this.config = config;
        this.organizationRestClient = organizationRestClient;
        this.organizationDiscoveryClient = organizationDiscoveryClient;
    }

//    @Autowired
//    public LicensingService(LicenseRepository licenseRepository, ServiceConfig config,
//                            OrganizationFeignClient organizationFeignClient, OrganizationRestTemplateClient organizationRestClient,
//                            OrganizationDiscoveryClient organizationDiscoveryClient) {
//        this.licenseRepository = licenseRepository;
//        this.config = config;
//        this.organizationFeignClient = organizationFeignClient;
//        this.organizationRestClient = organizationRestClient;
//        this.organizationDiscoveryClient = organizationDiscoveryClient;
//    }


    private Organization retrieveOrgInfo(String organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
//            case "feign":
//                System.out.println("I am using the feign client");
//                organization = organizationFeignClient.getOrganization(organizationId);
//                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                System.out.println("Используется дэфолтный ");
                organization = organizationRestClient.getOrganization(organizationId);

        }
        return organization;
    }
    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(config.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());

        licenseRepository.save(license);

    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

}
