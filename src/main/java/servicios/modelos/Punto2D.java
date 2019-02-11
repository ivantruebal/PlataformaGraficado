/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
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

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "punto2d")
public class Punto2D implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPunto2D;
    @Column
    private double ejeX;
    @Column
    private double ejeY;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "idForma2D", nullable = false)
    private Forma2D forma2D;

    public Punto2D(double ejeX, double ejeY, Forma2D forma2D) {
        this.ejeX =  ejeX;
        this.ejeY = ejeY;
        this.forma2D = forma2D;
    }

    public double getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public double getEjeY() {
        return ejeY;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    @Override
    public String toString() {
        return "Punto2D{" + "ejeX=" + ejeX + ", ejeY=" + ejeY + '}';
    }

    public Punto2D() {
    }
    
    
    
    
}
