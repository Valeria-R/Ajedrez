/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valeria
 */
public class Juego {
    private int turno = 0;
    private String[][] tablero;
    
    public Juego(){
        tablero = new String[8][8];
        inicializarTablero();
    }
    private void inicializarTablero(){
        inicializarBlancas();
        inicializarNegras();
    }

    private void inicializarBlancas() {
        tablero[7][1] = "CB";
        tablero[7][6] = "CB";
        tablero[7][2] = "AB";
        tablero[7][5] = "AB";
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = "PB";
        }
    }

    private void inicializarNegras() {
        tablero[0][1] = "CN";
        tablero[0][6] = "CN";
        tablero[0][2] = "AN";
        tablero[0][5] = "AN";
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = "PN";
        }
    }
    
    private void moverFicha(int i1, int j1, int i2, int j2){
        if (tablero[i1][j1].isEmpty()) {
            System.out.println("No hay ficha");
        }else{
            String caso = mirarFicha(tablero[i1][j1]);
            if (tablero[i1][j1].contains("N")) {
                
            }else{
                
            }
        }
    }
    
    private String mirarFicha(String ficha) {
        if (ficha.contains("A")) {
            return "alfil";
        }else if (ficha.contains("C")) {
            return "caballo";
        }
        return "peon";
    }
    
    @Override
    public String toString() {
        return "";
    }

    
}
