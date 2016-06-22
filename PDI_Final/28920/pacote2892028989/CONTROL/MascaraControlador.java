package pacote2892028989.CONTROL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import pacote2892028989.MODEL.Mascara;

public class MascaraControlador {
	
	
	public ArrayList<Mascara> mascaras;
	
	
	
	public ArrayList<Mascara> getMascaras() {
		return mascaras;
	}
	
	
	public MascaraControlador()
	{
		mascaras = new ArrayList<>();
		Scanner sc = null;
		ArrayList<String> arquivos = new ArrayList<>();
		arquivos.add("mascaras_1.txt");
		arquivos.add("mascaras_2.txt");
		arquivos.add("mascaras_3.txt");
		for(int i = 0 ; i < arquivos.size() ; i++)
		{
			try {
				sc = new Scanner(new File(arquivos.get(i)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			while(sc.hasNextInt())
			{			
				int nCol = sc.nextInt();
				int nLin = sc.nextInt();
				
				double valor = sc.nextDouble();			
				char[][] aux = new char[nCol][nLin];
				for(int cont = 0 ; cont < nCol ; cont++)
				{
					for(int j = 0 ; j < nLin ; j++)
					{
						aux[cont][j] = (char)(sc.nextInt());	
					}
				}
				Mascara mascara = new Mascara(aux,valor, nLin, nCol);
				mascaras.add(mascara);
			
		}
		
			
		}
		
		
		
		
		
	}


}
