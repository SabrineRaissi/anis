package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EtapeInscription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtapeInscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtapeInscriptionRepository extends JpaRepository<EtapeInscription, Long> {

}
