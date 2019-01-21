/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "activo", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")
    ,@UniqueConstraint(columnNames = "simbolo")})
public class Activo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActivo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String simbolo;
    @Column
    private String notas;
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "activo")
    private Set<Candlestick> candlestickSet;
    @ManyToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "activos")
    private Set<Analisis> analisis;
    @ManyToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "activos")
    private Set<ListaDeActivos> listasDeActivos;
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "activo")
    private Set<Operaciones> operaciones;

    public Activo() {
    }

    public Activo(String nombre, String simbolo, String notas) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.notas = notas;
    }

    public int getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(int idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Set<Candlestick> getCandlestickSet() {
        return candlestickSet;
    }

    public void setCandlestickSet(Set<Candlestick> candlestickSet) {
        this.candlestickSet = candlestickSet;
    }

}
