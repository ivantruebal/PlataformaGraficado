/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.utils;

import Presentacion.Interfaz.ActiveListWindow;
import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servicios.database.BBDD;

/**
 *
 * @author LacorZ
 */
public class Utils {

    public static String hashToMD5(String text) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void mostrarErrorGenerico() {
        mostrarErrorGenerico(null);
    }

    public static void mostrarErrorGenerico(Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), "ERROR: Contacte con el administrador", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        if (e != null) {
            java.util.logging.Logger.getLogger(Utils.class.getName()).log(java.util.logging.Level.FINEST, "Error generico", e.getMessage());
        }
    }
    
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(new JFrame(), "ERROR: Contacte con el administrador.\n"+mensaje, "ERROR",
                JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(Utils.class.getName()).log(java.util.logging.Level.FINEST, "Error: "+mensaje, "");
    }
    

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public static void generalSettings(JFrame jframe) {
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jframe.setIconImage(new ImageIcon("src/main/resources/pictures/LogoPlataformaGraficado_sin_titulo.png").getImage());
        jframe.setResizable(false);
    }

}
