package Testes;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testesJanela extends JFrame {

	int[] x = new int[100];
	int[] y = new int[100];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testesJanela frame = new testesJanela();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void paint(Graphics g) {
		
		calculaBezie(0,0,0,-50,-50,0,0,80,0,200,200,0);
		
		
		//g.drawPolyline(x, y, 100);
		
		int[] xlinha= new int[500];
		int[] ylinha = new int[500];
		
		int[] xcol= new int[500];
		int[] ycol = new int[500];
		
		for (int i = 0; i < 500; i++) {
			xlinha[i] = 200;
			ylinha[i] = i;
			xcol[i] = i;
			ycol[i] = 400;	
		}
		//g.setColor(Color.gray);
		//g.drawPolyline(xlinha, ylinha, 500);
		//g.drawPolyline(xcol, ycol, 500);
		
	}
	
	public void calculaBezie(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int x4,int y4,int z4) {
		float t = 0;
	
		for (int i = 0; i < 100; i++) {
			
			float teste = (float) Math.pow((1-t),3);
			int Tx = (int) (somaExpoente((1-t),3)*x1 + (3*t)*somaExpoente((1-t),2)*x2+somaExpoente((3*t), 2)*(1-t)*x3+somaExpoente(t,3)*x4);
			int Ty = (int) (somaExpoente((1-t),3)*y1 + (3*t)*somaExpoente((1-t),2)*y2+somaExpoente((3*t), 2)*(1-t)*y3+somaExpoente(t,3)*y4);
			int Tz = (int) (somaExpoente((1-t),3)*z1 + (3*t)*somaExpoente((1-t),2)*z2+somaExpoente((3*t), 2)*(1-t)*z3+somaExpoente(t,3)*z4);
			t += 0.01;
			this.x[i] = Tx+200;
			this.y[i] = -Ty+400;
		}
		

	}
	
	public float somaExpoente(float valor,int expoente) {
		float soma = valor;
		for (int i = 0; i < expoente; i++) {
			soma = soma*valor;
		}
		return soma; 
	}
	
	public testesJanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,900);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	
	}
}
