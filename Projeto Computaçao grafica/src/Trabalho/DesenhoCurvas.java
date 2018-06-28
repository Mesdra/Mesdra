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
		
		for (int i = 0; i < dadosPrint.contador; i++) {
			g.drawPolyline(dadosPrint.listaCurvasXprint[i], dadosPrint.listaCurvasyprint[i], 100);
		}
		g.setColor(Color.BLUE);
		//g.drawLine(dadosPrint.P1[0]-2,dadosPrint.P1[1]-2 , dadosPrint.P1[0]+2, dadosPrint.P1[1]+2);
		//g.drawLine(dadosPrint.P2[0]-2,dadosPrint.P2[1]-2 , dadosPrint.P2[0]+2, dadosPrint.P2[1]+2);
		//g.drawLine(dadosPrint.P3[0]-2,dadosPrint.P3[1]-2 , dadosPrint.P3[0]+2, dadosPrint.P3[1]+2);
		//g.drawLine(dadosPrint.P4[0]-2,dadosPrint.P4[1]-2 , dadosPrint.P4[0]+2, dadosPrint.P4[1]+2);
		

		g.fillOval(dadosPrint.receberX(),dadosPrint.receberY(),50,50);
		
		int[] xlinha= new int[900];
		int[] ylinha = new int[900];
		
		int[] xcol= new int[900];
		int[] ycol = new int[900];
		
		for (int i = 0; i < 900; i++) {
			xlinha[i] = 200;
			ylinha[i] = i;
			xcol[i] = i;
			ycol[i] = 600;	
		}
		g.setColor(Color.gray);
		g.drawPolyline(xlinha, ylinha, 900);
		g.drawPolyline(xcol, ycol, 900);
	}

	public void atualizarTela() {
		geraRoracao();
		ajusteDeImpreção(200, 400);
		this.repaint();
		
	}
	private void ajusteDeImpreção(int meioX, int meioY) {
		DadosPrint dadosPrint = DadosPrint.getInstance();

		for (int i = 0; i < dadosPrint.contador; i++) {
			for (int j = 0; j < 100; j++) {
				
				dadosPrint.listaCurvasXprint[i][j] = dadosPrint.listaCurvasX[i][j]+meioX;
				dadosPrint.listaCurvasyprint[i][j] = -dadosPrint.listaCurvasy[i][j]+meioY;
			
			}

		}


		
	}
	
	private void geraRoracao() {
		int x = visorCuvas.getGrausdex();
		int y = visorCuvas.getGrausdey();
		
		float ys = (float) Math.sin(Math.PI / 180 * y);

		float yc = (float) Math.cos(Math.PI / 180 * y);

		float xs = (float) Math.sin(Math.PI / 180 * x);

		float xc = (float) Math.cos(Math.PI / 180 * x);

		DadosPrint dadosPrint = DadosPrint.getInstance();
		int[] resultadosx = new int[100 * dadosPrint.contador];
		int[] resultadosy = new int[100 * dadosPrint.contador];

		for (int i = 0; i < dadosPrint.contador; i++) {

			for (int j = 0; j < 100; j++) {
				resultadosx[j] = (int) ((yc * dadosPrint.listaCurvasX[i][j] + yc * dadosPrint.listaCurvasz[i][j]));
				resultadosy[j] = (int) (((ys * xs) * dadosPrint.listaCurvasX[i][j])
						+ (xc * dadosPrint.listaCurvasy[i][j]) + ((-yc * xs) * dadosPrint.listaCurvasz[i][j]));

			}
			dadosPrint.listaCurvasX[i]=resultadosx;
			dadosPrint.listaCurvasy[i]=resultadosy;

		}
	}
	
	

	
		


}
