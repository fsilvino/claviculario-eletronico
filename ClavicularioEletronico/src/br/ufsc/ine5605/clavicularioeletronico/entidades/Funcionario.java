package br.ufsc.ine5605.clavicularioeletronico.entidades;

import java.util.Date;
import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;

public class Funcionario {

	private int matricula;

	private String nome;

	private Date nascimento;

	private String telefone;

	private boolean bloqueado;

	private Veiculo veiculo;

	private Cargo cargo;

}
