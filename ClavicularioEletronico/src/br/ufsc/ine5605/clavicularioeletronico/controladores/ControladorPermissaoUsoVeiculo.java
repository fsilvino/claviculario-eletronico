package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.entidades.Funcionario;
import br.ufsc.ine5605.clavicularioeletronico.entidades.PermissaoUsoVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Veiculo;
import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;
import br.ufsc.ine5605.clavicularioeletronico.excecoes.CadastroInvalidoPermissaoUsoVeiculoDiretoria;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaPermissaoUsoVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pelo controle do cadastrado das permissões de uso dos veículos
 * pelos funcionários
 * @author Flávio
 */
public class ControladorPermissaoUsoVeiculo extends ControladorCadastro<TelaPermissaoUsoVeiculo, PermissaoUsoVeiculo> {
    
    private static ControladorPermissaoUsoVeiculo instance;
    
    private ControladorPermissaoUsoVeiculo() {
        super();
        tela = new TelaPermissaoUsoVeiculo();
    }
    
    public static ControladorPermissaoUsoVeiculo getInstance() {
        if (instance == null) {
            instance = new ControladorPermissaoUsoVeiculo();
        }
        return instance;
    }

    @Override
    public void exibeListaItens() {
        throw new RuntimeException("Use exibeListaPermissoes!");
    }

    @Override
    public List<ItemListaCadastro> getListaItensCadastro() {
        throw new RuntimeException("Use exibeListaPermissoes!");
    }
    
    /**
     * Inclui a permissão de uso a um veículo para um funcionário pela 
     * matrícula e placa
     * @param matricula Matrícula do funcionário que terá permissão de uso
     * @param placa Placa do veículo que será permitido
     * @throws Exception Caso os dados sejam inválidos ou segundo a regra de negócio
     * não seja possível incluir esta permissão
     */
    public void inclui(int matricula, String placa) throws Exception {
        Funcionario funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);
        
        if (funcionario.getCargo() == Cargo.DIRETORIA) {
            throw new Exception("Este funcionario tem acesso a todos os veiculos pois seu cargo eh de diretoria.\n" +
                                "Nao sera possivel cadastrar os veiculos nesta opcao.");
        }
        
        Veiculo veiculo = ControladorVeiculo.getInstance().getVeiculoPelaPlaca(placa);
        
        if (permissaoExiste(funcionario, veiculo)) {
            throw new Exception(String.format("O funcionario {0} - {1} ja possui permissao para acessar o veiculo {2}.", funcionario.getMatricula(), funcionario.getNome(), veiculo.getPlaca()));
        }
        
        itens.add(new PermissaoUsoVeiculo(funcionario, veiculo));
    }
    
    /**
     * Remove a permissão de uso de um veículo de um funcionário pela matrícula e placa
     * @param matricula Matrícula do funcionário que terá a permissão removida
     * @param placa Placa do veículo que terá o uso negado ao funcionário
     * @throws Exception Caso os parâmtros sejam inválidos ou não exista a permissão
     */
    public void exclui(int matricula, String placa) throws Exception {
        PermissaoUsoVeiculo permissao = findPermissaoUsoVeiculo(matricula, placa);
        if (permissao == null) {
            throw new Exception(String.format("O funcionario de matricula {0} nao possui permissao de uso para o veiculo de placa {1}", matricula, placa));
        }
    }
    
    public void exibeListaPermissoes(int matricula) throws Exception {
        Funcionario funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);
        
        if (funcionario.getCargo() == Cargo.DIRETORIA) {
            throw new CadastroInvalidoPermissaoUsoVeiculoDiretoria();
        }
        
        List<ItemListaCadastro> lista = new ArrayList<>();
        for (PermissaoUsoVeiculo permissao : itens) {
            if (permissao.getFuncionario().getMatricula() == matricula) {
                lista.add(new ItemListaCadastro(String.format("{0}\t{1}", permissao.getVeiculo().getPlaca(), permissao.getVeiculo().getModelo())));
            }
        }
        tela.exibeLista(lista);
    }
    
    private PermissaoUsoVeiculo findPermissaoUsoVeiculo(Funcionario funcionario, Veiculo veiculo) {
        for (PermissaoUsoVeiculo permissao : itens) {
            if (permissao.getFuncionario().equals(funcionario) && permissao.getVeiculo().equals(veiculo)) {
                return permissao;
            }
        }
        return null;
    }
    
    private PermissaoUsoVeiculo findPermissaoUsoVeiculo(int matricula, String placa) throws Exception {
        Funcionario funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);
        
        if (funcionario.getCargo() == Cargo.DIRETORIA) {
            throw new CadastroInvalidoPermissaoUsoVeiculoDiretoria();
        }
        
        Veiculo veiculo = ControladorVeiculo.getInstance().getVeiculoPelaPlaca(placa);
        
        return findPermissaoUsoVeiculo(funcionario, veiculo);
    }
    
    private boolean permissaoExiste(Funcionario funcionario, Veiculo veiculo) throws Exception {
        return findPermissaoUsoVeiculo(funcionario, veiculo) != null;
    }
    
    
    
}