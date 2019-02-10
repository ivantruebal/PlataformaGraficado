/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.transaction.Transactional;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import servicios.database.BBDD;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "activo", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")
    ,@UniqueConstraint(columnNames = "simbolo")})
public class Activo extends HibernateEntity implements Serializable {

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
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "activo")
    private Set<Candlestick> candlestickSet;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "activo")
    private Set<Analisis> analisis;
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, mappedBy = "activos")
    private Set<ListaDeActivos> listasDeActivos;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "activo")
    private Set<Operaciones> operaciones;
   
    public Set<Analisis> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(Set<Analisis> analisis) {
        this.analisis = analisis;
    }

    public Set<ListaDeActivos> getListasDeActivos() {
        return listasDeActivos;
    }

    public void setListasDeActivos(Set<ListaDeActivos> listasDeActivos) {
        this.listasDeActivos = listasDeActivos;
    }

    public Set<Operaciones> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(Set<Operaciones> operaciones) {
        this.operaciones = operaciones;
    }

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

    @Override
    public int getId() {
        return getIdActivo();
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

    @Override
    public String toString() {
        return simbolo;
    }

    public String toStringFull() {
        return nombre + " " + simbolo + " " + idActivo;
    }
    
    /**
     * Metodo que devuelve los datos formateados para un grafico OHLC
     * @return 
     */
    @Transactional
    public DefaultHighLowDataset getData()
    {
        
        Set<Candlestick> candlestickSet1 = getCandlestickSet();
        int size = candlestickSet1.size();
        Date[] date = new Date[size];
        double[] high = new double[size];
        double[] low = new double[size];
        double[] open = new double[size];
        double[] close = new double[size];
        double[] volume = new double[size];
        if(candlestickSet1 != null && size > 0)
        {
            Object[] candlestickArray = candlestickSet1.toArray();
            for (int i = 0; i < candlestickArray.length; i++) {
                Candlestick candlestick = (Candlestick) candlestickArray[i];
                date[i]=candlestick.getTimestamp().getTime();
                high[i] = candlestick.getHigh().doubleValue();
                low[i] = candlestick.getLow().doubleValue();
                open[i] = candlestick.getOpen().doubleValue();
                close[i] = candlestick.getClose().doubleValue();
                volume[i] = candlestick.getVolumen().doubleValue();                
            }
        }       
        return new DefaultHighLowDataset("", date, high, low, open, close, volume);
    }

}
