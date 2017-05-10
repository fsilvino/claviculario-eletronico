package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorSistema;
import java.util.Scanner;

/**
 * 
 * @author Gabriel
 */
public class TelaSistema {

    private Scanner teclado;

    public TelaSistema() {
        this.teclado = new Scanner(System.in);
    }
    
    
      
    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("-----------------Menu Principal------------------");
            System.out.println("1 - Funcionarios");
            System.out.println("2 - Veiculos");
            System.out.println("3 - Permissoes");
            System.out.println("4 - Log de eventos");
            System.out.println("0 - Sair do sistema");
            System.out.println("-------------------------------------------------");
            System.out.print("Opcao escolhida: ");
            try {
                opcao = Integer.parseInt(this.teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("A opcao precisa ser um numero!");
            }
            if (opcao > 4 || opcao < 0) {
                System.out.println("Informe uma opcao valida!");
            }
            else {
                switch (opcao) {
                    case 1: ControladorSistema.getInstance().abreCadastroFuncionario();
                            break;
                    case 2: ControladorSistema.getInstance().abreCadastroVeiculo();
                            break;
                    case 3: ControladorSistema.getInstance().abreCadastroPermissaoUsoVeiculo();
                            break;
                }
            }
        }
    }

}
