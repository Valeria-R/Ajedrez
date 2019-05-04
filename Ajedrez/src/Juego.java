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

    public Juego() {
        tablero = new String[8][8];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = " ";
            }
        }
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

    private void moverFicha(int i1, int j1, int i2, int j2) {
        if (tablero[i1][j1].isEmpty()) {
            System.out.println("No hay ficha");
        } else {
            String caso = mirarFicha(tablero[i1][j1]);
            if (tablero[i1][j1].contains("N")) {
                sePuedeMover(11, j1, i2, j2);
            } else {

            }
        }
    }

    private String mirarFicha(String ficha) {
        if (ficha.contains("A")) {
            return "alfil";
        } else if (ficha.contains("C")) {
            return "caballo";
        }
        return "peon";
    }

    private void sePuedeMover(int i1, int j1, int i2, int j2) {
        String ficha = tablero[i1][j1];
        if (ficha.contains("A")) {
            if (moverAlfil(i1, j1, i2, j2)) {
                System.out.println("Movimiento realizado");
                turno++;
            } else {
                System.out.println("El movimiento no es posible"); //aca toca pensar como hacer para mantenerse repitiendo
            }
        } else if (ficha.contains("C")) {
            if (moverCaballo(i1, j1, i2, j2)) {

            }
        } else if (ficha.equals("PN")) {
            moverPeonNegro(i1, j1, i2, j2);
        } else if (ficha.equals("PB")) {
            moverPeonBlanco(i1, j1, i2, j2);
        }
    }

    private boolean moverAlfil(int i1, int j1, int i2, int j2) {
        int maximoI = Math.max(i1, i2);
        int maximoJ = Math.max(j1, j2);
        int minimoI = Math.min(i1, i2);
        int minimoJ = Math.min(j1, j2);
        if ((maximoI - minimoI) == (maximoJ - minimoJ)) {
            if (tablero[i1][j1].contains("N")) {
                puedeMoverAlfil(i1, j1, i2, j2, "N", "B");
            } else {
                puedeMoverAlfil(i1, j1, i2, j2, "B", "N");
            }
        }
        return false;
    }

    private boolean puedeMoverAlfil(int i1, int j1, int i2, int j2, String tipo, String enemigo) {
        if (tablero[i2][j2].contains(tipo)) {
            if (i1 < i2 && j1 < j2) {
                tablero[i2 - 1][j2 - 1] = tablero[i1][j1];
            } else if (i1 < i2 && j1 > j2) {
                tablero[i2 - 1][j2 + 1] = tablero[i1][j1];
            } else if (i1 > i2 && j1 < j2) {
                tablero[i2 + 1][j2 - 1] = tablero[i1][j1];
            } else {
                tablero[i2 + 1][j2 + 1] = tablero[i1][j1];
            }
            tablero[i1][j1] = " ";
            return true;
        } else if (tablero[i2][j2].contains(enemigo) || tablero[i2][j2].equals(" ")) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        }
        return false;
    }

    private boolean moverCaballo(int i1, int j1, int i2, int j2) {
        return false;

    }

    private boolean moverPeonNegro(int i1, int j1, int i2, int j2) { //se suma la i
        int movimientoI = i1 + 1;
        if (tablero[i2][j2].equals(" ") && movimientoI == i2) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        } else if (tablero[i2][j2].contains("B") && ((j1 + 1 == j2 || j1 - 1 == j2) && i1 + 1 == i2)) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        }
        return false;
    }

    private boolean moverPeonBlanco(int i1, int j1, int i2, int j2) { // se resta la I
        int movimientoI = i1 - 1;
        if (tablero[i2][j2].equals(" ") && movimientoI == i2) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        } else if (tablero[i2][j2].contains("B") && ((j1 + 1 == j2 || j1 - 1 == j2) && i1 - 1 == i2)) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (String[] strings : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (strings[j].equals(" ")) {
                    mensaje += "[  ]";
                } else {
                    mensaje += " " + strings[j] + " ";
                }
            }
            mensaje += "\n";
        }
        return mensaje;
    }

}
