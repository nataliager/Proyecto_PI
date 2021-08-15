/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: 
   -Cambio de la imagen de las paredes.
 */
package proyecto_pi;


import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhon Edward Correa 201958,Natalia Giraldo 201958446, Miguel Angel Paz 201958.
 */

//Clase hija que Hereda de la clase padre Actor. Tras la construcción, carga una iamgen
//de pared de los recursos.
public class Wall extends Actor{
    
    private Image image;

    public Wall(int x, int y) {
        super(x, y);
        
        initWall();
    }
    
    private void initWall() {
        
        ImageIcon icon = new ImageIcon("src/resources/wall.png");
        image = icon.getImage();
        setImage(image);
    }
    
}//Fin de la clase Wall.java
