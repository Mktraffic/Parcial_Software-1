package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "usuario")
@Data
@Builder
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user", nullable = false)
    @Size(min = 5, max = 50, message = "El usuario debe tener entre 5 y 50 caracteres")
    private String user;

    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 20, message = "La contraseña debe tener entre 5 y 20 caracteres")
    private String password;

    @ManyToOne //aca lo que dice es que muchos usuarios pueden compartir un mismo rol
    @JoinColumn(name = "id_rol", nullable = false)  // va a ser la llave foranea del Rol
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    public String getPassword(){
        return password;
    }


}