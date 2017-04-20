package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.telas.TelaCadastro;
import java.util.List;

public abstract class ControladorCadastro<C> {

	private TelaCadastro tela;

	private List<C> itens;

	public void inicia() {

	}

	protected abstract void exibeMenuCadastro();

	public void abreTelaInclui() {

	}

	public void abreTelaAltera() {

	}

	public void abreTelaExclui() {

	}

	public void exibeListaItens() {

	}

}
