package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PrivilegeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Privilege and its DTO PrivilegeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrivilegeMapper extends EntityMapper<PrivilegeDTO, Privilege> {



    default Privilege fromId(Long id) {
        if (id == null) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setId(id);
        return privilege;
    }
}
