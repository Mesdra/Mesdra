package Trabalho;

public class DadosPrint {

	private static DadosPrint instancia;
	int[][] listaCurvasX;
	int[][] listaCurvasy;
	int P1, P2, P3, P4;

	private DadosPrint() {
		listaCurvasX = new int[5][100];
		listaCurvasy = new int[5][100];
	}

	public static synchronized DadosPrint getInstance() {
		if (instancia == null) {
			instancia = new DadosPrint();
		}

		return instancia;
	}

	
	public void addDados(int[] x,int[] y,int p1,int p2,int p3,int p4){
		this.listaCurvasX[0] = x;
		this.listaCurvasy[0] = y;
	    P1=p1;
	    P2=p2;
	    P3=p3;
	    P4=p4;
	}

}
