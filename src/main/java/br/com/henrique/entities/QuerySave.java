package br.com.henrique.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "consulta")
@Data
public class QuerySave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "valor_pesquisado", nullable = false)
	private String consulta;
	@Column(name = "data_consulta", nullable = false)
	private LocalDateTime data;
	
	public QuerySave(String consulta) {
		this.consulta = consulta; 
	}
	

}
