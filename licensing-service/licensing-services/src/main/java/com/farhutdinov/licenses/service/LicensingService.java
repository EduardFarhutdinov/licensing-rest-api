package com.farhutdinov.licenses.service;

import com.farhutdinov.licenses.config.ServiceConfig;
import com.farhutdinov.licenses.model.License;
import com.farhutdinov.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicensingService {


    private final LicenseRepository licenseRepository;

    final ServiceConfig config;


    public LicensingService(@Autowired LicenseRepository licenseRepository, ServiceConfig config) {
        this.licenseRepository = licenseRepository;
        this.config = config;
    }


    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        license.setComment(config.getExampleProperty());

        return license;
    }

    public List<License> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.deleteById(license.getLicenseId());
    }
}
