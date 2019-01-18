/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "operaciones")
public class Operaciones implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idOperacion;
    @Column(nullable = false)
    private Date fechaApertura;
    @Column(nullable = false)
    private Date fechaCierre;
    @Column(nullable = true)
    private BigDecimal volumen;
    @Column(nullable = false)
    private BigDecimal precioApertura;
    @Column(nullable = true)
    private BigDecimal precioCierre;
    @Column(nullable = true)
    private boolean tipoOrden;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActivo", nullable = false)
    private Activo activo;

    public Operaciones() {
    }

    public Operaciones(int idOperacion, Date fechaApertura, Date fechaCierre, BigDecimal volumen, BigDecimal precioApertura, BigDecimal precioCierre, boolean tipoOrden, Activo activo) {
        this.idOperacion = idOperacion;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.volumen = volumen;
        this.precioApertura = precioApertura;
        this.precioCierre = precioCierre;
        this.tipoOrden = tipoOrden;
        this.activo = activo;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public BigDecimal getPrecioApertura() {
        return precioApertura;
    }

    public void setPrecioApertura(BigDecimal precioApertura) {
        this.precioApertura = precioApertura;
    }

    public BigDecimal getPrecioCierre() {
        return precioCierre;
    }

    public void setPrecioCierre(BigDecimal precioCierre) {
        this.precioCierre = precioCierre;
    }

    public boolean isTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(boolean tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

}
