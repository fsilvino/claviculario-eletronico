package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.telas.TelaSistema;

/**
 *
 * @author Flávio
 */
public class ControladorSistema {

    private static ControladorSistema instancia;
    
    private TelaSistema tela;
    
    private ControladorSistema() {
        tela = new TelaSistema();
    }

    public static ControladorSistema getInstance() {
        if (instancia == null) {
            instancia = new ControladorSistema();
        }
        return instancia;
    }

    public void inicia() {
        this.exibeMenuInicial();
    }

    public void exibeMenuInicial() {
        tela.exibeMenu();
    }

    public void abreCadastroVeiculo() {
        ControladorVeiculo.getInstance().inicia();
    }

    public void abreCadastroFuncionario() {
        ControladorFuncionario.getInstance().inicia();
    }

    public void abreClaviculario() {
        ControladorClaviculario.getInstance().inicia();
    }

}
