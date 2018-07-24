package FTPConectFormatos;

import java.awt.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class RequisiçãoDeArquivosSimepar extends RequisiçãoDeArquivos {

	public RequisiçãoDeArquivosSimepar(String caminho) {
		super(caminho);
		// TODO Auto-generated constructor stub
	}

	public RequisiçãoDeArquivosSimepar() {
		super();
	}

	public java.util.List<String> recebeArquivos() {
		List lista = new List();
		FTPClient ftpClient = this.getConecFtp();
		
		try {
			// problemas com o retrive File Stream, pois so o primeira requisicao retorna um arquivo valido.
			FTPFile[] files1 = ftpClient.listFiles(this.getCaminhoRemoto());
			for (int i = 0; i < files1.length; i++) {
				final String caminho = this.getCaminhoRemoto() + "/" + files1[i].getName();
				InputStream dados = ftpClient.retrieveFileStream(caminho);

				try (Scanner scanner = new Scanner(dados)) {
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						lista.add(line);
					}
				}
			}
			// Finaliza coneccao com o FTP
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
			return (java.util.List<String>) lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("falhar ao abrir o arquivo para scannear os dados");
			e.printStackTrace();
		}

		return null;
	}

	public static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

	@Override
	public FTPClient criaConecao() {
		String server = "ftp3.simepar.br";
		int port = 21;
		String user = "iaparestacoes2";
		String pass = "d1dea0ad729";

		FTPClient ftpClient = new FTPClient();
		

		try {
			
			ftpClient.connect(server, port);
			showServerReply(ftpClient);

			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Connect failed");
				return null;
			}

			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);

			if (!success) {
				System.out.println("Could not login to the server");
				return null;
			}

			this.setConecFtp(ftpClient);
			return ftpClient;

		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
			ex.printStackTrace();
		}

		return ftpClient;
	}

}
