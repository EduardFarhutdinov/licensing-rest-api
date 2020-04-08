package com.farhutdinov.organizationservice.repository;

import com.farhutdinov.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization,String> {
//    public Organization findOrganizationById(String organizationId);
    public Organization findOrganizationById(String organizationId);
}
