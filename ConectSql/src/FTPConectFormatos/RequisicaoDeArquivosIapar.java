package FTPConectFormatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class RequisicaoDeArquivosIapar extends RequisicaoDeArquivos {

	public RequisicaoDeArquivosIapar(String caminho) {
		super(caminho);
		// TODO Auto-generated constructor stub
	}

	public RequisicaoDeArquivosIapar() {
		super();
	}

	public String recebeArquivos() {

		FTPClient ftpClient = this.getConecFtp();

		int reply = ftpClient.getReplyCode();
		// FTPReply stores a set of constants for FTP reply codes.
		if (!FTPReply.isPositiveCompletion(reply)) {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ftpClient.enterLocalPassiveMode();
		String caminhoDiretorio = "erro ao criar caminho"; 
		try {
			ftpClient.changeWorkingDirectory(this.getCaminhoRemoto());
			FTPFile[] files1 = ftpClient.listFiles();
			caminhoDiretorio = "/home/vinicius/Documentos/Teste/" + files1[0].getName();
			Boolean aqr = new File(caminhoDiretorio).mkdir();
			if (aqr) {
				for (int i = 0; i < files1.length; i++) {
					OutputStream output;
					output = new FileOutputStream(caminhoDiretorio+ "/"+ files1[i].getName());
					ftpClient.retrieveFile(files1[i].getName(), output);

				}
			} else {
				System.out.println("erro ao criar arquivo");
			}
			// Finaliza coneccao com o FTP
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}

			return caminhoDiretorio;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("falhar ao abrir o arquivo para scannear os dados");
			e.printStackTrace();
		}

		return caminhoDiretorio ;
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
