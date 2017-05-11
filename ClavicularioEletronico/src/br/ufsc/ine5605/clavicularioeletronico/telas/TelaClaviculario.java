package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorClaviculario;

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
            System.out.println("0) Voltar ao menu inicial");
        
            if (this.teclado.hasNextInt()) {
                opcao = this.teclado.nextInt();
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
            ControladorClaviculario.getInstance().retirarChave();
            System.out.println("Chave liberada com sucesso!");
            solicitaEnterParaContinuar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void devolverChave() {
        
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