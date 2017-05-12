package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorClaviculario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.List;
import java.util.Scanner;

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
                        exibeMenuRelatorios();
                        break;
                }
            } else {
                System.out.println("Informe uma opcao valida!");
            }
        }
    }
    
    private void exibeMenuRelatorios() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("----------------Relatorios-----------------");
            System.out.println("1) Exibe relatorio completo");
            System.out.println("2) Pesquisa por funcionario");
            System.out.println("3) Pesquisa por veiculo ");
            System.out.println("4) Pesquisa por evento");
            System.out.println("0) Sair");
        
            if (this.teclado.hasNextInt()) {
                opcao = this.teclado.nextInt();
                switch (opcao) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        exibeMenuEventos();
                        break;
                }
            } else {
                System.out.println("Informe uma opcao valida!");
            }
        }
    }
    
    private void exibeMenuEventos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("-----------Selecione um evento---------------");
            System.out.println("1) Acesso permitido");
            System.out.println("2) Acesso bloqueado");
            System.out.println("3) Usuario bloqueado");
            System.out.println("4) Matricula invalida");
            System.out.println("5) Permissao insuficiente");
            System.out.println("6) Veiculo indisponivel");
            System.out.println("7) Veiculo devolvido");
            System.out.println("0) Sair");
        
            if (this.teclado.hasNextInt()) {
                opcao = this.teclado.nextInt();
                switch (opcao) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
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
    
    private void exibeRelatorios (List<ItemListaCadastro> relatorio) {
        
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