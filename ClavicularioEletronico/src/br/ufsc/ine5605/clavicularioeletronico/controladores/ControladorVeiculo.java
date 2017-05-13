package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Veiculo;
import br.ufsc.ine5605.clavicularioeletronico.excecoes.PlacaNaoCadastradaException;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.Listavel;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pelo controle de cadastro dos veículos
 * @author Flávio
 */
public class ControladorVeiculo extends ControladorCadastro<TelaVeiculo, Veiculo> {

    private static ControladorVeiculo instance;

    private ControladorVeiculo() {
        super();
        tela = new TelaVeiculo();
    }

    public static ControladorVeiculo getInstance() {
        if (instance == null) {
            instance = new ControladorVeiculo();
        }
        return instance;
    }

    /**
     * Monta a lista de itens para imprimir na tela
     * @return Lista das descrições para imprimir a lista na tela
     */
    @Override
    public List<Listavel> getListaItensCadastro() {
        List<Listavel> lista = new ArrayList<>();
        for (Veiculo veiculo : itens) {
            lista.add(new ItemListaCadastro(veiculo.getPlaca() + "\t" + veiculo.getModelo()));
        }
        return lista;
    }

    /**
     * Inclui o cadastro do veiculo
     * @param dadosVeiculo Dados do veiculo passados pela tela
     * @throws Exception Caso algum veiculo já esteja cadastrado com a placa ou os dados sejam inválidos
     */
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

    /**
     * Altera os dados do veiculo baseado na placa
     * @param dadosVeiculo Dados do veiculo para alteracao
     * @throws Exception Caso os dados sejam invalidos ou nao encontre a placa
     */
    public void altera(DadosVeiculo dadosVeiculo) throws Exception {
        if (validaDadosVeiculo(dadosVeiculo)) {
            Veiculo veiculo = findVeiculoPelaPlaca(dadosVeiculo.getPlaca());
            if (veiculo != null) {
                copiaDadosParaVeiculo(dadosVeiculo, veiculo);
            } else {
                throw new PlacaNaoCadastradaException(dadosVeiculo.getPlaca());
            }
        }
    }

    /**
     * Exclui o veiculo pela placa
     * @param placa Placa do veiculo a ser excluido
     * @throws Exception Caso a placa seja invalida ou nao for passada
     */
    public void exclui(String placa) throws Exception {
        if (placa == null || placa.isEmpty()) {
            throw new InvalidParameterException("Falha ao excluir o veiculo! Placa nao informada.");
        }
        
        Veiculo veiculo = findVeiculoPelaPlaca(placa);
        if (veiculo == null) {
            throw new PlacaNaoCadastradaException(placa);
        }
        itens.remove(veiculo);
    }

    /**
     * Exclui o veiculo, pesquisa pela placa
     * @param veiculo Instancia de veiculo que contem a placa a ser excluida
     * @throws Exception Se o parametro for nulo ou nao encontrar a placa
     */
    public void exclui(Veiculo veiculo) throws Exception {
        if (veiculo == null) {
            throw new InvalidParameterException("Falha ao excluir o veiculo! Parametro nulo.");
        }
        exclui(veiculo.getPlaca());
    }
    
    /**
     * Pesquisa o veiculo pela placa
     * @param placa Placa a ser pesquisada
     * @return Veiculo encontrado pela placa ou nulo se nao encontrar
     */
    private Veiculo findVeiculoPelaPlaca(String placa) {
        for (Veiculo veiculo : itens) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    /**
     * Copia os dados da tela para uma instancia de veiculo
     * @param dadosVeiculo Dados passados pela tela
     * @param veiculo Objeto veiculo que ira receber os dados
     */
    private void copiaDadosParaVeiculo(DadosVeiculo dadosVeiculo, Veiculo veiculo) {
        veiculo.setPlaca(dadosVeiculo.getPlaca());
        veiculo.setModelo(dadosVeiculo.getModelo());
        veiculo.setMarca(dadosVeiculo.getMarca());
        veiculo.setAno(dadosVeiculo.getAno());
        veiculo.setQuilometragemAtual(dadosVeiculo.getQuilometragemAtual());
    }
    
    /**
     * Valida os dados do veiculo
     * @param dadosVeiculo Dados passados pela tela
     * @return True se os dados sao validos, caso contrario gera exceção
     * @throws Exception Se algum dado for invalido
     */
    private boolean validaDadosVeiculo(DadosVeiculo dadosVeiculo) throws Exception {
        
        if (dadosVeiculo == null) {
            throw new InvalidParameterException("Dados invalidos! Parametro nulo.");
        }
        
        if (dadosVeiculo.getPlaca() == null || dadosVeiculo.getPlaca().trim().isEmpty()) {
            throw new Exception("Informe a placa do veiculo!");
        }
        
        if (!dadosVeiculo.getPlaca().matches("[A-Z]{3}-[0-9]{4}")) {
            throw new Exception("Informe a placa com 3 letras maiusculas e 4 numeros, separados por um traco. Ex: AAA-9999.");
        }
        
        return true;
    }

    /**
     * Pesquisa o veículo pela placa e o retorna
     * @param placa Placa do veiculo no formato AAA-9999
     * @return Veiculo cadastrado com a placa informada
     * @throws PlacaNaoCadastradaException Caso nao seja encontrado o veiculo
     */
    public Veiculo getVeiculoPelaPlaca(String placa) throws PlacaNaoCadastradaException {
        Veiculo veiculo = findVeiculoPelaPlaca(placa);
        if (veiculo == null) {
            throw new PlacaNaoCadastradaException(placa);
        }
        return veiculo;
    }
    
     /**
     * Pesquisa o veículo pela placa e o retorna
     * @param placa Placa do veiculo no formato AAA-9999
     * @return True se o veiculo existe na lista
     */ 
    public boolean veiculoExiste(String placa) {
        return findVeiculoPelaPlaca(placa) != null;
    }
    
}