package FTPConectFormatos;

import org.apache.commons.net.ftp.FTPClient;

public abstract class RequisicaoDeArquivos {
	
	private String caminhoRemoto;
	private FTPClient conecFtp;
	
	public FTPClient getConecFtp() {
		return conecFtp;
	}
	public void setConecFtp(FTPClient conecFtp) {
		this.conecFtp = conecFtp;
	}
	public RequisicaoDeArquivos(String caminho) {
		caminhoRemoto = caminho;
	}
	public RequisicaoDeArquivos() {
		super();
	}

	public String getCaminhoRemoto() {
		return caminhoRemoto;
	}
	// cria um cliente utilizado na coneccao com o ftp
	public abstract FTPClient criaConecao();

	public void setCaminhoRemoto(String caminhoRemoto) {
		this.caminhoRemoto = caminhoRemoto;
	}
	//baixa os arquivos remotos (FTP) e colocatodos os dados em uma lista 
	public abstract String recebeArquivos();
}
