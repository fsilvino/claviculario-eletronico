package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.telas.TelaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flávio
 */
public abstract class ControladorCadastro<C> {

    protected TelaCadastro tela;
    protected List<C> itens;

    public ControladorCadastro() {
        itens = new ArrayList<>();
    }
    
    public abstract List<ItemListaCadastro> getListaItensCadastro();

    public void inicia() {
        this.exibeMenu();
    }

    protected void exibeMenu() {
        tela.exibeMenu();
    }

    public void abreTelaInclui() {
        tela.exibeTelaInclui();
    }

    public void abreTelaAltera() {
        tela.exibeTelaAltera();
    }

    public void abreTelaExclui() {
        tela.exibeTelaExclui();
    }

    public void exibeListaItens() {
        tela.exibeLista(getListaItensCadastro());
    }
    
}
