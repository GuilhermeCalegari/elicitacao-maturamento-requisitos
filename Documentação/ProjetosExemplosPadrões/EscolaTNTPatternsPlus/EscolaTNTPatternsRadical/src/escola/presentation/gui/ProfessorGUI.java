package escola.presentation.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escola.presentation.bd.ProfessorBD;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class ProfessorGUI extends JDialog implements MouseListener {

	private ProfessorBD professorBD;

	private static final long serialVersionUID = 1L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JLabel jLabelId = null;
	private JTextField jTextFieldIdProfessor = null;
	private JLabel jLabelNomeProfessor = null;
	private JTextField jTextFieldNomeProfessor = null;
	private JLabel jLabelEspecialidade = null;
	private JTextField jTextFieldEspecialidade = null;
	private JScrollPane jScrollPane = null;
	private JList jListProfessores = null;
	private JButton jButtonIncluir = null;
	private JButton jButtonAlterar = null;
	private JButton jButtonExcluir = null;
	private JButton jButtonLimpar = null;

	public ProfessorGUI() {
		professorBD = new ProfessorBD();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(747, 354);
		this.setContentPane(getJContentPane());
		this.setTitle(bundle.getMessage("tela.professor.titulo"));
		this.setVisible(true);
	}

	private JLabel getJLabelEspecialidade() {
		if (jLabelEspecialidade == null) {
			jLabelEspecialidade = new JLabel();
			jLabelEspecialidade.setBounds(new Rectangle(217, 9, 130, 16));
			jLabelEspecialidade.setText(bundle
					.getMessage("label.especialidade"));
		}
		return jLabelEspecialidade;
	}

	private JLabel getJLabelNomeProfessor() {
		if (jLabelNomeProfessor == null) {
			jLabelNomeProfessor = new JLabel();
			jLabelNomeProfessor.setBounds(new Rectangle(68, 59, 111, 16));
			jLabelNomeProfessor.setText(bundle
					.getMessage("label.nomeprofessor"));
		}
		return jLabelNomeProfessor;
	}

	private JLabel getJLabelId() {
		if (jLabelId == null) {
			jLabelId = new JLabel();
			jLabelId.setBounds(new Rectangle(68, 9, 26, 16));
			jLabelId.setText(bundle.getMessage("label.id"));
		}
		return jLabelId;
	}

	private JTextField getJTextFieldIdProfessor() {
		if (jTextFieldIdProfessor == null) {
			jTextFieldIdProfessor = new JTextField();
			jTextFieldIdProfessor.setBounds(new Rectangle(67, 32, 86, 20));
		}
		return jTextFieldIdProfessor;
	}

	private JTextField getJTextFieldNomeProfessor() {
		if (jTextFieldNomeProfessor == null) {
			jTextFieldNomeProfessor = new JTextField();
			jTextFieldNomeProfessor.setBounds(new Rectangle(67, 81, 601, 20));
		}
		return jTextFieldNomeProfessor;
	}

	private JTextField getJTextFieldEspecialidade() {
		if (jTextFieldEspecialidade == null) {
			jTextFieldEspecialidade = new JTextField();
			jTextFieldEspecialidade.setBounds(new Rectangle(213, 32, 455, 20));
		}
		return jTextFieldEspecialidade;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJLabelId(), null);
			jContentPane.add(getJLabelEspecialidade(), null);
			jContentPane.add(getJLabelNomeProfessor(), null);
			jContentPane.add(getJTextFieldIdProfessor(), null);
			jContentPane.add(getJTextFieldEspecialidade(), null);
			jContentPane.add(getJTextFieldNomeProfessor(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButtonIncluir(), null);
			jContentPane.add(getJButtonAlterar(), null);
			jContentPane.add(getJButtonExcluir(), null);
			jContentPane.add(getJButtonLimpar(), null);
		}
		return jContentPane;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(66, 112, 606, 139));
			jScrollPane.setViewportView(getJListProfessores());
		}
		return jScrollPane;
	}

	private JList getJListProfessores() {
		if (jListProfessores == null) {
			jListProfessores = new JList();
			jListProfessores.addMouseListener(this);
		}
		return jListProfessores;
	}

	private JButton getJButtonIncluir() {
		if (jButtonIncluir == null) {
			jButtonIncluir = new JButton();
			jButtonIncluir.setBounds(new Rectangle(70, 265, 102, 24));
			jButtonIncluir.setText(bundle.getMessage("botao.incluir"));
			jButtonIncluir.addMouseListener(this);
		}
		return jButtonIncluir;
	}

	private JButton getJButtonAlterar() {
		if (jButtonAlterar == null) {
			jButtonAlterar = new JButton();
			jButtonAlterar.setBounds(new Rectangle(239, 265, 102, 24));
			jButtonAlterar.setText(bundle.getMessage("botao.alterar"));
			jButtonAlterar.addMouseListener(this);
		}
		return jButtonAlterar;
	}

	private JButton getJButtonExcluir() {
		if (jButtonExcluir == null) {
			jButtonExcluir = new JButton();
			jButtonExcluir.setBounds(new Rectangle(399, 265, 102, 24));
			jButtonExcluir.setText(bundle.getMessage("botao.excluir"));
			jButtonExcluir.addMouseListener(this);
		}
		return jButtonExcluir;
	}

	private JButton getJButtonLimpar() {
		if (jButtonLimpar == null) {
			jButtonLimpar = new JButton();
			jButtonLimpar.setBounds(new Rectangle(567, 265, 102, 24));
			jButtonLimpar.setText(bundle.getMessage("botao.cancelar"));
			jButtonLimpar.addMouseListener(this);
		}
		return jButtonLimpar;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.getJButtonIncluir()) {
			handlerProfessor(bundle.getMessage("botao.incluir"));
		} else if (e.getSource() == this.getJButtonAlterar()) {
			handlerProfessor(bundle.getMessage("botao.alterar"));
		} else if (e.getSource() == this.getJButtonExcluir()) {
			handlerProfessor(bundle.getMessage("botao.excluir"));
		} else if (e.getSource() == this.getJListProfessores()) {
			handlerProfessor(bundle.getMessage("botao.selecionar"));
		} else {
			clearFields();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void handlerProfessor(String action) {
		if (action.equals(bundle.getMessage("botao.selecionar"))) {
			String infoList = (String) this.getJListProfessores()
					.getSelectedValue();
			String delimitor = "-";
			StringTokenizer tokens = new StringTokenizer(infoList, delimitor);
			String infos[] = new String[tokens.countTokens() + 1];
			int i = 0;
			while (tokens.hasMoreTokens()) {
				infos[i] = tokens.nextToken();
				i++;
			}
			jTextFieldIdProfessor.setText(infos[0].trim());
			jTextFieldNomeProfessor.setText(infos[1].trim());
			jTextFieldEspecialidade.setText(infos[2].trim());

		} else {

			if (action.equals(bundle.getMessage("botao.incluir"))) {
				// Passa o Transfer Object como parametro
				professorBD.addProfessor(this.getJTextFieldIdProfessor()
						.getText(),
						this.getJTextFieldEspecialidade().getText(), this
								.getJTextFieldNomeProfessor().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.alterar"))) {
				// Passa o Transfer Object como parametro
				professorBD.updateProfessor(this.getJTextFieldIdProfessor()
						.getText(),
						this.getJTextFieldEspecialidade().getText(), this
								.getJTextFieldNomeProfessor().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.excluir"))) {
				// Passa somente o codigo como parametro que foi armazenado no
				// Transfer Object
				professorBD.excludeProfessor(this.getJTextFieldIdProfessor()
						.getText());
				clearFields();
			}
		}
		updateList();

	}

	public void updateList() {
		String[] info = professorBD.getInfoProfessores();
		this.getJListProfessores().setListData(info);
	}

	public void clearAllFields() {
		jTextFieldIdProfessor.setText("");
		jTextFieldEspecialidade.setText("");
		jTextFieldNomeProfessor.setText("");
		jListProfessores.setListData(new String[0]);
	}

	public void clearFields() {
		getJTextFieldIdProfessor().setText("");
		getJTextFieldEspecialidade().setText("");
		getJTextFieldNomeProfessor().setText("");
	}
}
