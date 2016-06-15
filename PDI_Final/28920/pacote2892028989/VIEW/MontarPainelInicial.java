package pacote2892028989.VIEW;

	import java.awt.*;
	import java.io.File;
	import javax.swing.*;
	import pacote2892028989.CONTROL.ControlarAplicativo;

public class MontarPainelInicial  {

	private JFrame   baseFrame;
	private JPanel   basePanel;
	private JPanel   outputPanel, outputPanelEsq, outputPanelCen, outputPanelDir;
	private JPanel   controlePanelAcao1;
	private JPanel   controlePanelAcao2;
	private JPanel   controlePanelAcao3;
	private JPanel   controlePanelVisualImagens;
	private JPanel   controlePanelAcao4;

	private JButton  btAcao3;
	private JButton  btAcao1;
	private JButton  btSalva;
	private JButton  btReset;
	private JButton  btAcao4;

	private JRadioButton btAcao31; 
	private JRadioButton btAcao32;
	private ButtonGroup  btRdAcao3;

	private JRadioButton  btAcao11;
	private JRadioButton  btAcao12;
	private JRadioButton  btAcao13;
	private JRadioButton  btAcao14;
	private JRadioButton  btAcao15;
	private ButtonGroup   btRdAcao1;

	private JRadioButton  btAcao21;
	private JRadioButton  btAcao22;
	private JRadioButton  btAcao23;
	private JRadioButton  btAcao24;
	private JRadioButton  btAcao25;
	private JRadioButton  btAcao26;
	private ButtonGroup   btRdAcao2;

	private JRadioButton  btVisualNewImg;
	private JRadioButton  btVisualAllImg;
	private ButtonGroup   btRdVisualImg;

	private JRadioButton btAcao41; 
	private JRadioButton btAcao42;
	private ButtonGroup  btRdAcao4;

	private Graphics      desenhoCen;
	private Graphics      desenhoDir;

	//*******************************************************************************************
	public MontarPainelInicial( ControlarAplicativo controlePrograma )
	{
		JPanel  buttonPanel;
		JPanel  titlePanel;
		JPanel  acao3Panel;
		JPanel  acao1Panel;
		JPanel  acao2Panel;
		JPanel  visualImagensPanel;
		JPanel  acao4Panel;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout( new BoxLayout( baseFrame.getContentPane(), BoxLayout.Y_AXIS) );
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // FITS PANEL TO THE ACTUAL MONITOR SIZE
		baseFrame.setUndecorated(true);  // TURN OFF ALL THE PANEL BORDERS 

		basePanel = new JPanel();
		basePanel.setLayout( new BorderLayout() );

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize( new Dimension ( 0, 50 ) );
		titlePanel.setBackground( Color.gray );

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout( new BorderLayout() );

		outputPanelEsq = new JPanel();
		outputPanelEsq.setPreferredSize( new Dimension ( 130, 0 ) );
		outputPanelEsq.setLayout( new BoxLayout (outputPanelEsq, BoxLayout.Y_AXIS));
		outputPanelEsq.setBackground( Color.lightGray );

		outputPanelCen = new JPanel();
		outputPanelCen.setBackground( new Color ( 220, 220, 210 ) );
		outputPanelCen.setLayout( new BorderLayout() );

		outputPanelDir = new JPanel();
		outputPanelDir.setBackground( new Color ( 210, 200, 200 ) );
		outputPanelDir.setPreferredSize( new Dimension ( 580, 0 ) );
		outputPanelDir.setLayout( new BorderLayout() );

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize( new Dimension ( 0, 50 ) );
		buttonPanel.setBackground( Color.gray );

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel( "IMAGE LAB PROCESSING");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 25));
		titlePanel.add(titulo);

		// ADDING BUTTONS
		addAButton ( "New Image", "botaoImagem", buttonPanel, true, controlePrograma );
		btReset = addAButton ( "Reset", "botaoReset", buttonPanel, false, controlePrograma );
		btAcao1 = addAButton ( "Zoom bicubico", "zoom", buttonPanel, false, controlePrograma );
		btAcao3 = addAButton ( "Canny", "botaoAcao3", buttonPanel, false, controlePrograma );
		btAcao4 = addAButton ( "Transformada de Hough", "botaoAcao4", buttonPanel, false, controlePrograma );
		btSalva = addAButton ( "Save", "botaoSalva", buttonPanel, false, controlePrograma );
		addAButton ( "END", "botaoFim", buttonPanel, true, controlePrograma );

		// ADDING RADIO BUTTON PARA CONTROLE DA ACAO3
		controlePanelAcao3 = new JPanel();
		controlePanelAcao3.setBackground( Color.lightGray );
		controlePanelAcao3.setMaximumSize( new Dimension ( 130, 60 ) );
		outputPanelEsq.add( controlePanelAcao3 );

		btAcao31 = new JRadioButton ( "Acao 31", true );
		btAcao32 = new JRadioButton ( "Acao 32", false );

		btRdAcao3 = new ButtonGroup();
		btRdAcao3.add(btAcao31);
		btRdAcao3.add(btAcao32);

		btAcao31.addActionListener(controlePrograma);
		btAcao32.addActionListener(controlePrograma);

		acao3Panel = new JPanel();
		acao3Panel.setPreferredSize( new Dimension ( 120, 55 ) );
		acao3Panel.setLayout(new GridLayout(2, 1));

		acao3Panel.add( btAcao31 );
		acao3Panel.add( btAcao32 );

		acao3Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Acao3"));

		controlePanelAcao3.add(acao3Panel);
		controlePanelAcao3.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DA ACAO 1
		controlePanelAcao1 = new JPanel();
		controlePanelAcao1.setBackground( Color.lightGray );
		controlePanelAcao1.setMaximumSize( new Dimension ( 130, 115 ) );
		outputPanelEsq.add( controlePanelAcao1 );

		btAcao11 = new JRadioButton ( " Acao 11 ", true );
		btAcao12 = new JRadioButton ( " Acao 12 ", false );
		btAcao13 = new JRadioButton ( " Acao 13 ", false );
		btAcao14 = new JRadioButton ( " Acao 14 ", false );
		btAcao15 = new JRadioButton ( " Acao 15 ", false );

		btRdAcao1 = new ButtonGroup();
		btRdAcao1.add(btAcao11 );
		btRdAcao1.add(btAcao12 );		
		btRdAcao1.add(btAcao13 );		
		btRdAcao1.add(btAcao14 );		
		btRdAcao1.add(btAcao15 );

		btAcao11.addActionListener(controlePrograma);
		btAcao12.addActionListener(controlePrograma);
		btAcao13.addActionListener(controlePrograma);
		btAcao14.addActionListener(controlePrograma);
		btAcao15.addActionListener(controlePrograma);

		acao1Panel = new JPanel();
		acao1Panel.setPreferredSize( new Dimension ( 120, 110 ) );
		acao1Panel.setLayout(new GridLayout(5, 1));

		acao1Panel.add( btAcao11 );
		acao1Panel.add( btAcao12 );
		acao1Panel.add( btAcao13 );
		acao1Panel.add( btAcao14 );
		acao1Panel.add( btAcao15 );

		acao1Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ACAO 1"));

		controlePanelAcao1.add(acao1Panel);
		controlePanelAcao1.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DA ACAO 2
		controlePanelAcao2 = new JPanel();
		controlePanelAcao2.setBackground( Color.lightGray );
		controlePanelAcao2.setMaximumSize( new Dimension ( 130, 135 ) );
		outputPanelEsq.add( controlePanelAcao2 );

		btAcao21 = new JRadioButton ( " Acao21 ", true );
		btAcao22 = new JRadioButton ( " Acao22 ", false );
		btAcao23 = new JRadioButton ( " Acao23 ", false );
		btAcao24 = new JRadioButton ( " Acao24 ", false );
		btAcao25 = new JRadioButton ( " Acao25 ", false );
		btAcao26 = new JRadioButton ( " Acao26 ", false );

		btRdAcao2 = new ButtonGroup();
		btRdAcao2.add(btAcao21);
		btRdAcao2.add(btAcao22);
		btRdAcao2.add(btAcao23);
		btRdAcao2.add(btAcao24);
		btRdAcao2.add(btAcao25);
		btRdAcao2.add(btAcao26);

		btAcao21.addActionListener(controlePrograma);
		btAcao22.addActionListener(controlePrograma);
		btAcao23.addActionListener(controlePrograma);
		btAcao24.addActionListener(controlePrograma);
		btAcao25.addActionListener(controlePrograma);
		btAcao26.addActionListener(controlePrograma);

		btAcao21.setActionCommand( "botaoAcao21" );
		btAcao22.setActionCommand( "botaoAcao22" );
		btAcao23.setActionCommand( "botaoAcao23" );
		btAcao24.setActionCommand( "botaoAcao24" );
		btAcao25.setActionCommand( "botaoAcao25" );
		btAcao26.setActionCommand( "botaoAcao26" );

		acao2Panel = new JPanel();
		acao2Panel.setPreferredSize( new Dimension ( 120, 130 ) );
		acao2Panel.setLayout(new GridLayout(6, 1));

		acao2Panel.add( btAcao21 );
		acao2Panel.add( btAcao22 );
		acao2Panel.add( btAcao23 );
		acao2Panel.add( btAcao24 );
		acao2Panel.add( btAcao25 );
		acao2Panel.add( btAcao26 );

		acao2Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ACAO 2"));

		controlePanelAcao2.add(acao2Panel);
		controlePanelAcao2.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DO TIPO DA ACAO 4
		controlePanelAcao4 = new JPanel();
		controlePanelAcao4.setBackground( Color.lightGray );
		controlePanelAcao4.setMaximumSize( new Dimension ( 130, 60 ) );
		outputPanelEsq.add( controlePanelAcao4 );

		btAcao41 = new JRadioButton ( "  Acao 41 ", true );
		btAcao42 = new JRadioButton ( "  Acao 42 ", false );

		btRdAcao4 = new ButtonGroup();
		btRdAcao4.add(btAcao41);
		btRdAcao4.add(btAcao42);

		btAcao41.addActionListener(controlePrograma);
		btAcao42.addActionListener(controlePrograma);

		acao4Panel = new JPanel();
		acao4Panel.setPreferredSize( new Dimension ( 120, 55 ) );
		acao4Panel.setLayout(new GridLayout(2, 1));

		acao4Panel.add( btAcao41 );
		acao4Panel.add( btAcao42 );

		acao4Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ACAO 4"));

		controlePanelAcao4.add(acao4Panel);
		controlePanelAcao4.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DA VISUALIZACAO DAS IMAGENS
		controlePanelVisualImagens = new JPanel();
		controlePanelVisualImagens.setBackground( Color.lightGray );
		controlePanelVisualImagens.setMaximumSize( new Dimension ( 130, 65 ) );
		outputPanelEsq.add( controlePanelVisualImagens );

		btVisualNewImg = new JRadioButton ( " new image", true );
		btVisualAllImg = new JRadioButton ( "transitions", false );

		btRdVisualImg = new ButtonGroup();
		btRdVisualImg.add(btVisualNewImg);
		btRdVisualImg.add(btVisualAllImg);

		btVisualNewImg.addActionListener(controlePrograma);
		btVisualAllImg.addActionListener(controlePrograma);

		visualImagensPanel = new JPanel();
		visualImagensPanel.setPreferredSize( new Dimension ( 120, 55 ) );
		visualImagensPanel.setLayout(new GridLayout(2, 1));

		visualImagensPanel.add( btVisualNewImg );
		visualImagensPanel.add( btVisualAllImg );

		visualImagensPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Image Control"));

		controlePanelVisualImagens.add(visualImagensPanel);
		controlePanelVisualImagens.setVisible(false);

		// VISIBLE PANELS
		outputPanel.add( outputPanelEsq, BorderLayout.LINE_START );
		outputPanel.add( outputPanelCen, BorderLayout.CENTER );
		outputPanel.add( outputPanelDir, BorderLayout.LINE_END );

		basePanel.add( titlePanel, BorderLayout.PAGE_START );
		basePanel.add( outputPanel, BorderLayout.CENTER );
		basePanel.add( buttonPanel, BorderLayout.PAGE_END );

		baseFrame.add(basePanel);
		baseFrame.setVisible(true);
	}

	//*******************************************************************************************
	public void limpaPainelCen ( Graphics desenho )
	{
		outputPanelCen.removeAll();
		outputPanelCen.update( desenho );
	}

	//*******************************************************************************************
	public void limpaPainelDir ( Graphics desenho )
	{
		outputPanelDir.removeAll();
		outputPanelDir.update( desenho );
	}

	//*******************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA

	private JButton addAButton( String              textoBotao,
                                String              textoControle,
                                Container           container,
                                boolean             estado,
                                ControlarAplicativo controlePrograma
                              ) 
	{
		JButton botao;

		botao = new JButton( textoBotao );
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setEnabled(estado);

		botao.setActionCommand( textoControle );

		botao.addActionListener( controlePrograma );

		return ( botao );
	}

	//*******************************************************************************************
	public void mudarBotoes() 
	{
		btAcao3.setEnabled(true);
		btAcao1.setEnabled(true);
		btSalva.setEnabled(true);
		btReset.setEnabled(true);
		btAcao4.setEnabled(true);
		controlePanelAcao3.setVisible(true);
		controlePanelAcao1.setVisible(true);
		controlePanelAcao2.setVisible(true);
		controlePanelVisualImagens.setVisible(true);
		controlePanelAcao4.setVisible(true);
	}

	//*******************************************************************************************
	// METODO PARA APRESENTAR O MENU DE ESCOLHA DE ARQUIVOS
	// 1 - PARA LEITURA
	// 2 - PARA GRAVACAO
	
	public String escolherArquivo ( int operacao )   
	{
		int          retorno;
		String       caminhoArquivo;
		JFileChooser arquivo;

		retorno = 0;
		arquivo = new JFileChooser(new File("."));
		int aux = 1;
		// TIPO DE OPERACAO A SER REALIZADA
		caminhoArquivo = null;
		switch ( operacao ) {
		case 1:
			retorno = JFileChooser.APPROVE_OPTION;
			aux = 0;
			break;

		case 2:
			retorno = arquivo.showSaveDialog(null);
		}

		// OPERACAO
//		caminhoArquivo = null;
		if(aux==1)
		{
			if(retorno == JFileChooser.APPROVE_OPTION){
				try {
					caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
				}	catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("erro: " + e);
				}
			} 
		}
		if(aux==0)
			caminhoArquivo = "cc.jpg";
		
		return ( caminhoArquivo );
	}

	//*******************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO

	public void showPanel() 
	{
		basePanel.setVisible(true);
	}

	//*******************************************************************************************
	public void ativarPainelAcao3()
	{
		controlePanelAcao3.setVisible(true);
	}

	//*******************************************************************************************
	public void desativarPainelAcao3()
	{
		controlePanelAcao3.setVisible(false);
	}

	//*******************************************************************************************
	public void ativarPainelAcao1()
	{
		controlePanelAcao1.setVisible(true);
	}

	//*******************************************************************************************
	public void desativarPainelAcao1()
	{
		controlePanelAcao1.setVisible(false);
	}

	//*******************************************************************************************
	public void iniciarGraphics()
	{
		desenhoCen = outputPanelCen.getGraphics();
		desenhoDir = outputPanelDir.getGraphics();
	}

	//*******************************************************************************************
	public Graphics getDesenhoC()
	{
		return ( desenhoCen );
	}

	//*******************************************************************************************
	public Graphics getDesenhoD()
	{
		return ( desenhoDir );
	}

	//******************************************************************************************
	public int getTipoVisualImage() 
	{
		int tipo;
		
		tipo = 1;
		if ( btVisualAllImg.isSelected() ) tipo = 2;

		return ( tipo );
	}

	//******************************************************************************************
	public void resetaSistema()
	{
		btAcao11.setSelected(true);
		btAcao21.setSelected(true);
		btAcao31.setSelected(true);
		btVisualNewImg.setSelected(true);
	}
	
	//******************************************************************************************
}
