package Testes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class testesJanela extends JFrame {

	int[] x = new int[500];
	int[] y = new int[500];

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
		
		calculaBezie(0,0,0,50,50,0,30,30,0,100,100,0);
		
		
		g.drawPolygon(x, y, 500);
	}
	
	public void calculaBezie(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3,int x4,int y4,int z4) {
		float t = 0;
	
		for (int i = 0; i < 100; i++) {
			t += 0.1;
			
			int Tx = (int) (Math.pow((1-t),3)*x1 + 3*t*Math.pow((1-t),2)*x2+Math.pow((3*t), 2)*(1-t)*x3+Math.pow(t,3)*x4);
			int Ty = (int) (Math.pow((1-t),3)*y1 + 3*t*Math.pow((1-t),2)*y2+Math.pow((3*t), 2)*(1-t)*y3+Math.pow(t,3)*y4);
			int Tz = (int) (Math.pow((1-t),3)*z1 + 3*t*Math.pow((1-t),2)*z2+Math.pow((3*t), 2)*(1-t)*z3+Math.pow(t,3)*z4);
		
			this.x[i] = Tx;
			this.y[i] = Ty;
		}
		

	}
	
	public testesJanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,900);
		setLocationRelativeTo(null);
	
	}

}
