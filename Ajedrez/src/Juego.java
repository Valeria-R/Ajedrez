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

    private int turno;
    private String[][] tablero;
    private boolean movimiento;

    public Juego() {
        tablero = new String[8][8];
        turno = 0;
        movimiento = false;
        inicializarTablero();
    }

    //turno
    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
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

    public void moverFicha(int i1, int j1, int i2, int j2) {
        movimiento = false;
        if (tablero[i1][j1].contains(" ")) {
            System.out.println("No hay ficha en la posición actual");
        } else {
            if (tablero[i1][j1].contains("N") && (turno % 2 != 0)) {
                sePuedeMover(i1, j1, i2, j2);
            } else if (tablero[i1][j1].contains("B") && (turno % 2 == 0)) {
                sePuedeMover(i1, j1, i2, j2);
            } else {
                System.out.println("La posición actual no es de su color");
            }
        }

    }

    private void sePuedeMover(int i1, int j1, int i2, int j2) {
        String ficha = tablero[i1][j1];
        if (ficha.contains("A")) {
            if (moverAlfil(i1, j1, i2, j2)) {
                System.out.println("Movimiento del alfil realizado");
                movimiento = true;
                turno++;
            } else {
                System.out.println("El movimiento del alfil no es posible");
            }
        } else if (ficha.contains("C")) {
            if (ficha.contains("N")) {
                if (moverCaballo(i1, j1, i2, j2, "N")) {
                    System.out.println("Movimiento del caballo realizado");
                    movimiento = true;
                    turno++;
                } else {
                    System.out.println("El movimiento del caballo no es posible");
                }
            } else {
                if (moverCaballo(i1, j1, i2, j2, "B")) {
                    System.out.println("Movimiento del caballo realizado");
                    movimiento = true;
                    turno++;
                } else {
                    System.out.println("El movimiento del caballo no es posible");
                }
            }

        } else if (ficha.equals("PN")) {
            if (moverPeonNegro(i1, j1, i2, j2)) {
                System.out.println("Movimiento del peon realizado");
                movimiento = true;
                turno++;
            } else {
                System.out.println("El movimiento del peon no es posible");
            }
        } else if (ficha.equals("PB")) {
            if (moverPeonBlanco(i1, j1, i2, j2)) {
                System.out.println("Movimiento del peon realizado");
                movimiento = true;
                turno++;
            } else {
                System.out.println("El movimiento del peon no es posible");
            }
        }
    }

    private boolean moverAlfil(int i1, int j1, int i2, int j2) {
        int maximoI = Math.max(i1, i2);
        int maximoJ = Math.max(j1, j2);
        int minimoI = Math.min(i1, i2);
        int minimoJ = Math.min(j1, j2);
        if ((maximoI - minimoI) == (maximoJ - minimoJ)) {
            if (tablero[i1][j1].contains("N")) {
                if (recorridoLibre(i1, j1, i2, j2, (maximoI - minimoI),"B")) {
                    return puedeMoverAlfil(i1, j1, i2, j2, "N", "B");
                }
            } else {
                if (recorridoLibre(i1, j1, i2, j2, (maximoI - minimoI),"N")) {
                    return puedeMoverAlfil(i1, j1, i2, j2, "B", "N");
                }
            }
        }
        return false;
    }

    private boolean recorridoLibre(int i1, int j1, int i2, int j2, int totalRecorrer, String enemigo) {
        //casos
        System.out.println("toal: "+(totalRecorrer-1));
        boolean atravesado = false;
        if (i1 < i2 && j1 < j2) {
            for (int i = i1+1, j = j1+1, k = 0; k < totalRecorrer-1; i++, j++, k++) {
                if (!tablero[i][j].contains(" ")) {
                    atravesado = true;
                    System.out.println("Hay alguien atravesado para poder llegar a la posición deseada (I = "+i+" J = "+j);
                    break;
                }
            }
        } else if (i1 < i2 && j1 > j2) {
            for (int i = i1+1, j = j1-1, k = 0; k < totalRecorrer-1; i++, j--, k++) {
                if (!tablero[i][j].contains(" ")) {
                    atravesado = true;
                    System.out.println("Hay alguien atravesado para poder llegar a la posición deseada (I = "+i+" J = "+j);
                    break;
                }
            }
        } else if (i1 > i2 && j1 < j2) {
            for (int i = i1-1, j = j1+1, k = 0; k < totalRecorrer-1; i--, j++, k++) {
                if (!tablero[i][j].contains(" ")) {
                    atravesado = true;
                    System.out.println("Hay alguien atravesado para poder llegar a la posición deseada (I = "+i+" J = "+j);
                    break;
                }
            }
        } else {
            for (int i = i1-1, j = j1-1, k = 0; k < totalRecorrer-1; i--, j--, k++) {
                if (!tablero[i][j].contains(" ")) {
                    atravesado = true;
                    System.out.println("Hay alguien atravesado para poder llegar a la posición deseada (I = "+i+" J = "+j);
                    break;
                }
            }
        }
        if (atravesado == false) {
            return true;
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

    private boolean moverCaballo(int i1, int j1, int i2, int j2, String tipo) {
        if (i1 + 2 == i2 || i1 - 2 == i2) { //arriba-abajo
            if (j1 + 1 == j2 || j1 - 1 == j2) {
                if (tipo.equals("N") && (tablero[i2][j2].contains("B") || tablero[i2][j2].contains(" "))) {
                    tablero[i2][j2] = tablero[i1][j1];
                    tablero[i1][j1] = " ";
                    return true;
                } else if (tipo.equals("B") && (tablero[i2][j2].contains("N") || tablero[i2][j2].contains(" "))) {
                    tablero[i2][j2] = tablero[i1][j1];
                    tablero[i1][j1] = " ";
                    return true;
                }

            }
        } else if (j1 + 2 == j2 || j1 - 2 == j2) { //derecha-izquierda
            if (i1 + 1 == i2 || i1 - 1 == i2) {
                if (tipo.equals("N") && (tablero[i2][j2].contains("B") || tablero[i2][j2].contains(" "))) {
                    tablero[i2][j2] = tablero[i1][j1];
                    tablero[i1][j1] = " ";
                    return true;
                } else if (tipo.equals("B") && (tablero[i2][j2].contains("N") || tablero[i2][j2].contains(" "))) {
                    tablero[i2][j2] = tablero[i1][j1];
                    tablero[i1][j1] = " ";
                    return true;
                }
            }
        }
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
        } else if (tablero[i2][j2].contains("N") && ((j1 + 1 == j2 || j1 - 1 == j2) && i1 - 1 == i2)) {
            tablero[i2][j2] = tablero[i1][j1];
            tablero[i1][j1] = " ";
            return true;
        }
        return false;
    }

    public boolean juegoTerminado() {
        int contadorBlancas = 0;
        int contadorNegras = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].contains("B")) {
                    contadorBlancas++;
                } else if (tablero[i][j].contains("N")) {
                    contadorNegras++;
                }
            }
        }
        if (contadorBlancas == 0) {
            System.out.println("Ha ganado el Negro");
            return true;
        } else if (contadorNegras == 0) {
            System.out.println("Ha ganado el Blanco");
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
