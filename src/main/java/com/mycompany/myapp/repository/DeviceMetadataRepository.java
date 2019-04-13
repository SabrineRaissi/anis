package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DeviceMetadata;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DeviceMetadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {

}
