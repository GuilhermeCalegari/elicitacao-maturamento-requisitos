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

import escola.presentation.bd.AlunoBD;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class AlunoGUI extends JDialog implements MouseListener {

	private AlunoBD alunoBD;

	private static final long serialVersionUID = 1L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JLabel jLabelId = null;
	private JTextField jTextFieldIdAluno = null;
	private JLabel jLabelNomeAluno = null;
	private JTextField jTextFieldNomeAluno = null;
	private JLabel jLabelRA = null;
	private JTextField jTextFieldRA = null;
	private JScrollPane jScrollPaneAluno = null;
	private JList jListAlunos = null;
	private JButton jButtonIncluir = null;
	private JButton jButtonAlterar = null;
	private JButton jButtonExcluir = null;
	private JButton jButtonLimpar = null;

	public AlunoGUI() {
		alunoBD = new AlunoBD();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(747, 354);
		this.setContentPane(getJContentPane());
		this.setTitle(bundle.getMessage("tela.aluno.titulo"));
		this.updateList();
		this.setVisible(true);
	}

	private JLabel getJLabelRA() {
		if (jLabelRA == null) {
			jLabelRA = new JLabel();
			jLabelRA.setBounds(new Rectangle(581, 8, 38, 16));
			jLabelRA.setText(bundle.getMessage("label.ra"));
		}
		return jLabelRA;
	}

	private JLabel getJLabelNomeAluno() {
		if (jLabelNomeAluno == null) {
			jLabelNomeAluno = new JLabel();
			jLabelNomeAluno.setBounds(new Rectangle(68, 59, 84, 16));
			jLabelNomeAluno.setText(bundle.getMessage("label.nomealuno"));
		}
		return jLabelNomeAluno;
	}

	private JLabel getJLabelId() {
		if (jLabelId == null) {
			jLabelId = new JLabel();
			jLabelId.setBounds(new Rectangle(68, 8, 26, 16));
			jLabelId.setText(bundle.getMessage("label.id"));
		}
		return jLabelId;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJLabelId(), null);
			jContentPane.add(getJLabelRA(), null);
			jContentPane.add(getJLabelNomeAluno(), null);
			jContentPane.add(getJTextFieldIdAluno(), null);
			jContentPane.add(getJTextFieldNomeAluno(), null);
			jContentPane.add(getJTextFieldRA(), null);
			jContentPane.add(getJScrollPaneAluno(), null);
			jContentPane.add(getJButtonIncluir(), null);
			jContentPane.add(getJButtonAlterar(), null);
			jContentPane.add(getJButtonExcluir(), null);
			jContentPane.add(getJButtonLimpar(), null);
		}
		return jContentPane;
	}

	private JTextField getJTextFieldIdAluno() {
		if (jTextFieldIdAluno == null) {
			jTextFieldIdAluno = new JTextField();
			jTextFieldIdAluno.setBounds(new Rectangle(67, 32, 86, 20));
		}
		return jTextFieldIdAluno;
	}

	private JTextField getJTextFieldNomeAluno() {
		if (jTextFieldNomeAluno == null) {
			jTextFieldNomeAluno = new JTextField();
			jTextFieldNomeAluno.setBounds(new Rectangle(67, 81, 601, 20));
		}
		return jTextFieldNomeAluno;
	}

	private JTextField getJTextFieldRA() {
		if (jTextFieldRA == null) {
			jTextFieldRA = new JTextField();
			jTextFieldRA.setBounds(new Rectangle(581, 38, 84, 20));
		}
		return jTextFieldRA;
	}

	private JScrollPane getJScrollPaneAluno() {
		if (jScrollPaneAluno == null) {
			jScrollPaneAluno = new JScrollPane();
			jScrollPaneAluno.setBounds(new Rectangle(66, 112, 606, 139));
			jScrollPaneAluno.setViewportView(getJListAlunos());
		}
		return jScrollPaneAluno;
	}

	private JList getJListAlunos() {
		if (jListAlunos == null) {
			jListAlunos = new JList();
			jListAlunos.addMouseListener(this);
		}
		return jListAlunos;
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
			handlerAluno(bundle.getMessage("botao.incluir"));
		} else if (e.getSource() == this.getJButtonAlterar()) {
			handlerAluno(bundle.getMessage("botao.alterar"));
		} else if (e.getSource() == this.getJButtonExcluir()) {
			handlerAluno(bundle.getMessage("botao.excluir"));
		} else if (e.getSource() == this.getJListAlunos()) {
			handlerAluno(bundle.getMessage("botao.selecionar"));
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

	public void handlerAluno(String action) {
		if (action.equals(bundle.getMessage("botao.selecionar"))) {
			String infoList = (String) this.getJListAlunos().getSelectedValue();
			String delimitor = "-";
			StringTokenizer tokens = new StringTokenizer(infoList, delimitor);
			String infos[] = new String[tokens.countTokens() + 1];
			int i = 0;
			while (tokens.hasMoreTokens()) {
				infos[i] = tokens.nextToken();
				i++;
			}
			jTextFieldIdAluno.setText(infos[0].trim());
			jTextFieldRA.setText(infos[1].trim());
			jTextFieldNomeAluno.setText(infos[2].trim());
		} else {
			/*
			 * Instancia um Transfer Object para armazenar as informacoes e
			 * tranferi-las para as demais camadas
			 */

			if (action.equals(bundle.getMessage("botao.incluir"))) {
				alunoBD.addAluno(this.getJTextFieldIdAluno().getText(), this
						.getJTextFieldNomeAluno().getText(), this
						.getJTextFieldRA().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.alterar"))) {
				// Passa o Transfer Object como parametro
				alunoBD.updateAluno(this.getJTextFieldIdAluno().getText(), this
						.getJTextFieldNomeAluno().getText(), this
						.getJTextFieldRA().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.excluir"))) {
				// Passa somente o codigo como parametro que foi armazenado no
				// Transfer Object
				alunoBD.excludeAluno(this.getJTextFieldRA().getText());
				clearFields();
			}
		}
		updateList();

	}

	public void updateList() {
		String[] info = alunoBD.getInfoAlunos();
		this.getJListAlunos().setListData(info);
	}

	public void clearAllFields() {
		jTextFieldIdAluno.setText("");
		jTextFieldRA.setText("");
		jTextFieldNomeAluno.setText("");
		jListAlunos.setListData(new String[0]);
	}

	public void clearFields() {
		getJTextFieldIdAluno().setText("");
		getJTextFieldRA().setText("");
		getJTextFieldNomeAluno().setText("");
	}

}
