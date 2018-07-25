package FTPConectFormatos;

import org.apache.commons.net.ftp.FTPClient;

public class RequisicaoDeArquivosSantaElena extends RequisicaoDeArquivos{

	
	public RequisicaoDeArquivosSantaElena(String caminho) {
		super(caminho);
		// TODO Auto-generated constructor stub
	}
	
	public RequisicaoDeArquivosSantaElena() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public FTPClient criaConecao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recebeArquivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
