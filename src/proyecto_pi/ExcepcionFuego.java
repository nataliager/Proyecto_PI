/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 29/09/2020
 * Ultima fecha de modificación: 30/09/2020
 * Explicación: clase ExcepcionFuego.java encargada de manejar el mensaje de excepción
   para el poder de borrar covids y fuegos(areas).
 */
package proyecto_pi;

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */

public class ExcepcionFuego extends Exception{
     public ExcepcionFuego(){
        
        super("Solo Puedes eliminar un covid ");
    }
}

