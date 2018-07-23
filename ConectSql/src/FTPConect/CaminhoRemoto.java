package FTPConect;

import java.io.IOException;
import java.util.List;

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

	public List<String> buscarNovaLeitura( String estacao){
		if(estacao.equals("simepar")) {
		RequisiçãoDeArquivos dados = new RequisiçãoDeArquivosSimepar();	
		//Lists files and directories
		FTPClient ftpClient = new FTPClient();// nao deve ficar assim , este dado deve vir da requisi;'ao dos arquivos
		try {
			FTPFile[] files1 = ftpClient.listDirectories();
			String caminho = "/"+files1[files1.length];
			files1 = ftpClient.listFiles(caminho);
			caminho  = caminho+"/"+ files1[files1.length];
			RequisiçãoDeArquivos dados1 = new RequisiçãoDeArquivosSimepar(caminho);
			return dados1.recebeArquivos();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return null;
	}

	
	
	
}
