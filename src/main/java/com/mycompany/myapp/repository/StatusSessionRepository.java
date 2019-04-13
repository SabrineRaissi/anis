package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.StatusSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatusSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusSessionRepository extends JpaRepository<StatusSession, Long> {

}
