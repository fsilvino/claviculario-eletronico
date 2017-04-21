package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Veiculo;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flávio
 */
public class ControladorVeiculo extends ControladorCadastro<Veiculo> {

    private static ControladorVeiculo instancia;

    private ControladorVeiculo() {
        super();
        tela = new TelaVeiculo();
    }

    public static ControladorVeiculo getInstance() {
        if (instancia == null) {
            instancia = new ControladorVeiculo();
        }
        return instancia;
    }

    @Override
    public List<ItemListaCadastro> getListaItensCadastro() {
        List<ItemListaCadastro> lista = new ArrayList<>();
        for (Veiculo veiculo : itens) {
            lista.add(new ItemListaCadastro(veiculo.getPlaca() + "\t" + veiculo.getModelo()));
        }
        return lista;
    }

    public void inclui(DadosVeiculo dadosVeiculo) throws Exception {
        if (validaDadosVeiculo(dadosVeiculo)) {
            if (findVeiculoPelaPlaca(dadosVeiculo.getPlaca()) == null) {
                Veiculo veiculo = new Veiculo();
                copiaDadosParaVeiculo(dadosVeiculo, veiculo);
                itens.add(veiculo);
            } else {
                throw new Exception("Falha ao incluir o veiculo!\nJa existe um veiculo cadastrado com a placa: " + dadosVeiculo.getPlaca());
            }
        }
    }

    public void altera(DadosVeiculo dadosVeiculo) throws Exception {
        if (validaDadosVeiculo(dadosVeiculo)) {
            Veiculo veiculo = findVeiculoPelaPlaca(dadosVeiculo.getPlaca());
            if (veiculo != null) {
                copiaDadosParaVeiculo(dadosVeiculo, veiculo);
            } else {
                throw new Exception("Nao foi possivel encontrar o veiculo com a placa: " + dadosVeiculo.getPlaca());
            }
        }
    }

    public void exclui(String placa) throws Exception {
        if (placa == null || placa.isEmpty()) {
            throw new InvalidParameterException("Falha ao excluir o veiculo! Placa nao informada.");
        }
        
        Veiculo veiculo = findVeiculoPelaPlaca(placa);
        if (veiculo == null) {
            throw new Exception("Nenhum veiculo cadastrado com a placa: " + placa);
        }
        itens.remove(veiculo);
    }

    public void exclui(Veiculo veiculo) throws Exception {
        if (veiculo == null) {
            throw new InvalidParameterException("Falha ao excluir o veiculo! Parametro nulo.");
        }
        exclui(veiculo.getPlaca());
    }
    
    private Veiculo findVeiculoPelaPlaca(String placa) {
        for (Veiculo veiculo : itens) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    private void copiaDadosParaVeiculo(DadosVeiculo dadosVeiculo, Veiculo veiculo) {
        veiculo.setPlaca(dadosVeiculo.getPlaca());
        veiculo.setModelo(dadosVeiculo.getModelo());
        veiculo.setMarca(dadosVeiculo.getMarca());
        veiculo.setAno(dadosVeiculo.getAno());
        veiculo.setQuilometragemAtual(dadosVeiculo.getQuilometragemAtual());
    }
    
    private boolean validaDadosVeiculo(DadosVeiculo dadosVeiculo) throws Exception {
        
        if (dadosVeiculo == null) {
            throw new InvalidParameterException("Dados invalidos! Parametro nulo.");
        }
        
        if (dadosVeiculo.getPlaca() == null || dadosVeiculo.getPlaca().trim().isEmpty()) {
            throw new Exception("Informe a placa do veiculo!");
        }
        
        return true;
    }

}
