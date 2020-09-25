package Chats;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		setSize(500,300);
		setLocation(500,300);
		setResizable(true);
		setTitle("Chat");
		Widgets milamina=new Widgets();
		add(milamina);
		Image MyIcon=myscreen.getImage("src/Chats/ImageLogo.png");
		setIconImage(MyIcon);
	}
}

class Widgets extends JPanel{
	public Widgets(){
		JLabel text1=new JLabel("Tu mensaje:");
		add(text1);
		
		JTextField campo1=new JTextField("Text...",30);
		add(campo1);
		
		JButton mybutton1=new JButton("Enviar");
		add(mybutton1);
	}
}