package estoque.business.to;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

// Padrão Transfer Object
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	@Id
	@Column(name = "idCategoria", nullable = false)
	private Integer id;

	@Column(name = "descCategoria", nullable = false)
	private String categoria;

	private Situacao sitCategoria;

	@JoinColumn(name = "idCategoria")
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private java.util.Collection<Produto> produtos;

	public Categoria() {
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection produtos) {
		this.produtos = produtos;
	}

	public Situacao getSitCategoria() {
		return sitCategoria;
	}

	public void setSitCategoria(Situacao sitCategoria) {
		this.sitCategoria = sitCategoria;
	}

}