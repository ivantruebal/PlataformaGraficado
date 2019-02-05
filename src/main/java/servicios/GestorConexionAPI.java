/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Presentacion.Interfaz.Main;
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
            input.put("interval",interval);
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return data;
    }
    /*public DefaultHighLowDataset getUltimoDato(DefaultHighLowDataset data, String simboloActivo, String interval){
        DefaultHighLowDataset dataSet=null;
        try {
            input.clear();
            input.put("pair",simboloActivo);
            input.put("since","0");
            input.put("interval",interval);
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
            
            Date[] date = new Date[data.getItemCount(0)];
            double[] high = new double[data.getItemCount(0)];
            double[] low = new double[data.getItemCount(0)];
            double[] open = new double[data.getItemCount(0)];
            double[] close = new double[data.getItemCount(0)];
            double[] volume = new double[data.getItemCount(0)];
            for(int i=0;i<data.getItemCount(0);i++){
                if(i!=(data.getItemCount(0)-1)){
                    date[i]=data.getXDate(0, i);
                    high[i]=data.getHighValue(0, i);
                    open[i]=data.getOpenValue(0, i);
                    low[i]=data.getLowValue(0, i);
                    close[i]=data.getCloseValue(0, i);
                    volume[i]=data.getVolumeValue(0, i);
                }
                else{
                    jaResult2=jaResult.getJSONArray(i);
                    date[i]=new Date(jaResult2.getLong(0));
                    open[i]=jaResult2.getDouble(1);
                    high[i]=jaResult2.getDouble(2);
                    low[i]=jaResult2.getDouble(3);
                    close[i]=jaResult2.getDouble(4);
                    volume[i]=jaResult2.getDouble(6);
                }
            }
            dataSet = new DefaultHighLowDataset("", date, high, low, open, close, volume);
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dataSet;
    }*/
    public DefaultHighLowDataset getUltimoDato(DefaultHighLowDataset data, String simboloActivo, String interval){
        DefaultHighLowDataset dataSet=null;
        try {
            Date fechaActualizar=data.getXDate(0, data.getItemCount(0)-1);
//            
            input.clear();
            input.put("pair",simboloActivo);
            input.put("since",String.valueOf(fechaActualizar.getTime()+1000000000L));
            input.put("interval",interval);
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
            JSONArray jaResult2=jaResult.getJSONArray(0);
            if(jaResult2.getLong(0)==fechaActualizar.getTime()){
                Date[] date = new Date[data.getItemCount(0)];
                double[] high = new double[data.getItemCount(0)];
                double[] low = new double[data.getItemCount(0)];
                double[] open = new double[data.getItemCount(0)];
                double[] close = new double[data.getItemCount(0)];
                double[] volume = new double[data.getItemCount(0)];
                System.out.println(data.getXDate(0, data.getItemCount(0)-1).getTime());
                for(int i=0;i<data.getItemCount(0);i++){
                    if(i==data.getItemCount(0)-1){
                        jaResult2=jaResult.getJSONArray(0);
                        date[i]=new Date(jaResult2.getLong(0));
                        open[i]=jaResult2.getDouble(1);
                        high[i]=jaResult2.getDouble(2);
                        low[i]=jaResult2.getDouble(3);
                        close[i]=jaResult2.getDouble(4);
                        volume[i]=jaResult2.getDouble(6);
                    }
                    else{
                        date[i]=data.getXDate(0, i);
                        high[i]=data.getHighValue(0, i);
                        open[i]=data.getOpenValue(0, i);
                        low[i]=data.getLowValue(0, i);
                        close[i]=data.getCloseValue(0, i);
                        volume[i]=data.getVolumeValue(0, i);
                    }
                }
                dataSet = new DefaultHighLowDataset("", date, high, low, open, close, volume);
            }
            else{
                Date[] date = new Date[data.getItemCount(0)+1];
                double[] high = new double[data.getItemCount(0)+1];
                double[] low = new double[data.getItemCount(0)+1];
                double[] open = new double[data.getItemCount(0)+1];
                double[] close = new double[data.getItemCount(0)+1];
                double[] volume = new double[data.getItemCount(0)+1];
                for(int i=0;i<data.getItemCount(0)+1;i++){
                    if(i==data.getItemCount(0)){
                        date[i]=new Date(jaResult2.getLong(0));
                        open[i]=jaResult2.getDouble(1);
                        high[i]=jaResult2.getDouble(2);
                        low[i]=jaResult2.getDouble(3);
                        close[i]=jaResult2.getDouble(4);
                        volume[i]=jaResult2.getDouble(6);
                    }
                    else{
                        date[i]=data.getXDate(0, i);
                        high[i]=data.getHighValue(0, i);
                        open[i]=data.getOpenValue(0, i);
                        low[i]=data.getLowValue(0, i);
                        close[i]=data.getCloseValue(0, i);
                        volume[i]=data.getVolumeValue(0, i);
                    }
                }
                dataSet = new DefaultHighLowDataset("", date, high, low, open, close, volume);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return dataSet;
    }
}
