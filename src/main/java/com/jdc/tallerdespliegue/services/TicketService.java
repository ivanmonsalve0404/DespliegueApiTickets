package com.jdc.tallerdespliegue.services;

import com.jdc.tallerdespliegue.dto.CreateTicketDTO;
import com.jdc.tallerdespliegue.models.TicketEntity;

import java.util.List;

public interface TicketService {
    public List<TicketEntity> findAll(); // Obtain all registers
    public TicketEntity findById(Long id); // Obtain a ticket's registration by ID
    public TicketEntity save(CreateTicketDTO ticketDTO); // Create a ticket
    TicketEntity saves(TicketEntity ticket);
    public void deleteById(Long id); //Delete for id
}
