package com.florido_hub.fipe_consultation.repository;

import com.florido_hub.fipe_consultation.entity.ReferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenciaRepository extends JpaRepository<Long, ReferenciaEntity> {
}
