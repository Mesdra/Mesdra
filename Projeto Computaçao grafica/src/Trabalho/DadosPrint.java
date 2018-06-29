package Trabalho;

public class DadosPrint{

	private static DadosPrint instancia;
	int[][] listaCurvasX;
	int[][] listaCurvasy;
	int[][] listaCurvasXprint;
	int[][] listaCurvasyprint;
	int[][] listaCurvasz;
	int[][] listaCurvasXrotacionados;
	int[][] listaCurvasyrotacionados;
	int[] P1, P2, P3, P4;
	int contador = 0;
	int andarX = 0;
	int andarY = 0;
	
	private DadosPrint() {
		listaCurvasX = new int[5][100];
		listaCurvasy = new int[5][100];
		listaCurvasz = new int[5][100];
		listaCurvasXprint = new int[5][100];
		listaCurvasyprint = new int[5][100];
		listaCurvasXrotacionados = new int[5][100];
		listaCurvasyrotacionados = new int[5][100];
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

	
	public void addDados(int[] x,int[] y,int[] z,int[] p1,int[] p2,int[] p3,int[] p4){
		if(contador == 1) {
			System.out.println("c");
		}
		this.listaCurvasX[contador] = x;
		this.listaCurvasy[contador] = y;
		this.listaCurvasz[contador] = z;
		contador++;
	    P1=p1;
	    P2=p2;
	    P3=p3;
	    P4=p4;
	}
	
	

	public int receberX() {
		synchronized (this) {
			return this.andarX;
		}
		
	}

	public int receberY() {
		synchronized (this) {
			return this.andarY;
		}
	}

	public void andarX(int i) {
		synchronized (this) {
			andarX = i;
		}
		
	}

	public void andarY(int i) {
		synchronized (this) {
			andarY = i;
		}
	}

}
