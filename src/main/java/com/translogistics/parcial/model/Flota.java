package com.translogistics.parcial.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "Flota")
@Data
@Builder
public class Flota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    @OneToMany
    private ArrayList<Usuario> conductores = new ArrayList<>();
}