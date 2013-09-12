/**
 * Autbank Projetos e Consultoria Ltda.
 * <br>
 * Criado em 11/09/2013 - 10:50:38
 * <br>
 * @version $Revision$ de $Date$<br>
 *           por $Author$<br>
 * @author luizricardo<br>
 */
package br.com.starcode.exemplos.jpa01.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Banco {

	@Id
	private String codigo;
	
	@Basic
	private String nome;
	
	public Banco() {
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Banco)) return false;
		Banco other = (Banco) obj;
		if (codigo == null) {
			if (other.codigo != null) return false;
		} else if (!codigo.equals(other.codigo)) return false;
		return true;
	}
	
}
