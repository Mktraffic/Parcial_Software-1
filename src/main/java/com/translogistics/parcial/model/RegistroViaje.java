package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "id_registro")
    @Column(name = "id_registro", length = 50)
    private int id;

    @Column(name = "fecha_viaje", length = 50)
    @Size(min = 3, max = 50)
    private String fechaViaje;

    @OneToMany(mappedBy = "placa_vehiculo")
    @JoinColumn(name = "placa_vehiculo", unique = true, nullable = false)
    @Size(min = 3, max = 50)
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "id_conductor")
    @JoinColumn(name = "id_conductor", unique = true, nullable = false)
    @Size(min = 3, max = 50)
    private RolConductor conductor;

}
