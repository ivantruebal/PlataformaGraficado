/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Presentacion.Interfaz.Main;
import Presentacion.api.KrakenApi;
import com.lowagie.text.SplitCharacter;
import java.awt.BorderLayout;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import servicios.modelos.Activo;
import servicios.modelos.Candlestick;
import servicios.modelos.ListaDeActivos;
import servicios.modelos.Operaciones;

/**
 *
 * @author usuario
 */
public class GestorConexionAPI {

    private final String key = "UgjbRVuEA2SCfCfB2JBU47Rckinqj9FAzcs7xB3vue/AmJ7WuspWgtok";
    private final String secret = "wXYTr9VdczwHsvwy5ji3yaU+Gx9svpysVbq9JOQMmNTZ0m/85TqNfhXnqTAlQg4TwXcvw363bh+DPcN4ODfKVQ==";
    private String response;
    private KrakenApi api;
    private Map<String, String> input;

    public GestorConexionAPI() {
        this.response = "";
        this.input = new HashMap<>();
        this.api = new KrakenApi();
        api.setKey(key);
        api.setSecret(secret);
    }

    /**
     * Método que pide a la API todas las listas disponibles y las guarda en una
     * lista
     *
     * @return List<String> contiene todos los activos disponibles de la API
     */
    public List<String> getListaActivos() {
        List<String> listaActivos = new ArrayList<>();
        try {
            response = api.queryPublic(KrakenApi.Method.ASSET_PAIRS);

            JSONObject job = new JSONObject(response);
            JSONObject jobResult = job.getJSONObject("result");
            Iterator<String> it = jobResult.keys();
            String key;
            while (it.hasNext()) {
                key = it.next();
                if (key.substring(key.length() - 2, key.length()).equalsIgnoreCase(".d")) {
                    key = key.substring(0, key.length() - 2);
                }
                listaActivos.add(key);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaActivos;
    }

    // TODO sin acabar, cambiará
    public DefaultHighLowDataset getDatosActivo(String simboloActivo, String interval) {
        DefaultHighLowDataset data = null;
        try {
            input.clear();
            input.put("pair", simboloActivo);
            input.put("since", "0");
            input.put("interval", interval);
            response = api.queryPublic(KrakenApi.Method.OHLC, input);

            JSONObject job = new JSONObject(response);

            JSONObject jobResult = job.getJSONObject("result");
            Iterator<String> it = jobResult.keys();
            String key;
            JSONArray jaResult = null;
            while (it.hasNext()) {
                key = it.next();
                if (!key.equalsIgnoreCase("last")) {
                    jaResult = jobResult.getJSONArray(key);
                }
            }

            JSONArray jaResult2;
            Date[] date = new Date[jaResult.length()];
            double[] high = new double[jaResult.length()];
            double[] low = new double[jaResult.length()];
            double[] open = new double[jaResult.length()];
            double[] close = new double[jaResult.length()];
            double[] volume = new double[jaResult.length()];
            for (int i = 0; i < jaResult.length(); i++) {
                jaResult2 = jaResult.getJSONArray(i);
                date[i] = new Date(jaResult2.getLong(0));
                open[i] = jaResult2.getDouble(1);
                high[i] = jaResult2.getDouble(2);
                low[i] = jaResult2.getDouble(3);
                close[i] = jaResult2.getDouble(4);
                volume[i] = jaResult2.getDouble(6);
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
    public DefaultHighLowDataset getUltimoDato(DefaultHighLowDataset data, String simboloActivo, String interval) {
        DefaultHighLowDataset dataSet = null;
        try {
            Date fechaActualizar = data.getXDate(0, data.getItemCount(0) - 1);
//            
            input.clear();
            input.put("pair", simboloActivo);
            input.put("since", String.valueOf(fechaActualizar.getTime() + 1000000000L));
            input.put("interval", interval);
            response = api.queryPublic(KrakenApi.Method.OHLC, input);

            JSONObject job = new JSONObject(response);

            JSONObject jobResult = job.getJSONObject("result");
            Iterator<String> it = jobResult.keys();
            String key;
            JSONArray jaResult = null;
            while (it.hasNext()) {
                key = it.next();
                if (!key.equalsIgnoreCase("last")) {
                    jaResult = jobResult.getJSONArray(key);
                }
            }
            JSONArray jaResult2 = jaResult.getJSONArray(0);
            if (jaResult2.getLong(0) == fechaActualizar.getTime()) {
                Date[] date = new Date[data.getItemCount(0)];
                double[] high = new double[data.getItemCount(0)];
                double[] low = new double[data.getItemCount(0)];
                double[] open = new double[data.getItemCount(0)];
                double[] close = new double[data.getItemCount(0)];
                double[] volume = new double[data.getItemCount(0)];
                System.out.println(data.getXDate(0, data.getItemCount(0) - 1).getTime());
                for (int i = 0; i < data.getItemCount(0); i++) {
                    if (i == data.getItemCount(0) - 1) {
                        jaResult2 = jaResult.getJSONArray(0);
                        date[i] = new Date(jaResult2.getLong(0));
                        open[i] = jaResult2.getDouble(1);
                        high[i] = jaResult2.getDouble(2);
                        low[i] = jaResult2.getDouble(3);
                        close[i] = jaResult2.getDouble(4);
                        volume[i] = jaResult2.getDouble(6);
                    } else {
                        date[i] = data.getXDate(0, i);
                        high[i] = data.getHighValue(0, i);
                        open[i] = data.getOpenValue(0, i);
                        low[i] = data.getLowValue(0, i);
                        close[i] = data.getCloseValue(0, i);
                        volume[i] = data.getVolumeValue(0, i);
                    }
                }
                dataSet = new DefaultHighLowDataset("", date, high, low, open, close, volume);
            } else {
                Date[] date = new Date[data.getItemCount(0) + 1];
                double[] high = new double[data.getItemCount(0) + 1];
                double[] low = new double[data.getItemCount(0) + 1];
                double[] open = new double[data.getItemCount(0) + 1];
                double[] close = new double[data.getItemCount(0) + 1];
                double[] volume = new double[data.getItemCount(0) + 1];
                for (int i = 0; i < data.getItemCount(0) + 1; i++) {
                    if (i == data.getItemCount(0)) {
                        date[i] = new Date(jaResult2.getLong(0));
                        open[i] = jaResult2.getDouble(1);
                        high[i] = jaResult2.getDouble(2);
                        low[i] = jaResult2.getDouble(3);
                        close[i] = jaResult2.getDouble(4);
                        volume[i] = jaResult2.getDouble(6);
                    } else {
                        date[i] = data.getXDate(0, i);
                        high[i] = data.getHighValue(0, i);
                        open[i] = data.getOpenValue(0, i);
                        low[i] = data.getLowValue(0, i);
                        close[i] = data.getCloseValue(0, i);
                        volume[i] = data.getVolumeValue(0, i);
                    }
                }
                dataSet = new DefaultHighLowDataset("", date, high, low, open, close, volume);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSet;
    }

    public List<String> getTrade(String simboloActivo) {
        List<String> listaActivos = new ArrayList<>();
        try {
            input.clear();
            input.put("pair", simboloActivo);
            input.put("since", "0");
            response = api.queryPublic(KrakenApi.Method.TRADES, input);
            System.out.println(response);
            JSONObject job = new JSONObject(response);
            if (job.getJSONArray("error").isEmpty()) {
                Iterator<String> it = job.getJSONObject("result").keys();
                JSONArray jaResult = null;
                String keyResult="";
                while (it.hasNext()) {
                    String clave = it.next();
                    if (!clave.equalsIgnoreCase("last")) {
                        keyResult=clave;
                    }
                }
                List<Operaciones> listaOperaciones=new ArrayList<>();
                for(int i=0;i<job.getJSONObject("result").getJSONArray(keyResult).length();i++){
                    jaResult = job.getJSONObject("result").getJSONArray(keyResult).getJSONArray(i);
                    Operaciones o=new Operaciones();
                    o.setPrecioApertura(new BigDecimal(jaResult.getString(0)));
                    o.setVolumen(new BigDecimal(jaResult.getString(1)));
                    o.setFechaApertura(new Date(Math.round(jaResult.getDouble(2)*1000)));
                    System.out.println(o.getPrecioApertura());
                    System.out.println(o.getFechaApertura());
                    listaOperaciones.add(o);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaActivos;
    }
    public List<String> getOpenOrders(String simboloActivo) {
        List<String> listaActivos = new ArrayList<>();
        try {
//            input.clear();
//            input.put("pair", simboloActivo);
//            input.put("since", "0");
            response = api.queryPrivate(KrakenApi.Method.OPEN_ORDERS);
            System.out.println(response);
            JSONObject job = new JSONObject(response);
            if (job.getJSONArray("error").isEmpty()) {
                Iterator<String> it = job.getJSONObject("result").keys();
                JSONArray jaResult = null;
                String keyResult="";
                while (it.hasNext()) {
                    String clave = it.next();
                    if (!clave.equalsIgnoreCase("last")) {
                        keyResult=clave;
                    }
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaActivos;
    }
    public Operaciones nuevaOrden(Operaciones op){
        Operaciones operacion=op;
        try {
            input.clear();
            op.getActivo().getSimbolo();
            input.put("pair", operacion.getActivo().getSimbolo());
            input.put("type", "buy");
            input.put("ordertype", "limit");
            input.put("price", "100");
            input.put("volume", ".01"); 
            //input.put("validate", "true");
            //input.put("oflags","post");
            response = api.queryPrivate(KrakenApi.Method.ADD_ORDER, input);
            System.out.println(response);
            JSONObject job = new JSONObject(response);
            if (job.getJSONArray("error").isEmpty()) {
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return op;
    }
    public void cancelarOrden(String idOrden){
        try {
            input.clear();
            input.put("txid", idOrden);
            response = api.queryPrivate(KrakenApi.Method.CANCEL_ORDER, input);
            System.out.println(response);
            JSONObject job = new JSONObject(response);
            if (job.getJSONArray("error").isEmpty()) {
                Iterator<String> it = job.getJSONObject("result").keys();
                JSONArray jaResult = null;
                String keyResult="";
                while (it.hasNext()) {
                    String clave = it.next();
                    if (!clave.equalsIgnoreCase("last")) {
                        keyResult=clave;
                    }
                }  
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestorConexionAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ListaDeActivos cargaInicial(){
        ListaDeActivos lista=new ListaDeActivos();
        lista.setNombre("ListaKraken");
        lista.setEsPrivada(false);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    response = api.queryPublic(KrakenApi.Method.ASSET_PAIRS);

                    JSONObject job = new JSONObject(response);
                    if (job.getJSONArray("error").isEmpty()) {
                        JSONObject jobResult = job.getJSONObject("result");
                        Iterator<String> it = jobResult.keys();
                        Set<Activo> activos=null;
                        String key;
                        while (it.hasNext()) {
                            key = it.next();
                            if (key.substring(key.length() - 2, key.length()).equalsIgnoreCase(".d")) {
                                key = key.substring(0, key.length() - 2);
                            }
                            Activo activo=new Activo();
                            JSONObject jobActivo=jobResult.getJSONObject(key);
                            String[] nombre=jobActivo.getString("wsname").split("\\");
                            activo.setNombre(nombre[0]+nombre[1]);
                            activo.setSimbolo(key);
                            activos.add(activo);
                        }
                        lista.setActivos(activos);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        return lista;
    }
}
        
