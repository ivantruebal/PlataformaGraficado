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
public class ActualizadorActivo extends SwingWorker<String,Object>{

    private PanelGrafico pg;
    private String lista;
    private int cont;
    private int intervalo;

    public ActualizadorActivo(PanelGrafico pg, String lista) {
        this.pg = pg;
        this.lista = lista;
        this.cont=0;
        this.intervalo=intervaloToInt();
    }
    private int intervaloToInt(){
        return Integer.parseInt(pg.getPeriodo());
    }
    private int contToInt(){
        return cont/60;
    }
    private boolean comprobarIntervalo(){
        if(intervalo!=intervaloToInt()){
            intervalo=intervaloToInt();
            return true;
        }
        return false;
    }
    
    @Override
    protected String doInBackground() throws Exception {
        while (!isCancelled()) { 
            Thread.sleep(30000);
            if(comprobarIntervalo()){
                pg.pintarGrafico(lista);
                cont=0;
            }
            else{
                if(contToInt()>intervaloToInt()){
                    pg.pintarGrafico(lista);
                    cont=0;
                }else{
                    pg.pintarUltimoDato(lista);
                    this.cont+=30;
                }
            }
        }
        return lista;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
