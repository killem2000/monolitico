/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.allan.produto.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "operador")
@SequenceGenerator(name = "seq_operador", sequenceName = "seq_operador")
public class Operador implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_operador")
    @Column(name="id")
    private int id;
	
    @Size(max = 3)
    @Column(name = "cod_interno")
    private String codInterno;
    
    @Size(max = 40)
    @Column(name = "nome")
    private String nome;
    
    @Size(max = 6)
    @Column(name = "senha")
    private String senha;
        
    public String getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(String codInterno) {
        this.codInterno = codInterno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + this.id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Operador other = (Operador) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
