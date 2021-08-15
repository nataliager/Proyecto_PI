/*
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
