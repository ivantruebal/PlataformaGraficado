/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "forma2d")
public class Forma2D implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idForma2D;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "idAnalisis", nullable = false)
    private Analisis analisis;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "forma2D")
    private Set<Punto2D> puntos2D;
    @Column
    private String tipoForma;

    public Forma2D() {
    }

    public Forma2D(Analisis analisis, String tipoForma) {
        this.analisis = analisis;
        this.tipoForma = tipoForma;
    }

    public Analisis getAnalisis() {
        return analisis;
    }

    public void setAnalisis(Analisis analisis) {
        this.analisis = analisis;
    }

    public String getTipoForma() {
        return tipoForma;
    }

    public void setTipoForma(String tipoForma) {
        this.tipoForma = tipoForma;
    }

    public int getIdForma2D() {
        return idForma2D;
    }

    public Set<Punto2D> getPuntos2D() {
        return puntos2D;
    }

    public void setPuntos2D(Set<Punto2D> puntos2D) {
        this.puntos2D = puntos2D;
    }

}
