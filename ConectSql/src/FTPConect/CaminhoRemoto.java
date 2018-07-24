package FTPConect;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import FTPConectFormatos.RequisiçãoDeArquivos;
import FTPConectFormatos.RequisiçãoDeArquivosSimepar;

public class CaminhoRemoto {
	private String[] ultimasLeituras;

	public CaminhoRemoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] getUltimasLeituras() {
		return ultimasLeituras;
	}

	public void setUltimasLeituras(String[] ultimasLeituras) {
		this.ultimasLeituras = ultimasLeituras;
	}

// cria o cliente e o caminho utilizado para realizar as leituras corretas, 
//	utilizando o parametro para identificar qual a estacao que esta sendo trabalhada
	public RequisiçãoDeArquivos buscarNovaLeitura(String estacao) {
		// coneccao com FTP simepar
		if (estacao.equals("simepar")) {
			RequisiçãoDeArquivos dados = new RequisiçãoDeArquivosSimepar();
			
			FTPClient ftpClient = dados.criaConecao();

			try {
				FTPFile[] files1 = ftpClient.listFiles();
				String caminho = "/" + files1[files1.length - 2].getName();
				files1 = ftpClient.listFiles(caminho);
				caminho = caminho + "/" + files1[files1.length - 1].getName();
				files1 = ftpClient.listFiles(caminho);
				caminho = caminho + "/" + files1[files1.length - 1].getName();
				files1 = ftpClient.listFiles(caminho);
				caminho = caminho + "/" + files1[files1.length - 1].getName();
				dados.setCaminhoRemoto(caminho);
				return dados;

			} catch (IOException e) {
				System.out.println("erro ao criar caminho simepar");
				e.printStackTrace();
			}

		}
		// ha fazer
		if(estacao.equals("SLV3")){
			
		}
		return null;
	}

}
