package TiposFormatos;

import org.json.JSONException;
import org.json.JSONObject;

public class DadosSoloVivo6 extends Formatacao {

	public DadosSoloVivo6(int quantDados, String[][] dadoSeparadosEstacoes) {
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
				jsonObj.put("vw_1_min", dados[i][4]);
				jsonObj.put("vw_1_max", dados[i][5]);
				jsonObj.put("vw_1_avg", dados[i][6]);
				jsonObj.put("p_us1_min", dados[i][7]);
				jsonObj.put("p_us1_max", dados[i][8]);
				jsonObj.put("p_us1_avg", dados[i][9]);
				jsonObj.put("vw_2_min", dados[i][10]);
				jsonObj.put("vw_2_max", dados[i][11]);
				jsonObj.put("vw_2_avg", dados[i][12]);
				jsonObj.put("p_us2_min", dados[i][13]);
				jsonObj.put("p_us2_max", dados[i][14]);
				jsonObj.put("p_us2_avg", dados[i][15]);
				jsonObj.put("vw_3_min", dados[i][16]);
				jsonObj.put("vw_3_max", dados[i][17]);
				jsonObj.put("vw_3_avg", dados[i][18]);
				jsonObj.put("p_us3_min", dados[i][19]);
				jsonObj.put("p_us3_max", dados[i][20]);
				jsonObj.put("p_us3_avg", dados[i][21]);
				jsonObj.put("vw_4_min", dados[i][22]);
				jsonObj.put("vw_4_max", dados[i][23]);
				jsonObj.put("vw_4_avg", dados[i][24]);
				jsonObj.put("p_us4_min", dados[i][25]);
				jsonObj.put("p_us4_max", dados[i][26]);
				jsonObj.put("p_us4_avg", dados[i][27]);
				jsonObj.put("vw_5_min", dados[i][28]);
				jsonObj.put("vw_5_max", dados[i][29]);
				jsonObj.put("vw_5_avg", dados[i][30]);
				jsonObj.put("p_us5_min", dados[i][31]);
				jsonObj.put("p_us5_max", dados[i][32]);
				jsonObj.put("p_us5_avg", dados[i][33]);
				jsonObj.put("pot_mat_kpa1_min", dados[i][34]);
				jsonObj.put("pot_mat_kpa1_max", dados[i][35]);
				jsonObj.put("pot_mat_kpa1_avg", dados[i][36]);
				jsonObj.put("temp1_min", dados[i][37]);				
				jsonObj.put("temp1_max",  dados[i][38]);
				jsonObj.put("temp1_avg", dados[i][39]);
				jsonObj.put("pot_mat_kpa2_min", dados[i][40]);
				jsonObj.put("pot_mat_kpa2_max", dados[i][41]);
				jsonObj.put("pot_mat_kpa2_avg", dados[i][42]);
				jsonObj.put("temp2_min", dados[i][43]);				
				jsonObj.put("temp2_max",  dados[i][44]);
				jsonObj.put("temp2_avg", dados[i][45]);
				jsonObj.put("pot_mat_kpa3_min", dados[i][46]);
				jsonObj.put("pot_mat_kpa3_max", dados[i][47]);
				jsonObj.put("pot_mat_kpa3_avg", dados[i][48]);
				jsonObj.put("temp3_min", dados[i][49]);				
				jsonObj.put("temp3_max",  dados[i][50]);
				jsonObj.put("temp3_avg", dados[i][51]);
				jsonObj.put("pot_mat_kpa4_min", dados[i][52]);
				jsonObj.put("pot_mat_kpa4_max", dados[i][53]);
				jsonObj.put("pot_mat_kpa4_avg", dados[i][54]);
				jsonObj.put("temp4_min", dados[i][55]);				
				jsonObj.put("temp4_max",  dados[i][56]);
				jsonObj.put("temp4_avg", dados[i][57]);
				jsonObj.put("pot_mat_kpa5_min", dados[i][58]);
				jsonObj.put("pot_mat_kpa5_max", dados[i][59]);
				jsonObj.put("pot_mat_kpa5_avg", dados[i][60]);
				jsonObj.put("temp5_min", dados[i][61]);				
				jsonObj.put("temp5_max",  dados[i][62]);
				jsonObj.put("temp5_avg", dados[i][63]);
		

				form[i][4] = jsonObj.toString();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.setDados(form);
		
	}

}
