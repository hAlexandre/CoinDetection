package pacote2892028989.CONTROL;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import pacote2892028989.MODEL.Moeda;
import pacote2892028989.VIEW.*;

public class ControlarAplicativo implements ActionListener {
	
	private Rotacao				rotacao = new Rotacao();
	private MontarPainelInicial pnCenario;
	
	private Graphics            desenhoCen, desenhoDir;
	private ControlarImagem     controleImagem;
	private String              nomeArquivoImagemDada;
	private char[][]            imagemCinza;
	private char[][]            imagemAtual;

	public int                 nLinImageAtual, nColImageAtual;
	public int                 nLinImageInic, nColImageInic;
	private boolean             estadoDesenho;
	
	private ControladorMoeda	moedas;
	private EdgeDetector canny;
	private MascaraControlador m;

	//*******************************************************************************************
	public ControlarAplicativo( )
	{
		pnCenario = new MontarPainelInicial( this );
		pnCenario.showPanel();
		estadoDesenho  = false;
		m = new MascaraControlador();
		
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

				
			try {
				 moedas = new ControladorMoeda(imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir, controleImagem, pnCenario, m);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if ( comando.equals( "botaoValor" ) ) {
			
			try {
				moedas.calcularValor();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		if ( comando.equals( "compressao" ) )  {						
			JFileChooser fc = new JFileChooser();			
			JOptionPane.showMessageDialog(null,"Imagem comprimida. Selecione onde deseja salvar o arquivo no formato .jpg");
			
	        FileNameExtensionFilter tipos = new FileNameExtensionFilter("JPEG (.jpg)",
	                "jpg");
	        fc.addChoosableFileFilter(tipos);
	        fc.setAcceptAllFileFilterUsed(false); 
	        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        int returnValue = fc.showSaveDialog(null);
	        String arq = null;
	        if (returnValue == JFileChooser.APPROVE_OPTION)
	        {
	        	arq = fc.getSelectedFile().toString();	        	
	        	        
	        }
	        controleImagem.gravarImagem(arq , imagemAtual, nLinImageAtual, nColImageAtual );
	        String arq2 = arq+"_comprimido.jpg";
	        arq = arq+".jpg";
	        

	        new Jpeg(arq, arq2);
			
			
		} 

		
		
		if ( comando.equals( "botaoHelp" )) {
//			ControladorCompressao comprimir = new ControladorCompressao();
			JOptionPane.showMessageDialog(null,
				    "Passos para o funcionamento do programa: \n"
				    + "1 - New Image: selecionar uma imagem,\n"
				    + "2 - Reset: desfazer as operações realizadas na imagem\n"
				    + "3 - Canny: aplicar o filtro de canny à imagem,\n"
				    + "4 - Encontrar moedas: após aplicado o filtro, encontra as moedas,\n"
				    + "5 - Encontrar valor: após a detecção das moedas, identifica as moedas e apresenta o valor contido na imagem,\n"				    
				    + "6 - Comprimir: comprime e salva a imagem alterada\n"
				    + "7 - Salvar: salva a imagem sem realizar compressão\n"
				    + "8 - Help: apresenta os passos para o funcionamento do programa"
				    + "9 - End: finaliza a execução da aplicação");
			
			
			
		}


		if ( comando.equals( "botaoSalva" ) && estadoDesenho ) {			
			nomeArquivo = pnCenario.escolherArquivo ( 2 );
			
			controleImagem.gravarImagem( nomeArquivo, imagemAtual, nLinImageAtual, nColImageAtual );
		}

		if ( comando.equals( "botaoReset" ) && estadoDesenho ) {
			pnCenario.limpaPainelCen( desenhoCen );
			controleImagem = new ControlarImagem( nomeArquivoImagemDada, desenhoCen );
			nLinImageAtual   = nLinImageInic;
			nColImageAtual   = nColImageInic;
			imagemAtual      = controleImagem.copiarImagem ( imagemCinza, nLinImageInic, nColImageInic );						
			controleImagem.mostrarImagemMatriz ( imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir );
			pnCenario.limpaPainelDir( desenhoDir );

//			pnCenario.ativarPainelAcao1();
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
