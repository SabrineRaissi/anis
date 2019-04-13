package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EtudeUniversitaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtudeUniversitaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtudeUniversitaireRepository extends JpaRepository<EtudeUniversitaire, Long> {

}
