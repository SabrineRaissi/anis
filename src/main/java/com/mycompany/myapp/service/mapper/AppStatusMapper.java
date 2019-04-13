package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AppStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppStatus and its DTO AppStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppStatusMapper extends EntityMapper<AppStatusDTO, AppStatus> {



    default AppStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppStatus appStatus = new AppStatus();
        appStatus.setId(id);
        return appStatus;
    }
}
