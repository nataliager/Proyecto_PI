/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 29/09/2020
 * Ultima fecha de modificación: 30/09/2020
 * Explicación: clase excepcion.java encargada de manejar el mensaje de excepción
   para el poder de borrar los muros.
 */
package proyecto_pi;

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */

public class excepcion extends Exception{
     public excepcion(){
        super("Ya no puedes eliminar muros. ");
    }
}