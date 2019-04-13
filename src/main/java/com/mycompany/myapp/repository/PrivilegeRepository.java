package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Privilege;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Privilege entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
