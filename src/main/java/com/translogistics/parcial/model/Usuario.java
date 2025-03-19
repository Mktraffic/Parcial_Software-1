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
@Table(name = "usuario")
@Data
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @Size(min = 5, max = 50, message = "El usuario debe tener entre 5 y 50 caracteres")
    private String user_name;

    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 30, message = "La contraseña debe tener entre 5 y 20 caracteres")
    private String password;

    @OneToOne //aca lo que dice es que muchos usuarios pueden compartir un mismo rol
    @JoinColumn(name = "id_rol", nullable = false)  // va a ser la llave foranea del Rol
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

}