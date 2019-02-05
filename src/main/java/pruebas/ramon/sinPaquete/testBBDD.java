/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ramon.sinPaquete;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import servicios.database.BBDD;
import servicios.modelos.Activo;

/**
 *
 * @author LacorZ
 */
public class testBBDD {
     
     public static void main(String args[]) {
         Session session = BBDD.getSession();
         Query query = session.createQuery("from Activo");
         List list = query.list();
         for (Iterator it = list.iterator(); it.hasNext();) {
             Activo activo = (Activo) it.next();
             System.out.println(activo);
         }
    }
}
