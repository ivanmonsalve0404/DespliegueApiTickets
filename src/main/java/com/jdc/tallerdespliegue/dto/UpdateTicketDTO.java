package com.jdc.tallerdespliegue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTicketDTO {
    private String asunto;
    private String descripcion;
    private String prioridad;
    private String estado;
}
