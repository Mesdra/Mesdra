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
		
		calculaBezie(0, 50, 80, 0, 50, 30, 0, 50, 50);
		
		
		g.drawPolygon(x, y, 500);
	}
	
	public void calculaBezie(int x1,int x2,int x3,int y1,int y2,int y3,int z1,int z2,int z3) {
		float t = 0;
	
		for (int i = 0; i < 100; i++) {
			t += 0.1;
			
			int Tx = (int) (Math.pow((1-t),2)*x1 + 2*t*(1-t)*x2+2*t*x3);
			int Ty = (int) (Math.pow((1-t),2)*y1 + 2*t*(1-t)*y2+2*t*y3);
			int Tz = (int) (Math.pow((1-t),2)*z1 + 2*t*(1-t)*z2+2*t*z3);
		
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
