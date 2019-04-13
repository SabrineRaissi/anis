package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.These;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the These entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TheseRepository extends JpaRepository<These, Long> {

}
