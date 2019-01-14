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
import javax.persistence.FetchType;
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
@Table(name = "lista_de_activos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")})
public class ListaDeActivos implements Serializable {

    @Id
    @Column
    private int idLista;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private boolean esPrivada;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaDeActivos")
    Set<Usuario> usuarios = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lista_de_activos_has_activos",
            joinColumns = {
                @JoinColumn(name = "idLista")},
            inverseJoinColumns = {
                @JoinColumn(name = "idActivo")}
    )
    Set<Activo> activos = new HashSet<>();

    public ListaDeActivos() {
    }

    public ListaDeActivos(int idLista, String nombre, boolean esPrivada) {
        this.idLista = idLista;
        this.nombre = nombre;
        this.esPrivada = esPrivada;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsPrivada() {
        return esPrivada;
    }

    public void setEsPrivada(boolean esPrivada) {
        this.esPrivada = esPrivada;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
