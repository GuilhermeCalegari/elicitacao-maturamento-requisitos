package estoque.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import estoque.business.PrecoCustoStry;
import estoque.business.PrecoPontaEstoqueStry;
import estoque.business.PrecoVendaStry;
import estoque.util.GlobalParameter;

// Client de Singleton
public class LoginEstoque extends JDialog {

	// Atributo para Singleton	
	private GlobalParameter globalParam	=	GlobalParameter.getInstance();

	private JPanel jContentPane = null;
	private JLabel labelCodigo = null;
	private JTextField usuario = null;
	private JButton btnCancelar = null;
	private JButton btnEntrar = null;

	private JRadioButton radioPortugues = null;
	private JRadioButton radioIngles = null;
	private ButtonGroup grupoRadioButton;  
	private ButtonGroup precoRadioButton;  
	
	private JLabel txtIdioma = null;
	private JLabel jLabel = null;
	private JRadioButton radioCusto = null;
	private JRadioButton radioPonta = null;
	private JRadioButton radioVenda = null;


	private JTextField getUsuario() {
		if (usuario == null) {
			usuario = new JTextField();
		}
		return usuario;
	}


	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setText(Messages.getString("LoginEstoque.0")); 
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	
	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton();
			btnEntrar.setText(Messages.getString("LoginEstoque.1")); 
			btnEntrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (usuario.getText().equals(""))	{ 
						JOptionPane.showMessageDialog(btnEntrar, Messages.getString("LoginEstoque.2"), Messages.getString("LoginEstoque.3"), JOptionPane.WARNING_MESSAGE); 
						btnCancelar.doClick();
					}
					else {
						instanciaSingleton();						
						// Chama o menu principal do sistema
						MenuEstoque menuEstoque = new MenuEstoque();
						menuEstoque.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						menuEstoque.setVisible(true);
						
						dispose();
					}
				}
			});
		}
		return btnEntrar;
	}


	private JRadioButton getRadioPortugues() {
		if (radioPortugues == null) {
			radioPortugues = new JRadioButton();
			radioPortugues.setText(Messages.getString("LoginEstoque.6")); 
			radioPortugues.setSelected(true); // Valor Default
			radioPortugues.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Muda o idioma para português
					Messages.setLocale(new Locale("pt", "BR"));  
				}
			});
		}
		return radioPortugues;
	}

	
	private JRadioButton getRadioIngles() {
		if (radioIngles == null) {
			radioIngles = new JRadioButton();
			radioIngles.setText(Messages.getString("LoginEstoque.7")); 
			radioIngles.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Muda o idioma para inglês
					Messages.setLocale(new Locale("en", "US"));  
				}
			});
		}
		return radioIngles;
	}
	
	private ButtonGroup getGrupoRadioButton() {
		if (grupoRadioButton == null) {
			grupoRadioButton = new ButtonGroup();
			grupoRadioButton.add(getRadioPortugues()); // Agrupa os JRadioButton
			grupoRadioButton.add(getRadioIngles());
		}
		return grupoRadioButton;
	}

	public void setGrupoRadio(ButtonGroup grupoRadioButton) {
		this.grupoRadioButton = grupoRadioButton;
	}

	private ButtonGroup getPrecoRadioButton() {
		if (precoRadioButton == null) {
			precoRadioButton = new ButtonGroup();
			precoRadioButton.add(getRadioCusto()); // Agrupa os JRadioButton
			precoRadioButton.add(getRadioPonta());
			precoRadioButton.add(getRadioVenda());
		}
		return precoRadioButton;
	}

	public void setGrupoPrecoRadio(ButtonGroup precoRadioButton) {
		this.precoRadioButton = precoRadioButton;
	}
	

	private JRadioButton getRadioCusto() {
		if (radioCusto == null) {
			radioCusto = new JRadioButton();
			radioCusto.setText(Messages.getString("LoginEstoque.8")); 
			radioCusto.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Muda a política de preço para preço de custo
					GlobalParameter.getInstance().setPoliticaPreco(new PrecoCustoStry());
				}
			});
		}
		return radioCusto;
	}

	
	private JRadioButton getRadioPonta() {
		if (radioPonta == null) {
			radioPonta = new JRadioButton();
			radioPonta.setText(Messages.getString("LoginEstoque.9")); 
			radioPonta.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Muda a política de preço para Ponta de Estoque
					GlobalParameter.getInstance().setPoliticaPreco(new PrecoPontaEstoqueStry());
				}
			});
		}
		return radioPonta;
	}

	

	private JRadioButton getRadioVenda() {
		if (radioVenda == null) {
			radioVenda = new JRadioButton();
			radioVenda.setText(Messages.getString("LoginEstoque.10")); 
			radioVenda.setSelected(true); // Valor Default
			radioVenda.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Muda a política de preço para preço de venda
					GlobalParameter.getInstance().setPoliticaPreco(new PrecoVendaStry());
				}
			});			
		}
		return radioVenda;
	}

	

	public static void main(String[] args) {
		LoginEstoque loginEstoque = new LoginEstoque();
		loginEstoque.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		loginEstoque.setVisible(true);
	}	

	public LoginEstoque() {
		super();
		initialize();
	}

	

	private void initialize() {
		this.setSize(411, 152);
		this.setTitle(Messages.getString("LoginEstoque.4")); 
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
	}

	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			getGrupoRadioButton();// Agrupa os Radio Buttons para terem um único valor
			getPrecoRadioButton();// Agrupa os Radio Buttons de Política de Preço para terem um único valor
			
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 3;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 2;
			gridBagConstraints41.anchor = GridBagConstraints.WEST;
			gridBagConstraints41.gridwidth = 1;
			gridBagConstraints41.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints41.gridy = 2;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 1;
			gridBagConstraints32.anchor = GridBagConstraints.WEST;
			gridBagConstraints32.insets = new Insets(0, 7, 0, 0);
			gridBagConstraints32.gridy = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.anchor = GridBagConstraints.EAST;
			gridBagConstraints21.gridy = 2;
			jLabel = new JLabel();
			jLabel.setText(Messages.getString("LoginEstoque.11")); 
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 1;
			txtIdioma = new JLabel();
			txtIdioma.setText(Messages.getString("LoginEstoque.12")); 
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.insets = new Insets(0, 7, 0, 0);
			gridBagConstraints12.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridy = 4;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.anchor = GridBagConstraints.EAST;
			gridBagConstraints11.fill = GridBagConstraints.NONE;
			gridBagConstraints11.gridy = 4;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 80.0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.insets = new Insets(0, 7, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridy = 0;
			labelCodigo = new JLabel();
			labelCodigo.setText(Messages.getString("LoginEstoque.5")); 
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(labelCodigo, gridBagConstraints);
			jContentPane.add(getUsuario(), gridBagConstraints1);
			jContentPane.add(getBtnEntrar(), gridBagConstraints11);
			jContentPane.add(getBtnCancelar(), gridBagConstraints3);
			jContentPane.add(getRadioPortugues(), gridBagConstraints12);
			jContentPane.add(getRadioIngles(), gridBagConstraints2);
			jContentPane.add(txtIdioma, gridBagConstraints4);
			jContentPane.add(jLabel, gridBagConstraints21);
			jContentPane.add(getRadioCusto(), gridBagConstraints5);
			jContentPane.add(getRadioPonta(), gridBagConstraints41);
			jContentPane.add(getRadioVenda(), gridBagConstraints32);
		}
		return jContentPane;
	}

	private void instanciaSingleton() {
		// Instancia o Singleton e grava o usuário 
		globalParam.setUsuario(usuario.getText());
		globalParam.imprimeValores(globalParam);
	}
}  
