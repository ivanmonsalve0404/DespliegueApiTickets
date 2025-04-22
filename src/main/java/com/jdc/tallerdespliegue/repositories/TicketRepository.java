package com.jdc.tallerdespliegue.repositories;

import com.jdc.tallerdespliegue.models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
