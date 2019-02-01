/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ivan;

import javax.swing.SwingWorker;

/**
 *
 * @author usuario
 */
public class ActualizadorActivo extends SwingWorker<String,Object>{

    private PanelGrafico pg;
    private String lista;

    public ActualizadorActivo(PanelGrafico pg, String lista) {
        this.pg = pg;
        this.lista = lista;
    }
    
    @Override
    protected String doInBackground() throws Exception {
        while (!isCancelled()) { 
            pg.pintarGrafico(lista);
            Thread.sleep(30000);
        }
        return lista;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
