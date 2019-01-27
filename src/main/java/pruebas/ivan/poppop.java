/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ivan;

/**
 *
 * @author usuario
 */
public class poppop {

    public poppop() {
    }
    
    public void getpop(){
    new java.util.Timer().schedule(
        new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("PopPop");
            }
        },
        5000            
        );
    }
    
}
