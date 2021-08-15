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
   -Cambio al nombre de la clase Sokoban = KillingCovid
   -Nuevo nombre del juego: Killing covid
 */

package proyecto_pi;

//Paquetes
import java.awt.EventQueue;//pone en cola eventos.
import javax.swing.JFrame;// para generar ventanas sobre las cuales añadir objetos.

//Es la clase principal (Contiene el metodo main)
public class KillingCovid extends JFrame{
    
    private final int OFFSET = 30;
    
    //Constructor
    public KillingCovid(String lvl) {

        initUI();
    }

    //Metodo initUI
    private void initUI( ) {
        
        Board board = new Board();
        add(board);

        setTitle("Killing covid");
        
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }//Fin del metodo initUI

    //Metodo main
    public static void main(String[] args) {
      
        EventQueue.invokeLater(() -> {
            
            KillingCovid game = new KillingCovid("");
            game.setVisible(true);
        });
      }
}//Fin de la clase KillingCovid.java
