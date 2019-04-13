package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SessionInscription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SessionInscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SessionInscriptionRepository extends JpaRepository<SessionInscription, Long> {

}
