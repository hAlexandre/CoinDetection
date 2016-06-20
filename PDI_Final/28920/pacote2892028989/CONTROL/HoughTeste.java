package pacote2892028989.CONTROL;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import pacote2892028989.MODEL.Ponto;

public class HoughTeste {
	
	ArrayList<Ponto> pontos, proximos;
	Boolean[][] visitados;
	int nCol, nLin, maX, meX, maY, meY, moedas;
	char[][] imagem;
	Graphics g;
	public HoughTeste (char[][] imagem, int nLin, int nCol, Graphics g)
	{
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
		BufferedWriter bw = null;
		
		
		File arquivo = new File("arquivo2.txt");
		
		for(int cont = 0 ; cont < pontos.size() ; cont++)
		{
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
					int xx = pontos.get(0).getX();
					int yy = pontos.get(0).getY();
					if(!visitados[xx][yy])
					{
						
					}
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
//								g.drawLine(x, y, x, y);
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
//				contaPixel();
				
			
			}						
		}
		System.out.println(moedas+" moedas");
		
		
	}
	
	private void contaPixel()
	{
		int x = (maX - meX) /2 ;
		int y = (maY - meY) /2;
				
		int offset = (int)(x*0.7);
		
		x+= meX;
		y+= meY;
		
//		g.drawLine(x,y-offset,x+2*offset,y-offset);
//		g.drawLine(x, y+offset, x, y-offset);
//		g.drawLine(x+2*offset,y-offset,x+2*offset,y+offset);
//		g.drawLine(x+2*offset, y+offset, x, y+offset);
//		
//		
//		for(int i = x-offset ; i <= (x+0.5*offset) ; i++)
//		{
//			for(int j = (int) (y-offset*1.2) ; j <= (y+0.42*offset) ;j++)
//			{
//				
//				g.drawLine(i, j, i, j);	
//			}
//		}


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
//						g.drawLine(x, y, x, y);
						proximos.add(new Ponto(x,y));						
					}										
				}
			}
		
						
		
		
	}
}