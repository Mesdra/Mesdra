package TiposFormatos;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosSoloVivo3 extends Formatacao {

	public DadosSoloVivo3(int quantDados, String[][] dadoSeparadosEstacoes) {
		setDados(dadoSeparadosEstacoes);
		setQuantDados(quantDados);
	}

	@Override
	public void GerarFormato() {
		String[][] form = new String[getQuantDados()][5];
		String dados[][] = getDados().clone();
		for (int i = 0; i < getQuantDados(); i++) {

			form[i][0] = dados[i][2];
			form[i][1] = dados[i][0];
			String datahora[] = dados[i][1].split(" ");
			form[i][2] = datahora[0];
			form[i][3] = datahora[1];

			try {
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("battv", dados[i][3]);
				jsonObj.put("temp_ar_avg", dados[i][4]);
				jsonObj.put("temp_ar_max", dados[i][5]);
				jsonObj.put("temp_ar_min", dados[i][6]);
				jsonObj.put("umid_rel", dados[i][7]);
				jsonObj.put("umid_rel_max", dados[i][8]);
				jsonObj.put("umid_rel_min", dados[i][9]);
				jsonObj.put("precip_tot", dados[i][10]);
				jsonObj.put("vel_vento_s_wvt", dados[i][11]);
				jsonObj.put("dir_vento_d1_wvt", dados[i][12]);
				jsonObj.put("vel_vento_max", dados[i][13]);
				jsonObj.put("vw_avg", dados[i][14]);
				jsonObj.put("pa_us_avg", dados[i][15]);
				jsonObj.put("vw_2_avg", dados[i][16]);
				jsonObj.put("pa_us_2_avg",  dados[i][17]);
				jsonObj.put("vw_3_avg",  dados[i][18]);
				jsonObj.put("pa_us_3_avg", dados[i][19]);
				jsonObj.put("vw_4_avg", dados[i][20]);
				jsonObj.put("cma6_inc", dados[i][21]);
				jsonObj.put("cma6_ref", dados[i][22]);
				jsonObj.put("cma6_alb", dados[i][23]);
				

				form[i][4] = jsonObj.toString();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.setDados(form);
	}

}
