/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ramon.sinPaquete;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servicios.database.BBDD;
import servicios.modelos.Activo;
import servicios.modelos.Candlestick;

/**
 *
 * @author LacorZ
 */
public class testBBDD {
     
     public static void main(String args[]) {
         Activo activo = null;
         Session session = BBDD.getSession();         
         Query createQuery = session.createQuery("from Activo where idActivo = :idActivo");
//         Query createQuery = session.createQuery("from Activo");
         createQuery.setParameter("idActivo", 8);
         List<Activo> list = createQuery.list();
         for (Activo activo2 : list) {
             activo = activo2;
             System.out.println("Activo recogido de nuevo de bbdd: "+activo2.toStringFull());
             System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         }
         Candlestick candlestick = new Candlestick(new BigDecimal(0), new BigDecimal(0),new BigDecimal(0), new BigDecimal(0), Calendar.getInstance(), new BigDecimal(0), activo);
         Transaction beginTransaction = session.beginTransaction();
         session.save(candlestick);
         System.out.println("Transacción iniciada");   
         System.out.println("Activo SIN mergeado:" + activo.toStringFull());
         System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         activo = (Activo) session.get(activo.getClass(),activo.getIdActivo());
         System.out.println("Activo mergeado:" + activo.toStringFull());    
         System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         session.update(activo);        
         System.out.println("Activo guardado:" + activo.toStringFull());    
         System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         session.flush();
         beginTransaction.commit();         
         session.clear();
         
         activo = (Activo) session.get(activo.getClass(),activo.getIdActivo());
         System.out.println("Activo mergeado:" + activo.toStringFull());    
         System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         
         createQuery = session.createQuery("from Activo where idActivo = :idActivo");
//         Query createQuery = session.createQuery("from Activo");
         createQuery.setParameter("idActivo", 8);
         list = createQuery.list();
         for (Activo activo2 : list) {
             activo = activo2;
             System.out.println("Activo recogido de nuevo de bbdd: "+activo2.toStringFull());
             System.out.println("-------------------------------Numero de candlesticks: "+activo.getCandlestickSet().size());
         }
         
    }
}
