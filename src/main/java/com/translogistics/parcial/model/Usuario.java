package com.translogistics.parcial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @Size(min = 5, max = 50, message = "El usuario debe tener entre 5 y 50 caracteres")
    private String user_name;

    @Column(name = "user_password", nullable = false)
    @Size(min = 5, max = 30, message = "La contraseña debe tener entre 5 y 20 caracteres")
    private String user_password;

    @ManyToOne // Un rol puede ser compartido por varios usuarios
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
