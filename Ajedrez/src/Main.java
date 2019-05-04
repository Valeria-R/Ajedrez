
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author valeria
 */
public class Main {

    private final Juego jn;
    private final Scanner sc;

    public Main() {
        jn = new Juego();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main juego = new Main();
        juego.iniciar();
    }

    private void iniciar() {
        System.out.println("Bienvenido al ajedrez.");
        while (!jn.juegoTerminado()) {
            while (jn.getTurno() % 2 == 0) { // turno de blanco
                System.out.println(jn.toString());
                do {
                    System.out.println("Es el turno del blanco(B).\nPara mover una ficha digite su posición actual y la posición a usar. Ejemplo: 6 0 5 0");
                    jn.moverFicha(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()); //aca se mira si esta vacia o no es de tu tipo de ficha
                    
                } while (!jn.isMovimiento());

            }
            while (jn.getTurno() % 2 != 0) { //turno de negro
                System.out.println(jn.toString());
                do {
                    System.out.println("Es el turno del negro(N).\nPara mover una ficha digite su posición actual y la posición a usar. Ejemplo: 6 0 5 0");
                    jn.moverFicha(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()); //aca se mira si esta vacia o no es de tu tipo de ficha
                    
                } while (!jn.isMovimiento());

            }
        }
    }

}
