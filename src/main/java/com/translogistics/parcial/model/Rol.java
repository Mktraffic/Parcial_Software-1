package com.translogistics.parcial.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Usa JOINED para crear una tabla por cada subclase
@Table(name = "rol")  // Crea una tabla "rol" para almacenar todos los roles
@DiscriminatorColumn(name = "tipo_rol", discriminatorType = DiscriminatorType.STRING) // Diferencia los roles
@Data
@AllArgsConstructor
@NoArgsConstructor 
public abstract class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Long id;

    @Column(name = "nombre_rol", length = 50)
    @Size(min = 3, max = 50)
    private String nombreRol;
    
}
