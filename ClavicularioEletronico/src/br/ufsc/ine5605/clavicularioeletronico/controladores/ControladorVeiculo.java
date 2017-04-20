package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.entidades.Veiculo;

public class ControladorVeiculo extends ControladorCadastro {

    private static ControladorVeiculo instancia;

    private ConteudoCadVeiculo conteudoCadVeiculo;

    public static ControladorVeiculo getInstance() {
        return null;
    }

    @Override
    protected void exibeMenuCadastro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Veiculo findVeiculoPelaPlaca(String placa) {
        return null;
    }

    public void inclui(ConteudoCadVeiculo veiculo) {

    }

    public void altera(ConteudoCadVeiculo veiculo) {

    }

    public void exclui(String placa) {

    }

    public void exclui(Veiculo veiculo) {

    }

}
