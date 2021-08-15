/*
 * Proyecto PROGRAMACION INTERACTIVA
 * Universidad del valle sede Tuluá
 * Programa: Ingeniería de sistemas
 * Modalidad: Virtual
 * Fecha de creación: 26/08/2020
 * Ultima fecha de modificación: 30/09/2020
 * NOTA: Codigo fuente base http://zetcode.com/javagames/sokoban/
 * MODIFICACIONES:
   -Se implementó un metodo ReproducirSonido que es el encargado
   de dar los sonidos dinamicos en el juego sokoban.
   -Se cambió la forma de iniciar el nivel, el usuario ingresa un archivo txt y es 
   leído correspondientemente.(Se pueden ingresar los niveles que se deseen).
   -Se creó un objeto Usuario en el cual se actualizan sus atributos: Nombre,
   Puntaje, Movimientos, Reinicios y el estado de la partida.
   -Se implementó un manejadorJuego, encargado de manejar la base de datos, y a
   partir de esto se creó la conexión con la base de datos.
   -Cambio en las imagenes del juego, la parte grafica y el nombre, el nombre 
   nuevo del juego es Killing the covid y las imagenes son el sokoban cambio por
   una inyección, el baggage cambió por un covid, las areas por fuegos y los muros
   por muros diferentes.
 * Implementación de 3 poderes haciendo uso del MouseListener y keyAdapter:
   -El ususario puede eliminar tres muros haciendo click sobre ellos.
   -El usuario puede eliminar un covid y un area(Fuego) dando click en ambos.
   -El usuario puede adelantar dos casillas solo una vez presionando las teclas
    (AWSD).
 */
package proyecto_pi;

//Paquetes
import java.awt.Color;//Encapsula los colores en el espacio de color sRGB predeterminado.
import java.awt.Graphics;//Para usar el buildWorld(dibujar en java).
import java.awt.HeadlessException;//Exception para permitir solo entrada de Strings
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;//Recibe eventos de teclado.
import java.awt.event.KeyEvent;// Evento que indica que se produjo una pulsación de tecla.
import java.awt.event.MouseEvent;//Eventos del mouse
import java.awt.event.MouseListener;//Escucha del evento mouse
import java.io.BufferedReader;//Lee texto de una secuencia de entrada de caracteres
import java.io.File;//Nombres de rutas de archivos y directorios.
import java.io.FileNotFoundException;//Señala que ha fallado un intento de abrir
//el archivo indicado por un nombre de ruta especificado.
import java.io.FileReader;// para leer archivos de caracteres. 
import java.io.IOException;//Excepción de error entrada o salida
import java.util.ArrayList;//Para definir los ArrayLis.
import javax.sound.sampled.AudioInputStream;//Flujo de entrada de sonido 
import javax.sound.sampled.AudioSystem;//Punto de entrada a los recursos del sistema de audio.
import javax.sound.sampled.Clip;//para reproducir ficheros de sonido en java
import javax.sound.sampled.LineUnavailableException;//excepción que indica que una línea no se puede
//abrir porque no está disponible.
import javax.sound.sampled.UnsupportedAudioFileException;//excepción que indica que una operación 
import javax.swing.Icon;
import javax.swing.ImageIcon;
//falló porque un archivo no contenía datos válidos.
import javax.swing.JOptionPane;//Usar los mensajes en pantalla de JOption pane
import javax.swing.JPanel;//Para poder implementar el panel
import javax.swing.Timer;//se usa para hacer el temporizador del juego.

/**
 *
 * @author Jhon Edward Correa 201958557,Natalia Giraldo 201958446, Miguel Angel Paz 201958444.
 */ 


public class Board extends JPanel implements MouseListener {

    ManejadorJuego manejador = new ManejadorJuego(); //Creamos manejadoorJuego encargado
    //de manejar la base de datos conectada con el juego Killing covid
    Actor a = new Actor(0, 0);
    private final int OFFSET = 30;
    private final int SPACE = 20;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList<Wall> walls;// Almacena los objetos de tipo Wall
    private ArrayList<Covid> baggs;//Almacena los objetos Baggage
    private ArrayList<Area> areas;//Almacena las areas
    private Clip clip;//Clip para reporducir los sonidos
    private int movimientos = 0;//Contador de los movimientos 
    private int contadorPuntaje = 0;//Contador del puntaje
    private int contadorReinicio = 0;//Contador de los reincios
    private String nombre = "Invitado";// nombre inicializado en invitado
    private String partida = "";//String que se actualiza si supero o no supero
    //la partida
    Usuario usu = new Usuario("Usuario", 0, 0, 0, "",0,0);//creacion de objeto usuario
    //en donde se almacena el nombre,puntaje,movimientos y estado de la partida.
    
    //Contadores 
    private int clicks = 0;
    private int clicks1 = 0;
    private int saltos = 0;
    private int poderes=0;
    private int totalEventos=0;
    private Player soko;
    private int w = 0;
    private int h = 0;
    private String lvl = "";
    private String archivo;
    private boolean isCompleted = false;
    private boolean terminar = false;
    private int posXPoder = 0;
    private int posYPoder = 0;
    private Timer temporizador; 
    private ManejaEjemploTimer manejadorTime;
    
    //Iconos
    Icon icono = new ImageIcon("src/resources/menu.png");
    Icon error = new ImageIcon("src/resources/error.png");
    Icon errorMuro = new ImageIcon("src/resources/errorMuro.png");
    Icon errorVirus = new ImageIcon("src/resources/errorVirus.png");
    Icon errorSaltar = new ImageIcon("src/resources/errorSalto.png");
    Icon ganar = new ImageIcon("src/resources/ganar.gif");
    Icon perder = new ImageIcon("src/resources/ataud.gif");
    
    public Board() {
        initBoard();
        addMouseListener(this);
        ReproducirSonido("src/fondo.wav");
        manejadorTime = new ManejaEjemploTimer();
        temporizador = new Timer(1000, manejadorTime);
        temporizador.start();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());//Sirve para que el personaje se mueva en el mundo
        setFocusable(true);// indica si un componente puede obtener el foco si se
        //le solicita que lo haga.
        establecerNombre();
        leerNivel();//Lee el nivel por medio de un archivo txt
        initWorld();//Inicia el mundo, lo dibuja con clase graphics

    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    //Metodo leerNivel, se encarga de pedir al usuario por un JOption pane que 
    //nivel desea jugar, y lee archivos txt utilizando las funciones BufferedReader,
    // y Antes de inciar el mundo muestra un JOption pane en donde muestra las 
    //instrucciones del juego. Se utiliza el try y los catch para el manejo de las
    //excepciones en caso de que el archivo no se encuente, entre otros casos.
    public void leerNivel() {
        try {
            archivo = JOptionPane.showInputDialog(null, "Ingrese el nivel.", "Seleccionar nivel", 3);
            BufferedReader br = new BufferedReader(new FileReader(archivo + ".txt"));

            String linea;
            linea = br.readLine();
            lvl = lvl + linea + "\n";// Establece el nivel en los Strings que lee del 
            //archivo txt y les agrega un salto de linea.

            while (linea != null) {
                linea = br.readLine(); //Leemos siguiente línea
                if (linea != null) {
                    lvl = lvl + linea + "\n";
                }
            }
            //Mostramos la matriz leída
            System.out.println(lvl);
            JOptionPane.showMessageDialog(null,
                    "R: Reiniciar Partida "
                    + "\nT: Terminar Partida "
                    + "\nI: Mostrar Instrucciones"
                    + "\n→: Derecha "
                    + "\n←: Izquierda "
                    + "\n↑: Arriba"
                    + "\n↓: Abajo"
                    + "\nPoder1: Puedes remover 3 bloques haciendo click."
                    + "\nPoder2: Elimina un covid haciendo click sobre un virus y una llama."
                    + "\nPoder3: Se mueve en cualquier direccion 2 espacios una sola vez:"
                    + "\nD: Derecha "
                    + "\nA: Izquierda "
                    + "\nW: Arriba"
                    + "\nS: Abajo", "Instrucciones de juego", 1 ,icono);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"No se pudo encontrar el archivo", "Error", 1 ,error);
            System.exit(0);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Caracter no valido", "Error", 1 ,error);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error accediendo al archivo.");
            System.exit(0);
        }
    }

    //Metodo que inicia el mundo del juego, pasa por la cadena de nuvel y llena las listas 
    private void initWorld() {

        walls = new ArrayList<>();
        baggs = new ArrayList<>();
        areas = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Covid b;
        Area a;

        //Establece el nombre,Puntaje,Movimientos y reinicios del usuario.
        usu.setNombre(nombre);
        usu.setPuntaje(contadorPuntaje);
        usu.setMovimientos(movimientos);
        usu.setReinicio(contadorReinicio);
        usu.setPoderes(poderes);
        usu.setPartida(partida);

        for (int i = 0; i < lvl.length(); i++) {

            char item = lvl.charAt(i);

            //Lee los diferentes strings y los cambia por los obejtos correspondientes.
            switch (item) {

                case '\n':
                    y += SPACE;

                    if (this.w < x) {
                        this.w = x;
                    }
                    x = OFFSET;
                    break;

                case '#':
                    wall = new Wall(x, y);
                    walls.add(wall);

                    x += SPACE;
                    break;

                case '$':
                    b = new Covid(x, y);
                    baggs.add(b);
                    x += SPACE;
                    break;

                case '.':
                    a = new Area(x, y);
                    areas.add(a);
                    x += SPACE;
                    break;

                case '@':
                    soko = new Player(x, y);
                    x += SPACE;
                    break;

                case ' ':
                    x += SPACE;
                    break;

                default:
                    break;
            }
            h = y;
        }
    }//Fin del metodo initWorld

    //Metodo que dibuja el mundo del juego en la ventana
    private void buildWorld(Graphics g) {

        g.setColor(new Color(145, 213, 232));//Color del fondo.
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Se crea una lista mundial que incluye todos los objetos del juego
        ArrayList<Actor> world = new ArrayList<>();

        world.addAll(walls);
        world.addAll(areas);
        world.addAll(baggs);
        world.add(soko);

        //Prueba para ver si se actualiza el nombre, Puntaje, Movimientos, etc.
        System.out.println("Nombre: " + usu.getNombre() + "\nPuntaje: " + contadorPuntaje
                + "\nN° movimientos: " + movimientos + "\nN° Reinicios: " + contadorReinicio);

        //Se repite el contenedor del mundo y se dibujan los objetos. Se agregan 2px a las
        //coordenadas para centrarlas.
        for (int i = 0; i < world.size(); i++) {

            Actor item = world.get(i);
            g.setColor(Color.black);
            g.drawString(contadorPuntaje + " Pts", 400, 20);
            g.drawString(contadorReinicio + " Rs", 370, 20);
            g.drawString("Tiempo: "+totalEventos+" Segundos" , 25, 20);

            if (item instanceof Player || item instanceof Covid) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            //Muestra mensaje en pantalla avisando que gano y reproduce sonido.
            if (isCompleted) {
                temporizador.stop();
                clip.stop();//Para el audio doh.wav
                clip.stop();//Para el audio fondo.wav
                ReproducirSonido("src/ganar.wav");//Reproduce un sonido cuando
                //gana.
                //Si se completa el nivel se dibuja un String que diga completado
                g.setColor(Color.black);
                JOptionPane.showMessageDialog(null, "You Won!", "¡You Destroyed Covid!",1,ganar);
                System.exit(0);
            }
            
            //Muestra mensaje en pantalla avisando que finalizó(perdio) y reproduce sonido.
            if (terminar) {
                temporizador.stop();
                clip.stop();//Para el audio doh.wav
                clip.stop();//Para el audio fondo.wav
                ReproducirSonido("src/audioAtaud.wav");//Reproduce un sonido cuando
                //pierde.
                g.setColor(Color.black);
                JOptionPane.showMessageDialog(null, "You Lose!", "¡Covid Killed You!",1,perder);
                System.exit(0);
            }
        }
    }//Fin del metodo buildWorld
    
    //Metodo ManejaEjemploTimer
    class ManejaEjemploTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventoAccion) {
            
            totalEventos++;
            repaint();
            System.out.println("Tiempo"+totalEventos);
            System.out.println("-------------------------------");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        buildWorld(g);
    }

    //Metodo ReproducirSonido : Función que se encarga de reproducir los diferentes clips
    // a lo largo del juego(música de fondo, animaciones, etc).
    public void ReproducirSonido(String nombreSonido) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            //AudioInputStream obtiene una secuencia de entrada de audio de un archivo  externo.
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();//Inicia los clip
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }//Fin del metodo ReproducirSonido

    //Metodo establecer nombre, se encarga de establecer el nombre del jugador por
    //medio de un JOption pane y luego se setea el usu.setNombre.
    public void establecerNombre() {
        try {
            nombre = JOptionPane.showInputDialog("Ingrese su nombre: " );
            if (null == nombre || nombre.equals("")) {
                usu.setNombre("Invitado");
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error de datos solo letras");
        }
        usu.setNombre(nombre);
    }//Fin del metodo establecerNombre

    @Override
    public void mouseClicked(MouseEvent e) {
            
        try {
            
            //For que itera el arreglo de muros y pregunta si la coordenada donde se da click es muro
            for (int i = 0; i < walls.size(); i++) {
                if (e.getX() >= walls.get(i).x() && e.getY() >= walls.get(i).y() && e.getX() <= walls.get(i).x() + 20 && e.getY() <= walls.get(i).y() + 20) {
                    System.out.println("es muro");
                    System.out.println(clicks + "clicks");
                    if (clicks < 3) {
                        walls.remove(i);//si es muro lo borra al hacer click
                        poderes++;
                        clicks++;
                        repaint();
                    } else {
                        throw new excepcion();
                    }

                }
            }

            //For que itera el arreglo de covids(baggs) y pregunta si la cordenada donde se da click es covid
            for (int i = 0; i < baggs.size(); i++) {
                if (e.getX() >= baggs.get(i).x() && e.getY() >= baggs.get(i).y() && e.getX() <= baggs.get(i).x() + 20 && e.getY() <= baggs.get(i).y() + 20) {
                    System.out.println("es covid");
                    System.out.println(clicks + "clicks");
                    posXPoder = baggs.get(i).x();//Almacena la coordenada x que se presiona si es covid.
                    posYPoder = baggs.get(i).y();//Almacena la coordenada x que se presiona si es covid.
                }
            }

            //For que recorre el arreglo de fuegos(areas)  y pregunta si la coordenada donde se da click es area y en el
            //for interno recorre el arreglo de fuegos y elimine el fuego y el covid el posición j y la posición i.
            for (int i = 0; i < areas.size(); i++) {
                if (e.getX() >= areas.get(i).x() && e.getY() >= areas.get(i).y() && e.getX() <= areas.get(i).x() + 20 && e.getY() <= areas.get(i).y() + 20) {
                    System.out.println("es llama");
                    System.out.println(clicks + "clicks");
                    if (clicks1 < 1) {
                        for (int j = 0; j < baggs.size(); j++) {
                            if (posXPoder >= baggs.get(j).x() && posYPoder >= baggs.get(j).y() && posXPoder <= baggs.get(j).x() + 20 && posYPoder <= baggs.get(j).y() + 20) {
                                baggs.remove(j);//
                                areas.remove(i);
                                clicks1++;
                                poderes++;
                                repaint(); 
                            }
                        }

                    } else {
                        throw new ExcepcionFuego();
                    }

                }
            }

            System.out.println(e.getX());
            System.out.println(e.getY());
        } catch (ExcepcionFuego ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Poder agotado!",1,errorVirus);
        } catch (excepcion ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Poder agotado!",1,errorMuro);
        }
    }//Fin del MouseClicked
    
     @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (isCompleted) {
                return;
            }
            int key = e.getKeyCode();

            switch (key) {

                //Se comprueba que teclas se presionaron, se controla el objeto sokoban
                //con las teclas del cursor 
                case KeyEvent.VK_LEFT:

                    if (checkWallCollision(soko,
                            LEFT_COLLISION)) {
                        ReproducirSonido("src/doh.wav");//Reproduce sonido 
                        //cuando choca a izq.
                        movimientos++;//Cuenta los movimientos del juego.
                        return;
                    }

                    if (checkBagCollision(LEFT_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        return;
                    }

                    soko.move(-SPACE, 0);
                    movimientos++;
                    break;

                case KeyEvent.VK_RIGHT:

                    if (checkWallCollision(soko, RIGHT_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        movimientos++;
                        return;
                    }

                    if (checkBagCollision(RIGHT_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        return;
                    }

                    soko.move(SPACE, 0);
                    movimientos++;
                    break;

                case KeyEvent.VK_UP:

                    if (checkWallCollision(soko, TOP_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        movimientos++;
                        return;
                    }

                    if (checkBagCollision(TOP_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        return;
                    }

                    soko.move(0, -SPACE);
                    movimientos++;
                    break;

                case KeyEvent.VK_DOWN:

                    if (checkWallCollision(soko, BOTTOM_COLLISION)) {
                        ReproducirSonido("src/doh.wav");//chocar contra la pared
                        movimientos++;
                        return;
                    }

                    if (checkBagCollision(BOTTOM_COLLISION)) {
                        ReproducirSonido("src/doh.wav");
                        return;
                    }

                    soko.move(0, SPACE);
                    movimientos++;

                    break;

                case KeyEvent.VK_W:
                    if (saltos == 0) {
                        soko.move(0, -2 * SPACE);//adelanta dos espacios hacia arriba
                        movimientos++;
                        saltos++;
                        poderes++;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Solo puedes saltar una vez.", "Poder Agotado", 1 ,errorSaltar);
                    }

                    break;
                case KeyEvent.VK_S:
                    if (saltos == 0) {
                        soko.move(0, 2 * SPACE);//adelanta dos espacios hacia abajo
                        movimientos++;
                        saltos++;
                        poderes++;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Solo puedes saltar una vez.", "Error", 1 ,errorSaltar);
                    }

                    break;
                case KeyEvent.VK_A:
                    if (saltos == 0) {
                        soko.move(-2 * SPACE, 0);//adelanta dos espacios hacia la izq
                        movimientos++;
                        saltos++;
                        poderes++;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Solo puedes saltar una vez.", "Error", 1 ,errorSaltar);
                    }

                    break;
                case KeyEvent.VK_D:
                    if (saltos == 0) {
                        soko.move(2 * SPACE, 0);//adelanta dos espacios hacia la der
                        movimientos++;
                        saltos++;
                        poderes++;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Solo puedes saltar una vez.", "Error", 1 ,errorSaltar);
                    }

                    break;

                //Se reinicia el nivel preionando la tecla R
                case KeyEvent.VK_R:
                    restartLevel();
                    contadorReinicio++;//Cuenta los reinicios del juego.
                    break;

                case KeyEvent.VK_I:
                    JOptionPane.showMessageDialog(null,
                    "R: Reiniciar Partida "
                    + "\nT: Terminar Partida "
                    + "\nI: Mostrar Instrucciones"
                    + "\n→: Derecha "
                    + "\n←: Izquierda "
                    + "\n↑: Arriba"
                    + "\n↓: Abajo"
                    + "\nPoder1: Puedes remover 3 bloques haciendo click."
                    + "\nPoder2: Elimina un covid haciendo click sobre un virus y una llama."
                    + "\nPoder3: Se mueve en cualquier direccion 2 espacios una sola vez:"
                    + "\nD: Derecha "
                    + "\nA: Izquierda "
                    + "\nW: Arriba"
                    + "\nS: Abajo", "Instrucciones de juego", 1 , icono);
                    break;

                //Se termina la partida presionando la tecla T
                case KeyEvent.VK_T:

                    terminar = true;//Se establece la variable terminar como true,
                    //pero teniendo en cuenta que el juego no ha sido superado.

                    //Se establecen nuevamente los puntajes,movimientos,reinicios.
                    usu.setPuntaje(contadorPuntaje);
                    usu.setMovimientos(movimientos);
                    usu.setReinicio(contadorReinicio);
                    usu.setPartida("No superada");//Se establece la partida como
                    //no superada.
                    usu.setPoderes(poderes);
                    usu.setSegundos(totalEventos);

                    terminarNivel();//Se hace llamado al metodo terminarNivel
                    repaint();//Se pinta nuevamente el mundo.
                    break;

                default:
                    break;
            }
            repaint();
        }
    }//Fin del metodo TAdapter

    //Metodo para garantizar que la inyección o el covid no pasen por la pared
    //hay cuatro tipo de colisiones. el codigo anterior comprueban la colision
    //izquierda
    private boolean checkWallCollision(Actor actor, int type) {

        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isLeftCollision(wall)) {

                        return true;
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case TOP_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isTopCollision(wall)) {

                        return true;
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isBottomCollision(wall)) {

                        return true;
                    }
                }

                return false;

            default:
                break;
        }

        return false;
    }//Fin del metodo checkWallCollision

    //Un covid puede chocar con una pared, con un objeto sokoban o con otro equipaje.
    //El covid se puede mover solo si choca con un sokoban y no choca con otro covid o una pared. 
    //Cuando se mueve el covid, se verifica si el nivel se completa llamando al método isCompleted.
    private boolean checkBagCollision(int type) {

        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Covid bag = baggs.get(i);

                    if (soko.isLeftCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Covid item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isLeftCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, LEFT_COLLISION)) {
                                return true;
                            }
                        }

                        bag.move(-SPACE, 0);
                        isCompleted();
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Covid bag = baggs.get(i);

                    if (soko.isRightCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Covid item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isRightCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, RIGHT_COLLISION)) {
                                return true;
                            }
                        }

                        bag.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;

            case TOP_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Covid bag = baggs.get(i);

                    if (soko.isTopCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Covid item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isTopCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, TOP_COLLISION)) {
                                return true;
                            }
                        }

                        bag.move(0, -SPACE);
                        isCompleted();
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < baggs.size(); i++) {

                    Covid bag = baggs.get(i);

                    if (soko.isBottomCollision(bag)) {

                        for (int j = 0; j < baggs.size(); j++) {

                            Covid item = baggs.get(j);

                            if (!bag.equals(item)) {

                                if (bag.isBottomCollision(item)) {
                                    return true;
                                }
                            }

                            if (checkWallCollision(bag, BOTTOM_COLLISION)) {

                                return true;
                            }
                        }

                        bag.move(0, SPACE);
                        isCompleted();
                    }
                }

                break;

            default:
                break;
        }

        return false;
    }//Fin del metodo checkBagCollision

    //Metodo que comprueba si el nivel esta completado. Se obtienen el numero de bolsas
    //se comparan las coordenadas X e Y de todas las maletas y las areas de destino.
    public void isCompleted() {

        int nOfBags = baggs.size();
        int finishedBags = 0;
        contadorPuntaje = 0;

        for (int i = 0; i < nOfBags; i++) {

            Covid bag = baggs.get(i);

            for (int j = 0; j < nOfBags; j++) {

                Area area = areas.get(j);

                if (bag.x() == area.x() && bag.y() == area.y()) {

                    finishedBags += 1;
                    contadorPuntaje += 50;
                }
            }
        }

        //El juego termina cuando la finishedBagsvariable es igual al número de bolsas en el juego.
        if (finishedBags == nOfBags) {

            //Se establecen los atributos del usu.
            usu.setPuntaje(contadorPuntaje);
            usu.setMovimientos(movimientos);
            usu.setReinicio(contadorReinicio);
            usu.setPartida("Superada");
            usu.setPoderes(poderes);
            usu.setSegundos(totalEventos);

            isCompleted = true;

            terminarNivel();//Llamado al metodo terminarNivel
            repaint();//Pinta nuevamente el juego
        }
    }//Fin del metodo isCompleted

    //Metodo que reinicia el nivel si se hace un mal movimeinto, en donde se eliminan
    //todos los objetos de las listas y se vuelve a iniciar el mundo.
    public void restartLevel() {

        areas.clear();
        baggs.clear();
        walls.clear();
        clicks = 0;
        clicks1 = 0;
        saltos=0;
        
        ReproducirSonido("src/restart.wav");//Se reproduce un sonido cuando se
        //reinicia el mundo.

        contadorPuntaje = 0;//contador puntaje inicializado en 0

        initWorld();//Vuelve a iniciar el mundo

        if (isCompleted) {
            isCompleted = false;// variable que se establece en falso
            contadorReinicio++;
        }
    }//Fin del metodo restartLevel

    //Metodo terminarNivel, se encarga de crear la conexión con la base de datos
    //y mandar los datos respectivos del usuario a la base de datos.
    public void terminarNivel() {

        manejador.crearConexion();//Conexión con la base de datos

        String consulta;
        consulta = "INSERT INTO public.\"Juego\" (\"Nombre\",\"Puntaje\", \"Movimientos\",  \"Reinicios\", \"Partida\", \"Poderes Usados\", \"Segundos\")"
                + " VALUES(\'" + usu.getNombre() + "\'," + usu.getPuntaje() + "," + usu.getMovimientos() + "," + usu.getReinicio() + ",\'" + usu.getPartida() + "\'," + usu.getPoderes()+","+usu.getSegundos()+");";
        System.out.println(consulta);//Prueba para verificar si los datos han sido guardados
        //satisfactoriamente

        manejador.insertarTuplas(consulta);//Inserta las tuplas en la BD.
    }//Fin del metodo terminarNivel
}//Fin de la clase Board.java

