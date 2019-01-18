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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "analisis")
public class Analisis implements Serializable {

    
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAnalisis;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Analisis_has_activos",
            joinColumns = {
                @JoinColumn(name = "idAnalisis")},
            inverseJoinColumns = {
                @JoinColumn(name = "idActivo")}
    )
    Set<Activo> activos;

    public Analisis() {
    }

    public Analisis(int idAnalisis, Set<Activo> activos) {
        this.idAnalisis = idAnalisis;
        this.activos = activos;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Set<Activo> getActivos() {
        return activos;
    }

    public void setActivos(Set<Activo> activos) {
        this.activos = activos;
    }

}
