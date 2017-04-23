package br.ufsc.ine5605.clavicularioeletronico.transferencias;

/**
 * Objeto utilizado para transferir as descrições dos itens cadastrados
 * para a tela listá-los
 * (Data Transfer Object)
 * @author Flávio
 */
public class ItemListaCadastro {
    
    private String descricao;

    public ItemListaCadastro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
