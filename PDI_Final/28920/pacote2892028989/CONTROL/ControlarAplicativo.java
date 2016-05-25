package pacote2892028989.CONTROL;

	import java.awt.*;
	import java.awt.event.*;
import java.awt.image.ImagingOpException;
import java.util.ResourceBundle.Control;

import pacote2892028989.VIEW.*;

public class ControlarAplicativo implements ActionListener {

	private ControlarTransformacoes transformacoes = new ControlarTransformacoes();
	private MontarPainelInicial pnCenario;
	private Graphics            desenhoCen, desenhoDir;
	private ControlarImagem     controleImagem;
	private String              nomeArquivoImagemDada;
	private char[][]            imagemCinza;
	private char[][]            imagemAtual;

	private int                 nLinImageAtual, nColImageAtual;
	private int                 nLinImageInic, nColImageInic;
	private boolean             estadoDesenho;

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

		if ( comando.equals( "zoom" ) )  {
			
			imagemCinza = transformacoes.bicubica(imagemAtual, nColImageAtual, nLinImageAtual, 2);
			controleImagem.mostrarImagemMatriz(imagemCinza,2* nLinImageAtual, 2* nColImageAtual, desenhoDir);
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

	}

	//*******************************************************************************************
}
