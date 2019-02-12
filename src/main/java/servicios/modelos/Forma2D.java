/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.event.OverlayChangeListener;
import org.jfree.chart.panel.Overlay;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "forma2d")
public class Forma2D implements Serializable, Overlay {

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
        this.puntos2D = new HashSet<>();
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

    @Override
    public void paintOverlay(Graphics2D g2, ChartPanel chartPanel) {
        
        switch (getTipoForma()) {
            case "Linea":
                ArrayList<Punto2D> arrayListPuntos2D = new ArrayList(getPuntos2D());
                final Punto2D punto1 = arrayListPuntos2D.get(0);
                final Punto2D punto2 = arrayListPuntos2D.get(1);
                final double x1 = punto1.getEjeX();
                final double y1 = punto1.getEjeY();
                final double x2 = punto2.getEjeX();
                final double y2 = punto2.getEjeY();        
                g2.draw(new Line2D.Double(x1, y1, x2, y2));                              
                System.out.println("x1 = "+x1+"y1 = "+y1+"x2 = "+x2+"y2 = "+y2);
//               

                break;
            case "Rectangulo":

                break;
        }
    }

    @Override
    public void addChangeListener(OverlayChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeChangeListener(OverlayChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
