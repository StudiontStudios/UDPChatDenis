/*
Consulta el enlace donde aparece un ejemplo de comunicación
bidireccional en UDP, prueba a hacer un pequeño chat, con las
características siguientes:
● El programa servidor, tendrá un hilo que se encargará únicamente
de hacer receive y cuando reciba algún mensaje, mostrará por
pantalla el mensaje.
● El programa servidor, en su programa padre realizará send, a
partir de los mensajes leídos al usuario con Scanner.
● El programa cliente, tendrá igual que el servidor su propio hilo
que hará los receive y mostrará por pantalla lo que reciba.
● El programa cliente, del mismo modo en su programa padre
realizará send, a partir de los mensajes leídos al usuario con
Scanner.
Crea un IGU con Swing, con un JList (para mostrar los mensajes del
usuario1 y el usuario2), un JButton (para enviar lo que se ponga en
JTextField) y JTextField (donde el usuario pondrá el mensaje)
 */
package PSP_T2_UDP_IP.Chat;

import java.awt.List;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Vespertino
 */
public class Hilo implements Runnable {

    private List r;
    private int puerto;

    public Hilo(List r, int puerto) {
        this.r = r;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        final int MAX_LEN = 100;
        try {
            DatagramSocket mySocket = new DatagramSocket(puerto);
            //INSTANCIAMOS
            while (true) {
                byte[] buffer = new byte[MAX_LEN];
                DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
                mySocket.receive(datagram);
                String message = new String(buffer);
                r.add("\n" + message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
