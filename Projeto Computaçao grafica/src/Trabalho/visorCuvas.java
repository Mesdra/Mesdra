package Trabalho;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class visorCuvas extends JFrame {

	public class Dados {
		public Dados(int[] xM, int[] yM, int[] zM) {
			this.xM = xM;
			this.yM = yM;
			this.zM = zM;
		}

		int[] xM = new int[100];
		int[] yM = new int[100];
		int[] zM = new int[100];

	}

	private JPanel contentPane;
	private JTextField varX1;
	private JTextField varY1;
	private JTextField varZ1;
	private JTextField varZ2;
	private JTextField varY2;
	private JTextField varX2;
	private JTextField varX3;
	private JTextField varY3;
	private JTextField varZ3;
	private int[] xT = new int[100];
	private int[] yT = new int[100];
	private int[] zT = new int[100];
	private int X0, Y0, Z0 = 0;
	private int[] pontoControle1 = new int[3];
	private int[] pontoControle2 = new int[3];
	private int[] pontoControle3 = new int[3];
	private int[] pontoControle4 = new int[3];
	static private int grausdex = 0;
	static private int grausdey = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visorCuvas frame = new visorCuvas();
					frame.setVisible(true);
					DesenhoCurvas frame2 = DesenhoCurvas.getInstance();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void geraCurva() {
		calculaBezie(X0, Y0, Z0, Integer.parseInt(varX1.getText()), Integer.parseInt(varY1.getText()),
				Integer.parseInt(varZ1.getText()), Integer.parseInt(varX2.getText()), Integer.parseInt(varY2.getText()),
				Integer.parseInt(varZ2.getText()), Integer.parseInt(varX3.getText()), Integer.parseInt(varY3.getText()),
				Integer.parseInt(varZ3.getText()));
		
		DadosPrint listaDados = DadosPrint.getInstance();
		listaDados.addDados(xT, yT, zT, this.pontoControle1, this.pontoControle2, this.pontoControle3,
				this.pontoControle4);
		DesenhoCurvas frame2 = DesenhoCurvas.getInstance();
		frame2.atualizarTela();
		

	}

	

	static public int getGrausdex() {
		return grausdex;
	}



	static public int getGrausdey() {
		return grausdey;
	}

	

	public void calculaBezie(int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3, int x4, int y4,
			int z4) {
		float t = 0;
		int[] dados1 = { x1, y1, z1 };
		int[] dados2 = { x2, y2, z2 };
		int[] dados3 = { x3, y3, z3 };
		int[] dados4 = { x4, y4, z4 };
		pontoControle1 = dados1;
		pontoControle2 = dados2;
		pontoControle3 = dados3;
		pontoControle4 = dados4;

		for (int i = 0; i < 100; i++) {

			float teste = (float) Math.pow((1 - t), 3);
			int Tx = (int) (somaExpoente((1 - t), 3) * x1 + (3 * t) * somaExpoente((1 - t), 2) * x2
					+ somaExpoente((3 * t), 2) * (1 - t) * x3 + somaExpoente(t, 3) * x4);
			int Ty = (int) (somaExpoente((1 - t), 3) * y1 + (3 * t) * somaExpoente((1 - t), 2) * y2
					+ somaExpoente((3 * t), 2) * (1 - t) * y3 + somaExpoente(t, 3) * y4);
			int Tz = (int) (somaExpoente((1 - t), 3) * z1 + (3 * t) * somaExpoente((1 - t), 2) * z2
					+ somaExpoente((3 * t), 2) * (1 - t) * z3 + somaExpoente(t, 3) * z4);
			t += 0.01;
			this.xT[i] = Tx;
			this.yT[i] = Ty;
			this.zT[i] = Tz;
		}
		X0 = xT[99];
		Y0 = yT[99];
		Z0 = zT[99];

	}

	public float somaExpoente(float valor, int expoente) {
		float soma = valor;
		for (int i = 0; i < expoente; i++) {
			soma = soma * valor;
		}
		return soma;
	}

	

	/**
	 * Create the frame.
	 */
	public visorCuvas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		varX1 = new JTextField();
		varX1.setColumns(10);
		varX1.setBounds(28, 56, 41, 20);
		contentPane.add(varX1);

		JTextPane textPane = new JTextPane();
		textPane.setText("X1");
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(42, 35, 18, 20);
		contentPane.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Y1");
		textPane_1.setEnabled(false);
		textPane_1.setEditable(false);
		textPane_1.setBounds(91, 35, 18, 20);
		contentPane.add(textPane_1);

		varY1 = new JTextField();
		varY1.setColumns(10);
		varY1.setBounds(79, 56, 41, 20);
		contentPane.add(varY1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Z1");
		textPane_2.setEnabled(false);
		textPane_2.setEditable(false);
		textPane_2.setBounds(143, 35, 18, 20);
		contentPane.add(textPane_2);

		varZ1 = new JTextField();
		varZ1.setColumns(10);
		varZ1.setBounds(132, 56, 41, 20);
		contentPane.add(varZ1);

		JButton gerarCurva = new JButton("Gerar Curva ");
		gerarCurva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geraCurva();
			}

		});
		gerarCurva.setBounds(183, 55, 115, 23);
		contentPane.add(gerarCurva);

		JButton gerarCaminho = new JButton("Gerar Caminho");
		gerarCaminho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DadosPrint dados = DadosPrint.getInstance();
				dados.passarObjetoporCaminho();
			}
		});
		gerarCaminho.setBounds(183, 111, 115, 23);
		contentPane.add(gerarCaminho);

		varZ2 = new JTextField();
		varZ2.setColumns(10);
		varZ2.setBounds(132, 112, 41, 20);
		contentPane.add(varZ2);

		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Z3");
		textPane_3.setEnabled(false);
		textPane_3.setEditable(false);
		textPane_3.setBounds(143, 91, 18, 20);
		contentPane.add(textPane_3);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("Y2");
		textPane_4.setEnabled(false);
		textPane_4.setEditable(false);
		textPane_4.setBounds(91, 91, 18, 20);
		contentPane.add(textPane_4);

		varY2 = new JTextField();
		varY2.setColumns(10);
		varY2.setBounds(79, 112, 41, 20);
		contentPane.add(varY2);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("X2");
		textPane_5.setEnabled(false);
		textPane_5.setEditable(false);
		textPane_5.setBounds(42, 91, 18, 20);
		contentPane.add(textPane_5);

		varX2 = new JTextField();
		varX2.setColumns(10);
		varX2.setBounds(28, 112, 41, 20);
		contentPane.add(varX2);

		varX3 = new JTextField();
		varX3.setColumns(10);
		varX3.setBounds(28, 164, 41, 20);
		contentPane.add(varX3);

		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("X3");
		textPane_6.setEnabled(false);
		textPane_6.setEditable(false);
		textPane_6.setBounds(42, 143, 18, 20);
		contentPane.add(textPane_6);

		varY3 = new JTextField();
		varY3.setColumns(10);
		varY3.setBounds(79, 164, 41, 20);
		contentPane.add(varY3);

		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("Y3");
		textPane_7.setEnabled(false);
		textPane_7.setEditable(false);
		textPane_7.setBounds(91, 143, 18, 20);
		contentPane.add(textPane_7);

		varZ3 = new JTextField();
		varZ3.setColumns(10);
		varZ3.setBounds(132, 164, 41, 20);
		contentPane.add(varZ3);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("Z3");
		textPane_8.setEnabled(false);
		textPane_8.setEditable(false);
		textPane_8.setBounds(143, 143, 18, 20);
		contentPane.add(textPane_8);

		JButton cima = new JButton("^\r\n");
		cima.setBounds(79, 195, 41, 33);
		contentPane.add(cima);

		JButton esquerda = new JButton("<\r\n");
		esquerda.setBounds(28, 227, 41, 33);
		contentPane.add(esquerda);

		JButton Baixo = new JButton(" \r\n");
		Baixo.setBounds(79, 259, 41, 33);
		contentPane.add(Baixo);

		JButton Direita = new JButton(">\r\n");
		Direita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grausdex += 10;
				DesenhoCurvas frame2 = DesenhoCurvas.getInstance();
				frame2.atualizarTela();
				
			}
		});
		Direita.setBounds(130, 227, 41, 33);
		contentPane.add(Direita);

	}

}
