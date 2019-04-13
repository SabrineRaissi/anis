package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.StatusInscription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatusInscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusInscriptionRepository extends JpaRepository<StatusInscription, Long> {

}
