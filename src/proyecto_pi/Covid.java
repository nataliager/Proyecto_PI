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

