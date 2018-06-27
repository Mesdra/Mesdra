package Trabalho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DesenhoCurvas extends JFrame {

	private JPanel contentPane;
	private static DesenhoCurvas instancia;

	private DesenhoCurvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public static synchronized DesenhoCurvas getInstance() {
		if (instancia == null) {
			instancia = new DesenhoCurvas();
		}
		return instancia;
	}

	/**
	 * Launch the application.
	 */

	public void paint(Graphics g) {
		
		DadosPrint dadosPrint = DadosPrint.getInstance();
		
		g.drawPolyline(dadosPrint.listaCurvasX[0], dadosPrint.listaCurvasy[0], 100);
		
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
		g.setColor(Color.gray);
		g.drawPolyline(xlinha, ylinha, 500);
		g.drawPolyline(xcol, ycol, 500);
	}
		


}
