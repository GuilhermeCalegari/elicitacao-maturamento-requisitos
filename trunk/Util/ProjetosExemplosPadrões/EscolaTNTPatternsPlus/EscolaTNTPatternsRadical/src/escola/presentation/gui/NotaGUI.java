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

import escola.presentation.bd.AlunoBD;
import escola.presentation.bd.CursoBD;
import escola.presentation.bd.NotaBD;
import escola.presentation.bd.TurmaBD;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class NotaGUI extends JDialog implements MouseListener, ItemListener {

	// Business Delegate
	private NotaBD notaBD;
	private TurmaBD turmaBD;
	private CursoBD cursoBD;
	private AlunoBD alunoBD;

	private static final long serialVersionUID = 1L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JComboBox jComboBoxTurma = null;
	private JLabel jLabelTurma = null;
	private JLabel jLabelNota = null;
	private JTextField jTextFieldNota = null;
	private JButton jButtonIncluir = null;
	private JButton jButtonAlterar = null;
	private JButton jButtonExcluir = null;
	private JButton jButtonCancelar = null;
	private JLabel jLabelCurso = null;
	private JComboBox jComboBoxCurso = null;
	private JLabel jLabelProfessor = null;
	private JScrollPane jScrollPane = null;
	private JList jListNotas = null;
	private JLabel jLabelAluno = null;
	private JComboBox jComboBoxAluno = null;
	private JTextField jTextFieldProfessor = null;

	public NotaGUI() {

		notaBD = new NotaBD();
		turmaBD = new TurmaBD();
		cursoBD = new CursoBD();
		alunoBD = new AlunoBD();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(584, 492);
		this.setContentPane(getJContentPane());
		this.setTitle(bundle.getMessage("tela.nota.titulo"));
		this.setVisible(true);
		loadJCompos();
	}

	public JComboBox getJComboBoxTurma() {
		if (jComboBoxTurma == null) {
			jComboBoxTurma = new JComboBox();
			jComboBoxTurma.setBounds(new Rectangle(82, 58, 484, 25));
			jComboBoxTurma.addItemListener(this);
			jComboBoxTurma.addItem("");
		}
		return jComboBoxTurma;
	}

	public JTextField getJTextFieldNota() {
		if (jTextFieldNota == null) {
			jTextFieldNota = new JTextField();
			jTextFieldNota.setBounds(new Rectangle(82, 190, 110, 25));
		}
		return jTextFieldNota;
	}

	public JButton getJButtonIncluir() {
		if (jButtonIncluir == null) {
			jButtonIncluir = new JButton();
			jButtonIncluir.setBounds(new Rectangle(19, 402, 102, 26));
			jButtonIncluir.setText(bundle.getMessage("botao.incluir"));
			jButtonIncluir.addMouseListener(this);
		}
		return jButtonIncluir;
	}

	public JButton getJButtonAlterar() {
		if (jButtonAlterar == null) {
			jButtonAlterar = new JButton();
			jButtonAlterar.setBounds(new Rectangle(172, 402, 102, 26));
			jButtonAlterar.setText(bundle.getMessage("botao.alterar"));
			jButtonAlterar.addMouseListener(this);
		}
		return jButtonAlterar;
	}

	public JButton getJButtonExcluir() {
		if (jButtonExcluir == null) {
			jButtonExcluir = new JButton();
			jButtonExcluir.setBounds(new Rectangle(320, 404, 102, 24));
			jButtonExcluir.setText(bundle.getMessage("botao.excluir"));
			jButtonExcluir.addMouseListener(this);
		}
		return jButtonExcluir;
	}

	private JButton getJButtonCancelar() {
		if (jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			jButtonCancelar.setBounds(new Rectangle(465, 400, 102, 28));
			jButtonCancelar.setText(bundle.getMessage("botao.cancelar"));
			jButtonCancelar.addMouseListener(this);
		}
		return jButtonCancelar;
	}

	public JComboBox getJComboBoxCurso() {
		if (jComboBoxCurso == null) {
			jComboBoxCurso = new JComboBox();
			jComboBoxCurso.setBounds(new Rectangle(82, 17, 484, 25));
			jComboBoxCurso.addItemListener(this);
		}
		return jComboBoxCurso;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(19, 225, 548, 156));
			jScrollPane.setViewportView(getJListNotas());
		}
		return jScrollPane;
	}

	public JList getJListNotas() {
		if (jListNotas == null) {
			jListNotas = new JList();
			jListNotas.addMouseListener(this);
		}
		return jListNotas;
	}

	public JComboBox getJComboBoxAluno() {
		if (jComboBoxAluno == null) {
			jComboBoxAluno = new JComboBox();
			jComboBoxAluno.setBounds(new Rectangle(82, 145, 484, 25));
		}
		return jComboBoxAluno;
	}

	public JLabel getJLabelTurma() {
		if (jLabelTurma == null) {
			jLabelTurma = new JLabel();
			jLabelTurma.setBounds(new Rectangle(19, 58, 50, 16));
			jLabelTurma.setText(bundle.getMessage("label.turma"));
		}
		return jLabelTurma;
	}

	public JLabel getJLabelAluno() {
		if (jLabelAluno == null) {
			jLabelAluno = new JLabel();
			jLabelAluno.setBounds(new Rectangle(19, 145, 38, 16));
			jLabelAluno.setText(bundle.getMessage("label.aluno"));
		}
		return jLabelAluno;
	}

	public JLabel getJLabelProfessor() {
		if (jLabelProfessor == null) {
			jLabelProfessor = new JLabel();
			jLabelProfessor.setBounds(new Rectangle(19, 104, 39, 16));
			jLabelProfessor.setText(bundle.getMessage("label.professor"));
		}
		return jLabelProfessor;
	}

	public JLabel getJLabelCurso() {
		if (jLabelCurso == null) {
			jLabelCurso = new JLabel();
			jLabelCurso.setBounds(new Rectangle(19, 17, 38, 16));
			jLabelCurso.setText(bundle.getMessage("label.curso"));
		}
		return jLabelCurso;
	}

	public JLabel getJLabelNota() {
		if (jLabelNota == null) {
			jLabelNota = new JLabel();
			jLabelNota.setBounds(new Rectangle(19, 190, 40, 16));
			jLabelNota.setToolTipText("");
			jLabelNota.setText(bundle.getMessage("label.nota"));
		}
		return jLabelNota;
	}

	private JTextField getJTextFieldProfessor() {
		if (jTextFieldProfessor == null) {
			jTextFieldProfessor = new JTextField();
			jTextFieldProfessor.setBounds(new Rectangle(82, 104, 484, 25));
			jTextFieldProfessor.setEditable(false);
		}
		return jTextFieldProfessor;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJLabelAluno(), null);
			jContentPane.add(getJLabelCurso(), null);
			jContentPane.add(getJLabelNota(), null);
			jContentPane.add(getJLabelProfessor(), null);
			jContentPane.add(getJLabelTurma(), null);
			jContentPane.add(getJTextFieldNota(), null);
			jContentPane.add(getJComboBoxAluno(), null);
			jContentPane.add(getJComboBoxCurso(), null);
			jContentPane.add(getJTextFieldProfessor(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJComboBoxTurma(), null);
			jContentPane.add(getJButtonAlterar(), null);
			jContentPane.add(getJButtonExcluir(), null);
			jContentPane.add(getJButtonIncluir(), null);
			jContentPane.add(getJButtonCancelar(), null);

		}
		return jContentPane;
	}

	public void handlerNota(String option) {
		String idCurso, idTurma, raAluno;
		String[] infoCurso, infoAluno, infoTurma;
		String infoComboCurso = 
			getJComboBoxCurso().getSelectedItem() != null ? getJComboBoxCurso().getSelectedItem().toString() : "";
		String infoComboTurma = 
			getJComboBoxTurma().getSelectedItem() != null ? getJComboBoxTurma().getSelectedItem().toString() : "";
		String infoComboAluno = 
			getJComboBoxAluno().getSelectedItem() != null ? getJComboBoxAluno().getSelectedItem().toString() : "";
		String notaAluno = getJTextFieldNota().getText();
		infoCurso = getComboElements(infoComboCurso);
		idCurso = infoCurso[0].trim();
		infoAluno = getComboElements(infoComboAluno);
		raAluno = infoAluno[1];
		infoTurma = getComboElements(infoComboTurma);
		idTurma = infoTurma[0];
		if (option.equals(bundle.getMessage("botao.incluir"))) {
			notaBD.addNota(idCurso, idTurma, raAluno, notaAluno);
			updateList(idCurso, idTurma);
			getJTextFieldNota().setText("");
		} else if (option.equals(bundle.getMessage("botao.alterar"))) {
			notaBD.updateNota(idCurso, idTurma, raAluno, notaAluno);
			updateList(idCurso, idTurma);
			getJTextFieldNota().setText("");
		} else if (option.equals(bundle.getMessage("botao.excluir"))) {
			notaBD.excludeNota(idCurso, idTurma, raAluno, notaAluno);
			updateList(idCurso, idTurma);
			getJTextFieldNota().setText("");

		} else if (option.equals(bundle.getMessage("botao.selecionar"))) {
			infoComboAluno = getJListNotas().getSelectedValue().toString();
			infoAluno = getComboElements(infoComboAluno);
			notaAluno = infoAluno[3];
			String idAluno = infoAluno[0];
			raAluno = infoAluno[1];
			String nomeAluno = infoAluno[2];
			getJComboBoxAluno().setSelectedItem(
					idAluno + " - " + raAluno + " - " + nomeAluno);
			getJTextFieldNota().setText(notaAluno);

		} else if (option.equals(bundle.getMessage("combo.comboturma"))) {
			String[] infoCombo = getComboElements(infoComboCurso);
			idCurso = infoCombo[0].trim();
			String[] turmas = turmaBD.getInfoTurmas(idCurso);
			getJComboBoxTurma().removeAllItems();
			for (int i = 0; i < turmas.length; i++) {
				infoTurma = getComboElements(turmas[i]);
				idTurma = infoTurma[2];
				String periodo = infoTurma[5];
				getJComboBoxTurma().addItem(idTurma + " - " + periodo);
				if (i == 0) {
					String idProf = infoTurma[3];
					String nomeProf = infoTurma[4];
					getJTextFieldProfessor().setText(idProf + " - " + nomeProf);
					getJComboBoxTurma().setSelectedIndex(0);
					updateList(idCurso, idTurma);
				}
			}
		} else if (option.equals(bundle.getMessage("combo.combocurso"))) {
			String[] infoCombo = getComboElements(infoComboCurso);
			idCurso = infoCombo[0].trim();
			String[] turmas = turmaBD.getInfoTurmas(idCurso);
			getJComboBoxTurma().removeAllItems();
			for (int i = 0; i < turmas.length; i++) {
				infoTurma = getComboElements(turmas[i]);
				idTurma = infoTurma[2];
				String periodo = infoTurma[5];
				getJComboBoxTurma().addItem(idTurma + " - " + periodo);
				if (i == 0) {
					String idProf = infoTurma[3];
					String nomeProf = infoTurma[4];
					getJTextFieldProfessor().setText(idProf + " - " + nomeProf);
					getJComboBoxTurma().setSelectedIndex(0);
					updateList(idCurso, idTurma);
				}
			}

		}

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.getJButtonIncluir()) {
			handlerNota(bundle.getMessage("botao.incluir"));
		} else if (e.getSource() == this.getJButtonAlterar()) {
			handlerNota(bundle.getMessage("botao.alterar"));
		} else if (e.getSource() == this.getJButtonExcluir()) {
			handlerNota(bundle.getMessage("botao.excluir"));
		} else if (e.getSource() == this.getJListNotas()) {
			handlerNota(bundle.getMessage("botao.selecionar"));
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

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == getJComboBoxCurso()) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				handlerNota(bundle.getMessage("combo.combocurso"));
			}
		} else if (e.getSource() == getJComboBoxTurma()) {

			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (getJComboBoxTurma().getSelectedItem().toString().length() > 0) {
				}
			}
		}

	}

	public void loadJCompos() {
		String[] cursos = cursoBD.getInfoCursos();
		String[] alunos = alunoBD.getInfoAlunos();
		String[] infoCurso = null;
		for (int i = 0; i < alunos.length; i++) {
			String[] infoAlu = getComboElements(alunos[i]);

			String idAluno = infoAlu[0].trim();
			String raAluno = infoAlu[1].trim();
			String nomeAluno = infoAlu[2].trim();
			getJComboBoxAluno().addItem(
					idAluno + " - " + raAluno + " - " + nomeAluno);
		}
		for (int i = 0; i < cursos.length; i++) {
			infoCurso = getComboElements(cursos[i]);
			String idCurso = infoCurso[0].trim();
			String nomeCurso = infoCurso[1].trim();
			getJComboBoxCurso().addItem(idCurso + " - " + nomeCurso);
		}
	}

	private void updateList(String idCurso, String idTurma) {
		String[] infos = notaBD.getInfoNotas(idCurso, idTurma);
		getJListNotas().setListData(infos);
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

}
