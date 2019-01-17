/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.database;

import Presentacion.Interfaz.ActiveListWindow;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;

import org.hibernate.Session;
import servicios.modelos.Usuario;
import servicios.utils.Utils;

/**
 *
 * @author LacorZ
 */
public class BBDD {

    public static boolean comprobarCredencialesDeUsuario(String username, String password) throws NoResultException{

        password = Utils.hashToMD5(password);
        try {
            Session openSession = HibernateUtil.getSessionFactory().openSession();
            Query createQuery = openSession.createQuery("from Usuario u where u.nombre = :nombre and u.password = :password");
            createQuery.setParameter("nombre", username);
            createQuery.setParameter("password", password);
            List list = createQuery.list();
            if (list.size() > 0) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINEST, "Usuario {0} se ha logueado correctamente", username);
                return true;
            } else {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, "Usuario {0} no se ha logueado, credenciales incorrectas", username);
                return false;
            }

        } catch (javax.persistence.NoResultException e) {
            throw  e;
        }

    }

    public static void añadirCargaInicial() {
        
    }
}
