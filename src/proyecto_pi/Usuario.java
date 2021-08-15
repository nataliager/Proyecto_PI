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
 * Fecha de creación: 16/09/2020
 * Ultima fecha de modificación: 29/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * EXPLICACIÓN: Objeto Usuario que tiene como atributos nombre, puntaje, movimientos
   reinicio, partida, que serán utilizados en el juego Killing covid para almacenar
   los datos que serán emviados a la base de datos.
 */

package proyecto_pi;

//Clase Usuario.java con atributos: nombre,puntaje, movimientos, reinicio, partida
//poderes y el tiempo de juego.
public class Usuario {
    
    protected String nombre;
    protected int puntaje;
    protected int movimientos;
    protected int reinicio;
    protected int poderes;
    protected int segundos;
    protected String partida;
    
    //Constructor
    public Usuario(String nombre, int puntaje,int movimientos, int reinicio, String partida, int poderes, int segundos){
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.movimientos = movimientos;
        this.reinicio = reinicio;
        this.partida = partida;
        this.poderes = poderes;
        this.segundos=segundos;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public int getPoderes() {
        return poderes;
    }
    
    public void setPoderes(int poderes) {
        this.poderes = poderes;
    }
    
     public int getSegundos() {
        return segundos;
    }
    
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the movimientos
     */
    public int getMovimientos() {
        return movimientos;
    }

    /**
     * @param movimientos the movimientos to set
     */
    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    String setNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getReinicio(){
        return reinicio;
    }
    
    public void setReinicio(int reinicio){
        this.reinicio = reinicio;
    }
    public String getPartida(){
        return partida;
    }
    
     public void setPartida(String partida){
        this.partida = partida;
        
    }
}//Fin de la clase Usuario.java
