package TiposFormatos;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosIapar extends Formatacao {

	public DadosIapar(int quantDados, String[][] dadosSeparados) {
		this.setDados(dadosSeparados);
		this.setQuantDados(quantDados);
	}

	@Override
	public void GerarFormato() {
		String[][] form = new String[getQuantDados()-4][6];
		String dados[][] = getDados().clone();
		int contador = 0;
		for (int i = 4; i < getQuantDados(); i++) {
			form[contador][0] =  Integer.toString(dados[0][0].hashCode());
			form[contador][1] = dados[0][0];
			form[contador][2] = dados[0][1];
			String datahora[] = dados[i][0].split(" ");
			form[contador][3] = datahora[0];
			form[contador][4] = datahora[1];

			try {
				JSONObject jsonObj = new JSONObject();

				jsonObj.put("RECORD", dados[i][1]);
				jsonObj.put("VENTOVEL10M", dados[i][2]);
				jsonObj.put("VENTODIR10M", dados[i][3]);
				jsonObj.put("DISTVENTO", dados[i][4]);
				jsonObj.put("RAJ10M", dados[i][5]);
				jsonObj.put("RAJDIR10M", dados[i][6]);
				jsonObj.put("VENTOVEL2M", dados[i][7]);
				jsonObj.put("PREC", dados[i][8]);
				jsonObj.put("PRECACUM", dados[i][9]);
				jsonObj.put("PRECACUMTOT", dados[i][10]);
				jsonObj.put("RADSOLGLBD", dados[i][11]);
				jsonObj.put("ETSZ", dados[i][12]);
				jsonObj.put("RSo", dados[i][13]);
				jsonObj.put("RADPARMED", dados[i][14]);
				jsonObj.put("PRESSAO", dados[i][15]);
				jsonObj.put("TEMPMEDAR2M", dados[i][16]);
				jsonObj.put("TEMPMAXAR2M", dados[i][17]);
				jsonObj.put("TEMPMINAR2M", dados[i][18]);
				jsonObj.put("UMIDRELMED2M", dados[i][19]);
				jsonObj.put("UMIDRELMAX2M", dados[i][20]);
				jsonObj.put("UMIDRELMIX2M", dados[i][21]);
				jsonObj.put("TEMPMEDAR1M", dados[i][22]);
				jsonObj.put("TEMPMAXAR1M", dados[i][23]);
				jsonObj.put("TEMPMINAR2M", dados[i][24]);
				jsonObj.put("UMIDRELMED1M", dados[i][25]);
				jsonObj.put("UMIDRELMAX1M", dados[i][26]);
				jsonObj.put("UMIDRELMIN1M", dados[i][27]);
				jsonObj.put("TEMPMEDRELVA", dados[i][28]);
				jsonObj.put("TEMPMINRELVA", dados[i][29]);
				jsonObj.put("MOLFOLIAR1M_SEC", dados[i][30]);
				jsonObj.put("MOLFOLIAR_CON", dados[i][31]);
				jsonObj.put("MOLFOLIAR2M_SEC", dados[i][32]);
				jsonObj.put("MOLFOLIAR2M_CON", dados[i][33]);
				jsonObj.put("MOLFOLIAR2M_MOL", dados[i][34]);
				jsonObj.put("UMIDSOLOCM20CM", dados[i][35]);
				jsonObj.put("UMIDSOLONU20CM", dados[i][36]);
				jsonObj.put("TEMPSOLOGRA2CM", dados[i][37]);
				jsonObj.put("TEMPSOLOGRA5M", dados[i][38]);
				jsonObj.put("TEMPSOLOGRA10CM", dados[i][39]);
				jsonObj.put("TEMPSOLOGRA20CM", dados[i][40]);
				jsonObj.put("TEMPSOLOGRA40CM", dados[i][41]);
				jsonObj.put("TEMPSOLOGRA100CM", dados[i][42]);
				jsonObj.put("TEMPSOLONU2CM", dados[i][43]);
				jsonObj.put("TEMPSOLONU5CM", dados[i][44]);
				jsonObj.put("TEMPSOLONU10CM", dados[i][45]);
				jsonObj.put("TEMPSOLONU20CM", dados[i][46]);
				jsonObj.put("TEMPSOLONU40CM", dados[i][47]);
				jsonObj.put("TEMPSOLONU100CM", dados[i][48]);
				jsonObj.put("TEMPSOLOCM2CM", dados[i][49]);
				jsonObj.put("TEMPSOLOCM5CM", dados[i][50]);
				jsonObj.put("TEMPSOLOCM10CM", dados[i][51]);
				jsonObj.put("TEMPSOLOCM20CM", dados[i][52]);
				jsonObj.put("TEMPSOLOCM40CM", dados[i][53]);
				jsonObj.put("TEMPSOLOCM100CM", dados[i][54]);

				form[contador][5] = jsonObj.toString();
				contador++;

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		setQuantDados(getQuantDados()-4);
		this.setDados(form);

	}

}
