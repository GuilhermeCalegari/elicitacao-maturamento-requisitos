package estoque.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import estoque.presentation.CommandCategoria;
import estoque.presentation.CommandParametro;
import estoque.presentation.CommandProduto;
import estoque.presentation.ReceptorCategoria;
import estoque.presentation.ReceptorParametro;
import estoque.presentation.ReceptorProduto;
import estoque.util.GlobalParameter;


public class MenuEstoque extends JFrame {

	// Atributo para Singleton	
	private JPanel jContentPane = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuBar jJMenuBar = null;
	private JMenuItem jMenuItem2 = null;
	private JMenu jMenu1 = null;
	private JMenuItem jMenuItem3 = null;
	private JLabel lblUsuario = null;
	private JTextField txtUsuario = null;

	private JPanel jPanel = null;


	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText(Messages.getString("MenuEstoque.0")); 
			jMenu.setMnemonic(java.awt.event.KeyEvent.VK_C);
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
		}
		return jMenu;
	}



	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText(Messages.getString("MenuEstoque.1")); 
			jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_MASK));
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Chama o cadastro de Categoria utilizando o padrão Command
					CommandCategoria commandCategoria = new CommandCategoria(new ReceptorCategoria());
					commandCategoria.executar();
				}
			});
		}
		return jMenuItem;
	}



	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText(Messages.getString("MenuEstoque.2")); //$NON-NLS-1$
			jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_MASK));
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Chama o cadastro de Produto utilizando o padrÃ£o Command
					CommandProduto commandProduto = new CommandProduto(new ReceptorProduto());
					commandProduto.executar();
				}
			});
		}
		return jMenuItem1;
	}



	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
		}
		return jJMenuBar;
	}



	public MenuEstoque() {
		super();
		initialize();
	}

	
	private void initialize() {
		this.setSize(508, 219);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle(Messages.getString("MenuEstoque.10")); 
		GlobalParameter globalParam = GlobalParameter.getInstance(); // Pega a instância de Paramâtros
		txtUsuario.setText(globalParam.getUsuario());		
	}

	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblUsuario = new JLabel();
			lblUsuario.setText(Messages.getString("MenuEstoque.15"));
			lblUsuario.setForeground(Color.blue);
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText(Messages.getString("MenuEstoque.11")); 
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Saiu do sistema()");  
					dispose();
				}
			});
		}
		return jMenuItem2;
	}

	
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText(Messages.getString("MenuEstoque.13")); 
			jMenu1.add(getJMenuItem3());
		}
		return jMenu1;
	}

	
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText(Messages.getString("MenuEstoque.14")); 
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Chama o alteração de markUp utilizando o padrão Command
					CommandParametro commandParametro = new CommandParametro(new ReceptorParametro());
					commandParametro.executar();

				}
			});
		}
		return jMenuItem3;
	}

	
	private JTextField getJTextField() {
		if (txtUsuario == null) {
			txtUsuario = new JTextField();
			txtUsuario.setForeground(Color.blue);
			txtUsuario.setEditable(false);
			txtUsuario.setEnabled(false);
		}
		return txtUsuario;
	}

	
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = -1;
			gridBagConstraints1.gridy = -1;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.weightx = 100.0;
			gridBagConstraints1.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.gridy = -1;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridx = -1;
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(lblUsuario, gridBagConstraints);
			jPanel.add(getJTextField(), gridBagConstraints1);
		}
		return jPanel;
	}

}  
