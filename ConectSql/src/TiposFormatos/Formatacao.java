package TiposFormatos;


public abstract class Formatacao {
	private String[][] dados;
	private int quantDados;
	
	public abstract void GerarFormato();

	public String[][] getDados() {
		return dados;
	}

	public void setDados(String[][] dados) {
		this.dados = dados;
	}

	public int getQuantDados() {
		return quantDados;
	}

	public void setQuantDados(int quantDados) {
		this.quantDados = quantDados;
	}
	
	
}
