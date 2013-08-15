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

import escola.presentation.bd.CursoBD;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class CursoGUI extends JDialog implements MouseListener {

	private CursoBD cursoBD;

	private static final long serialVersionUID = 1L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JLabel jLabelId = null;
	private JTextField jTextFieldIdCurso = null;
	private JLabel jLabelNomeCurso = null;
	private JTextField jTextFieldNomeCurso = null;
	private JTextField jTextFieldRA = null;
	private JScrollPane jScrollPaneCurso = null;
	private JList jListCursos = null;
	private JButton jButtonIncluir = null;
	private JButton jButtonAlterar = null;
	private JButton jButtonExcluir = null;
	private JButton jButtonLimpar = null;

	public CursoGUI() {
		cursoBD = new CursoBD();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(747, 354);
		this.setContentPane(getJContentPane());
		this.setTitle(bundle.getMessage("tela.curso.titulo"));
		this.updateList();
		this.setVisible(true);
	}

	private JLabel getJLabelNomeCurso() {
		if (jLabelNomeCurso == null) {
			jLabelNomeCurso = new JLabel();
			jLabelNomeCurso.setBounds(new Rectangle(68, 59, 84, 16));
			jLabelNomeCurso.setText(bundle.getMessage("label.nomecurso"));
		}
		return jLabelNomeCurso;
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
			jContentPane.add(getJLabelNomeCurso(), null);
			jContentPane.add(getJTextFieldIdCurso(), null);
			jContentPane.add(getJTextFieldNomeCurso(), null);
			jContentPane.add(getJScrollPaneCurso(), null);
			jContentPane.add(getJButtonIncluir(), null);
			jContentPane.add(getJButtonAlterar(), null);
			jContentPane.add(getJButtonExcluir(), null);
			jContentPane.add(getJButtonLimpar(), null);
		}
		return jContentPane;
	}

	private JTextField getJTextFieldIdCurso() {
		if (jTextFieldIdCurso == null) {
			jTextFieldIdCurso = new JTextField();
			jTextFieldIdCurso.setBounds(new Rectangle(67, 32, 86, 20));
		}
		return jTextFieldIdCurso;
	}

	private JTextField getJTextFieldNomeCurso() {
		if (jTextFieldNomeCurso == null) {
			jTextFieldNomeCurso = new JTextField();
			jTextFieldNomeCurso.setBounds(new Rectangle(67, 81, 601, 20));
		}
		return jTextFieldNomeCurso;
	}

	private JScrollPane getJScrollPaneCurso() {
		if (jScrollPaneCurso == null) {
			jScrollPaneCurso = new JScrollPane();
			jScrollPaneCurso.setBounds(new Rectangle(66, 112, 606, 139));
			jScrollPaneCurso.setViewportView(getJListCursos());
		}
		return jScrollPaneCurso;
	}

	private JList getJListCursos() {
		if (jListCursos == null) {
			jListCursos = new JList();
			jListCursos.addMouseListener(this);
		}
		return jListCursos;
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
			handlerCurso(bundle.getMessage("botao.incluir"));
		} else if (e.getSource() == this.getJButtonAlterar()) {
			handlerCurso(bundle.getMessage("botao.alterar"));
		} else if (e.getSource() == this.getJButtonExcluir()) {
			handlerCurso(bundle.getMessage("botao.excluir"));
		} else if (e.getSource() == this.getJListCursos()) {
			handlerCurso(bundle.getMessage("botao.selecionar"));
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

	public void handlerCurso(String action) {
		if (action.equals(bundle.getMessage("botao.selecionar"))) {
			String infoList = (String) this.getJListCursos().getSelectedValue();
			String delimitor = "-";
			StringTokenizer tokens = new StringTokenizer(infoList, delimitor);
			String infos[] = new String[tokens.countTokens() + 1];
			int i = 0;
			while (tokens.hasMoreTokens()) {
				infos[i] = tokens.nextToken();
				i++;
			}
			jTextFieldIdCurso.setText(infos[0].trim());
			jTextFieldNomeCurso.setText(infos[1].trim());
		} else {

			if (action.equals(bundle.getMessage("botao.incluir"))) {
				// Passa o Transfer Object como parametro
				cursoBD.addCurso(this.getJTextFieldIdCurso().getText(), this
						.getJTextFieldNomeCurso().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.alterar"))) {
				// Passa o Transfer Object como parametro
				cursoBD.updateCurso(this.getJTextFieldIdCurso().getText(), this
						.getJTextFieldNomeCurso().getText());
				clearFields();
			} else if (action.equals(bundle.getMessage("botao.excluir"))) {
				// Passa somente o codigo como parametro que foi armazenado no
				// Transfer Object
				cursoBD.excludeCurso(this.getJTextFieldIdCurso().getText());
				clearFields();
			}
		}
		updateList();

	}

	public void updateList() {
		String[] info = cursoBD.getInfoCursos();
		this.getJListCursos().setListData(info);
	}

	public void clearAllFields() {
		jTextFieldIdCurso.setText("");
		jTextFieldRA.setText("");
		jTextFieldNomeCurso.setText("");
		jListCursos.setListData(new String[0]);
	}

	public void clearFields() {
		getJTextFieldIdCurso().setText("");
		getJTextFieldNomeCurso().setText("");
	}

}
