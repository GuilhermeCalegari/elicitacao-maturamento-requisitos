package estoque.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import estoque.business.bd.CategoriaBD;
import estoque.business.bd.ProdutoBD;
import estoque.business.to.Categoria;
import estoque.business.to.Produto;
import estoque.business.to.Situacao;
import estoque.util.FormatHelper;

public class CadastrarProduto extends JDialog {

	private JPanel jContentPane = null;
	private JComboBox jCboCategoria = null;
	private JLabel jLblCategoria = null;
	private JButton jBtnCadastrar = null;
	private JLabel jLblDescricao = null;
	private JTextField jTxtDescricao = null;
	private JLabel jLblCodigo = null;
	private JTextField jTxtCodigo = null;
	private JLabel jLblData = null;
	private JTextField jTxtData = null;
	private JTextField jTxtValor = null;
	private JLabel jLblValor = null;
	private JLabel jLblObs = null;
	private JScrollPane jScrollObs = null;
	private JTextArea jAreaObs = null;
	private JButton jBtnCarregar = null;
	// Cria um array list de produto
	private ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	// Cria um array list de categoria
	private ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();
	private JButton jBtnOk = null;
	private JButton jBtnSalvar = null;
	private JScrollPane jScrollImagem = null;
	private int pOpcao = 0; // 0 - Inclusão / 1 - Alteração
	private Categoria categoria = new Categoria(); // Instancia um novo TO de
													// categoria
	private CategoriaBD categoriaBD = new CategoriaBD();
	private Produto produto = new Produto(); // Instancia um novo TO de
												// categoria
	private ProdutoBD produtoBD = new ProdutoBD();
	private JComboBox jCboSituacao = null;
	private JLabel jLblSituacao = null;
	private JLabel jLblPrecoVenda = null;
	private ArrayList<Situacao> listaSituacao = new ArrayList<Situacao>();
	private JTextField jTxtPrecoVenda = null;

	private JComboBox getJCboCategoria() {
		if (jCboCategoria == null) {
			jCboCategoria = new JComboBox();
		}
		return jCboCategoria;
	}

	private JButton getJBtnCadastrar() {
		if (jBtnCadastrar == null) {
			jBtnCadastrar = new JButton();
			jBtnCadastrar.setText(Messages.getString("CadastrarProduto.0"));
			jBtnCadastrar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							// Chama o cadastro de categoria
							CadastrarCategoria cadastroCategoria = new CadastrarCategoria();
							cadastroCategoria.setModal(true);
							cadastroCategoria.setVisible(true);

							// Atualiza o combo de categoria
							atualizaValoresCombo();
						}
					});
		}
		return jBtnCadastrar;
	}

	private JTextField getJTxtDescricao() {
		if (jTxtDescricao == null) {
			jTxtDescricao = new JTextField();
		}
		return jTxtDescricao;
	}

	private JTextField getJTxtCodigo() {
		if (jTxtCodigo == null) {
			jTxtCodigo = new JTextField();
		}
		return jTxtCodigo;
	}

	private JTextField getJTxtData() {
		if (jTxtData == null) {
			jTxtData = new JTextField();
		}
		return jTxtData;
	}

	private JTextField getJTextField() {
		if (jTxtValor == null) {
			jTxtValor = new JTextField();
			jTxtValor.addFocusListener(new java.awt.event.FocusListener() {
				public void focusGained(FocusEvent e) {
				}

				public void focusLost(FocusEvent e) {
					calcularPrecoVenda();
				}

			});
		}
		return jTxtValor;
	}

	private JScrollPane getJScrollObs() {
		if (jScrollObs == null) {
			jScrollObs = new JScrollPane();
			jScrollObs.setViewportView(getJAreaObs());
		}
		return jScrollObs;
	}

	private JTextArea getJAreaObs() {
		if (jAreaObs == null) {
			jAreaObs = new JTextArea();
			jAreaObs.setBounds(new Rectangle(0, 0, 176, 111));
		}
		return jAreaObs;
	}

	private JButton getJBtnOk() {
		if (jBtnOk == null) {
			jBtnOk = new JButton();
			jBtnOk.setText(Messages.getString("CadastrarProduto.1"));
			jBtnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jBtnOk;
	}

	private JButton getJBtnSalvar() {
		if (jBtnSalvar == null) {
			jBtnSalvar = new JButton();
			jBtnSalvar.setText(Messages.getString("CadastrarProduto.2"));
			jBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Chama o método salvar
					salvar();
				}
			});
		}
		return jBtnSalvar;
	}

	private JComboBox getJCboSituacao() {
		if (jCboSituacao == null) {
			jCboSituacao = new JComboBox();
		}
		return jCboSituacao;
	}

	public CadastrarProduto() {
		super();
		pOpcao = 0; // Inclusão
		initialize();
	}

	// Novo contrutor para receber o produto como parâmetro
	public CadastrarProduto(Produto pProduto) {
		super();
		this.produto = pProduto;

		System.out.println("Valor do produto parametro 1: " + pProduto.getId());
		int codigo = pProduto.getId();
		// Le o registro para ter acesso a todos os campos
		try {
			// Tem que converter para produto, pois retorna um objeto
			produto = (Produto) produtoBD.consultar(Produto.class, codigo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		pOpcao = 1; // Alteração

		initialize();
	}

	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setSize(477, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(Messages.getString("CadastrarProduto.5"));

		// Atualiza valores do combo com um Array de Categoria
		atualizaValoresCombo();

		// Carrega os valores da Enumeração no combo de Situação
		for (Situacao sit : Situacao.values()) {
			listaSituacao.add(sit);
			System.out.println("Valores do Enum: " + sit);
		}
		getJCboSituacao().setModel(
				new DefaultComboBoxModel(listaSituacao.toArray()) {
					public Object getElementAt(int index) {
						return listaSituacao.get(index);
					}
				});

		// Verifica o último registro
		System.out.println("Valor do produto parametro: " + produto);
		if (pOpcao == 0)
			defineNovoRegistro();
		else
			alteraRegistro();
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 5;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.gridwidth = 2;
			gridBagConstraints13.gridx = 2;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 0;
			gridBagConstraints32.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints32.anchor = GridBagConstraints.EAST;
			gridBagConstraints32.gridy = 5;
			jLblPrecoVenda = new JLabel();
			jLblPrecoVenda.setText(Messages.getString("CadastrarProduto.34"));
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 0;
			gridBagConstraints22.anchor = GridBagConstraints.EAST;
			gridBagConstraints22.insets = new Insets(0, 0, 0, 5);
			gridBagConstraints22.gridy = 3;
			jLblSituacao = new JLabel();
			jLblSituacao.setText(Messages.getString("CadastrarProduto.35"));
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridy = 3;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridwidth = 3;
			gridBagConstraints16.gridx = 2;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 4;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.gridwidth = 3;
			gridBagConstraints12.gridx = 2;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 4;
			gridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints18.insets = new java.awt.Insets(0, 0, 0, 0);
			gridBagConstraints18.weightx = 0.0;
			gridBagConstraints18.weighty = 0.0;
			gridBagConstraints18.gridy = 7;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 5;
			gridBagConstraints17.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints17.insets = new java.awt.Insets(0, 10, 0, 0);
			gridBagConstraints17.gridy = 7;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 5;
			gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.insets = new java.awt.Insets(0, 10, 0, 0);
			gridBagConstraints15.gridy = 4;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints14.gridy = 6;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.weighty = 1.0;
			gridBagConstraints14.gridwidth = 3;
			gridBagConstraints14.gridheight = 1;
			gridBagConstraints14.gridx = 2;
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.gridx = 0;
			gridBagConstraints111.anchor = java.awt.GridBagConstraints.NORTH;
			gridBagConstraints111.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints111.gridy = 6;
			jLblObs = new JLabel();
			jLblObs.setText(Messages.getString("CadastrarProduto.7"));
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 4;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 4;
			gridBagConstraints7.insets = new java.awt.Insets(0, 10, 0, 5);
			gridBagConstraints7.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints7.gridy = 4;
			jLblValor = new JLabel();
			jLblValor.setText("PreÃ§o Custo:");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 4;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridwidth = 0;
			gridBagConstraints6.gridx = 5;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridy = 4;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridwidth = 1;
			gridBagConstraints5.gridx = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints4.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints4.gridy = 4;
			jLblData = new JLabel();
			jLblData.setText(Messages.getString("CadastrarProduto.10"));
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.gridx = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints21.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints21.gridy = 1;
			jLblCodigo = new JLabel();
			jLblCodigo.setText(Messages.getString("CadastrarProduto.11"));
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.gridwidth = 4;
			gridBagConstraints11.gridx = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints3.gridy = 2;
			jLblDescricao = new JLabel();
			jLblDescricao.setText(Messages.getString("CadastrarProduto.12"));
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 5;
			gridBagConstraints1.insets = new java.awt.Insets(0, 10, 0, 0);
			gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
			gridBagConstraints2.gridy = 0;
			jLblCategoria = new JLabel();
			jLblCategoria.setText(Messages.getString("CadastrarProduto.13"));
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridwidth = 3;
			gridBagConstraints.gridx = 2;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJCboCategoria(), gridBagConstraints);
			jContentPane.add(jLblCategoria, gridBagConstraints2);
			jContentPane.add(getJBtnCadastrar(), gridBagConstraints1);
			jContentPane.add(jLblDescricao, gridBagConstraints3);
			jContentPane.add(getJTxtDescricao(), gridBagConstraints11);
			jContentPane.add(jLblCodigo, gridBagConstraints21);
			jContentPane.add(getJTxtCodigo(), gridBagConstraints31);
			jContentPane.add(jLblData, gridBagConstraints4);
			jContentPane.add(getJTxtData(), gridBagConstraints5);
			jContentPane.add(getJTextField(), gridBagConstraints6);
			jContentPane.add(jLblValor, gridBagConstraints7);
			jContentPane.add(jLblObs, gridBagConstraints111);
			jContentPane.add(getJScrollObs(), gridBagConstraints14);
			jContentPane.add(getJBtnOk(), gridBagConstraints17);
			jContentPane.add(getJBtnSalvar(), gridBagConstraints18);
			jContentPane.add(getJCboSituacao(), gridBagConstraints16);
			jContentPane.add(jLblSituacao, gridBagConstraints22);
			jContentPane.add(jLblPrecoVenda, gridBagConstraints32);
			jContentPane.add(getJTextField2(), gridBagConstraints13);
		}
		return jContentPane;
	}

	public void atualizaValoresCombo() {
		// Carrega a lista de categoria
		listaCategoria = new ArrayList<Categoria>(); // Inicializa a lista
		try {
			listaCategoria = (ArrayList<Categoria>) categoriaBD
					.listar(Categoria.class);
			System.out.println("Carregou Combo de Categoria");

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * OBS: Até o momento a lista de valores do combo nos exemplos que
		 * trabalhamos era determinada por um array de String[]. O exemplo acima
		 * implementa o model para o combo, assim como fazemos com o JTable.
		 * Dessa forma, podemos utilizar um ArrayList de objetos e especificar
		 * qual valor queremos que seja exibido. Na implementação acima,
		 * indicamos que o atributo categoria do array de objetos Categoria será
		 * o valor exibido ao usuário.
		 */
		getJCboCategoria().setModel(
				new DefaultComboBoxModel(listaCategoria.toArray()) {
					public Object getElementAt(int index) {
						return listaCategoria.get(index).getCategoria();
					}
				});
	}

	public void defineNovoRegistro() {

		// Recupera o último registro de produto
		// Coloca -1 no dados.size porque o array no Java começa com 0
		try {
			Object ultimoProduto = produtoBD.consultarUltimo(Produto.class,
					"id");
			if (ultimoProduto == null)
				jTxtCodigo.setText("1");
			else
				jTxtCodigo.setText(String.valueOf((Integer) ultimoProduto + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Limpa o registro e requisita o foco */
		jTxtDescricao.setText("");
		jTxtData.setText("");
		jTxtValor.setText("");
		jTxtPrecoVenda.setText("");
		jAreaObs.setText("");
		jTxtDescricao.requestFocusInWindow();
	}

	public void alteraRegistro() {
		/* Mostra os dados do produto */
		jTxtCodigo.setText(produto.getId().toString());
		jTxtDescricao.setText(produto.getDescricao());
		jTxtData.setText(FormatHelper.formataData(produto.getData()));
		jTxtValor.setText(FormatHelper.df.format(produto.getValor()));
		jTxtPrecoVenda.setText(FormatHelper.df.format(produto.getPrecoVenda()));

		System.out.println("Observação:  " + produto.getObservacao());

		jAreaObs.setText(produto.getObservacao());
		jCboCategoria.setSelectedItem(produto.getCategoria().getCategoria());
		jCboSituacao.setSelectedItem(produto.getSitProduto());
		jTxtDescricao.requestFocusInWindow();
	}

	public void salvar() {
		try {
			// Valida os dados de entrada
			if (!validacao()) {
				return;
			}

			/* Salvar os dados */
			produto = new Produto();
			produto.setId(Integer.valueOf(jTxtCodigo.getText()));
			produto.setDescricao(jTxtDescricao.getText());
			produto.setData(FormatHelper.verificaData(jTxtData.getText()));
			produto.setValor(FormatHelper.verificaNumero(jTxtValor.getText()));
			produto.setPrecoVenda(FormatHelper.verificaNumero(jTxtPrecoVenda
					.getText()));

			// Atualiza a categoria do produto
			produto.setCategoria(listaCategoria.get(jCboCategoria
					.getSelectedIndex()));

			// Atualiza a situacao do produto
			produto.setSitProduto(listaSituacao.get(jCboSituacao
					.getSelectedIndex()));
			produto.setObservacao(jAreaObs.getText());

			// Atualiza o produto no array de produtos
			atualizaProduto();

			// Limpa a entrada para um novo registro
			if (pOpcao == 0) { // Inclusão
				defineNovoRegistro();
			} else {
				// Força a execução do botão Ok e sai do programa
				jBtnOk.doClick();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public Boolean validacao() {

		// Valida se a descricao foi informada
		if (jTxtDescricao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.26"));
			return false;
		}

		// Valida se a data foi informada
		if (jTxtData.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.28"));
			return false;
		}
		// Valida se o valor foi informado
		if (jTxtValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.30"));
			return false;
		}

		// Valida se o preco de Venda foi calculado
		if (jTxtPrecoVenda.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"O Preço de Venda não foi calculado");
			return false;
		}

		if (FormatHelper.verificaData(jTxtData.getText()) == null) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.31"));
			return false;
		}

		if (FormatHelper.verificaNumero(jTxtValor.getText()) == null) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.32")
					+ Messages.getLocale());
			return false;
		}

		return true;
	}

	private void atualizaProduto() {
		// Adiciona o TO a tabela do banco de dados
		try {
			if (pOpcao == 0) // Inclusão
				produtoBD.salvar(produto);
			else
				// Alteração
				produtoBD.alterar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTextField getJTextField2() {
		if (jTxtPrecoVenda == null) {
			jTxtPrecoVenda = new JTextField();
			jTxtPrecoVenda.setForeground(Color.blue);
			jTxtPrecoVenda.setEnabled(false);
		}
		return jTxtPrecoVenda;
	}

	private void calcularPrecoVenda() {
		// Valida se o valor foi informado
		if (jTxtValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.30"));
			jTxtValor.requestFocusInWindow();
		} else if (FormatHelper.verificaNumero(jTxtValor.getText()) == null) {
			JOptionPane.showMessageDialog(null, Messages
					.getString("CadastrarProduto.32")
					+ Messages.getLocale());
			jTxtValor.requestFocusInWindow();
		}
		// Calcula o preço de venda do Produto de acordo com a Política de Preço
		// (Strategy)
		else {
			System.out.println("Valor Custo: " + jTxtValor.getText());
			produto.setValor(FormatHelper.verificaNumero(jTxtValor.getText()));
			BigDecimal precoVenda = produtoBD.calcularPrecoVenda(produto
					.getValor());
			jTxtPrecoVenda.setText(FormatHelper.df.format(precoVenda));
			System.out.println("Valor Venda: " + jTxtPrecoVenda.getText());
		}
	}

}
