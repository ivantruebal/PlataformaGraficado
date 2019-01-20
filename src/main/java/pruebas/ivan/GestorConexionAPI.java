/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ivan;

import Presentacion.api.KrakenApi;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author usuario
 */
public class GestorConexionAPI {
    private final String key="eFdZ+5zMcIda/AIXmxgAQleAY02CQDauk0cmRBdmR1VdN4eoo9HtWraX";
    private final String secret="CeLyCF83pNbPz8VjlGjl04RdiulpVVFCS8C/+XeaXT/3Ck8URYGuiJT4BWm3tfm9W4d0vRw/sJrBYveuf5GScg==";
    private String response;
    private KrakenApi api;
    private Map<String, String> input;
    
    public GestorConexionAPI(){
        this.response="";
        this.input=new HashMap<>();
        this.api=new KrakenApi();
        api.setKey(key);
        api.setSecret(secret);   
    }
    
    /**
     * Método que pide a la API todas las listas disponibles y las guarda en una lista
     * @return List<String> contiene todos los activos disponibles de la API
     */
    public List<String> getListaActivos(){
        List<String> listaActivos=new ArrayList<>();
        try{
            response = api.queryPublic(KrakenApi.Method.ASSET_PAIRS);
            
            JSONObject job=new JSONObject(response);
            JSONObject jobResult=job.getJSONObject("result");
            Iterator<String> it=jobResult.keys();
            String key;
            while(it.hasNext()){
                key=it.next();
                if(key.substring(key.length()-2, key.length()).equalsIgnoreCase(".d")){
                    key=key.substring(0, key.length()-2);
                }
                listaActivos.add(key);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewMDIApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaActivos;
    }
    
    // TODO sin acabar, cambiará
    public DefaultHighLowDataset getDatosActivo(String simboloActivo, String interval){
        DefaultHighLowDataset data=null;
        try {
            input.clear();
            input.put("pair",simboloActivo);
            input.put("since","0");
            input.put("interval","30");
            response = api.queryPublic(KrakenApi.Method.OHLC, input);
            
            JSONObject job=new JSONObject(response);
            
            JSONObject jobResult=job.getJSONObject("result");
            Iterator<String> it=jobResult.keys();
            String key;
            JSONArray jaResult=null;
            while(it.hasNext()){
                key=it.next();
                if(!key.equalsIgnoreCase("last"))
                    jaResult=jobResult.getJSONArray(key);
            }
            
            JSONArray jaResult2;
            Date[] date = new Date[jaResult.length()];
            double[] high = new double[jaResult.length()];
            double[] low = new double[jaResult.length()];
            double[] open = new double[jaResult.length()];
            double[] close = new double[jaResult.length()];
            double[] volume = new double[jaResult.length()];
            for(int i=0;i<jaResult.length();i++){
                jaResult2=jaResult.getJSONArray(i);
                date[i]=new Date(jaResult2.getLong(0));
                open[i]=jaResult2.getDouble(1);
                high[i]=jaResult2.getDouble(2);
                low[i]=jaResult2.getDouble(3);
                close[i]=jaResult2.getDouble(4);
                volume[i]=jaResult2.getDouble(6);
            }
            data = new DefaultHighLowDataset("", date, high, low, open, close, volume);
            
        } catch (IOException ex) {
            Logger.getLogger(NewMDIApplication.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return data;
    }
}
