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

import estoque.business.bd.CategoriaBD;
import estoque.business.to.Categoria;

public class ListarCategoria extends JFrame {

	private JPanel jContentPane = null;
	private JTextField descricao = null;
	private JScrollPane jScrollPane = null;
	private JTable gridCategoria = null;
	private JButton btnPesquisar = null;
	private JButton btnIncluir = null;
	private JButton btnExcluir = null;
	private JLabel labelDescricao = null;
	private ArrayList<Categoria> listaCategoria = new ArrayList <Categoria>();
	private CategoriaBD categoriaBD = null;	
	
	
	private JTextField getDescricao() {
		if (descricao == null) {
			descricao = new JTextField();
		}
		return descricao;
	}

	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getGridCategoria());

		}
		return jScrollPane;
	}

	String[] colunaCategoria = { Messages.getString("ListarCategoria.0"), Messages.getString("ListarCategoria.1"), Messages.getString("ListarCategoria.17") };  
	public final int ID_CATEGORIA = 0;
	public final int CATEGORIA = 1;
	public final int SITUACAO = 2;
	private JButton jBtnAlterar = null;
	


	private JTable getGridCategoria() {
		if (gridCategoria == null) {
			gridCategoria = new JTable(new AbstractTableModel() {

				public int getRowCount() {
					return listaCategoria.size();
				}

				public int getColumnCount() {
					return colunaCategoria.length;
				}

				public Object getValueAt(int rowIndex, int columnIndex) {
					if (columnIndex == ID_CATEGORIA) {
						return listaCategoria.get(rowIndex).getId();
					} else if (columnIndex == CATEGORIA) {
						return listaCategoria.get(rowIndex).getCategoria();
					} else if (columnIndex == SITUACAO) {
						return listaCategoria.get(rowIndex).getSitCategoria();
					}	
					else 
						return null;
				}
				
				// getColumnName
				// Retorna o "título" da coluna, de acordo com o índice recebido
				// como parâmetro
				public String getColumnName(int column) {
					return colunaCategoria[column];
				}
				
				// isCellEditable
				// Retorna um boolean indicando se a coluna é editável, com base
				// nos parâmetros linha e coluna recebidos
				// Quando não implementado, não permite editar as colunas
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					if (columnIndex == CATEGORIA)
						return true;
					
					return false;
				}
			});
			// Define para selecionar um único registro no GRID 
			gridCategoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// Trata o duplo clique 
			gridCategoria.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int linha = 0;
					
					if (e.getClickCount() == 2)
					{
						// Verifica qual é a linha  
						linha = gridCategoria.rowAtPoint(e.getPoint());
						if (linha != -1) {
							listaCategoria.get(linha).getId();
//							JOptionPane.showMessageDialog(null, "Verificar detalhe para o registro de código :" + codigo);
							//Mostra os dados de detalhe
							jBtnAlterar.doClick();
						}
					}
				}
			});
			
		}
		return gridCategoria;
	}

	
	private JButton getBtnPesquisar() {
		if (btnPesquisar == null) {
			btnPesquisar = new JButton();
			btnPesquisar.setText(Messages.getString("ListarCategoria.2")); 
			btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					// Inicializa novamente o listaCategoria para uma nova posição de memória
					listaCategoria = new ArrayList<Categoria>();
					try {
						if (descricao.getText().equals(""))	{ 
							// Pesquisa todos os registros de categoria
							listaCategoria = (ArrayList<Categoria>) categoriaBD.listar(Categoria.class);
							System.out.println("Carregou o filtro" + listaCategoria.size());						 
						}
						else {
							// Pesquisa a categoria de acordo com o parâmetro informado
							listaCategoria = (ArrayList<Categoria>) categoriaBD.pesquisar(Categoria.class, "categoria", descricao.getText(), "categoria");  
							System.out.println("Carregou o filtro" + listaCategoria.size());						 
						}	
						
						// Atualiza o Grid
						((AbstractTableModel) gridCategoria.getModel()).fireTableDataChanged();
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnPesquisar;
	}



	private JButton getBtnIncluir() {
		if (btnIncluir == null) {
			btnIncluir = new JButton();
			btnIncluir.setText(Messages.getString("ListarCategoria.7")); 
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
			btnExcluir.setText(Messages.getString("ListarCategoria.8")); 
			btnExcluir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (gridCategoria.getSelectedRow() != -1) {
						
						String mensagem = MessageFormat.format(Messages.getString("ListarCategoria.9"), new Object[] {listaCategoria.get(gridCategoria.getSelectedRow()).getCategoria()}); 
						
						/* Mostra uma mensagem e so entra no bloco do IF se a resposta for YES */
						if  (JOptionPane.showConfirmDialog(btnExcluir, mensagem, Messages.getString("ListarCategoria.10"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {						  //$NON-NLS-2$
							
							// Exclui o registro do banco de dados
							try {
								// Pega o Id do registro do Browse e exclui do banco de dados
								Categoria categoria = new Categoria();
								categoria = (Categoria) categoriaBD.consultar(Categoria.class, listaCategoria.get(gridCategoria.getSelectedRow()).getId());
								categoriaBD.apagar(categoria);
								
								// Remove o TO do array de dados
								listaCategoria.remove(gridCategoria.getSelectedRow());
								
								// Atualiza as informações do Grid
								((AbstractTableModel) gridCategoria.getModel()).fireTableDataChanged();
								
							} catch (Exception e1) {
								e1.printStackTrace();
							}
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
			jBtnAlterar.setText(Messages.getString("ListarCategoria.16")); 
			jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (gridCategoria.getSelectedRow() != -1) {
						alterar();	
					}

					
				}
			});
		}
		return jBtnAlterar;
	}
	
	public ListarCategoria() {

		super();
		initialize();
	}

	
	private void initialize() {
		this.setSize(413, 291);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle(Messages.getString("ListarCategoria.11")); 
		this.setVisible(true);

		/* Carrega a Lista do Grid */
		try {
			categoriaBD = new CategoriaBD();
			listaCategoria = (ArrayList<Categoria>) categoriaBD.listar(Categoria.class);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 2;
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
			labelDescricao.setText(Messages.getString("ListarCategoria.12")); 
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
			jContentPane.setPreferredSize(new java.awt.Dimension(154,104));
			jContentPane.add(getDescricao(), gridBagConstraints);
			jContentPane.add(getJScrollPane(), gridBagConstraints1);
			jContentPane.add(getBtnPesquisar(), gridBagConstraints2);
			jContentPane.add(getBtnIncluir(), gridBagConstraints3);
			jContentPane.add(getBtnExcluir(), gridBagConstraints4);
			jContentPane.add(labelDescricao, gridBagConstraints5);
			jContentPane.add(getJBtnAlterar(), gridBagConstraints11);
		}
		return jContentPane;
	}
	
	public void incluir() {
		CadastrarCategoria cadastroCategoria = new CadastrarCategoria();
		cadastroCategoria.setModal(true);
		cadastroCategoria.setVisible(true);

		// Reposiciona o objeto de Categoria, após a pesquisa
		try {
			listaCategoria = (ArrayList<Categoria>) categoriaBD.listar(Categoria.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Atualiza o Grid
		((AbstractTableModel) gridCategoria.getModel()).fireTableDataChanged();
	}

	public void alterar() {
		// Passa o produto como parâmetro
		CadastrarCategoria cadastrarCategoria = new CadastrarCategoria(listaCategoria.get(gridCategoria.getSelectedRow()));
		cadastrarCategoria.setModal(true);
		cadastrarCategoria.setVisible(true);

		// Reposiciona o objeto de Produto, após a pesquisa
		try {
			listaCategoria = (ArrayList<Categoria>) categoriaBD.listar(Categoria.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Atualiza o Grid
		((AbstractTableModel) gridCategoria.getModel()).fireTableDataChanged();
	}
	
}  
