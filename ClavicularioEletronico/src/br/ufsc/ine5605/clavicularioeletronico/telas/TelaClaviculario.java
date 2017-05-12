package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorClaviculario;
import br.ufsc.ine5605.clavicularioeletronico.validacoes.ValidacaoDadosFuncionario;

public class TelaClaviculario extends TelaBase {
        
    public TelaClaviculario() {
        super();
    }
    
    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("----------------Claviculario Eletronico-----------------");
            System.out.println("1) Retirar Chave");
            System.out.println("2) Devolver Chave");
            System.out.println("3) Relatorios");
            System.out.println("0) Sair");
        
            try {
                opcao = Integer.parseInt(this.teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("A opcao precisa ser um numero!");
            }
            if (opcao < 3 || opcao > 0) {
                switch (opcao) {
                    case 1:
                        retirarChave();
                        break;
                    case 2:
                        devolverChave();
                        break;
                    case 3:
                        relatorios();
                        break;
                }
            } else {
                System.out.println("Informe uma opcao valida!");
            }
        }
    }
    
    private void retirarChave() {
        try {
            ControladorClaviculario.getInstance().retirarChave(inputMatricula());
            System.out.println("Chave liberada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }
    
    private void devolverChave() {
        try {
            ControladorClaviculario.getInstance().devolverVeiculo(
                    inputMatricula(), inputPlaca(), inputQuilometragemAtual()
            );
            System.out.println("Chave devolvida com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }
    
    private void relatorios() {
        
    }
    
    public int pedeMatricula() {
        System.out.println("Matricula: ");
        if (this.teclado.hasNextInt()) {
            return this.teclado.nextInt();
        }
        System.out.println("A matricula deve conter apenas numeros!");
        return 0;
    }

    public String pedePlaca() {
        System.out.println("Placa: ");
        String placa = this.teclado.next();
        if (placa == null || placa.isEmpty()) {
            System.out.println("Informe a placa!");
        }
        return placa;
    }
    
}