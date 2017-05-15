package br.ufsc.ine5605.clavicularioeletronico.validacoes;

/**
 *
 * @author Gabriel
 */
public class ValidacaoDadosVeiculo {
    
    private ValidacaoDadosVeiculo() {
        
    }

    public static boolean validaPlaca(String placa) throws Exception {
        if (!placa.matches("[A-Z]{3}-{1}\\d{4}")) {
            throw new Exception("A placa deve seguir o seguinte formato: (AAA-9999)");
        }
        return true;
    }
    
    public static boolean validaModelo(String modelo) throws Exception {
        if (!modelo.matches("^[^ ][a-zA-Z0-9 ]+")) {
            throw new Exception("Você deve digitar o modelo do carro.");
        }
        return true;
    }
    
    public static boolean validaMarca(String marca) throws Exception {
        if (!marca.matches("^[^ ][a-zA-Z0-9 ]+")) {
            throw new Exception("Você deve digitar a marca do carro.");
        }
        return true;
    }
    
    public static boolean validaAno(String ano) throws Exception {
        if (!ano.matches("\\d{4}")) {
            throw new Exception("Você deve informar um ano com 4 digitos.");
        }
        return true;
    }
    
    public static boolean validaQuilometragem(String quilometragem) throws Exception {
        if (!quilometragem.matches("[0-9]+")) {
            throw new Exception("Você deve informar a quilometragem do carro com 1 ou mais digitos.");
        }
        return true;
    }
}
