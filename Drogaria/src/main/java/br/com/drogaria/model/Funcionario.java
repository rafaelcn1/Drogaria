package br.com.drogaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tbl_funcionarios")
/*@NamedQueries({
	@NamedQuery(name = "Funcionario.lista", query = "SELECT Funcionario FROM Funcionario funcionario")
})*/
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fun_codigo")
	private Long codigo;
	
	@NotEmpty(message = "Campo nome é obrigatório!")
	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;
	
	@NotEmpty(message = "Campo função é obrigatório!")
	@Column(name = "fun_funcao", length = 50, nullable = false)
	private String funcao;
	
	@NotEmpty(message = "Campo cpf é obrigatório!")
	@CPF
	@Column(name = "fun_cpf", length = 14, nullable = false)
	private String cpf;
	
	@NotEmpty(message = "Campo senha é obrigatório!")
	@Size(max = 8, min = 8, message = "Tamanho da senha tem que ser 8 caracteres")
	@Column(name = "fun_senha", length = 32, nullable = false)
	private String senha;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", funcao=" + funcao + ", cpf=" + cpf + ", senha="
				+ senha + "]";
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
		Funcionario other = (Funcionario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
