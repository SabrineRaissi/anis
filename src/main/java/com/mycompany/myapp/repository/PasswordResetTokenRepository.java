package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PasswordResetToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
