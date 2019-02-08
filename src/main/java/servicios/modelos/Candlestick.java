/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "candlestick", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"timestamp"})})
public class Candlestick extends HibernateEntity implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCandlestick;
    @Column(nullable = false)
    private BigDecimal open;
    @Column(nullable = false)
    private BigDecimal high;
    @Column(nullable = false)
    private BigDecimal low;
    @Column(nullable = false)
    private BigDecimal close;
    @Column(nullable = false)
    private BigDecimal volumen;
    @Column(nullable = false)
    private Calendar timestamp;
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name = "idActivo", nullable = false)
    private Activo activo;

    public Candlestick() {
    }

    public Candlestick(BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Calendar timestamp, BigDecimal volumen, Activo activo) {
        if (open != null) {
            this.open = open;
        } else {
            this.open = close;
        }
        if (open != null) {
            this.high = high;
        } else {
            this.high = close;
        }
        this.high = high;
        if (open != null) {
            this.low = low;
        } else {
            this.low = close;
        }
        this.close = close;
        this.timestamp = timestamp;
        if (volumen != null) {
            this.volumen = volumen;
        } else {
            this.volumen = new BigDecimal(0);
        }
        this.activo = activo;
    }

    public int getIdCandlestick() {
        return idCandlestick;
    }

    public void setIdCandlestick(int idCandlestick) {
        this.idCandlestick = idCandlestick;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    
    @Override
    public int getId() {
        return getIdCandlestick();
    }

}
