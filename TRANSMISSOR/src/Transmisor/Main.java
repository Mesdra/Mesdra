package Transmisor;


import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Funcoes_Tranzacao funcoes = new Funcoes_Tranzacao();
		
		Ler_Grava_Arq ler_Grava = new Ler_Grava_Arq();
		
		List<String> lista = ler_Grava.lerArquivo();
		
		List<String> lista2 = new ArrayList<String>();
		for(int i =0;i < lista.size();i++ ){
			
			int resp = generateChecksum(lista.get(i));
			String  dadoCheckSum = Integer.toBinaryString(resp); 
			String Dado_Check = lista.get(i).concat(dadoCheckSum);
			lista2.add(Dado_Check);
		
		}
		lista = new ArrayList<String>(); 
		
		lista = funcoes.insercaoDeBits(lista2);
		
		String conc = funcoes.inserirFlags(lista);
		
		System.out.println(conc);
		
		
		
	
	}
	
	 static int generateChecksum(String s)
	    {
	        String hex_value = new String();
	        // 'hex_value' will be used to store various hex values as a string
	        int x, i, checksum = 0;
	        // 'x' will be used for general purpose storage of integer values
	        // 'i' is used for loops
	        // 'checksum' will store the final checksum
	        for (i = 0; i < s.length() - 2; i = i + 2)
	        {
	            x = (int) (s.charAt(i));
	            hex_value = Integer.toHexString(x);
	            x = (int) (s.charAt(i + 1));
	            hex_value = hex_value + Integer.toHexString(x);
	            // Extract two characters and get their hexadecimal ASCII values
	            //System.out.println(s.charAt(i) + "" + s.charAt(i + 1) + " : "
	            //       + hex_value);
	            x = Integer.parseInt(hex_value, 16);
	            // Convert the hex_value into int and store it
	            checksum += x;
	            // Add 'x' into 'checksum'
	        }
	        if (s.length() % 2 == 0)
	        {
	            // If number of characters is even, then repeat above loop's steps
	            // one more time.
	            x = (int) (s.charAt(i));
	            hex_value = Integer.toHexString(x);
	            x = (int) (s.charAt(i + 1));
	            hex_value = hex_value + Integer.toHexString(x);
	            //System.out.println(s.charAt(i) + "" + s.charAt(i + 1) + " : "
	            //        + hex_value);
	            x = Integer.parseInt(hex_value, 16);
	        }
	        else
	        {
	            // If number of characters is odd, last 2 digits will be 00.
	            x = (int) (s.charAt(i));
	            hex_value = "00" + Integer.toHexString(x);
	            x = Integer.parseInt(hex_value, 16);
	            //System.out.println(s.charAt(i) + " : " + hex_value);
	        }
	        checksum += x;
	        // Add the generated value of 'x' from the if-else case into 'checksum'
	        hex_value = Integer.toHexString(checksum);
	        // Convert into hexadecimal string
	        if (hex_value.length() > 4)
	        {
	            // If a carry is generated, then we wrap the carry
	            int carry = Integer.parseInt(("" + hex_value.charAt(0)), 16);
	            // Get the value of the carry bit
	            hex_value = hex_value.substring(1, 5);
	            // Remove it from the string
	            checksum = Integer.parseInt(hex_value, 16);
	            // Convert it into an int
	            checksum += carry;
	            // Add it to the checksum
	        }
	        checksum = generateComplement(checksum);
	        // Get the complement
	        return checksum;
	    }
	 
	 Boolean receive(String s, int checksum)
	    {
	        int generated_checksum = generateChecksum(s);
	        // Calculate checksum of received data
	        generated_checksum = generateComplement(generated_checksum);
	        // Then get its complement, since generated checksum is complemented
	        int syndrome = generated_checksum + checksum;
	        // Syndrome is addition of the 2 checksums
	        syndrome = generateComplement(syndrome);
	        // It is complemented
	        System.out.println("Syndrome = " + Integer.toHexString(syndrome));
	        if (syndrome == 0)
	        {
	            System.out.println("Data is received without error.");
	            return true;
	        }
	        else
	        {
	            System.out.println("There is an error in the received data.");
	            return false;
	        }
	    }
	 
	 static int generateComplement(int checksum)
	    {
	        // Generates 15's complement of a hexadecimal value
	        checksum = Integer.parseInt("FFFF", 16) - checksum;
	        return checksum;
	    }

}
