/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Presentacion.Interfaz.PanelGrafico;
import javax.swing.SwingWorker;

/**
 *
 * @author usuario
 */
public class ActualizadorActivo extends SwingWorker<String, Object> {

    private PanelGrafico pg;
    private String lista;
    private int intervalo;

    public ActualizadorActivo(PanelGrafico pg, String lista) {
        this.pg = pg;
        this.lista = lista;
        this.intervalo = intervaloToInt();
    }

    private int intervaloToInt() {
        return Integer.parseInt(pg.getPeriodo());
    }

    private boolean comprobarIntervalo() {
        if (intervalo != intervaloToInt()) {
            intervalo = intervaloToInt();
            return true;
        }
        return false;
    }

    @Override
    protected String doInBackground() throws Exception {
        while (!isCancelled()) {
            Thread.sleep(5000);
            if (comprobarIntervalo()) {
                pg.pintarGrafico(lista);
            } else {
                pg.pintarUltimoDato(lista);
            }
        }
        return lista;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
