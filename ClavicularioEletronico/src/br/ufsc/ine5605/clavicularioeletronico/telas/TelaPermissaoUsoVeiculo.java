package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorPermissaoUsoVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.List;

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
            System.out.println("1 - Incluir");       
            System.out.println("2 - Excluir");
            System.out.println("3 - Listar cadastros");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("-------------------------------------------------");
            System.out.print("Opção escolhida: ");
            
            try {
                opcao = Integer.parseInt(this.teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("A opcao precisa ser um numero!");
            }
            if (opcao > 3 || opcao < 0) {
                System.out.println("Informe uma opcao valida!");
            }
            else {
                switch (opcao) {
                    case 1: exibeTelaInclui();
                            break;

                    case 2: exibeTelaExclui();
                            break;
                            
                    case 3: exibeLista();
                            break;
                }
            }
        }             
    }
    
    @Override
    public void exibeTelaInclui() {
        try {
            ControladorPermissaoUsoVeiculo.getInstance().inclui(inputMatricula(), inputPlaca());
            System.out.println("Permissao cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void exibeTelaAltera() {
        
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorPermissaoUsoVeiculo.getInstance().exclui(inputMatricula(), inputPlaca());
            System.out.println("Permissao excluida com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para solicitar a matrícula do funcionário para imprimir a
     * lista
     */

    @Override
    public void exibeLista() {
        try {
            System.out.println("-------------------Permissoes---------------------");
            for (ItemListaCadastro item: ControladorPermissaoUsoVeiculo.getInstance().exibeListaPermissoes(inputMatricula())) {
                System.out.println(item.getDescricao());
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String inputPlaca() {
        System.out.println("Digite a placa do veiculo. Ex: (AAA-9999): ");
        String placa = this.teclado.nextLine();
        //if (!placa.matches("[A-Z]{3}-{1}\\d{4}")) {
        //    System.out.println("A placa deve ser digitada no seguinte modelo: AAA-9999");
        //}
        return placa;
    }
    
    private int inputMatricula() {
        System.out.println("Digite a matricula: ");
        String input = this.teclado.nextLine();
        int matricula = -1;
        if (input.matches("[0-9]")) {
            matricula = Integer.parseInt(input);
        }
        return matricula;
    }
}
