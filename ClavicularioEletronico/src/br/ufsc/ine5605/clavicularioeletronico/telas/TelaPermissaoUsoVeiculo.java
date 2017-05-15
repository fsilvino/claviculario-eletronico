package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorPermissaoUsoVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.Listavel;

/**
 *
 * @author Gabriel
 */
public class TelaPermissaoUsoVeiculo extends TelaCadastro {
    
    @Override
    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("----------------Menu Permissoes-----------------");
            System.out.println("1 - Incluir Permissao");       
            System.out.println("2 - Excluir Permissao");
            System.out.println("3 - Listar Veiculos que o funcionario pode utilizar");
            System.out.println("0 - Voltar");
            opcao = inputOpcao();
            if (opcao != 0) {
                switch (opcao) {
                    case 1:
                        exibeTelaInclui();
                        break;
                    case 2:
                        exibeTelaExclui();
                        break;    
                    case 3:
                        exibeLista();
                        break;
                    default:
                        System.out.println("Informe uma opcao valida!");
                }
            }
        }             
    }
    
    @Override
    public void exibeTelaInclui() {
        try {
            ControladorPermissaoUsoVeiculo.getInstance().inclui();
            System.out.println("Permissao cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }

    @Override
    public void exibeTelaAltera() {
        throw new UnsupportedOperationException("Esta opcao nao deve ser utilizada nesta tela!");
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorPermissaoUsoVeiculo.getInstance().exclui();
            System.out.println("Permissao excluida com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }

    /**
     * Método usado para solicitar a matrícula do funcionário para imprimir a
     * lista
     */

    @Override
    public void exibeLista() {
        try {
            System.out.println("-------------------Permissoes de Uso dos Veiculos---------------------");
            for (Listavel item: ControladorPermissaoUsoVeiculo.getInstance().getListaPermissoes(inputMatricula())) {
                System.out.println(item.getDescricao());
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }
}
