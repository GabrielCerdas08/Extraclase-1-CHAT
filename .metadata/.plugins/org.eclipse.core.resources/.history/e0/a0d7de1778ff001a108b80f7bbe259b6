package Chats;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Ventana2 miventana=new Ventana2();
		miventana.setVisible(true);
		miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Ventana2 extends JFrame implements Runnable{
	public Ventana2(){
		Toolkit myscreen=Toolkit.getDefaultToolkit();
		setSize(500,300);
		setLocation(500,300);
		JPanel miwidget=new JPanel();
		miwidget.setLayout(new BorderLayout());
		areatexto = new JTextArea();
		miwidget.add(areatexto, BorderLayout.CENTER);
		add(miwidget);
		setTitle("Chat.Server");
		Image MyIcon=myscreen.getImage("src/Chats/ImageLogo.png");
		setIconImage(MyIcon);
		setVisible(true);
		Thread hilo=new Thread(this);
		hilo.start();
		
	}
	private JTextArea areatexto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidor=new ServerSocket(9999);
			String nombre, ip, mensaje;
			empaquetado paquete_recibido;
			while(true){
				Socket misocket=servidor.accept();
				ObjectInputStream paquete_datos=new ObjectInputStream(misocket.getInputStream());
				paquete_recibido =(empaquetado) paquete_datos.readObject();
				nombre = paquete_recibido.getNombre();
				ip = paquete_recibido.getIp();
				mensaje = paquete_recibido.getCaja1();
				areatexto.append("\n" + nombre + ": " + mensaje+ "  para: " + ip);
				Socket enviaDestinatario=new Socket(ip,9090);
				
				ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
				
				paqueteReenvio.writeObject(paquete_recibido);
				paqueteReenvio.close();
				
				enviaDestinatario.close();
				
				misocket.close();
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
