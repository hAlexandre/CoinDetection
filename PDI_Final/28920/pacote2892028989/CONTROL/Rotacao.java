package pacote2892028989.CONTROL;

import java.awt.Color;
import java.awt.Graphics;

public class Rotacao {

  public int[][] main (int ncols, int nrows, double angle, int in_img[][]) {
    int     out_img[][];
    int     i, j, x0, y0, x1, y1;
    double  ci, cj, x, y, p, q, cs, sn;
    
    angle = angle*Math.PI/180;
    out_img = new int[ncols][nrows];
    
    
    double cos = Math.cos(angle);
    double sin = Math.cos(angle);
    
    ci = ncols/2;  // (ci, cj): centro da imagem
    cj = nrows/2;
    cs = Math.cos(-angle);   // Mapping backward: rotação reversa
    sn = Math.sin(-angle);
    

    for (i=0; i<ncols; i++) {    // (i, j): coord. na imagem rotacionada
      for (j=0; j<nrows; j++) {  // (x, y): coord. na imagem original
        x = (i-ci)*cs - (j-cj)*sn + ci;
        y = (i-ci)*sn + (j-cj)*cs + cj;

        // Fora da imagem original
        if (x < 0)
        	continue;
//          x = 0;
        else if (x >= ncols-1)
        	continue;
//          x = ncols - 2;
        if (y < 0)
        	continue;
//          y = 0;
        else if (y >= nrows-1)
        	continue;
//          y = nrows - 2;

	// Interpolação Bilinear
        x0 = (int)Math.floor(x);
        y0 = (int)Math.floor(y);
       	x1 = x0 + 1;
       	y1 = y0 + 1;
        p = (x1-x)*in_img[x0][y0] + (x-x0)*in_img[x1][y0];
        q = (x1-x)*in_img[x0][y1] + (x-x0)*in_img[x1][y1];
        out_img[i][j] = (int)((y1-y)*p + (y-y0)*q);

      }  //  for j
    }  //  for i

    return out_img;
  }

  
  

}