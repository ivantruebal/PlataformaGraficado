/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "lista_de_activos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")})
public class ListaDeActivos extends HibernateEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idLista;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private boolean esPrivada;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaDeActivos")
    List<Usuario> usuarios = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinTable(
            name = "lista_de_activos_has_activos",
            joinColumns = {
                @JoinColumn(name = "idLista")},
            inverseJoinColumns = {
                @JoinColumn(name = "idActivo")}
    )
    Set<Activo> activos = new HashSet<>();

    public static String [] columnasMostrablesEnTablaCRUD = {"ID","Nombre", "Privacidad"};
    
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

    public boolean EsPrivada() {
        return esPrivada;
    }

    public void setEsPrivada(boolean esPrivada) {
        this.esPrivada = esPrivada;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Activo> getActivos() {
        return activos;
    }

    public void setActivos(Set<Activo> activos) {
        this.activos = activos;
    }
    
    @Override
    public int getId() {
        return getIdLista();
    }

    @Override
    public String toString() {
        return nombre;
    }
    

    
}
