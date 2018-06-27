package Trabalho;

public class DadosPrint {

	private static DadosPrint instancia;
	int[][] listaCurvasX;
	int[][] listaCurvasy;
	int[] P1, P2, P3, P4;
	int contador = 0;
	Boolean passarForma = false;
	int posicaoCorrida = 0;
	int contadorCorrida = 0;
	
	private DadosPrint() {
		listaCurvasX = new int[5][100];
		listaCurvasy = new int[5][100];
		P1 = new int[3];
		P2 = new int[3];
		P3 = new int[3];
		P4 = new int[3];
	}

	public static synchronized DadosPrint getInstance() {
		if (instancia == null) {
			instancia = new DadosPrint();
		}

		return instancia;
	}

	
	public void addDados(int[] x,int[] y,int[] p1,int[] p2,int[] p3,int[] p4){
		this.listaCurvasX[contador] = x;
		this.listaCurvasy[contador] = y;
		contador++;
	    P1=p1;
	    P2=p2;
	    P3=p3;
	    P4=p4;
	}
	
	public void passarObjetoporCaminho(){
		passarForma = true;
		 DesenhoCurvas frame2 = DesenhoCurvas.getInstance();
		for (int i = 0; i < contador*100; i++) {
			frame2.repaint();
			posicaoCorrida++;
			
		}
		passarForma = false;
		posicaoCorrida = 0;
		contadorCorrida = 0;
		
	}

}
