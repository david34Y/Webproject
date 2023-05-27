package com.example.webproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;

    @Column(nullable = false)
    @NotBlank(message = "Complete sus datos")
    @NotNull
    @Pattern(regexp = "[/^[A-Za-záéíñóúüÁÉÍÑÓÚÜ_.\\s]+$/g]{2,254}",message = "Solo puede ingresar letras")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "Complete sus datos")
    @Pattern(regexp = "[/^[A-Za-záéíñóúüÁÉÍÑÓÚÜ_.\\s]+$/g]{2,254}",message = "Solo puede ingresar letras")
    private String apellidos;


    @Column(nullable = false)
    @NotBlank(message = "Complete sus datos")
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$",message = "Debe tener el formato nombre@correo.com")
    private String correo;

    @Column(nullable = false)
    @NotBlank(message = "Complete su contraseña")
    @Size(min = 8, message = "Mínimo 8 caracteres")
    private String password;


    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(String nombre, String apellido, String correo, String celular, Rol rol) {
        this.nombre = nombre;
        this.apellidos = apellido;
        this.correo = correo;
        this.rol = rol;
    }

    // Getters y Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idUsuario) {
        this.idusuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellido) {
        this.apellidos = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Método toString para imprimir el objeto
}