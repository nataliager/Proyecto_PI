/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Natalia Andrea Giraldo Erazo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 
 * @version 1.0
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 * 
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 18/09/2020
 * Ultima fecha de modificación: 20/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * EXPLICACIÓN: ManejadorJuego clase encargada de crear la conexión con la base
   de datos y de insertar las tuplas(Los atributos del objeto Usuario en la 
   base de datos.
 */

package proyecto_pi;

//Paquetes
import java.sql.*;//para acceder y procesar datos almacenados en una fuente de datos.

//Clase ManejadorJuego
public class ManejadorJuego {
    
    Connection conexion;
    
  //Constructor
    public ManejadorJuego(){
    
    }
    
    public void crearConexion(){
        
        conexion=null;
        
        try{
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sirpeace");
        }
        catch (Exception e){
            System.out.println( "No se pudo crear la conexión" );
            return ;
        }
    }//Fin del metodo crearConexion
    
    //Metodo insertarTuplas, encargado de insertar los datos en la tabla.
    public void insertarTuplas(String query){
        
        crearConexion();
        System.out.println(query);
        
        try{
            
            Statement s = conexion.createStatement();
            s.executeUpdate(query);    
        }
        catch (Exception e){
            e.toString();
        }
        cerrarConexion();	
    }//Fin del metodo insertarTuplas
    
    //Metodo cerrarConexión encargado de llamar la función conexion.close
    public void cerrarConexion(){
        
        try{
            conexion.close();
        }
        catch (Exception e){
            e.toString();
        }
    }//Fin del metodo cerrarConexion
    
}//Fin de la clase ManejadorJuego.java
