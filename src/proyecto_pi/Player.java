/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: 
   -Cambio de imagen de sokoban por una inyección.
 */
package proyecto_pi;

//Paquetes
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhon Edward Correa 201958,Natalia Giraldo 201958446, Miguel Angel Paz 201958.
 */

//Clae hija que hereda de la clase padre Actor 
public class Player extends Actor{

    
    private int x;
    private int y;
    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {

        ImageIcon icon = new ImageIcon("src/resources/sokoban.png");
        Image image = icon.getImage();
        setImage(image);
    }

    //Metodo que mueve el objeto dentro del mundo
    public void move(int x, int y) {

        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}//Fin de la clase Player.java

