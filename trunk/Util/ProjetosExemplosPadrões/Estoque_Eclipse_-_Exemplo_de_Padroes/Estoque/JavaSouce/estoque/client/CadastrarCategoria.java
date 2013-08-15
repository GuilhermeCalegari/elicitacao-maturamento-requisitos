package estoque.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import estoque.business.LoggerObs;
import estoque.business.ParameterObs;
import estoque.business.ProdutoObs;
import estoque.business.SituacaoSubjectObs;
import estoque.business.bd.CategoriaBD;
import estoque.business.to.Categoria;
import estoque.business.to.Situacao;

public class CadastrarCategoria extends JDialog {

	private JPanel jContentPane = null;
	private JLabel labelCodigo = null;
	private JTextField codigo = null;
	private JLabel lblDescricao = null;
	private JTextField descricao = null;
	private JPanel jPanel = null;
	private JButton btnOk = null;
	private JButton btnSalvar = null;
	private Categoria categoria = new Categoria(); // Instancia um novo TO de categoria  
	private CategoriaBD categoriaBD = new CategoriaBD();  
	private int pOpcao = 0;	// 0 - Inclusão / 1 - Alteração	
	private JLabel jLblSituacao = null;
	private JComboBox jCboSituacao = null;
	private ArrayList<Situacao> listaSituacao = new ArrayList<Situacao>();

	// Instancia os Observadores de situação de Categoria
	SituacaoSubjectObs situacaoSubjectObs = new SituacaoSubjectObs(categoria.getSitCategoria()); // objeto Observado
	ProdutoObs produtoObs; // objeto Observador
	ParameterObs parameterObs; // objeto Observador
	LoggerObs loggerObs; // objeto Observador
	

	private JTextField getCodigo() {
		if (codigo == null) {
			codigo = new JTextField();
		}
		return codigo;
	}


	private JTextField getDescricao() {
		if (descricao == null) {
			descricao = new JTextField();
		}
		return descricao;
	}


	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.add(getBtnSalvar(), null);
			jPanel.add(getBtnOk(), null);
		}
		return jPanel;
	}


	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setText(Messages.getString("CadastrarCategoria.0")); 
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fechaTela();
				}
			});
		}
		return btnOk;
	}


	private JButton getBtnSalvar() {
		if (btnSalvar == null) {
			btnSalvar = new JButton();
			btnSalvar.setText(Messages.getString("CadastrarCategoria.1")); 
			btnSalvar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (descricao.getText().equals(""))	{ 
						JOptionPane.showConfirmDialog(btnSalvar, Messages.getString("CadastrarCategoria.3"), Messages.getString("CadastrarCategoria.4"), JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE); 
					}
					else {
						// Adiciona o TO a tabela do banco de dados
						try {
							categoria.setCategoria(descricao.getText());

							// Atualiza a situacao do produto
							categoria.setSitCategoria(listaSituacao.get(jCboSituacao.getSelectedIndex()));

							
							// Adiciona o VO a tabela do banco de dados
							if (pOpcao	==	0)   { // Inclusão
								categoria.setId(Integer.parseInt(codigo.getText()));
								categoriaBD.salvar(categoria);
							}	
							else {				// Alteração
								// Informa ao observador a situação atual da categoria
								if (categoria.getSitCategoria()	==	Situacao.estudo)	
									situacaoSubjectObs.estudo();
								else
									if (categoria.getSitCategoria()	==	Situacao.ativo)	
										situacaoSubjectObs.ativa();
									else
										if (categoria.getSitCategoria()	==	Situacao.inativo)
											situacaoSubjectObs.inativa();
								
								categoriaBD.alterar(categoria);
							}	
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						/* Limpa o registro e requisita o foco */
						descricao.setText(""); 
						descricao.requestFocusInWindow();
						
						// Cria um novo registro em Branco
						// Recupera o último registro de Categoria
						// Coloca -1 no dados.size porque o array no Java começa com 0
						// Limpa a entrada para um novo registro
						if (pOpcao	==	0) { // Inclusão
							defineNovoRegistro();
						}	
						else {
							// Força a execução do botão Ok e sai do programa
							btnOk.doClick();
						}
					}
				}
			});
		}
		return btnSalvar;
	}


	private JComboBox getJCboSituacao() {
		if (jCboSituacao == null) {
			jCboSituacao = new JComboBox();
		}
		return jCboSituacao;
	}

	public CadastrarCategoria() {
		super();
		pOpcao 	=	0;	// Inclusão
		initialize();
		
	}

	// Novo contrutor para receber a categoria como parâmetro
	public CadastrarCategoria(Categoria pCategoria) {
		super();
		this.categoria = pCategoria;

		System.out.println("Valor do produto parametro 1: " + pCategoria.getId()); 
		pOpcao	=	1;	// Alteração
		
		initialize();
	}
	

	private void initialize() {
		this.setSize(300, 200);
		this.setTitle(Messages.getString("CadastrarCategoria.7")); 
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		
		// Carrega os valores da Enumeração no combo de Situação
		for (Situacao sit : Situacao.values()) {
			listaSituacao.add(sit);
			System.out.println("Valores do Enum: " + sit); 
		}
		
		getJCboSituacao().setModel(new DefaultComboBoxModel(listaSituacao.toArray()) {
			public Object getElementAt(int index) {
				return listaSituacao.get(index);
			}
		});
		

		// Verifica o último registro
		if	(pOpcao	==	0)
			defineNovoRegistro();
		else
			alteraRegistro();
	}


	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridwidth = 3;
			gridBagConstraints6.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.gridy = 3;
			jLblSituacao = new JLabel();
			jLblSituacao.setText(Messages.getString("CadastrarCategoria.11")); 
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new java.awt.Insets(0,10,0,0);
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			lblDescricao = new JLabel();
			lblDescricao.setText(Messages.getString("CadastrarCategoria.8")); 
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.insets = new java.awt.Insets(0,10,0,0);
			gridBagConstraints1.weightx = 60.0;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			labelCodigo = new JLabel();
			labelCodigo.setText(Messages.getString("CadastrarCategoria.9")); 
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(labelCodigo, gridBagConstraints);
			jContentPane.add(getCodigo(), gridBagConstraints1);
			jContentPane.add(lblDescricao, gridBagConstraints2);
			jContentPane.add(getDescricao(), gridBagConstraints3);
			jContentPane.add(getJPanel(), gridBagConstraints4);
			jContentPane.add(jLblSituacao, gridBagConstraints5);
			jContentPane.add(getJCboSituacao(), gridBagConstraints6);
		}
		return jContentPane;
	}

	public void defineNovoRegistro() {
		// Recupera o último registro de categoria
		// Coloca -1 no dados.size porque o array no Java começa com 0
		try {
			Object ultimaCategoria =  categoriaBD.consultarUltimo(Categoria.class, "id"); 
			if	(ultimaCategoria	==	null)
				codigo.setText("1"); 
			else	
				codigo.setText(String.valueOf( (Integer) ultimaCategoria + 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alteraRegistro() {
		produtoObs = new ProdutoObs(categoria); // objeto Observador
		parameterObs = new ParameterObs(categoria); // objeto Observador
		loggerObs = new LoggerObs(categoria); // objeto Observador
		
		System.out.println("Adiciona Observadores... Categoria: "	+	categoria.getCategoria()); 
		situacaoSubjectObs.notificadorEstudoCategoria().addObserver(produtoObs.observadorEstudo());
		situacaoSubjectObs.notificadorAtivaCategoria().addObserver(produtoObs.observadorAtivo());
		situacaoSubjectObs.notificadorInativaCategoria().addObserver(produtoObs.observadorInativo());

		situacaoSubjectObs.notificadorEstudoCategoria().addObserver(parameterObs.observadorEstudo());
		situacaoSubjectObs.notificadorAtivaCategoria().addObserver(parameterObs.observadorAtivo());
		situacaoSubjectObs.notificadorInativaCategoria().addObserver(parameterObs.observadorInativo());

		situacaoSubjectObs.notificadorEstudoCategoria().addObserver(loggerObs.observadorEstudo());
		situacaoSubjectObs.notificadorAtivaCategoria().addObserver(loggerObs.observadorAtivo());
		situacaoSubjectObs.notificadorInativaCategoria().addObserver(loggerObs.observadorInativo());
		
		/* Mostra os dados do produto */
		codigo.setText(categoria.getId().toString());
		descricao.setText(categoria.getCategoria().toString());
		jCboSituacao.setSelectedItem(categoria.getSitCategoria());
		descricao.requestFocusInWindow();
		
	}

	private void fechaTela() {
		if	(pOpcao	==	1)	{	// Alteração
			System.out.println("Deleta os Observadores... Categoria: "	+	categoria.getCategoria()); 
			situacaoSubjectObs.notificadorEstudoCategoria().deleteObserver(produtoObs.observadorEstudo());
			situacaoSubjectObs.notificadorAtivaCategoria().deleteObserver(produtoObs.observadorAtivo());
			situacaoSubjectObs.notificadorInativaCategoria().deleteObserver(produtoObs.observadorInativo());
		
			situacaoSubjectObs.notificadorEstudoCategoria().deleteObserver(parameterObs.observadorEstudo());
			situacaoSubjectObs.notificadorAtivaCategoria().deleteObserver(parameterObs.observadorAtivo());
			situacaoSubjectObs.notificadorInativaCategoria().deleteObserver(parameterObs.observadorInativo());

			situacaoSubjectObs.notificadorEstudoCategoria().deleteObserver(loggerObs.observadorEstudo());
			situacaoSubjectObs.notificadorAtivaCategoria().deleteObserver(loggerObs.observadorAtivo());
			situacaoSubjectObs.notificadorInativaCategoria().deleteObserver(loggerObs.observadorInativo());
			
		}
		
		dispose();
	}	
}
