package com.jdc.tallerdespliegue.services.implement;

import com.jdc.tallerdespliegue.dto.CreateTicketDTO;
import com.jdc.tallerdespliegue.models.TicketEntity;
import com.jdc.tallerdespliegue.repositories.TicketRepository;
import com.jdc.tallerdespliegue.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class TicketServiceImplements implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<TicketEntity> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketEntity findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public TicketEntity save(CreateTicketDTO ticketDTO) {
        TicketEntity ticket = new TicketEntity();
        ticket.setAsunto(ticketDTO.getAsunto());
        ticket.setDescripcion(ticketDTO.getDescripcion());
        ticket.setPrioridad(ticketDTO.getPrioridad());
        ticket.setEstado(ticketDTO.getEstado());
        return ticketRepository.save(ticket);
    }

    @Override
    public TicketEntity saves(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }


    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
