package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;

public class TelaVeiculo extends TelaCadastro {

    @Override
    public void exibeMenu() {
        System.out.println("----------------Menu Veiculo-----------------");
        super.exibeMenu();
    }
    
    @Override
    public void exibeTelaInclui() {
        
        DadosVeiculo novoVeiculo = entradaDadosVeiculo();
        
        try {
            ControladorVeiculo.getInstance().inclui(novoVeiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void exibeTelaAltera() {
        DadosVeiculo alteraVeiculo = entradaDadosVeiculo();
        
        try {
            ControladorVeiculo.getInstance().altera(alteraVeiculo);
            System.out.println("Veiculo alterado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorVeiculo.getInstance().exclui(inputPlaca());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void exibeLista() {
        System.out.println("--------------Veiculos cadastrados---------------");
        for (ItemListaCadastro item: ControladorVeiculo.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
    }
    
    private DadosVeiculo entradaDadosVeiculo() {
        return new DadosVeiculo(inputPlaca(), inputModelo(), inputMarca(), inputAno(), inputQuilometragemAtual());
    }
    
    private String inputPlaca() {
        System.out.println("Digite a placa do veiculo. Ex: (AAA-9999): ");
        String placa = leitor.nextLine();
        //if (!placa.matches("[A-Z]{3}-{1}\\d{4}")) {
        //    System.out.println("A placa deve ser digitada no seguinte modelo: AAA-9999");
        //}
        return placa;
    }
    
    private String inputModelo() {
        System.out.println("Digite o modelo do veiculo: ");
        return leitor.nextLine();
    }
    
    private String inputMarca() {
        System.out.println("Digite o marca do veiculo: ");
        return leitor.nextLine();
    }
    
    private int inputAno(){
        System.out.println("Digite o ano do veiculo: ");
        String input = leitor.nextLine();
        int ano = 0;
        if (input.matches("[0-9]{4}")) {
            ano = Integer.parseInt(input);
        }
        //else {
        //    throw new Exception("Você deve informar um numero!");
        //}
        return ano;
    }
    
    private int inputQuilometragemAtual() {
        System.out.println("Digite a quilometragem atual do veiculo: ");
        String input = leitor.nextLine();
        int quilometragemAtual = 0;
        if (input.matches("[0-9]")) {
            quilometragemAtual = Integer.parseInt(input);
        }
        //else {
        //    throw new Exception("Você deve informar um numero!");
        //}
        return quilometragemAtual;
    }

}