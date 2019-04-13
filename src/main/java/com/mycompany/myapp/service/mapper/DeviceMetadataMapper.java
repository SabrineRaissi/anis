package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DeviceMetadataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeviceMetadata and its DTO DeviceMetadataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeviceMetadataMapper extends EntityMapper<DeviceMetadataDTO, DeviceMetadata> {



    default DeviceMetadata fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeviceMetadata deviceMetadata = new DeviceMetadata();
        deviceMetadata.setId(id);
        return deviceMetadata;
    }
}
