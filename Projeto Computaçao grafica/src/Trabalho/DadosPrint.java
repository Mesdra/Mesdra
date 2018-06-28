package Trabalho;

public class DadosPrint {

	private static DadosPrint instancia;
	int[][] listaCurvasX;
	int[][] listaCurvasy;
	int[][] listaCurvasXprint;
	int[][] listaCurvasyprint;
	int[][] listaCurvasz;
	int[] P1, P2, P3, P4;
	int contador = 0;
	private int andarX = 0;
	private int andarY = 0;
	
	private DadosPrint() {
		listaCurvasX = new int[5][100];
		listaCurvasy = new int[5][100];
		listaCurvasz = new int[5][100];
		listaCurvasXprint = new int[5][100];
		listaCurvasyprint = new int[5][100];
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
		this.listaCurvasX[contador] = x;
		this.listaCurvasy[contador] = y;
		this.listaCurvasz[contador] = z;
		contador++;
	    P1=p1;
	    P2=p2;
	    P3=p3;
	    P4=p4;
	}
	
	public void passarObjetoporCaminho(){
		 DesenhoCurvas frame2 = DesenhoCurvas.getInstance();
		 int cont2=0;
		 int curva=0;
		for (int i = 0; i < contador*100; i++) {
			if(cont2 == 99){
				cont2 = 0;
				curva++;
			}
			
			andarX = this.listaCurvasX[curva][cont2];
			andarY = this.listaCurvasy[curva][cont2];
			frame2.repaint();
			cont2 ++;
		}
		
		
	}

	public int receberX() {
		// TODO Auto-generated method stub
		return this.andarX;
	}

	public int receberY() {
		// TODO Auto-generated method stub
		return this.andarY ;
	}

}
