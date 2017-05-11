package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.validacoes.ValidacaoDadosVeiculo;

public class TelaVeiculo extends TelaCadastro {

    public TelaVeiculo() {
        super();
    }

    
    @Override
    public void exibeMenu() {
        System.out.println("----------------Menu Veiculo-----------------");
        super.exibeMenu();
    }
    
    @Override
    public void exibeTelaInclui() {
        
        
        
        try {
            DadosVeiculo novoVeiculo = entradaDadosVeiculo();
            ControladorVeiculo.getInstance().inclui(novoVeiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }

    @Override
    public void exibeTelaAltera() {
        
        
        try {
            DadosVeiculo alteraVeiculo = entradaDadosVeiculo();
            ControladorVeiculo.getInstance().altera(alteraVeiculo);
            System.out.println("Veiculo alterado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorVeiculo.getInstance().exclui(inputPlaca());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }
    
    @Override
    public void exibeLista() {
        System.out.println("--------------Veiculos cadastrados---------------");
        for (ItemListaCadastro item: ControladorVeiculo.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }
    
    private DadosVeiculo entradaDadosVeiculo() throws Exception {
        return new DadosVeiculo(inputPlaca(), inputModelo(), inputMarca(), inputAno(), inputQuilometragemAtual());
    }
    
    private String inputPlaca() throws Exception{
        System.out.println("Digite a placa do veiculo. Ex: (AAA-9999): ");
        String placa = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaPlaca(placa);
        return placa;
    }
    
    private String inputModelo() throws Exception{
        System.out.println("Digite o modelo do veiculo: ");
        String modelo = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaModelo(modelo);
        return modelo;
    }
    
    private String inputMarca() throws Exception{
        System.out.println("Digite o marca do veiculo: ");
        String marca = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaMarca(marca);
        return marca;
    }
    
    private int inputAno() throws Exception{
        System.out.println("Digite o ano do veiculo: ");
        String input = this.teclado.nextLine();
        int ano = 0;
        if (ValidacaoDadosVeiculo.validaAno(input)) {
            ano = Integer.parseInt(input);
        }
        return ano;
    }
    
    private int inputQuilometragemAtual() throws Exception{
        System.out.println("Digite a quilometragem atual do veiculo: ");
        String input = this.teclado.nextLine();
        int quilometragemAtual = 0;
        if (ValidacaoDadosVeiculo.validaQuilometragem(input)) {
            quilometragemAtual = Integer.parseInt(input);
        }
        return quilometragemAtual;
    }

}