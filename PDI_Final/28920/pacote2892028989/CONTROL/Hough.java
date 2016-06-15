package pacote2892028989.CONTROL;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pacote2892028989.MODEL.Ponto;

public class Hough {
	
	int[][] acumulador;
	int nLin,nCol;
	int aux2, aux = 0;
	Graphics g;
	
	public Hough(char[][] imagem, int nLin, int nCol, int rMin, int rMax, int dist, Graphics g)
	{
			
		ArrayList<Ponto> pontos = new ArrayList<>();		
		this.g = g;
		this.nCol=nCol;
		this.nLin=nLin;
		acumulador = new int[nCol+1][nLin+1];
		
		// Zerando matriz de candidatos a centros		
		for(int x = 0 ; x < nCol ; x ++)
		{
			for(int y = 0 ; y < nLin ; y++)
			{				
					acumulador[x][y]=0;
			
			}
		}		
		
//		Inserindo pontos brancos na lista de pontos
		for(int i = 0 ; i < nCol ; i++)
		{
			for(int j = 0 ; j < nLin ; j++)
			{
				if(imagem[i][j] == 255)
				{
					pontos.add(new Ponto(i, j));
					
				}
			}
		}
			
		int tam = pontos.size();
		int cont = 0;
		
		while(cont < (tam - 9))
		{
			int x1 = pontos.get(cont).getX();
			int y1 = pontos.get(cont).getY();
			
			int x2 = pontos.get(cont+8).getX();
			int y2 = pontos.get(cont+8).getY();
			
			int x3 = pontos.get(cont+4).getX();
			int y3 = pontos.get(cont+4).getY();
			
			int x4 = pontos.get(cont+6).getX();
			int y4 = pontos.get(cont+6).getY();

			g.setColor(Color.green);
//			g.drawLine(x1, y1, x2, y2);
//			g.drawLine(x2, y2, x3, y3);
			
			 
			
//			DELAY
							
			
			encontraCentro(x1,y1,x2,y2,x3,y3);
			encontraCentro(x1,y1,x3,y3,x2,y2);
			encontraCentro(x1,y1,x3,y3,x4,y4);
			encontraCentro(x4,y4,x3,y3,x2,y2);
			encontraCentro(x1,y1,x4,y4,x2,y2);
			encontraCentro(x1,y1,x2,y2,x4,y4);
			aux++;
						
			cont = cont + 1;	
		
			
		}
		
		int ma = 0;
		int x=0,y=0;
		for(int i = 0 ;  i < nCol ; i++)
		{
			for(int j = 0 ; j < nLin ; j++)
			{
				if(acumulador[i][j] > ma)
				{
					ma = acumulador[i][j];
					x=i;
					y=j;
				}
			}
		}
		
		int k = (int)(255/ma);
		
		for(int i = 0 ;  i < nCol ; i++)
		{
			for(int j = 0 ; j < nLin ; j++)
			{
					int aux = acumulador[i][j] * k;
					Color cor = new Color(aux,aux,aux);
					g.setColor(cor);
					g.drawLine(i, j, i, j);
				
			}
		}
		

//		g.setColor(Color.yellow);
//		g.drawLine(x-2, y, x+2, y);
//		g.drawLine(x, y-2, x, y+2);
		
		
		System.out.println(aux+ " chamadas");
		System.out.println(cont+" pontos ");
		System.out.println(ma+ " mais acumulado");
		
		
	}
	
	
	
	private void encontraCentro(int x1, int y1, int x2, int y2, int x3, int y3)
	{
		
		
		Ponto ponto12 = new Ponto(((x1+x2)/2),((y1+y2)/2));
		Ponto ponto23 = new Ponto(((x2+x3)/2),((y2+y3)/2));
//		Visualização dos pontos médios
//		g.setColor(Color.red);
		
		
		
//		y = mx + c
		double m12 =  ((double)(y1-y2)/(double)(x1-x2));		
		double c12 = -(m12 * x1) + y1;

//		reta perpendicular
		double mPerp12 = (double)(-1)/m12;
		double cPerp12 = -(mPerp12 * ponto12.getX()) + ponto12.getY();

		g.setColor(Color.yellow);		
		
		for(int x = ponto12.getX()-100 ; x<= ponto12.getX()+100 ; x++)
		{
			int y = (int) (mPerp12 * x + cPerp12);			
//			g.drawLine(x, y, x, y);
		
		}
		
		
		double m23 =  ((double)(y2-y3)/(double)(x2-x3));		
		double c23 = -(m23 * x2) + y2;
		
		

//		reta perpendicular
		double mPerp23 = (double)(-1)/m23;
		double cPerp23 = -(mPerp23 * ponto23.getX()) + ponto23.getY();

		g.setColor(Color.yellow);		
		
		for(int x = ponto23.getX()-100 ; x<= ponto23.getX()+100 ; x++)
		{
			int y = (int) (mPerp23 * x + cPerp23);			
//			g.drawLine(x, y, x, y);
		
		}
		
			
		double xx =  ((cPerp12-cPerp23)/(mPerp23-mPerp12));
		double yy =  (mPerp12*xx+cPerp12);
		
		int x = (int) xx;
		int y = (int) yy;
		
		if((x<nCol) && (y<nLin) && (x>0) && (y>0))
		{
			acumulador[x][y] = acumulador[x][y]  + 1;	
			if( (yy>y) && (xx>x))
				acumulador[x+1][y+1]= acumulador[x+1][y+1] + 1;
			else
				if(xx>x)
					acumulador[x+1][y] = acumulador[x+1][y] + 1;
				if(yy>y)					
						acumulador[x][y+1] = acumulador[x][y+1] + 1;
			
		}
				
		
		
		
		
	}
	
	

}
