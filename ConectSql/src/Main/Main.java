package Main;

import java.awt.List;
import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import Arquivos.LerArq;
import FTPConect.CaminhoRemoto;
import FTPConectFormatos.RequisiçãoDeArquivos;
import FTPConectFormatos.RequisiçãoDeArquivosSimepar;
import SqlConect.ConeccaoBancodeDados;
import TiposFormatos.Formatacao;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		// teste com a requisicao de dados FTP.
		CaminhoRemoto caminho = new CaminhoRemoto();
		// cria instancia de RequisicaoDeArquivos de acordo com a estacao desejada.
		RequisiçãoDeArquivos leiturasDosArquivos = caminho.buscarNovaLeitura("simepar");
		List lista = (List) leiturasDosArquivos.recebeArquivos();
		System.out.println(lista.toString());
		// funcionamento do sistema de insercao dos dados utilizadndo um reposit[orio
		// local
		/*
		 * final File folder = new File("/home/vinicius/Documentos/leituras");
		 * 
		 * for (final File fileEntry : folder.listFiles()) {
		 * 
		 * 
		 * // recebe a lista de arquivos dentro do diretorio. LerArq leituras = new
		 * LerArq();
		 * 
		 * // separa todas as leituras do arquivo atual em linhas.
		 * leituras.lerDadosArquivos(fileEntry.getName());
		 * 
		 * // formata os dados para a insercao no banco. Formatacao formato =
		 * leituras.tratamentoDaMensagem(); formato.GerarFormato();
		 * 
		 * // conecta e envia os dados para o banco. ConeccaoBancodeDados banco = new
		 * ConeccaoBancodeDados();
		 * 
		 * banco.conecPostgre(formato.getDados(),formato.getQuantDados());
		 * 
		 * }
		 */

	}

}
