/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.database;

import Presentacion.Interfaz.UnDefinedHeadersException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import servicios.modelos.Activo;
import servicios.modelos.Candlestick;
import servicios.modelos.ListaDeActivos;
import servicios.modelos.Usuario;
import servicios.utils.Utils;
import servicios.modelos.HibernateEntity;

/**
 *
 * @author LacorZ
 */
public class BBDD {

    private static Session session;
    private static Usuario usuarioActual;

    /**
     * Recibe el tipo de entidad a mostrar, y la tabla para llenarla con los
     * datos de la base de datos devolviendo el modelo a mostrar bloqueando la
     * edición.
     *
     * @param entityType
     * @param jTable
     */
    public static List putDataInTableCRUD(String entityType, JTable jTable) {
        Query query = null;
        List list = null;
        Iterator consulta;
        DefaultTableModel tabla = null;
        String[] columNames;
        switch (entityType) {
            case "Activos":
                query = getSession().createQuery("from Activo");
                list = query.list();
                consulta = list.iterator();
                columNames = new String[3];
                columNames[0] = "ID";
                columNames[1] = "Nombre";
                columNames[2] = "Símbolo";
                tabla = new DefaultTableModel(new String[0][3], columNames) {
                    public boolean isCellEditable(int rowIndex, int mColIndex) {
                        return false;
                    }
                };
                while (consulta.hasNext()) {
                    String[] datos = new String[3];
                    Activo fila = (Activo) consulta.next();
                    datos[0] = (String.valueOf(fila.getId()));
                    datos[1] = (fila.getNombre());
                    datos[2] = (fila.getSimbolo());
                    tabla.addRow(datos);
                }

                break;
            case "Lista De Activos":
                query = getSession().createQuery("from ListaDeActivos");
                list = query.list();
                consulta = list.iterator();
                columNames = new String[3];
                columNames[0] = "ID";
                columNames[1] = "Nombre";
                columNames[2] = "Privacidad";
                tabla = new DefaultTableModel(new String[0][3], columNames) {
                    public boolean isCellEditable(int rowIndex, int mColIndex) {
                        return false;
                    }
                };
                while (consulta.hasNext()) {
                    Vector<String> datos = new Vector();
                    ListaDeActivos fila = (ListaDeActivos) consulta.next();
                    datos.add(String.valueOf(fila.getIdLista()));
                    datos.add(fila.getNombre());
                    if (fila.EsPrivada()) {
                        datos.add("Privada");
                        tabla.addRow(datos);
                    }

                }
                break;
        }
//        Vector dataVector = tabla.getDataVector();
//        for (Iterator iterator1 = dataVector.iterator(); iterator1.hasNext();) {
//            Object next = iterator1.next();
//            System.out.println(next);
//        }
        jTable.setModel(tabla);

        return list;
    }

    /**
     * Metodo que devuelve true si existe dicha entidad en BBDD
     *
     * @param entidad
     * @return true si existe
     */
    public static boolean compruebaSiExisteEntidadPorID(HibernateEntity entidad) {
        try {
            Object entidadBBDD = getSession().get(entidad.getClass(), entidad.getId());
            if (entidadBBDD != null) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, "Existe la entidad tipo " + entidad.getClass() + " con id " + entidad.getId(), "");
                return true;
            } else {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, "NO Existe la entidad tipo " + entidad.getClass() + " con id " + entidad.getId(), "");
                return false;
            }

        } catch (HibernateException e) {
            java.util.logging.Logger.getLogger(BBDD.class.getName()).log(java.util.logging.Level.FINE, e.getMessage());
            return false;
        }
    }

    public enum Indice {
        OPEN, HIGH, LOW, CLOSE, DATE, VOLUME
    }

    public static Session getSession() {

        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        if (session.isOpen()) {
            return session;
        } else {
            session.disconnect();
            session = null;
            session = HibernateUtil.getSessionFactory().openSession();
            return session;
        }
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
                usuarioActual = (Usuario) list.get(0);
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
//            session.close();
        }

    }

//    public static boolean generarColeccionDeCandlestickApartirDeFicheroCSV(String rutaFichero, String caracterDeSeparacion, Activo activo, String patronFecha) throws UnDefinedHeadersException, ParseException {
//        Transaction transaction = getSession().beginTransaction();
//        File ficheroCSV = new File(rutaFichero);
//        BufferedReader br = null;
//        String line = "";
//        int[] indiceColumnas = new int[6];
//        indiceColumnas[0] = -1;
//        indiceColumnas[1] = -1;
//        indiceColumnas[2] = -1;
//        indiceColumnas[3] = -1;
//        indiceColumnas[4] = -1;
//        indiceColumnas[5] = -1;
//        Set<Candlestick> candlestickList = null;
//        candlestickList = new HashSet<>();
//        boolean columnasDefinidas = false, cierreDefinido = false, fechaDefinida = false;
//        if (activo != null) {
//            try {
//                br = new BufferedReader(new FileReader(ficheroCSV));
//                while ((line = br.readLine()) != null) {
//                    String[] data;
//                    //Saltamos las lineas vacias
//                    if (!line.equalsIgnoreCase("")) {
//                        data = line.split(caracterDeSeparacion);
//                        //Obtenemos el orden de las columnas is estas estan nombradas como espera el software
//                        if (!columnasDefinidas) {
//                            for (int indice = 0; indice < data.length; indice++) {
//                                String columna = data[indice].trim();
//                                if (columna.equalsIgnoreCase(Indice.OPEN.name())) {
//                                    indiceColumnas[Indice.OPEN.ordinal()] = indice;
//                                }
//                                if (columna.equalsIgnoreCase(Indice.HIGH.name())) {
//                                    indiceColumnas[Indice.HIGH.ordinal()] = indice;
//                                }
//                                if (columna.equalsIgnoreCase(Indice.LOW.name())) {
//                                    indiceColumnas[Indice.LOW.ordinal()] = indice;
//                                }
//                                if (columna.equalsIgnoreCase(Indice.CLOSE.name())) {
//                                    indiceColumnas[Indice.CLOSE.ordinal()] = indice;
//                                    cierreDefinido = true;
//                                }
//                                if (columna.equalsIgnoreCase(Indice.DATE.name())) {
//                                    indiceColumnas[Indice.DATE.ordinal()] = indice;
//                                    fechaDefinida = true;
//                                }
//                                if (columna.equalsIgnoreCase(Indice.VOLUME.name())) {
//                                    indiceColumnas[Indice.VOLUME.ordinal()] = indice;
//                                }
//                            }
//                            //Si estan definidas las columnas minimas, se sigue con la lectura
//                            if (cierreDefinido && fechaDefinida) {
//                                columnasDefinidas = true;
//                            } else {
//                                throw new UnDefinedHeadersException("Las encabezados de las columnas del fichero CSV no estan bien definidas");
//                            }
//
//                        } else {
//
//                            BigDecimal open;
//                            if (indiceColumnas[Indice.OPEN.ordinal()] != -1) {
//                                open = new BigDecimal(data[indiceColumnas[Indice.OPEN.ordinal()]]);
//                            } else {
//                                open = new BigDecimal(data[indiceColumnas[Indice.CLOSE.ordinal()]]);
//                            }
//                            BigDecimal high;
//                            if (indiceColumnas[Indice.HIGH.ordinal()] != -1) {
//                                high = new BigDecimal(data[indiceColumnas[Indice.HIGH.ordinal()]]);
//                            } else {
//                                high = new BigDecimal(data[indiceColumnas[Indice.CLOSE.ordinal()]]);
//                            }
//                            BigDecimal low;
//                            if (indiceColumnas[Indice.LOW.ordinal()] != -1) {
//                                low = new BigDecimal(data[indiceColumnas[Indice.LOW.ordinal()]]);
//                            } else {
//                                low = new BigDecimal(data[indiceColumnas[Indice.CLOSE.ordinal()]]);
//                            }
//                            BigDecimal close;
//                            close = new BigDecimal(data[indiceColumnas[Indice.CLOSE.ordinal()]]);
//                            BigDecimal volumen;
//                            if (indiceColumnas[Indice.VOLUME.ordinal()] != -1) {
//                                volumen = new BigDecimal(data[indiceColumnas[Indice.VOLUME.ordinal()]]);
//                            } else {
//                                volumen = new BigDecimal(0);
//                            }
//
//                            Calendar calendar = Calendar.getInstance();
//                            SimpleDateFormat sdf = new SimpleDateFormat(patronFecha);
//                            calendar.setTime(sdf.parse(data[indiceColumnas[Indice.DATE.ordinal()]]));
//                            Candlestick candlestick = new Candlestick(open, high, low, close, calendar, volumen, activo);
//                            candlestickList.add(candlestick);
//                        }
//                    }
//                }
//                activo.setCandlestickSet(candlestickList);
//                getSession().save(activo);
//                transaction.commit();
//                return true;
//
//            } catch (FileNotFoundException ex) {
//                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
//                transaction.rollback();
//            } catch (IOException ex) {
//                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
////                session.close();
//            }
//        }
//
//        java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, "El activo es nulo");
//        return false;
//    }

    

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

}
