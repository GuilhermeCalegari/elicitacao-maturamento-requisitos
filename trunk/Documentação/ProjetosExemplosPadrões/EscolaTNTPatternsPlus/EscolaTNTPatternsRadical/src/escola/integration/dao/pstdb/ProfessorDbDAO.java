package escola.integration.dao.pstdb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import escola.business.to.Professor;
import escola.integration.dao.ProfessorDAO;

/**
 * Padrão Abstract Factory (Concrete Product) 
 * 
 * @author Luiz Gustavo
 */
public class ProfessorDbDAO implements ProfessorDAO {

	private ArrayList<Professor> professores;

	public ProfessorDbDAO() {
		professores = new ArrayList<Professor>();
	}

	public void addProfessor(Professor professor) {

		Connection conn = null;
		CallableStatement spManterProfessor = null;
		try {
			System.out.println(professor.getNomePessoa());
			String sql = "{call tnt.manter_professor(?,?,?)}";
			conn = ConnectionTNT.getInstance().getConnection();
			spManterProfessor = conn.prepareCall(sql);
			spManterProfessor.setInt(1, professor.getIdPessoa());
			spManterProfessor.setString(2, professor.getNomePessoa());
			spManterProfessor.setString(3, professor.getEspecialidade());
			spManterProfessor.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (spManterProfessor != null)
					spManterProfessor.close();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void updateProfessor(Professor prof) {
		Professor professorProcura;
		professorProcura = findProfessor(prof);
		if (professorProcura != null) {
			professorProcura.setEspecialidade(prof.getEspecialidade());
			professorProcura.setNomePessoa(prof.getNomePessoa());
		}
	}

	public void excludeProfessor(Professor prof) {
		professores.remove(prof);
	}

	public Professor findProfessor(Professor prof) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rSet = null;
		Professor professorRetorno = null;

		String sql = "SELECT  pessoa.idpessoa, nomepessoa, especialidade ";
		sql += " FROM tnt.pessoa, tnt.professor ";
		sql += " WHERE tnt.professor.idpessoa = tnt.pessoa.idpessoa ";
		sql += " AND tnt.professor.idpessoa = (?)";

		try {
			conn = ConnectionTNT.getInstance().getConnection();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, prof.getIdPessoa());
			rSet = pStmt.executeQuery(sql);
			if (rSet != null) {
				professorRetorno = new Professor();
				professorRetorno.setIdPessoa(rSet.getInt("idpessoa"));
				professorRetorno.setNomePessoa(rSet.getString("nomepessoa"));
				professorRetorno.setEspecialidade(rSet
						.getString("especialidade"));
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
		return professorRetorno;
	}

	public String[] getInfoProfessores() {

		professores = getProfessores();
		String[] infos = new String[professores.size()];
		Iterator itProf = professores.iterator();
		int i = 0;
		while (itProf.hasNext()) {
			Professor prof = (Professor) itProf.next();
			infos[i] = prof.getIdPessoa() + " - " + prof.getNomePessoa().trim()
					+ " - " + prof.getEspecialidade();
			i++;
		}
		return infos;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

	public ArrayList<Professor> getProfessores() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rSet = null;
		Professor professor = null;
		professores = new ArrayList();
		String sql = "SELECT  pessoa.idpessoa, nomepessoa, especialidade ";
		sql += " FROM tnt.pessoa, tnt.professor ";
		sql += " WHERE tnt.professor.idpessoa = tnt.pessoa.idpessoa";

		try {
			conn = ConnectionTNT.getInstance().getConnection();
			stmt = conn.createStatement();
			rSet = stmt.executeQuery(sql);
			if (rSet != null) {
				while (rSet.next()) {

					professor = new Professor();
					professor.setIdPessoa(rSet.getInt("idpessoa"));
					professor.setNomePessoa(rSet.getString("nomepessoa"));
					professor.setEspecialidade(rSet.getString("especialidade"));
					professores.add(professor);
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

		return professores;
	}
}
