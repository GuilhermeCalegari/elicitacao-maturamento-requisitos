package estoque.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import estoque.business.bd.ProdutoBD;
import estoque.business.to.Produto;
import estoque.util.FormatHelper;

public class ListarProduto extends JFrame {

	private JPanel jContentPane = null;
	private JTextField descricao = null;
	private JScrollPane jScrollPane = null;
	private JTable gridProduto = null;
	private JButton btnPesquisar = null;
	private JButton btnIncluir = null;
	private JButton btnExcluir = null;
	private JLabel labelDescricao = null;
	private ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	private ProdutoBD produtoBD = null;

	private JTextField getDescricao() {
		if (descricao == null) {
			descricao = new JTextField();
		}
		return descricao;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getGridProduto());

		}
		return jScrollPane;
	}

	String[] colunaProduto = {
			Messages.getString("ListarProduto.0"), Messages.getString("ListarProduto.1"), "Situação", Messages.getString("ListarProduto.2"), Messages.getString("ListarProduto.3"), Messages.getString("ListarProduto.17"), Messages.getString("ListarProduto.18") }; 

	public final int ID_PRODUTO = 0;
	public final int PRODUTO = 1;
	public final int SITUACAO = 2;
	public final int DATA = 3;
	public final int VALOR = 4;
	public final int CATEGORIA = 5;
	public final int DESC_CATEGORIA = 6;

	private JButton jBtnAlterar = null;

	private JTable getGridProduto() {
		if (gridProduto == null) {
			gridProduto = new JTable(new AbstractTableModel() {

				public int getRowCount() {
					// TODO Auto-generated method stub
					return listaProduto.size();
				}

				public int getColumnCount() {
					// TODO Auto-generated method stub
					return colunaProduto.length;
				}

				public Object getValueAt(int rowIndex, int columnIndex) {
					if (columnIndex == ID_PRODUTO) {
						return listaProduto.get(rowIndex).getId();
					} else if (columnIndex == PRODUTO) {
						return listaProduto.get(rowIndex).getDescricao();
					} else if (columnIndex == SITUACAO) {
						return listaProduto.get(rowIndex).getSitProduto();
					} else if (columnIndex == DATA) {
						// Formata a data
						return FormatHelper.formataData(listaProduto.get(
								rowIndex).getData());
					} else if (columnIndex == VALOR) {
						return listaProduto.get(rowIndex).getValor();
					} else if (columnIndex == CATEGORIA) {
						return listaProduto.get(rowIndex).getCategoria()
								.getId();
					} else if (columnIndex == DESC_CATEGORIA) {
						return listaProduto.get(rowIndex).getCategoria()
								.getCategoria();
					} else
						return null;
				}

				// getColumnName
				// Retorna o "título" da coluna, de acordo com o índice recebido
				// como parâmetro
				public String getColumnName(int column) {
					// TODO Auto-generated method stub
					return colunaProduto[column];
				}

				// isCellEditable
				// Retorna um boolean indicando se a coluna é editável, com base
				// nos parâmetros linha e coluna recebidos
				// Quando não implementado, não permite editar as colunas
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					// TODO Auto-generated method stub
					if (columnIndex == PRODUTO)
						return true;

					return false;
				}
			});

			// Define para selecionar um único registro no GRID
			gridProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// Trata o duplo clique
			gridProduto.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int linha = 0;

					if (e.getClickCount() == 2) {
						// Verifica qual é a linha
						linha = gridProduto.rowAtPoint(e.getPoint());
						if (linha != -1) {
							listaProduto.get(linha).getId();
							// JOptionPane.showMessageDialog(null,
							// "Verificar detalhe para o registro de código :" +
							// codigo);
							// Mostra os dados de detalhe
							jBtnAlterar.doClick();
						}
					}
				}
			});

		}
		return gridProduto;
	}

	private JButton getBtnPesquisar() {
		if (btnPesquisar == null) {
			btnPesquisar = new JButton();
			btnPesquisar.setText(Messages.getString("ListarProduto.4"));
			btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					pesquisar();
				}

			});
		}
		return btnPesquisar;
	}

	private JButton getBtnIncluir() {
		if (btnIncluir == null) {
			btnIncluir = new JButton();
			btnIncluir.setText(Messages.getString("ListarProduto.9"));
			btnIncluir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					incluir();
				}
			});
		}
		return btnIncluir;
	}

	private JButton getBtnExcluir() {
		if (btnExcluir == null) {
			btnExcluir = new JButton();
			btnExcluir.setText(Messages.getString("ListarProduto.10"));
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (gridProduto.getSelectedRow() != -1) {

						String mensagem = MessageFormat.format(Messages
								.getString("ListarProduto.11"),
								new Object[] { listaProduto.get(
										gridProduto.getSelectedRow())
										.getDescricao() });

						/*
						 * Mostra uma mensagem e so entra no bloco do IF se a
						 * resposta for YES
						 */
						if (JOptionPane
								.showConfirmDialog(
										btnExcluir,
										mensagem,
										Messages.getString("ListarProduto.12"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) { 
							// Exclui o registro do banco de dados
							try {
								// Pega o Id do registro do Browse e exclui do
								// banco de dados
								Produto produto = new Produto();
								produto.setId(listaProduto.get(
										gridProduto.getSelectedRow()).getId());
								produtoBD.apagar(produto);

								// Remove o TO do array de dados
								listaProduto.remove(gridProduto
										.getSelectedRow());

								// Atualiza as informações do Grid
								((AbstractTableModel) gridProduto.getModel())
										.fireTableDataChanged();

							} catch (Exception e1) {
								e1.printStackTrace();
							}

							// Atualiza as informações do Grid
							((AbstractTableModel) gridProduto.getModel())
									.fireTableDataChanged();
						}
					}

				}
			});
		}
		return btnExcluir;
	}

	private JButton getJBtnAlterar() {
		if (jBtnAlterar == null) {
			jBtnAlterar = new JButton();
			jBtnAlterar.setText(Messages.getString("ListarProduto.13"));
			jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (gridProduto.getSelectedRow() != -1) {
						alterar();
					}
				}
			});
		}
		return jBtnAlterar;
	}

	public ListarProduto() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(589, 252);
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle(Messages.getString("ListarProduto.14"));
		this.setVisible(true);

		/* Carrega a Lista do Grid */
		try {
			produtoBD = new ProdutoBD();
			listaProduto = (ArrayList<Produto>) produtoBD.listar(Produto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 2;
			gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.gridy = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 4;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.gridwidth = 1;
			gridBagConstraints6.gridheight = 1;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 0;
			labelDescricao = new JLabel();
			labelDescricao.setText(Messages.getString("ListarProduto.15"));
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTH;
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 4;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getDescricao(), gridBagConstraints);
			jContentPane.add(getJScrollPane(), gridBagConstraints1);
			jContentPane.add(getBtnPesquisar(), gridBagConstraints2);
			jContentPane.add(getBtnIncluir(), gridBagConstraints3);
			jContentPane.add(getBtnExcluir(), gridBagConstraints4);
			jContentPane.add(labelDescricao, gridBagConstraints5);
			jContentPane.add(getJBtnAlterar(), gridBagConstraints16);
		}
		return jContentPane;
	}

	public void incluir() {
		CadastrarProduto cadastrarProduto = new CadastrarProduto();
		cadastrarProduto.setModal(true);
		cadastrarProduto.setVisible(true);

		System.out.println("" + listaProduto.size());

		// Reposiciona o objeto de Produto, após a pesquisa
		try {
			listaProduto = (ArrayList<Produto>) produtoBD.listar(Produto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Atualiza o Grid
		((AbstractTableModel) gridProduto.getModel()).fireTableDataChanged();
	}

	public void alterar() {
		// Passa o produto como parâmetro
		CadastrarProduto cadastrarProduto = new CadastrarProduto(listaProduto
				.get(gridProduto.getSelectedRow()));
		cadastrarProduto.setModal(true);
		cadastrarProduto.setVisible(true);

		// Reposiciona o objeto de Produto, após a pesquisa
		try {
			listaProduto = (ArrayList<Produto>) produtoBD.listar(Produto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Atualiza o Grid
		((AbstractTableModel) gridProduto.getModel()).fireTableDataChanged();
	}

	private void pesquisar() {
		// Inicializa novamente o listaProduto para uma nova posição de memória
		listaProduto = new ArrayList<Produto>();

		try {
			if (descricao.getText().equals("")) {
				// Pesquisa todos os registros de categoria
				listaProduto = (ArrayList<Produto>) produtoBD
						.listar(Produto.class);
				System.out.println(Messages.getString("ListarProduto.5")
						+ listaProduto.size());
			} else {
				// Pesquisa o produto de acordo com o parâmetro informado
				listaProduto = (ArrayList<Produto>) produtoBD.pesquisar(
						Produto.class,
						"descricao", descricao.getText(), "descricao"); 
				System.out.println(Messages.getString("ListarProduto.5")
						+ listaProduto.size());
			}

			// Atualiza o Grid
			((AbstractTableModel) gridProduto.getModel())
					.fireTableDataChanged();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
