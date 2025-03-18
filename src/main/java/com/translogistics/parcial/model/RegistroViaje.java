package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "registro_viaje")
@Data
@Builder
public class RegistroViaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro", nullable = false)
    private Long id;

    @Column(name = "fecha_viaje", length = 50)
    @Size(min = 3, max = 50)
    private String fechaViaje;

    @OneToOne
    @JoinColumn(name = "placa_vehiculo", unique = true, nullable = false)
    @Size(min = 3, max = 50)
    private Vehiculo vehiculo;

    @OneToOne
    @JoinColumn(name = "id_conductor", unique = true, nullable = false)
    @Size(min = 3, max = 50)
    private Usuario conductor;

}
