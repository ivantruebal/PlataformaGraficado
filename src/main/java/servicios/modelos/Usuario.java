/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")
    ,
		@UniqueConstraint(columnNames = "email")})
public class Usuario implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idUsuario;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "lista_de_activos_has_usuario",
            joinColumns = {
                @JoinColumn(name = "idUsuario")},
            inverseJoinColumns = {
                @JoinColumn(name = "idListaDeActivos")}
    )
    Set<ListaDeActivos> listaDeActivos = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ListaDeActivos> getListaDeActivos() {
        return listaDeActivos;
    }

    public void setListaDeActivos(Set<ListaDeActivos> listaDeActivos) {
        this.listaDeActivos = listaDeActivos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", listaDeActivos=" + listaDeActivos + '}';
    }
    
}
