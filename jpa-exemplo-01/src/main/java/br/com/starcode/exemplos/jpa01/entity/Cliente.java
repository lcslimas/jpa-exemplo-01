/**
 * Autbank Projetos e Consultoria Ltda.
 * <br>
 * Criado em 11/09/2013 - 10:49:02
 * <br>
 * @version $Revision$ de $Date$<br>
 *           por $Author$<br>
 * @author luizricardo<br>
 */
package br.com.starcode.exemplos.jpa01.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
@Table(name = "Cliente")
public class Cliente {
	
	@Id @GeneratedValue
	private long id;

	@Basic private String nome;

	@Temporal(TemporalType.DATE)
	private Date nascimento;

	@ManyToOne
	@JoinColumn(name = "banco", referencedColumnName = "codigo")
	private Banco banco;

	public Cliente() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
