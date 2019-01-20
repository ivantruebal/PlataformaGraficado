/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import servicios.modelos.Activo;
import servicios.modelos.Candlestick;
import servicios.modelos.Usuario;
import servicios.utils.Utils;

/**
 *
 * @author LacorZ
 */
public class BBDD {

    public enum Indice {
        OPEN, HIGH, LOW, CLOSE, DATE, VOLUME
    }

    public static Session session;

    public static Session getSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public static boolean comprobarCredencialesDeUsuario(String username, String password) throws NoResultException {

        password = Utils.hashToMD5(password);
        try {
            Session openSession = getSession();
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
            throw e;
        }

    }

    public static boolean registrarUsuario(String username, String email, String password) {
        password = Utils.hashToMD5(password);

        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Usuario usuario = new Usuario(username, email, password);
            session.save(usuario);
            session.flush();
            tx.commit();
            java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINEST, "Usuario {0} se ha registrado correctamente", username);
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, "Usuario {0} no se ha registrado, algun campo esta repetido", username);
                return false;
            }
            throw e;
        } finally {
            session.close();
        }

    }
    public static boolean generarColeccionDeCandlestickApartirDeFicheroCSV(String rutaFichero, String caracterDeSeparacion, Activo activo, String patronFecha) throws UnDefinedHeadersException, ParseException {
        Transaction transaction = getSession().beginTransaction();
        File ficheroCSV = new File(rutaFichero);
        BufferedReader br = null;
        String line = "";
        int[] indiceColumnas = new int[6];
        Set<Candlestick> candlestickList = null;
        candlestickList = new HashSet<Candlestick>();
        boolean columnasDefinidas = false, cierreDefinido = false, fechaDefinida = false;
        if (activo != null) {
            try {
                br = new BufferedReader(new FileReader(ficheroCSV));
                while ((line = br.readLine()) != null) {
                    String[] data;
                    if (!line.equalsIgnoreCase("")) {
                        data = line.split(caracterDeSeparacion);
                        //Obtenemos el orden de las columnas is estas estan nombradas como espera el software
                        if (!columnasDefinidas) {
                            for (int indice = 0; indice < data.length; indice++) {
                                String columna = data[indice];
                                if (columna.equalsIgnoreCase(Indice.OPEN.name())) {
                                    indiceColumnas[Indice.OPEN.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(Indice.HIGH.name())) {
                                    indiceColumnas[Indice.HIGH.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(Indice.LOW.name())) {
                                    indiceColumnas[Indice.LOW.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(Indice.CLOSE.name())) {
                                    indiceColumnas[Indice.CLOSE.ordinal()] = indice;
                                    cierreDefinido = true;
                                }
                                if (columna.equalsIgnoreCase(Indice.DATE.name())) {
                                    indiceColumnas[Indice.DATE.ordinal()] = indice;
                                    fechaDefinida = true;
                                }
                                if (columna.equalsIgnoreCase(Indice.VOLUME.name())) {
                                    indiceColumnas[Indice.VOLUME.ordinal()] = indice;
                                }
                            }
                            //Si estan definidas las columnas minimas, se sigue con la lectura
                            if (cierreDefinido && fechaDefinida) {
                                columnasDefinidas = true;
                            } else {
                                throw new UnDefinedHeadersException("Las encabezados de las columnas del fichero CSV no estan bien definidas");
                            }

                        } else {
                            BigDecimal open = new BigDecimal(data[indiceColumnas[Indice.OPEN.ordinal()]]);
                            BigDecimal high = new BigDecimal(data[indiceColumnas[Indice.HIGH.ordinal()]]);
                            BigDecimal low = new BigDecimal(data[indiceColumnas[Indice.LOW.ordinal()]]);
                            BigDecimal close = new BigDecimal(data[indiceColumnas[Indice.CLOSE.ordinal()]]);
                            BigDecimal volumen = new BigDecimal(data[indiceColumnas[Indice.VOLUME.ordinal()]]);
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat(patronFecha);
                            calendar.setTime(sdf.parse(data[indiceColumnas[Indice.DATE.ordinal()]]));
                            Candlestick candlestick = new Candlestick(open, high, low, close, calendar, volumen, activo);
                            candlestickList.add(candlestick);
                        }
                    }
                }
                activo.setCandlestickSet(candlestickList);
                getSession().save(activo);
                transaction.commit();
                return true;

            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                transaction.rollback();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                session.close();
            }
        }

        java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, "El activo es nulo");
        return false;
    }

    public static Activo compruebaSiExisteActivoYcrealo(String nombre, String simbolo, String notas) {
        Transaction transaction = null;
        try {

            Session openSession = getSession();
            transaction = openSession.beginTransaction();
            Query createQuery = openSession.createQuery("from Activo a where a.simbolo = :simbolo");
            createQuery.setParameter("simbolo", simbolo);
            List list = createQuery.list();
            if (list.size() > 0) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINEST, "Activo {0} existe", simbolo);
                transaction.commit();
                return (Activo) list.get(0);
                
            } else {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, "Activo {0} no existe", simbolo);
                Activo activo = new Activo(nombre, simbolo, notas);
                openSession.save(activo);
                openSession.flush();
                transaction.commit();
                return activo;
            }

        } catch (javax.persistence.NoResultException e) {
            transaction.rollback();
            throw e;
            
        }
    }

}
