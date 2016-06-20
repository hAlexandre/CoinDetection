package pacote2892028989.CONTROL;

public class Rotacao {

  public int[][] main (int nrows, int ncols, double angle, int in_img[][]) {
    int     out_img[][];
    int     i, j, x0, y0, x1, y1;
    double  ci, cj, x, y, p, q, cs, sn;
    
    angle = angle*Math.PI/180;
    out_img = new int[nrows][ncols];

    ci = nrows/2;  // (ci, cj): centro da imagem
    cj = ncols/2;
    cs = Math.cos(-angle);   // Mapping backward: rotação reversa
    sn = Math.sin(-angle);

    for (i=0; i<nrows; i++) {    // (i, j): coord. na imagem rotacionada
      for (j=0; j<ncols; j++) {  // (x, y): coord. na imagem original
        x = (i-ci)*cs - (j-cj)*sn + ci;
        y = (i-ci)*sn + (j-cj)*cs + cj;

        // Fora da imagem original
        if (x < 0)
          x = 0;
        else if (x >= nrows-1)
          x = nrows - 2;
        if (y < 0)
          y = 0;
        else if (y >= ncols-1)
          y = ncols - 2;

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