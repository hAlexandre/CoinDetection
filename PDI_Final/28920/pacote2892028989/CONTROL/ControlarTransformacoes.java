package pacote2892028989.CONTROL;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ControlarTransformacoes {
	
	public char[][] bicubica(char[][] original, int width, int height,int k)
	{					
		int w = width;
		int h = height;
		char[][] alterada = null;
				for(int x = 0; x < k-1; x++)
				{	     
					alterada = new char[2*w][2*h];					
			        int tam = 2;
			        for (int i = 0; i < h; i++)
			        {
			        	int red = 0, green = 0, blue = 0, rgb;
			        	for (int j = 0; j < w; j++)
			        	{
			        		for(int m = i*tam, cont1 = 0; cont1 < tam; m++, cont1++)
			        		{
			        			for(int n = j*tam, cont2 = 0; cont2 < tam;  n++, cont2++)
			        			{				        	
			    					for(int lim = -1; lim < 3; lim++)
			        				{
			    						if(lim+i >= h) continue;			    									    						
			    						if(lim+i < 0) continue;			    									    						
			        					for(int b = -1; b < 3; b++)
			        					{
			        						if(b+j < 0) continue;
			        						if(b+j >= w) continue;
			        						double d1 = Math.abs(lim-(double)cont2);
			        						double d2 = Math.abs((double)cont1-b);
			        						if(d1 >= 0 && d1 <=1)			        						
			        							d1= 2/3 + Math.pow(d1,3)*1/2  - Math.pow(d1, 2);			        						
			        						else
			        						{
			        							if(d1 > 1 && d1 <=2)				        						
				        							d1 =  1/6 * Math.pow( (2-d1),3);				        						
			        							else			        							
			        								d1 = 1;			        							
			        						}
			        						
			        						if(d2 >= 0 && d2 <= 1)			        						
			        							d2= 2/3 + Math.pow(d2, 3)*1/2 - Math.pow(d2, 2);			        						
			        						else
			        						{
			        							if(d2 > 1 && d2 <= 2)				        						
				        							d2 =  1/6 * Math.pow((2-d2),3);				        						
			        							else			        							
			        								d2 = 1;			        							
			        						}
			        						
			        						double prop = Math.abs(d1 * d2);			
//			        						
			        						
			        						red =  (int) ((red + (int)original[b+j][lim+i]) * prop *3/2);
			        						
			        					}
			        				}		    					
			    					alterada[n][m] = (char) red;									
									red = 0;
									
			        			}
			        		}
			        	}
			        }
			        	
			        original = alterada;
				}
				
				
				return alterada;
	}


}
