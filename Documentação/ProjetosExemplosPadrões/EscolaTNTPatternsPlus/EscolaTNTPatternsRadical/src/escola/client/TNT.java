package escola.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import escola.client.command.Command;
import escola.client.command.CommandAluno;
import escola.client.command.CommandCurso;
import escola.client.command.CommandNota;
import escola.client.command.CommandProfessor;
import escola.client.command.CommandTurma;
import escola.client.command.ReceptorAluno;
import escola.client.command.ReceptorCurso;
import escola.client.command.ReceptorNota;
import escola.client.command.ReceptorProfessor;
import escola.client.command.ReceptorTurma;
import escola.presentation.gui.utils.ResourceBundleUtils;

public class TNT extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ResourceBundleUtils bundle;

	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenuCadastro = null;
	private JMenuItem jMenuItemProfessor = null;
	private JMenuItem jMenuItemAluno = null;
	private JMenuItem jMenuItemTurma = null;
	private JMenuItem jMenuItemCurso = null;
	private JMenuItem jMenuItemNota = null;
	private JMenu jMenuRelatorio = null;
	private JMenu jMenuSair = null;

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuCadastro());
			jJMenuBar.add(getJMenuRelatorio());
			jJMenuBar.add(getJMenuSair());
			jJMenuBar.setOpaque(true);
		}
		return jJMenuBar;
	}

	private JMenu getJMenuCadastro() {
		if (jMenuCadastro == null) {
			jMenuCadastro = new JMenu(bundle.getMessage("menu.cadastro"));
			jMenuCadastro.add(getJMenuItemProfessor());
			jMenuCadastro.add(getJMenuItemAluno());
			jMenuCadastro.add(getJMenuItemTurma());
			jMenuCadastro.add(getJMenuItemCurso());
			jMenuCadastro.add(getJMenuItemNota());
		}
		return jMenuCadastro;
	}

	private JMenuItem getJMenuItemProfessor() {
		if (jMenuItemProfessor == null) {
			jMenuItemProfessor = new JMenuItem();
			jMenuItemProfessor.setText(bundle.getMessage("menu.professor"));
			jMenuItemProfessor.addActionListener(this);
		}
		return jMenuItemProfessor;
	}

	private JMenuItem getJMenuItemAluno() {
		if (jMenuItemAluno == null) {
			jMenuItemAluno = new JMenuItem(bundle.getMessage("menu.aluno"));
			jMenuItemAluno.addActionListener(this);
		}
		return jMenuItemAluno;
	}

	private JMenuItem getJMenuItemTurma() {
		if (jMenuItemTurma == null) {
			jMenuItemTurma = new JMenuItem(bundle.getMessage("menu.turma"));
			jMenuItemTurma.addActionListener(this);
		}
		return jMenuItemTurma;
	}

	private JMenuItem getJMenuItemCurso() {
		if (jMenuItemCurso == null) {
			jMenuItemCurso = new JMenuItem();
			jMenuItemCurso.setText(bundle.getMessage("menu.curso"));
			jMenuItemCurso.addActionListener(this);
		}
		return jMenuItemCurso;
	}

	private JMenuItem getJMenuItemNota() {
		if (jMenuItemNota == null) {
			jMenuItemNota = new JMenuItem();
			jMenuItemNota.setText(bundle.getMessage("menu.notas"));
			jMenuItemNota.addActionListener(this);
		}
		return jMenuItemNota;
	}

	private JMenu getJMenuRelatorio() {
		if (jMenuRelatorio == null) {
			jMenuRelatorio = new JMenu();
			jMenuRelatorio.setText(bundle.getMessage("menu.relatorios"));

		}
		return jMenuRelatorio;
	}

	private JMenu getJMenuSair() {
		if (jMenuSair == null) {
			jMenuSair = new JMenu(bundle.getMessage("menu.sair"));
		}
		return jMenuSair;
	}

	public static void main(String[] args) {

		TNT thisClass = new TNT();
		thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisClass.setResizable(false);
		thisClass.setVisible(true);

	}

	public TNT() {
		super();
		bundle = ResourceBundleUtils.getInstance();
		initialize();
	}

	private void initialize() {
		this.setSize(745, 278);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.getJMenuItemProfessor()) {
			// Chama o cadastro de Professor utilizando o padrao Command
			Command commandProfessor = new CommandProfessor(
					new ReceptorProfessor());
			commandProfessor.executar();
		} else if (e.getSource() == getJMenuItemAluno()) {
			// Chama o cadastro de Aluno utilizando o padrao Command
			Command commandAluno = new CommandAluno(new ReceptorAluno());
			commandAluno.executar();

		} else if (e.getSource() == getJMenuItemTurma()) {
			// Chama o cadastro de Turma utilizando o padrao Command
			Command commandTurma = new CommandTurma(new ReceptorTurma());
			commandTurma.executar();
		} else if (e.getSource() == getJMenuItemCurso()) {
			// Chama o cadastro de Curso utilizando o padrao Command
			Command commandCurso = new CommandCurso(new ReceptorCurso());
			commandCurso.executar();
		} else if (e.getSource() == getJMenuItemNota()) {
			// Chama o cadastro de Nota utilizando o padrao Command
			Command commandNota = new CommandNota(new ReceptorNota());
			commandNota.executar();
		}
	}
}
