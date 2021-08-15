/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES: 
   -Cambiamos el icono del area por un fuego.
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

//Clase hija que hereda de clase padre Actor. Es el objeto sobre el que intentamos colocar
//los covid.
public class Area extends Actor{
    
    public Area(int x, int y) {
        super(x, y);
        
        initArea();
    }
    
    private void initArea() {

        ImageIcon icon = new ImageIcon("src/resources/area.png");//Imagen del area
        Image image = icon.getImage();
        setImage(image);
    }
    
}//Fin de la clase Area.java

