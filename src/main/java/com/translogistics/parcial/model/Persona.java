package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "persona")
@Data
@Builder
public class Persona {

    @Id
    @Column(name = "id_persona", length = 50)
    private int id;

    @Column(name = "nombre", length = 50)
    @Size(min = 3, max = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    @Size(min = 3, max = 50)
    private String apellido;
    

}
