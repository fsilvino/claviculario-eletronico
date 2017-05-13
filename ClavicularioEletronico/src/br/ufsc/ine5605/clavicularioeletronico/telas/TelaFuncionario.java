package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorSistema;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.Listavel;

public class TelaFuncionario extends TelaCadastro {

    public TelaFuncionario() {
        super();
    }
    
    @Override
    public void exibeMenu() {
        System.out.println("----------------Menu Funcionario-----------------");
        super.exibeMenu();
    }
    
    @Override
    protected void exibeOpcoesMenu() {
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Listagem");
        System.out.println("5 - Permissoes de uso dos veiculos");
        System.out.println("0 - Voltar");
    }

    @Override
    protected boolean executaOpcaoMenu(int opcao) {
        if (opcao == 5) {
            ControladorSistema.getInstance().abreCadastroPermissaoUsoVeiculo();
            return true;
        }
        return super.executaOpcaoMenu(opcao);
    }
    
    @Override
    public void exibeTelaInclui() {
        try {
            DadosFuncionario novoFuncionario = entradaDadosFuncionario();
            ControladorFuncionario.getInstance().inclui(novoFuncionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }

    @Override
    public void exibeTelaAltera() {
        try {
            DadosFuncionario alteraFuncionario = entradaDadosFuncionario();
            ControladorFuncionario.getInstance().altera(alteraFuncionario);
            System.out.println("Funcionário alterado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorFuncionario.getInstance().exclui(inputMatricula());
            System.out.println("Funcionário excluido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        solicitaEnterParaContinuar();
    }
    
    @Override
    public void exibeLista() {
        System.out.println("------------Funcionarios cadastrados-------------");
        for (Listavel item: ControladorFuncionario.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
        solicitaEnterParaContinuar();
    }
    
    public DadosFuncionario entradaDadosFuncionario() throws Exception {
        return new DadosFuncionario(inputMatricula(), inputNome(), inputDataNascimento(), inputTelefone(), inputCargo(), false);
    }
    

}
