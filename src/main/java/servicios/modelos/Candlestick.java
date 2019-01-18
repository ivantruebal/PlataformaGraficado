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
@Table(name = "candlestick")
public class Candlestick implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE)
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
    private Date timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActivo", nullable = false)
    private Activo activo;

    
    public Candlestick() {
    }

    public Candlestick(int idCandlestick, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Date timestamp, Activo activo) {
        this.idCandlestick = idCandlestick;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.timestamp = timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }
    
    
    
    
    
    
    

   
    
    
}
