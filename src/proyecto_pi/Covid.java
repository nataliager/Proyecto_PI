/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: 
   -Cambio en el icono del baggage por un covid.
   -Cambio de nombre a la clase Baggage = Covid
 */

package proyecto_pi;

//Paquetes
import java.awt.Image;//es la superclase de todas las clases que representan
//imágenes gráficas
import javax.swing.ImageIcon;// pinta iconos a partir de imágenes.

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */

//Clase hija que hereda de la clase padre Actor. Este objeto es movible, por lo que 
//tambien tiene el metodo move
public class Covid extends Actor{
    
    public Covid(int x, int y) {
        super(x, y);
        
        initCovid();
    }
    
    private void initCovid() {
        
        ImageIcon icon = new ImageIcon("src/resources/covid.png");//Imagen del covid
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
    
}//Fin de la clase Covid.java

