package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PasswordResetTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PasswordResetToken and its DTO PasswordResetTokenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PasswordResetTokenMapper extends EntityMapper<PasswordResetTokenDTO, PasswordResetToken> {



    default PasswordResetToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setId(id);
        return passwordResetToken;
    }
}
