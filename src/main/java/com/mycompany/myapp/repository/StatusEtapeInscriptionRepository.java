package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.StatusEtapeInscription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatusEtapeInscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusEtapeInscriptionRepository extends JpaRepository<StatusEtapeInscription, Long> {

}
