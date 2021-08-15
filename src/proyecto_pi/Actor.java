/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: No hubierón modificaciones.
 */
package proyecto_pi;

//Paquetes
import java.awt.Image;//es la superclase de todas las clases que representan
//imágenes gráficas

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */

//Clase base para otro actores del juego, encapsula la funcionabilidad basica de un
//objeto en el juego KillingCovid
public class Actor {
    
    private final int SPACE = 20;
    private int x;
    private int y;
    private Image image;
    
    public Actor(int x, int y) {
        
        this.x = x;
        this.y = y;
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        
        return x;
    }

    public int y() {
        
        return y;
    }

    public void setX(int x) {
        
        this.x = x;
    }

    public void setY(int y) {
        
        this.y = y;
    }

    //Comprueba si el actor choca con otro actor(pared, covid,jeringa) a la izquierda.
    public boolean isLeftCollision(Actor actor) {
        
        
        return x() - SPACE == actor.x() && y() == actor.y();
    }

    //Comprueba si el actor choca con otro actor(pared, covid,jeringa) a la derecha.
    public boolean isRightCollision(Actor actor) {
        
        return x() + SPACE == actor.x() && y() == actor.y();
    }

    public boolean isTopCollision(Actor actor) {
        
        return y() - SPACE == actor.y() && x() == actor.x();
    }

    public boolean isBottomCollision(Actor actor) {
        
        return y() + SPACE == actor.y() && x() == actor.x();
    }

}//Fin de la clase Actor.java

