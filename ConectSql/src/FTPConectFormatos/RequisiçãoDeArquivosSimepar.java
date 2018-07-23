package FTPConectFormatos;

import java.awt.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
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
		// criar a conecxao separada pois assim vai ser posivel chamas o cliente do ftp no caminho remoto
		String server = "ftp3.simepar.br";
		int port = 21;
		String user = "iaparestacoes2";
		String pass = "d1dea0ad729";
		List lista = new List();

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
	
				InputStream dados = ftpClient
						.retrieveFileStream(this.getCaminhoRemoto());
				try (Scanner scanner = new Scanner(dados)) {
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						lista.add(line);
					}
				}
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
				
				return (java.util.List<String>) lista;

		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
			ex.printStackTrace();
		} finally {
			// logs out and disconnects from server
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
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
}
