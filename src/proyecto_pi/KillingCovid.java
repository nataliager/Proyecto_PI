/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: 
   -Cambio al nombre de la clase Sokoban = KillingCovid
   -Nuevo nombre del juego: Killing covid
 */ 
package proyecto_pi;

//Paquetes
import java.awt.EventQueue;//pone en cola eventos.
import javax.swing.JFrame;// para generar ventanas sobre las cuales añadir objetos.

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */

//Es la clase principal (Contiene el metodo main)
public class KillingCovid extends JFrame{
    
    private final int OFFSET = 30;
    
    //Constructor
    public KillingCovid(String lvl) {

        initUI();
    }

    //Metodo initUI
    private void initUI( ) {
        
        Board board = new Board();
        add(board);

        setTitle("Killing covid");
        
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }//Fin del metodo initUI

    //Metodo main
    public static void main(String[] args) {
      
        EventQueue.invokeLater(() -> {
            
            KillingCovid game = new KillingCovid("");
            game.setVisible(true);
        });
      }
}//Fin de la clase KillingCovid.java
