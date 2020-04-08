package com.farhutdinov.organizationservice.service;

import com.farhutdinov.organizationservice.model.Organization;
import com.farhutdinov.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(@Autowired OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public void saveOrganization(Organization org){
        org.setId(UUID.randomUUID().toString());
        organizationRepository.save(org);
    }

    public void updateOrganization(Organization organization){
        organizationRepository.save(organization);
    }

    public void deleteOrganization(Organization org){
        organizationRepository.delete(org);
    }

    public Organization getOrganizationById(String organizationId){
        Organization organization = organizationRepository.findOrganizationById(organizationId);
        return organization;
    }
}
