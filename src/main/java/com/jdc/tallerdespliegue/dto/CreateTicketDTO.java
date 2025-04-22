package com.jdc.tallerdespliegue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTicketDTO {
    private Long id;
    private String asunto;
    private String descripcion;
    private String prioridad;
    private String estado;
}
