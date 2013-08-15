package estoque.business.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Padrão Transfer Object
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	@Id
	@Column(name = "idproduto")
	private Integer id;

	@Column(name = "descProduto")
	private String descricao;

	@Column(name = "dataInclusao")
	private Date data;

	private BigDecimal valor;

	private String observacao;

	private Situacao sitProduto;

	private BigDecimal precoVenda;

	@JoinColumn(name = "idCategoria")
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Situacao getSitProduto() {
		return sitProduto;
	}

	public void setSitProduto(Situacao sitProduto) {
		this.sitProduto = sitProduto;
	}

}