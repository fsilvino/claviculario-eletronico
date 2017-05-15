package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Funcionario;
import br.ufsc.ine5605.clavicularioeletronico.excecoes.MatriculaNaoCadastradaException;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.Listavel;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pelo controle de cadastros dos funcionários
 * @author Flávio
 */
public class ControladorFuncionario extends ControladorCadastro<TelaFuncionario, Funcionario> {

    private static ControladorFuncionario instance;

    private ControladorFuncionario() {
        super();
        tela = new TelaFuncionario();
    }

    public static ControladorFuncionario getInstance() {
        if (instance == null) {
            instance = new ControladorFuncionario();
        }
        return instance;
    }
    
    /**
     * Retorna lista de descrições para imprimir na tela
     * @return Lista dos itens
     */
    @Override
    public List<Listavel> getListaItensCadastro() {
        List<Listavel> lista = new ArrayList<>();
        for (Funcionario funcionario : itens) {
            lista.add(new ItemListaCadastro(funcionario.getMatricula() + "\t" + funcionario.getNome()));
        }
        return lista;
    }

    /**
     * Inclui o cadastro do funcionario
     * @param dadosFuncionario Dados do funcionario passados pela tela
     * @throws Exception Caso os dados sejam invalidos ou ja exista um funcionario cadastrado com a matrícula
     */
    public void inclui(DadosFuncionario dadosFuncionario) throws Exception {
        if (validaDadosFuncionario(dadosFuncionario)) {
            if (findFuncionarioPelaMatricula(dadosFuncionario.getMatricula()) == null) {
                Funcionario funcionario = new Funcionario();
                copiaDadosParaFuncionario(dadosFuncionario, funcionario);
                itens.add(funcionario);
            } else {
                throw new Exception("Ja existe um funcionario cadastrado com a matricula: " + dadosFuncionario.getMatricula());
            }
        }
    }

    /**
     * Altera os dados do funcionario baseado na matrícula
     * @param dadosFuncionario Dados do funcionario a ser alterado
     * @throws Exception Caso os dados sejam inválidos ou não exista um funcionario cadastrado com a matrícula informada
     */
    public void altera(DadosFuncionario dadosFuncionario) throws Exception {
        if (validaDadosFuncionario(dadosFuncionario)) {
            Funcionario funcionario = findFuncionarioPelaMatricula(dadosFuncionario.getMatricula());
            if (funcionario != null) {
                copiaDadosParaFuncionario(dadosFuncionario, funcionario);
            } else {
                throw new MatriculaNaoCadastradaException(dadosFuncionario.getMatricula());
            }
        }
    }

    /**
     * Exclui o funcionário pelo número da matrícula
     * @param matricula Matrícula do funcionário que será excluído
     * @throws Exception Caso não exista um funcionário com a matrícula informada
     */
    public void exclui(int matricula) throws Exception {
        Funcionario funcionario = findFuncionarioPelaMatricula(matricula);
        if (funcionario == null) {
            throw new MatriculaNaoCadastradaException(matricula);
        }
        
        if (ControladorClaviculario.getInstance().funcionarioEstaUtilizandoAlgumVeiculo(matricula)) {
            throw new Exception("Este funcionario esta possui chave(s) a ser(em) devolvida(s).\n" +
                                "Nao sera possivel excluir ate que todas sejam devolvidas.");
        }
        
        itens.remove(funcionario);
    }

    /**
     * Exclui o funcionário pela matrícula
     * @param funcionario Objeto funcionário que contém a matrícula a ser excluída
     * @throws Exception Caso o parâmetro seja nulo ou não exista um funcionário com a matrícula informada
     */
    public void exclui(Funcionario funcionario) throws Exception {
        if (funcionario == null) {
            throw new InvalidParameterException("Falha ao excluir o funcionario! Parametro nulo.");
        }
        exclui(funcionario.getMatricula());
    }
    
    /**
     * Pesquisa o funcionário pela matrícula
     * @param matricula Matrícula a ser pesquisada
     * @return Objeto do funcionário encontrado ou null caso não exista a matrícula
     */
    private Funcionario findFuncionarioPelaMatricula(int matricula) {
        for (Funcionario funcionario : itens) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

    /**
     * Copia os dados do funcionário fornecidos pela tela para o objeto funcionário que será salvo
     * @param dadosFuncionario Dados do funcionário passados pela tela
     * @param funcionario Objeto funcionário que será salvo
     */
    private void copiaDadosParaFuncionario(DadosFuncionario dadosFuncionario, Funcionario funcionario) {
        funcionario.setMatricula(dadosFuncionario.getMatricula());
        funcionario.setNome(dadosFuncionario.getNome());
        funcionario.setNascimento(dadosFuncionario.getNascimento());
        funcionario.setTelefone(dadosFuncionario.getTelefone());
        funcionario.setBloqueado(dadosFuncionario.getBloqueado());
        funcionario.setCargo(dadosFuncionario.getCargo());
    }
    
    /**
     * Valida os dados do funcionário passados pela tela
     * @param dadosFuncionario Dados do funcionário passados pela tela
     * @return True se o os dados estiverem corretos
     * @throws Exception Caso algum dado ou parâmetro seja inválido
     */
    private boolean validaDadosFuncionario(DadosFuncionario dadosFuncionario) throws Exception {
        
        if (dadosFuncionario == null) {
            throw new InvalidParameterException("Dados invalidos. Parametro nulo.");
        }
        
        if (dadosFuncionario.getMatricula() <= 0) {
            throw new Exception("O numero de matricula deve ser preenchido com um numero maior do que zero!");
        }
        
        if (dadosFuncionario.getNome() == null || dadosFuncionario.getNome().trim().isEmpty()) {
            throw new Exception("O nome do funcionario eh obrigatorio!");
        }
        
        if (dadosFuncionario.getCargo() == null) {
            throw new Exception("O cargo do funcionario eh obrigatorio!");
        }
        
        return true;
    }

    /**
     * Pesquisa o funcionário pela matrícula
     * @param matricula Matrícula a ser pesquisada
     * @return Retorna o objeto do funcionário se encontrar pela matrículaa
     * @throws MatriculaNaoCadastradaException Caso não exista a matrícula cadastrada
     */
    public Funcionario getFuncionarioPelaMatricula(int matricula) throws MatriculaNaoCadastradaException {
        Funcionario funcionario = findFuncionarioPelaMatricula(matricula);
        if (funcionario == null) {
            throw new MatriculaNaoCadastradaException(matricula);
        }
        return funcionario;
    }
    
     /**
     * Verifica se o funcionário existe
     * @param matricula Matrícula a ser pesquisada
     * @return true se o funcionário existe, false se não foi encontrado
     */
    
    public boolean funcionarioExiste(int matricula) {
        return findFuncionarioPelaMatricula(matricula) != null;
    }

    public void abreCadastroPermissaoUsoVeiculo() {
        ControladorPermissaoUsoVeiculo.getInstance().inicia();
    }
    
}
