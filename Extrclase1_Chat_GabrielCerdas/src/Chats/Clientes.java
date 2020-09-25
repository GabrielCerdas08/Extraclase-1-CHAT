package Chats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

public class Clientes {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Ventana1 miventana=new Ventana1();
		miventana.setVisible(true);
		miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Ventana1 extends JFrame{
	public Ventana1(){
		Toolkit myscreen=Toolkit.getDefaultToolkit();
		setSize(300,400);
		setLocation(500,300);
		setResizable(true);
		setTitle("Chat");
		Widgets milamina=new Widgets();
		add(milamina);
		Image MyIcon=myscreen.getImage("src/Chats/ImageLogo.png");
		setIconImage(MyIcon);
	}
}

class Widgets extends JPanel implements Runnable{
	public Widgets(){
		JLabel text2=new JLabel("Tu nombre:");
		add(text2);
		
		nombre=new JTextField(5);
		add(nombre);
		
		JLabel text1=new JLabel("IP:");
		add(text1);
		
		ip=new JTextField("127.0.0.1", 8);
		add(ip);
		
		JLabel text3=new JLabel("Tu mensaje:");
		add(text3);
		
		caja1=new JTextField("",20);
		add(caja1);
		
		areachat=new JTextArea(12, 20);
		add(areachat);
		
				
		
		JButton miboton1=new JButton("Enviar");
		EnviarMensaje evento=new EnviarMensaje();
		miboton1.addActionListener(evento);
		add(miboton1);
		
		Thread mihilo=new Thread(this);
		mihilo.start();
	}
	private class EnviarMensaje implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			try {
				Socket misocket =new Socket("127.0.0.1", 9999);
				empaquetado datos=new empaquetado();
				datos.setNombre(nombre.getText());
				datos.setIp(ip.getText());
				datos.setCaja1(caja1.getText());
				ObjectOutputStream paquete=new ObjectOutputStream(misocket.getOutputStream());
				paquete.writeObject(datos);
				misocket.close();
				

			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
			
		}
	}
	private JTextField caja1, nombre,ip;
	private JTextArea areachat;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ServerSocket servidor_cliente=new ServerSocket(9090);
			Socket cliente;
			empaquetado paqueteRecibido;
			while(true){
				cliente=servidor_cliente.accept();
				ObjectInputStream flujoentrada=new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido=(empaquetado) flujoentrada.readObject();
				areachat.append("\n" + paqueteRecibido.getNombre() + ": " + paqueteRecibido.getCaja1());
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}

class empaquetado implements Serializable{
	private String nombre, ip, caja1;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCaja1() {
		return caja1;
	}

	public void setCaja1(String caja1) {
		this.caja1 = caja1;
	}
	
}