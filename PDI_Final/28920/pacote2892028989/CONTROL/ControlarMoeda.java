package pacote2892028989.CONTROL;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import pacote2892028989.MODEL.Mascara;
import pacote2892028989.MODEL.Moeda;
import pacote2892028989.MODEL.Ponto;
import pacote2892028989.VIEW.MontarPainelInicial;

public class ControlarMoeda {
	
	ArrayList<Ponto> pontos, proximos;
	Boolean[][] visitados;
	ArrayList<Moeda> arrMoedas;
	
	FileWriter writer;
	Rotacao rotacao = new Rotacao();
	int nCol, nLin, maX, meX, maY, meY, moedas;
	char[][] imagem;
	ControlarImagem controleImagem;
	MontarPainelInicial pnCen;
	MascaraControlador mascaraControl;
	Graphics g;
	int k;
	int aux = 0;
	
	public ControlarMoeda (char[][] imagem, int nLin, int nCol, Graphics g, ControlarImagem controleImagem, MontarPainelInicial pnCen, MascaraControlador m) throws IOException
	{
		

		File file = new File("mascaras.txt");
		
		try {
			file.createNewFile();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		try {
			 writer = new FileWriter(file);
		} catch (IOException e) {			
			
		} 
		arrMoedas = new ArrayList<>();
		this.mascaraControl = m;
		this.pnCen = pnCen;
		this.controleImagem = controleImagem;
		this.g = g;
		this.nLin = nLin;
		this.nCol = nCol;
		this.imagem = imagem;
		
		
		visitados = new Boolean[nCol][nLin];
		pontos = new ArrayList<>();
		for(int i = 0 ; i < nCol ; i++)
		{
			for(int j = 0 ; j < nLin ; j++)
			{
				if(imagem[i][j] == 255)
				{
					pontos.add(new Ponto(i, j));											
				}
				visitados[i][j]=false;
			}
		}
		
		moedas = 0;
		
		
		
		
		for(int cont = 0 ; cont < pontos.size() ; cont++)
		{
			Moeda aux;
			if(!visitados[pontos.get(cont).getX()][pontos.get(cont).getY()])
			{
				moedas++;
				proximos = new ArrayList<>();
				proximos.add(pontos.get(cont));
				
				maX = meX = proximos.get(0).getX();
				maY = meY = proximos.get(0).getY();
				while(proximos.size()>0)
				{							
					verifica(proximos.get(0));
					proximos.remove(0);			
//					int xx = pontos.get(0).getX();
//					int yy = pontos.get(0).getY();
//					if(!visitados[xx][yy])
//					{
//						
//					}
				}				
				
				for(int x = meX ; x <= maX ; x++)
				{
					for(int y = meY ; y<= maY ; y++)
					{						
						if(imagem[x][y]==255)
						{							
							double r = ( (maX-meX)/2 + (maY-meY)/2 ) / 2;
							double dx = Math.pow(x-(meX+(maX-meX)/2), 2);
							double dy = Math.pow(y-(meY+(maY-meY)/2), 2);					
							if ( (Math.sqrt (dx+dy) <= r) )
							{
								visitados[x][y] = true;										//								
							}
						}		
					}					
				}
				
				g.setColor(Color.GREEN);
				g.drawLine(maX, maY, maX, meY);	
				g.drawLine(meX, maY, meX, meY);
				g.drawLine(meX, meY, maX, meY);
				g.drawLine(meX, maY, maX, maY);
				
				
				aux = new Moeda();
				
				k = Math.min((maY-meY), (maX-meX));
				char moeda[][] = new char[k][k];				
				aux.setMoedas(moeda);
				
				
				aux.setnLin(k);
				aux.setnCol(k);
				
				int cont1 = 0, cont2 = 0;
				for(int i = meX ; i < meX+k ; i++)
				{
					for(int j = meY ; j < meY+k ; j++)
					{		
						moeda[cont1][cont2] = imagem[i][j];
						cont2++;
					}
					cont2 = 0;
					cont1++; 
					
				}
				contaPixel();
				arrMoedas.add(aux);
			}	
		}
		

		
		for( int i = 0 ; i < 1 ; i++)
		{			
			Moeda aux = arrMoedas.get(i);						
//			controleImagem.mostrarImagemMatriz(aux.getMoedas(), aux.getnLin(), aux.getnCol() ,g);		
		}
		
	}
	
	public void calcularValor() throws IOException
	{
		
		
		ArrayList<Mascara> mascaras = mascaraControl.getMascaras();
//		System.out.println(mascaras.size()+" mascaras");
		for(int cont = 0 ; cont < arrMoedas.size() ; cont++)
		{			
				Moeda moeda = arrMoedas.get(cont);			
				double val = 0.0, prob = 0.0;
				Mascara imasc = null;
				int maxAng = 0;
				char[][] matMoeda   = moeda.getMoedas();
				
//				criaMascara(moeda);
				for(int ang = 0 ; ang <= 360 ; ang++)
				{
					int[][] aux = rotacao.main(moeda.getnCol(), moeda.getnLin(),ang, controleImagem.char2int(matMoeda, moeda.getnLin(), moeda.getnCol()));
					for(Mascara mascara : mascaras)
					{					
						int nPixels = 0;				
						int nMatches = 0;
						char[][] matMascara = mascara.getMascara();						
						
						char matMoeda2[][] = controleImagem.int2char(aux, moeda.getnLin(), moeda.getnCol());
						
						
						int nCol = mascara.getnCol();
						int nLin = mascara.getnLin();
						int nColMoeda = moeda.getnCol();
						for(int i = 0 ; i < nCol ; i++)
						{
							for(int j = 0 ; j < nLin ; j++)
							{
								
								g.setColor(Color.red);
								if((int)matMascara[i][j] == 0)
								{
									g.setColor(Color.green);
								}
								if((int)matMascara[i][j]==255)
								{
									g.setColor(Color.yellow);
								}
	//							g.drawLine(i, j, i, j);
								
								if((  (int)matMascara[i][j] == 300 ) || (i>=nColMoeda) || (j>=nColMoeda) )
								{							
	//								g.setColor(Color.green);
	//								g.drawLine(i, j, i, j);
									continue;
								}	
								nPixels++;
								if(matMascara[i][j] == matMoeda2[i][j])
								{					
	//								g.setColor(Color.red);
	//								g.drawLine(i, j, i, j);
									nMatches++;					
								}
								else
								{
	//								g.setColor(Color.blue);
	//								g.drawLine(i, j, i, j);							
								}
								
							}					
						}
						double k = (double) ((double)nMatches / (double)nPixels);
						if ( k > prob )
						{
							prob = k;
							imasc = mascara;
							maxAng = ang;
							moeda.setValor(mascara.getValor());		
							
						}	
						
				}						
			}
			System.out.println(prob+" "+maxAng+ " "+ moeda.getValor());
			int[][] aux = rotacao.main(moeda.getnCol(), moeda.getnLin(),maxAng, controleImagem.char2int(matMoeda, moeda.getnLin(), moeda.getnCol()));
			matMoeda = controleImagem.int2char(aux, moeda.getnLin(), moeda.getnCol());
//			pnCen.limpaPainelCen(g);
//			
//			controleImagem.mostrarImagemMatriz(matMoeda, moeda.getnLin(), moeda.getnCol(), g);
		}
		
		double total = 0;		
		for(Moeda moeda : arrMoedas)
		{
			System.out.print(moeda.getValor()+" ");			
			total+=moeda.getValor();			
		}
		System.out.println();
		System.out.println("R$"+String.format( "%.2f", total ));
		writer.flush();
		writer.close();
	}
	
	public void criaMascara(Moeda m)  throws IOException
	{	
		int x = m.getnCol() /2 ;
		int y = m.getnLin() /2;						
		int offset = (int)(x*0.7);
				
		int tam = m.getnCol();
		char imagem2[][] = new char[tam][tam]; 
		
		for(int i = 0 ; i < tam ; i++)
		{
			for(int j = 0 ; j<tam ; j++)
			{						
				imagem2[i][j] = (char) 300;			
			}					
		}	
		
		char[][] aux2 = m.getMoedas();
		for(int i = x-offset ; i <= (x+0.7*offset) ; i++)
		{
			for(int j = (int) (y-offset*1.2) ; j <= (y+0.32*offset) ;j++)
			{								
				imagem2[i][j] = aux2[i][j];		
				
			}			
		}
		
		
		writer.append(k + " " + k);		
		if(aux==0)		
			writer.append(" "+"1,0");
		if(aux==1)
			writer.append(" "+"0,50");
		if(aux==2){			
			writer.append(" "+"0,05");  			
		}
		if(aux==3)
			writer.append(" "+"0,25");
		if(aux==4)
			writer.append(" "+"0,10");
		if(aux==5)
			writer.append(" "+"0,10");
		if(aux==6)
			writer.append(" "+"0,10");
		if(aux==7)
			writer.append(" "+"1,0");
		
		aux++;
		
		
		writer.append(System.lineSeparator());
		writer.append(System.lineSeparator());
		for(int i = 0 ; i < k ; i++)
		{
			for(int j = 0; j <k ;j++)
			{				
				
				g.setColor(Color.blue);			
				int temp = (int)imagem2[i][j];
				String k1 = Integer.toString(temp);
				if(temp==0)
				{
//					g.setColor(Color.green);
//					g.drawLine(i, j, i, j);
				}
				if(temp==300)
				{
//					g.setColor(Color.gray);
//					g.drawLine(i, j, i, j);
				}
				if(temp==255)
				{
//					g.setColor(Color.red);
//					g.drawLine(i, j, i, j);
				}				
				writer.append(k1+" ");			
			}						
			writer.append(System.lineSeparator());		
		}
		writer.append(System.lineSeparator());
		writer.append(System.lineSeparator());
	}
	
	public ArrayList<Moeda> getArrMoedas() {
		return arrMoedas;
	}
	
	private void contaPixel()
	{
		g.setColor(Color.yellow);
		g.drawLine(maX, maY, maX, meY);	
		g.drawLine(meX, maY, meX, meY);
		g.drawLine(meX, meY, maX, meY);
		g.drawLine(meX, maY, maX, maY);
	}

	private void verifica(Ponto p)
	{		
		int xp = p.getX();
		int yp = p .getY();
			for(int i = -4 ; i <= 4 ; i++)
			{
				for(int j = -4 ; j <= 4 ; j++)
				{
					int x = xp + i;
					int y = yp + j;
					if( (x<0) || (y<0) || (x>=nCol) || (y>=nLin)  ) 											
						continue;											
										
					if((imagem[x][y]==255)  && (!visitados[x][y]))
					{																
						if(x>maX) maX = x;
						if(x<meX) meX = x;
						if(y<meY) meY = y;
						if(y>maY) maY = y;
						visitados[x][y]=true;
						g.setColor(Color.green);
						proximos.add(new Ponto(x,y));						
					}										
				}
			}
		
						
		
		
	}
	
	
}