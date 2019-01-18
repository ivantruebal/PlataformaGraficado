/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author LacorZ
 */
@Entity
@Table(name = "activo", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre")
    ,
		@UniqueConstraint(columnNames = "simbolo")})
public class forma2D implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idForma2D;
}
