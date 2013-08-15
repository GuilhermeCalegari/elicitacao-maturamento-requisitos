package estoque.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import estoque.util.FormatHelper;
import estoque.util.GlobalParameter;

public class CadastrarParametro extends JDialog {

	// Atributo para Singleton	
	private GlobalParameter globalParam; 
	
	private JPanel jContentPane = null;
	private JLabel lblDescricao = null;
	private JTextField markup = null;
	private JPanel jPanel = null;
	private JButton btnCancelar = null;
	private JButton btnOK = null;

	private JTextField getMarkup() {
		if (markup == null) {
			markup = new JTextField();
		}
		return markup;
	}


	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.add(getBtnOK(), null);
			jPanel.add(getBtnCancelar(), null);
		}
		return jPanel;
	}


	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setText("Cancelar"); 
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}


	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton();
			btnOK.setText("OK"); 
			btnOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (FormatHelper.verificaNumero(markup.getText()) == null)	{ 
						JOptionPane.showMessageDialog(btnOK, Messages.getString("CadastrarParametro.0"), Messages.getString("CadastrarParametro.1"), JOptionPane.WARNING_MESSAGE);  //$NON-NLS-1$ //$NON-NLS-2$
					}
					else {
						
						// Altera o valor do Markup no Parâmetro Geral (Singleton)
						alteraParametro(markup.getText());
						globalParam.imprimeValores(globalParam);
						dispose();
					}
				}
			});
		}
		return btnOK;
	}

	public CadastrarParametro() {
		super();
		initialize();
		
	}


	private void initialize() {
		this.setSize(300, 164);
		this.setTitle("Alterar MarkUp de Preços"); 
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		
		// Acesso o Singleton
		// Tentativa de criar outra instância, mas retorna a mesma já criada no Login
		globalParam = GlobalParameter.getInstance();
		mostraParametro();	// Mostra o MarkUp atual
	}


	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new java.awt.Insets(0,10,0,0);
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints2.gridy = 2;
			lblDescricao = new JLabel();
			lblDescricao.setText(Messages.getString("CadastrarParametro.2")); 
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(lblDescricao, gridBagConstraints2);
			jContentPane.add(getMarkup(), gridBagConstraints3);
			jContentPane.add(getJPanel(), gridBagConstraints4);
		}
		return jContentPane;
	}

	public void mostraParametro() {
		markup.setText(FormatHelper.df.format(globalParam.getMarkUp()));
	}
	
	public void alteraParametro(String pMarkUp) {
		globalParam.setMarkUp(FormatHelper.verificaNumero(markup.getText()));
	}
	
} 
