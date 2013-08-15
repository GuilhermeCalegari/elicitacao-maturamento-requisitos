package escola.presentation.gui;

import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escola.presentation.bd.CursoBD;
import escola.presentation.bd.ProfessorBD;
import escola.presentation.bd.TurmaBD;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class TurmaGUI extends JDialog implements MouseListener, ItemListener {

	// Business Delegate
	private TurmaBD turmaBD;
	private ProfessorBD professorBD;
	private CursoBD cursoBD;

	private static final long serialVersionUID = -6705466855652869188L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JLabel jLabelId = null;
	private JTextField jTextFieldIdTurma = null;
	private JScrollPane jScrollPaneTurma = null;
	private JList jListTurmas = null;
	private JButton jButtonIncluir = null;
	private JButton jButtonAlterar = null;
	private JButton jButtonExcluir = null;
	private JButton jButtonLimpar = null;
	private JComboBox jComboBoxProfessor = null;
	private JComboBox jComboBoxCurso = null;
	private JLabel jLabelProfessor = null;
	private JLabel jLabelCurso = null;
	private JLabel jLabelPeriodo = null;
	private JTextField jTextFieldPeriodo = null;

	public TurmaGUI() {
		turmaBD = new TurmaBD();
		professorBD = new ProfessorBD();
		cursoBD = new CursoBD();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(747, 492);
		this.setContentPane(getJContentPane());
		this.setTitle(bundle.getMessage("tela.turma.titulo"));
		this.loadJCombos();
		String values = jComboBoxCurso.getSelectedItem().toString();
		String[] infoCombo = getComboElements(values);
		String idCurso = infoCombo[0];
		this.updateList(idCurso);
		this.setVisible(true);
	}

	private JLabel getJLabelId() {
		if (jLabelId == null) {
			jLabelId = new JLabel();
			jLabelId.setBounds(new Rectangle(16, 115, 26, 16));
			jLabelId.setText(bundle.getMessage("label.id"));
		}
		return jLabelId;
	}

	private JLabel getJLabelCurso() {
		if (jLabelCurso == null) {
			jLabelCurso = new JLabel();
			jLabelCurso.setBounds(new Rectangle(16, 14, 38, 16));
			jLabelCurso.setText(bundle.getMessage("label.curso"));
		}
		return jLabelCurso;
	}

	private JLabel getJLabelProfessor() {
		if (jLabelProfessor == null) {
			jLabelProfessor = new JLabel();
			jLabelProfessor.setBounds(new Rectangle(16, 70, 67, 16));
			jLabelProfessor.setText(bundle.getMessage("label.professor"));
		}
		return jLabelProfessor;
	}

	private JLabel getJLabelPeriodo() {
		if (jLabelPeriodo == null) {
			jLabelPeriodo = new JLabel();
			jLabelPeriodo.setBounds(new Rectangle(544, 115, 82, 16));
			jLabelPeriodo.setText(bundle.getMessage("label.periodo"));
		}
		return jLabelPeriodo;
	}

	private JTextField getJTextFieldPeriodo() {
		if (jTextFieldPeriodo == null) {
			jTextFieldPeriodo = new JTextField();
			jTextFieldPeriodo.setBounds(new Rectangle(634, 115, 85, 20));
		}
		return jTextFieldPeriodo;
	}

	private JComboBox getJComboBoxProfessor() {
		if (jComboBoxProfessor == null) {
			jComboBoxProfessor = new JComboBox();
			jComboBoxProfessor.setBounds(new Rectangle(134, 70, 584, 24));
			jComboBoxProfessor.addItemListener(this);
		}
		return jComboBoxProfessor;
	}

	private JComboBox getJComboBoxCurso() {
		if (jComboBoxCurso == null) {
			jComboBoxCurso = new JComboBox();
			jComboBoxCurso.setBounds(new Rectangle(134, 14, 585, 24));
			jComboBoxCurso.addItemListener(this);
		}
		return jComboBoxCurso;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {

			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJLabelId(), null);
			jContentPane.add(getJLabelCurso(), null);
			jContentPane.add(getJLabelProfessor(), null);
			jContentPane.add(getJLabelPeriodo(), null);
			jContentPane.add(getJTextFieldIdTurma(), null);
			jContentPane.add(getJScrollPaneTurma(), null);
			jContentPane.add(getJButtonIncluir(), null);
			jContentPane.add(getJButtonAlterar(), null);
			jContentPane.add(getJButtonExcluir(), null);
			jContentPane.add(getJButtonLimpar(), null);
			jContentPane.add(getJComboBoxProfessor(), null);
			jContentPane.add(getJComboBoxCurso(), null);
			jContentPane.add(getJTextFieldPeriodo(), null);
		}
		return jContentPane;
	}

	private JTextField getJTextFieldIdTurma() {
		if (jTextFieldIdTurma == null) {
			jTextFieldIdTurma = new JTextField();
			jTextFieldIdTurma.setBounds(new Rectangle(134, 115, 86, 21));
		}
		return jTextFieldIdTurma;
	}

	private JScrollPane getJScrollPaneTurma() {
		if (jScrollPaneTurma == null) {
			jScrollPaneTurma = new JScrollPane();
			jScrollPaneTurma.setBounds(new Rectangle(9, 152, 712, 204));
			jScrollPaneTurma.setViewportView(getJListTurmas());
		}
		return jScrollPaneTurma;
	}

	private JList getJListTurmas() {
		if (jListTurmas == null) {
			jListTurmas = new JList();
			jListTurmas.addMouseListener(this);
		}
		return jListTurmas;
	}

	private JButton getJButtonIncluir() {
		if (jButtonIncluir == null) {
			jButtonIncluir = new JButton();
			jButtonIncluir.setBounds(new Rectangle(71, 388, 102, 24));
			jButtonIncluir.setText(bundle.getMessage("botao.incluir"));
			jButtonIncluir.addMouseListener(this);
		}
		return jButtonIncluir;
	}

	private JButton getJButtonAlterar() {
		if (jButtonAlterar == null) {
			jButtonAlterar = new JButton();
			jButtonAlterar.setBounds(new Rectangle(240, 388, 102, 24));
			jButtonAlterar.setText(bundle.getMessage("botao.alterar"));
			jButtonAlterar.addMouseListener(this);
		}
		return jButtonAlterar;
	}

	private JButton getJButtonExcluir() {
		if (jButtonExcluir == null) {
			jButtonExcluir = new JButton();
			jButtonExcluir.setBounds(new Rectangle(400, 388, 102, 24));
			jButtonExcluir.setText(bundle.getMessage("botao.excluir"));
			jButtonExcluir.addMouseListener(this);
		}
		return jButtonExcluir;
	}

	private JButton getJButtonLimpar() {
		if (jButtonLimpar == null) {
			jButtonLimpar = new JButton();
			jButtonLimpar.setBounds(new Rectangle(568, 388, 102, 24));
			jButtonLimpar.setText(bundle.getMessage("botao.cancelar"));
			jButtonLimpar.addMouseListener(this);
		}
		return jButtonLimpar;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.getJButtonIncluir()) {
			handlerTurma(bundle.getMessage("botao.incluir"));
		} else if (e.getSource() == this.getJButtonAlterar()) {
			handlerTurma(bundle.getMessage("botao.alterar"));
		} else if (e.getSource() == this.getJButtonExcluir()) {
			handlerTurma(bundle.getMessage("botao.excluir"));
		} else if (e.getSource() == this.getJListTurmas()) {
			handlerTurma(bundle.getMessage("botao.selecionar"));
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

	public void handlerTurma(String action) {
		String idCurso, idTurma, periodo, idProfessor;
		idTurma = getJTextFieldIdTurma().getText().trim();
		periodo = getJTextFieldPeriodo().getText().trim();
		String values = getJComboBoxProfessor().getSelectedItem().toString()
				.trim();
		String[] infoProf = getComboElements(values);
		idProfessor = infoProf[0].trim();
		values = getJComboBoxCurso().getSelectedItem().toString();
		idCurso = getComboElements(values)[0].trim();
		if (action.equals(bundle.getMessage("botao.incluir"))) {
			turmaBD.addTurma(idCurso, idTurma, idProfessor, periodo);
			clearFields();
		} else if (action.equals(bundle.getMessage("botao.alterar"))) {
			turmaBD.updateTurma(idCurso, idTurma, idProfessor, periodo);
			clearFields();
		} else if (action.equals(bundle.getMessage("botao.excluir"))) {
			turmaBD.excludeTurma(idCurso, idTurma);
			clearFields();
		} else if (action.equals(bundle.getMessage("combo.combocurso"))) {
			getJComboBoxProfessor().setSelectedIndex(0);
		} else {
			String infoList = getJListTurmas().getSelectedValue().toString();
			String infos[] = getComboElements(infoList);
			idCurso = infos[0];
			idTurma = infos[2];
			idProfessor = infos[3];
			String nomeProfessor = infos[4];
			periodo = infos[5];
			getJTextFieldIdTurma().setText(idTurma);
			getJTextFieldPeriodo().setText(periodo);
			jComboBoxProfessor.setSelectedItem(idProfessor + " - "
					+ nomeProfessor);
		}
		updateList(idCurso);

	}

	public void loadJCombos() {

		String[] professores = professorBD.getInfoProfessores();
		String[] cursos = cursoBD.getInfoCursos();

		for (int i = 0; i < professores.length; i++) {
			String[] infoProf = getComboElements(professores[i]);

			String idProfessor = infoProf[0].trim();
			String nomeProfessor = infoProf[1].trim();
			getJComboBoxProfessor()
					.addItem(idProfessor + " - " + nomeProfessor);
		}
		for (int i = 0; i < cursos.length; i++) {
			String[] infoCurso = getComboElements(cursos[i]);
			String idCurso = infoCurso[0].trim();
			String nomeCurso = infoCurso[1].trim();
			getJComboBoxCurso().addItem(idCurso + " - " + nomeCurso);
		}

	}

	public void updateList(String idCurso) {

		String[] info = turmaBD.getInfoTurmas(idCurso);
		this.getJListTurmas().setListData(info);
	}

	public void clearAllFields() {
		getJTextFieldIdTurma().setText("");
		getJTextFieldPeriodo().setText("");
		getJTextFieldPeriodo().setText("");
		getJListTurmas().setListData(new String[0]);
	}

	public void clearFields() {
		getJTextFieldIdTurma().setText("");
		getJTextFieldPeriodo().setText("");
	}

	private String[] getComboElements(String values) {
		String delimitor = "-";
		StringTokenizer tokens = new StringTokenizer(values, delimitor);
		String infos[] = new String[tokens.countTokens() + 1];
		int i = 0;
		while (tokens.hasMoreTokens()) {
			infos[i] = tokens.nextToken().trim();
			i++;
		}
		return infos;
	}

	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == this.getJComboBoxCurso()) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				handlerTurma(bundle.getMessage("combo.combocurso"));

			}
		}
	}
}
