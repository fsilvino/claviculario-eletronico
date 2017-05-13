package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.Listavel;

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
        solicitaEnterParaContinuar();
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
        solicitaEnterParaContinuar();
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorVeiculo.getInstance().exclui(inputPlaca());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }
    
    @Override
    public void exibeLista() {
        System.out.println("--------------Veiculos cadastrados---------------");
        for (Listavel item: ControladorVeiculo.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
        solicitaEnterParaContinuar();
    }
    
    private DadosVeiculo entradaDadosVeiculo() throws Exception {
        return new DadosVeiculo(inputPlaca(), inputModelo(), inputMarca(), inputAno(), inputQuilometragemAtual());
    }
}