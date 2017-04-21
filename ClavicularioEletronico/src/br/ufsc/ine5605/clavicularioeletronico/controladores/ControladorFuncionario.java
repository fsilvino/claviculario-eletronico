package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Funcionario;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flávio
 */
public class ControladorFuncionario extends ControladorCadastro<Funcionario> {

    private static ControladorFuncionario instancia;

    private ControladorFuncionario() {
        super();
        tela = new TelaFuncionario();
    }

    public static ControladorFuncionario getInstance() {
        if (instancia == null) {
            instancia = new ControladorFuncionario();
        }
        return instancia;
    }
    
    @Override
    public List<ItemListaCadastro> getListaItensCadastro() {
        List<ItemListaCadastro> lista = new ArrayList<>();
        for (Funcionario funcionario : itens) {
            lista.add(new ItemListaCadastro(funcionario.getMatricula() + "\t" + funcionario.getNome()));
        }
        return lista;
    }

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

    public void altera(DadosFuncionario dadosFuncionario) throws Exception {
        if (validaDadosFuncionario(dadosFuncionario)) {
            Funcionario funcionario = findFuncionarioPelaMatricula(dadosFuncionario.getMatricula());
            if (funcionario != null) {
                copiaDadosParaFuncionario(dadosFuncionario, funcionario);
            } else {
                throw new Exception("Nao foi possivel encontrar o funcionario com a matricula: " + dadosFuncionario.getMatricula());
            }
        }
    }

    public void exclui(int matricula) throws Exception {
        Funcionario funcionario = findFuncionarioPelaMatricula(matricula);
        if (funcionario == null) {
            throw new Exception("Nenhum funcionario cadastrado com a matricula: " + matricula);
        }
        itens.remove(funcionario);
    }

    public void exclui(Funcionario funcionario) throws Exception {
        if (funcionario == null) {
            throw new InvalidParameterException("Falha ao excluir o funcionario! Parametro nulo.");
        }
        exclui(funcionario.getMatricula());
    }
    
    private Funcionario findFuncionarioPelaMatricula(int matricula) {
        for (Funcionario funcionario : itens) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

    private void copiaDadosParaFuncionario(DadosFuncionario dadosFuncionario, Funcionario funcionario) {
        funcionario.setMatricula(dadosFuncionario.getMatricula());
        funcionario.setNome(dadosFuncionario.getNome());
        funcionario.setNascimento(dadosFuncionario.getNascimento());
        funcionario.setTelefone(dadosFuncionario.getTelefone());
        funcionario.setBloqueado(dadosFuncionario.getBloqueado());
        funcionario.setCargo(dadosFuncionario.getCargo());
    }
    
    private boolean validaDadosFuncionario(DadosFuncionario dadosFuncionario) throws Exception {
        
        if (dadosFuncionario == null) {
            throw new InvalidParameterException("Dados invalidos. Parametro nulo.");
        }
        
        if (dadosFuncionario.getMatricula() <= 0) {
            throw new Exception("O numero de matricula deve ser preenchido com um numero maior do que zero!");
        }
        
        if (dadosFuncionario.getNome() == null || dadosFuncionario.getNome().isEmpty()) {
            throw new Exception("O nome do funcionario eh obrigatorio!");
        }
        
        if (dadosFuncionario.getCargo() == null) {
            throw new Exception("O cargo do funcionario eh obrigatorio!");
        }
        
        return true;
    }

}
