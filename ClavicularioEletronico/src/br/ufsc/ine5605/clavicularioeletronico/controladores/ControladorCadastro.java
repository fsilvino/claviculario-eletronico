package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.telas.TelaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para os controladores de cadastros,
 * implementa as funcões básicas de cadastro (CRUD)
 * @author Flávio
 * @param <T> Tipo da Tela que sera utilizada
 * @param <E> Tipo de entidade a ser cadastrada
 */
public abstract class ControladorCadastro<T extends TelaCadastro, E> {

    protected T tela;
    protected List<E> itens;

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