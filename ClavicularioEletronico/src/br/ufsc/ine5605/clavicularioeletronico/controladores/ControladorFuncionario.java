package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.entidades.Funcionario;

public class ControladorFuncionario extends ControladorCadastro {

    private static ControladorFuncionario instancia;

    public static ControladorFuncionario getInstance() {
        return null;
    }

    @Override
    protected void exibeMenuCadastro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Funcionario findFuncionarioPelaMatricula(int matricula) {
        return null;
    }

    public void inclui(ConteudoCadFuncionario funcionario) {

    }

    public void altera(ConteudoCadFuncionario funcionario) {

    }

    public void exclui(int matricula) {

    }

    public void exclui(Funcionario funcionario) {

    }

}
