package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Doctorant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Doctorant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoctorantRepository extends JpaRepository<Doctorant, Long> {

}
