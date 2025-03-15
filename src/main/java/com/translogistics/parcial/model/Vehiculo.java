package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "vehiculo")
@Data
@Builder
public class Vehiculo {

    @Id
    @Column(name = "placa_vehiculo", length = 10)
    private String placa;

    @Column(name = "modelo", length = 80)
    @Size(min = 8, max = 80)
    private String modelo;

    @Column(name = "anio")
    private int anio;

    @Column(name = "tipo", length = 50)
    @Size(min = 3, max = 50)
    private String tipo;

    @Column(name = "estado", length = 50)
    @Size(min = 3, max = 50)
    private String estado;

}