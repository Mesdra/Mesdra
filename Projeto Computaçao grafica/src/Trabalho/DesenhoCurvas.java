package Trabalho;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DesenhoCurvas extends JFrame{

	private JPanel contentPane;
	private static DesenhoCurvas instancia;
	private Boolean validadorDePassagem = false;
	public final static int ONE_SECOND = 1000;
	private final JButton btnNewButton = new JButton("New button");
	private DesenhoCurvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
	

		JPanel panel = new JPanel() {

			public synchronized void paint(Graphics g) {

				DadosPrint dadosPrint = DadosPrint.getInstance();

				for (int i = 0; i < dadosPrint.contador; i++) {
					g.drawPolyline(dadosPrint.listaCurvasXprint[i], dadosPrint.listaCurvasyprint[i], 100);
				}

				int[] xlinha = new int[900];
				int[] ylinha = new int[900];

				int[] xcol = new int[900];
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

				if (validadorDePassagem) {
					g.setColor(Color.blue);
					DadosPrint dados = DadosPrint.getInstance();
					g.fillOval(dados.receberX(), dados.receberY(), 20, 20);
					g.setColor(Color.BLACK);
					
				}
			}

		};
		contentPane.add(panel, BorderLayout.CENTER);
	
	}
	

	public void pintarObjetografico() {
		
		validadorDePassagem = true;
		
		Thread Tobj = new Thread(new Runnable() {
			@Override
			public void run() {
				DadosPrint dados = DadosPrint.getInstance();
				DesenhoCurvas desenhoCurvas = DesenhoCurvas.getInstance();
				for (int i = 0; i < dados.contador; i++) {
					for (int j = 0; j < 100; j++) {

						dados.andarX(dados.listaCurvasXprint[i][j]);
						dados.andarY(dados.listaCurvasyprint[i][j]-10);
						System.out.println("SERIAL print");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						desenhoCurvas.repaint();
						
					}

				}
				desenhoCurvas.validadorDePassagem = false;
				desenhoCurvas.repaint();
				
			}
		});
		
		Runnable runn = new Runnable() {
			
			@Override
			public void run() {
				DesenhoCurvas visor = DesenhoCurvas.getInstance();
				visor.repaint();
			}
		};
		
		
		Tobj.start();
		

			
		
		
		
		//try {
	//		Tobj.join();
		//} catch (InterruptedException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		
		
		
		//validadorDePassagem = false;
		
		

	}

	public static synchronized DesenhoCurvas getInstance() {
		if (instancia == null) {
			instancia = new DesenhoCurvas();
		}
		return instancia;
	}

	public void atualizarTela() {
		geraRoracao();
		ajusteDeImpreção(200, 600);
		this.repaint();

	}

	/**
	 * Launch the application.
	 */

	private void ajusteDeImpreção(int meioX, int meioY) {
		DadosPrint dadosPrint = DadosPrint.getInstance();

		if (dadosPrint.contador == 2) {
			System.out.println("c");
		}

		for (int i = 0; i < dadosPrint.contador; i++) {
			for (int j = 0; j < 100; j++) {

				dadosPrint.listaCurvasXprint[i][j] = dadosPrint.listaCurvasXrotacionados[i][j] + meioX;
				dadosPrint.listaCurvasyprint[i][j] = -dadosPrint.listaCurvasyrotacionados[i][j] + meioY;

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

		if (dadosPrint.contador == 2) {
			System.out.println("c");
		}

		for (int i = 0; i < dadosPrint.contador; i++) {
			int[] resultadosx = new int[100];
			int[] resultadosy = new int[100];
			for (int j = 0; j < 100; j++) {
				resultadosx[j] = (int) ((yc * dadosPrint.listaCurvasX[i][j] + ys * dadosPrint.listaCurvasz[i][j]));
				resultadosy[j] = (int) (((ys * xs) * dadosPrint.listaCurvasX[i][j])
						+ (xc * dadosPrint.listaCurvasy[i][j]) + ((-yc * xs) * dadosPrint.listaCurvasz[i][j]));

			}
			dadosPrint.listaCurvasXrotacionados[i] = resultadosx;
			dadosPrint.listaCurvasyrotacionados[i] = resultadosy;

		}
	}

}
