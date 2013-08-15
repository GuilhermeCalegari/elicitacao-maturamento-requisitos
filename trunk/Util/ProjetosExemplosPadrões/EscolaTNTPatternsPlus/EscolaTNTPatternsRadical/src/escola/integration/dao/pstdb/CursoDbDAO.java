package escola.integration.dao.pstdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Curso;
import escola.business.to.Professor;
import escola.business.to.Turma;
import escola.integration.dao.CursoDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class CursoDbDAO implements CursoDAO {

	private ArrayList cursos;

	public CursoDbDAO() {
		cursos = new ArrayList<Curso>();
	}

	public void addCurso(Curso curso) {
		Connection conn = null;
		Statement stmt = null;
		cursos = null;
		String sql = "INSERT INTO tnt.curso (idcurso,nomecurso) values ('"
				+ curso.getIdCurso() + "','" + curso.getNomeCurso() + "')";
		try {
			conn = ConnectionTNT.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void addTurma(Curso curso, Turma turma) {

	}

	public void updateCurso(Curso curso) {
		Connection conn = null;
		Statement stmt = null;
		String sql = "UPDATE tnt.curso SET nomecurso = '"
				+ curso.getNomeCurso() + "'  WHERE idcurso ='"
				+ curso.getIdCurso() + "'";
		try {
			conn = ConnectionTNT.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// TODO REVER ESTES METODOS TEM Q. PASSAR OBJ.
	public void updateTurma(Curso curso, Turma turma) {

	}

	public void excludeCurso(Curso curso) {
		Connection conn = null;
		Statement stmt = null;

		String sql = "DELETE FROM tnt.curso  WHERE idcurso ='"
				+ curso.getIdCurso() + "'";
		try {
			conn = ConnectionTNT.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// TODO VERIFICAR
	public void excludeTurma(Curso curso, Turma turma) {

	}

	public Curso findCurso(Curso curso) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		Curso cursoRetorno = null;

		String sql = "SELECT  idcurso,nomecurso FROM tnt.curso WHERE idcurso = (?)";
		try {
			conn = ConnectionTNT.getInstance().getConnection();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, curso.getIdCurso());
			rSet = pStmt.executeQuery(sql);
			if (rSet != null) {
				cursoRetorno = new Curso();
				cursoRetorno.setIdCurso(rSet.getString("idcurso"));
				cursoRetorno.setNomeCurso(rSet.getString("nomecurso"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rSet != null)
					rSet.close();
				if (pStmt != null)
					pStmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cursoRetorno;
	}

	public String[] getInfoCursos() {
		cursos = this.getCursos();
		String[] info = new String[cursos.size()];
		for (int i = 0; i < cursos.size(); i++) {
			Curso curso = (Curso) cursos.get(i);
			info[i] = curso.getIdCurso() + " - " + curso.getNomeCurso();
		}
		return info;
	}

	public String[] getInfoTurmas(Curso curso) {

		return null;
	}

	public ArrayList getCursos() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rSet = null;
		Curso curso = null;
		cursos = new ArrayList();
		String sql = "SELECT  idcurso,nomecurso FROM tnt.curso";
		try {
			conn = ConnectionTNT.getInstance().getConnection();
			stmt = conn.createStatement();
			rSet = stmt.executeQuery(sql);
			if (rSet != null) {
				while (rSet.next()) {

					curso = new Curso();
					curso.setIdCurso(rSet.getString("idcurso"));
					curso.setNomeCurso(rSet.getString("nomecurso"));
					cursos.add(curso);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rSet != null)
					rSet.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public Turma findTurma(Curso curso, String idTurma) {
		Turma turmaLaco, turmaRetorno;

		turmaRetorno = null;
		Iterator iteratorTurmas = curso.getTurmas().iterator();
		while (iteratorTurmas.hasNext()) {
			turmaLaco = (Turma) iteratorTurmas.next();
			if (turmaLaco.getIdTurma().equals(idTurma))
				turmaRetorno = turmaLaco;
		}
		return turmaRetorno;
	}

	public void addTurma(Curso curso, String cod, Professor prof, String per) {
		Turma turma = new Turma(cod, prof, per);
		curso.getTurmas().add(turma);
	}

	public void removeTurma(Curso curso, Turma turma) {
		curso.getTurmas().remove(turma);
	}
}
