package pacote2892028989.CONTROL;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import pacote2892028989.VIEW.*;

public class ControlarAplicativo implements ActionListener {

	private ControlarTransformacoes transformacoes = new ControlarTransformacoes();
	private MontarPainelInicial pnCenario;
	private Graphics            desenhoCen, desenhoDir;
	private ControlarImagem     controleImagem;
	private String              nomeArquivoImagemDada;
	private char[][]            imagemCinza;
	private char[][]            imagemAtual;

	public int                 nLinImageAtual, nColImageAtual;
	public int                 nLinImageInic, nColImageInic;
	private boolean             estadoDesenho;
	private EdgeDetector canny;

	//*******************************************************************************************
	public ControlarAplicativo( )
	{
		pnCenario = new MontarPainelInicial( this );
		pnCenario.showPanel();
		estadoDesenho  = false;
		
	}
 
	//*******************************************************************************************
	// METODO PARA CONTROLE DOS BOTOES DO APLICATIVO
	
	public void actionPerformed(ActionEvent e)
	{
		String comando, nomeArquivo;
		
		comando = e.getActionCommand();

		// DEFINE AMBIENTE GRAFICO
		if ( !estadoDesenho ) {
			pnCenario.iniciarGraphics();
			desenhoCen = pnCenario.getDesenhoC();
			desenhoDir = pnCenario.getDesenhoD();
		}

		// ENDS THE PROGRAM
		if(comando.equals("botaoFim")) {
			System.exit(0);	
		}

		// INICIA O PROGRAMA
		if(comando.equals("botaoImagem")) {			
			// LE IMAGEM SOLICITADA
			nomeArquivoImagemDada = pnCenario.escolherArquivo ( 1 );
			if ( nomeArquivoImagemDada != null ) {
				controleImagem = new ControlarImagem( nomeArquivoImagemDada, desenhoCen );
				estadoDesenho  = true;
				imagemCinza    = controleImagem.getImagemCinza();
				nLinImageInic  = controleImagem.getNLin();
				nColImageInic  = controleImagem.getNCol();

				pnCenario.mudarBotoes();
				pnCenario.limpaPainelDir( desenhoDir );
				controleImagem.mostrarImagemMatriz ( imagemCinza, nLinImageInic, nColImageInic, desenhoDir );

				nLinImageAtual = nLinImageInic;
				nColImageAtual = nColImageInic;
				imagemAtual    = controleImagem.copiarImagem ( imagemCinza, nLinImageInic, nColImageInic );
			}
		}

		if ( comando.equals( "botaoAcao3" ) && estadoDesenho ) {
			controlarAcao3();
		}
		if ( comando.equals( "botaoAcao4" ) && estadoDesenho ) {
			int k = 0;

				
//				new Hough(imagemAtual, nLinImageAtual, nColImageAtual, 10, 220, 10, desenhoDir);
				new HoughTeste(imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir);
				
			
		}

		if ( comando.equals( "rotacao" ) )  {
			
			int[][] img = new int[nLinImageAtual][nColImageAtual];
			
			for(int i = 0; i < imagemAtual.length; i++)
			{
				for(int j = 0; j < nColImageAtual; j++)
				{
					img[i][j] = Character.getNumericValue(imagemAtual[i][j]);
				}
			}
			
			img = Rotacao.main(nLinImageAtual, nColImageAtual, Double.parseDouble("45"), img);
			char[][] out_img = new char[img[0].length][img[1].length];
			
			
			for(int i = 0; i < img[0].length; i++)
			{
				for(int j = 0; j < img[1].length; j++)
				{
					out_img[i][j] = (char) img[i][j];
				}
			}
			
			controleImagem.mostrarImagemMatriz(out_img, out_img[0].length, out_img[1].length, desenhoDir);
			
			
			//imagemCinza = transformacoes.bicubica(imagemAtual, nColImageAtual, nLinImageAtual, 2);
			//controleImagem.mostrarImagemMatriz(imagemCinza,2* nLinImageAtual, 2* nColImageAtual, desenhoDir);
		} 

		if ( comando.equals( "botaoAcao11" ) ) {
		}

		if ( comando.equals( "botaoAcao12" ) ) {
		}

		if ( comando.equals( "botaoAcao13" ) ) {
		}

		if ( comando.equals( "botaoAcao14" ) ) {
		}

		if ( comando.equals( "botaoAcao15" ) ) {
		}

		if ( comando.equals( "botaoAcao21" ) ) {
		}

		if ( comando.equals( "botaoAcao22" ) ) {
		}

		if ( comando.equals( "botaoAcao23" ) ) {
		}

		if ( comando.equals( "botaoAcao24" ) ) {
		}

		if ( comando.equals( "botaoAcao25" ) ) {
		}

		if ( comando.equals( "botaoAcao26" ) ) {
		}

		if ( comando.equals( "botaoSalva" ) && estadoDesenho ) {
//			ControladorCompressao comprimir = new ControladorCompressao();
			nomeArquivo = pnCenario.escolherArquivo ( 2 );
			
			controleImagem.gravarImagem( nomeArquivo, imagemAtual, nLinImageAtual, nColImageAtual );
		}

		if ( comando.equals( "botaoReset" ) && estadoDesenho ) {
			pnCenario.limpaPainelCen( desenhoCen );
			controleImagem = new ControlarImagem( nomeArquivoImagemDada, desenhoCen );
			nLinImageAtual   = nLinImageInic;
			nColImageAtual   = nColImageInic;
			imagemAtual      = controleImagem.copiarImagem ( imagemCinza, nLinImageInic, nColImageInic );

			pnCenario.limpaPainelDir( desenhoDir );
			controleImagem.mostrarImagemMatriz ( imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir );

			pnCenario.ativarPainelAcao1();
			pnCenario.resetaSistema();
		}
	}

	//*******************************************************************************************
	private void controlarAcao3()
	{
		canny = new EdgeDetector();
		
		char[][] imgChar = controleImagem.getImagemCinza();
		
		BufferedImage image = controleImagem.transformarMatriz2Buffer(imgChar,  nLinImageAtual, nColImageAtual);
		
		canny.setSourceImage(image);
		try {
			canny.process();
			
			Image img = canny.getEdgeImage();			
			
			BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);	
			
		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();
			
			
			controleImagem.criarImagemCinza(bimage);
			
			imagemCinza    = controleImagem.getImagemCinza();
			nLinImageInic  = controleImagem.getNLin();
			nColImageInic  = controleImagem.getNCol();

			pnCenario.mudarBotoes();
			pnCenario.limpaPainelDir( desenhoDir );
			int k = 0;
			
			
				
				
			controleImagem.mostrarImagemMatriz ( imagemCinza, nLinImageInic, nColImageInic, desenhoDir );
			nLinImageAtual = nLinImageInic;
			nColImageAtual = nColImageInic;
			imagemAtual    = controleImagem.copiarImagem ( imagemCinza, nLinImageInic, nColImageInic );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}

	//*******************************************************************************************
}
