package br.com.drogaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_fabricantes")
/*@NamedQueries({
		@NamedQuery(name = "Fabricante.buscarPorCodigo", query = "SELECT fabricante FROM Fabricante fabricante WHERE fabricante.codigo = :fab_codigo"),
		@NamedQuery(name = "Fabricante.listar", query =  "SELECT fabricante FROM Fabricante fabricante")
})	*/
public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fab_codigo")
	private Long codigo;
	
	@Size(max = 50, min = 5, message = "Tamanho inválido! (5 - 50)")
	@NotEmpty(message = "Campo obrigatório!")
	@Column(name = "fab_descricao", length = 50, nullable = false)
	private String descricao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Fabricante [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
