package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PossibleValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PossibleValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PossibleValueRepository extends JpaRepository<PossibleValue, Long> {

}
