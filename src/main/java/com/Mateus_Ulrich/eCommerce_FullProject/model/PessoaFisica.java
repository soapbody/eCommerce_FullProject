package com.Mateus_Ulrich.eCommerce_FullProject.model;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;
	@CPF(message = "CPF está inválido")
	@Column(nullable = false)
	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PessoaFisica that = (PessoaFisica) o;
		return Objects.equals(cpf, that.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), cpf);
	}
}
