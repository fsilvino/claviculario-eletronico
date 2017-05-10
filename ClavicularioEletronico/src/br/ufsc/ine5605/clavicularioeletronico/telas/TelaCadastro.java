package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.List;

public abstract class TelaCadastro {

    public abstract void exibeTelaInclui();

    public abstract void exibeTelaAltera();

    public abstract void exibeTelaExclui();

    public void exibeMenu() {

    }

    public void exibeLista(List<ItemListaCadastro> itens) {

    }

}