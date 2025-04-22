package com.jdc.tallerdespliegue.rest;

import com.jdc.tallerdespliegue.dto.CreateTicketDTO;
import com.jdc.tallerdespliegue.dto.UpdateTicketDTO;
import com.jdc.tallerdespliegue.models.TicketEntity;
import com.jdc.tallerdespliegue.repositories.TicketRepository;
import com.jdc.tallerdespliegue.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    //Enpoint para crear un ticket
    @PostMapping("/add")
    public ResponseEntity<TicketEntity> create(@RequestBody CreateTicketDTO ticketDTO){
        try {
            TicketEntity savedTicket = ticketService.save(ticketDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to get all the doctor
    @GetMapping("/list")
    private ResponseEntity<List<TicketEntity>> list() {
        return  ResponseEntity.ok(ticketService.findAll());
    }

    // Endpoint to gets doctor by id
    @GetMapping("/findby/{id}")
    private ResponseEntity<TicketEntity> listIndividual(@PathVariable("id") Long id){
        return ResponseEntity.ok(ticketService.findById(id));
    }

    //Enpoint to update doctor
    @PutMapping("/update/{id}")
    public ResponseEntity<TicketEntity> editar(@Validated @RequestBody UpdateTicketDTO updateTicketDTO, @PathVariable("id") Long id) {
        TicketEntity ticketExist = ticketService.findById(id);
        ticketExist.setAsunto(updateTicketDTO.getAsunto());
        ticketExist.setDescripcion(updateTicketDTO.getDescripcion());
        ticketExist.setPrioridad(updateTicketDTO.getPrioridad());
        ticketExist.setEstado(updateTicketDTO.getEstado());
        ticketService.saves(ticketExist);
        return ResponseEntity.ok(ticketExist);
    }

    //Endpoint to delete by id one doctor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            TicketEntity doctor = ticketService.findById(id);
            if (doctor == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El ticket con id " + id + " no existe");
            }
            ticketService.deleteById(id);
            return ResponseEntity.ok(list());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el ticket porque está siendo utilizada por otros registros. " );
        } catch (Exception e) {
            System.out.println("El error es: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar ticket: " + e.getMessage());
        }
    }
}
