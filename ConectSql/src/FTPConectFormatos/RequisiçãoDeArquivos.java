package FTPConectFormatos;



public abstract class RequisiçãoDeArquivos  {
	
	private String caminhoRemoto;
	
	public RequisiçãoDeArquivos(String caminho) {
		caminhoRemoto = caminho;
	}
	public RequisiçãoDeArquivos() {
		super();
	}

	public String getCaminhoRemoto() {
		return caminhoRemoto;
	}

	public void setCaminhoRemoto(String caminhoRemoto) {
		this.caminhoRemoto = caminhoRemoto;
	}

	public java.util.List<String> recebeArquivos(){
		return null;}
}
