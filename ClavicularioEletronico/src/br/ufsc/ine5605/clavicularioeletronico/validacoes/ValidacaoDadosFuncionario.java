package br.ufsc.ine5605.clavicularioeletronico.validacoes;

/**
 *
 * @author Gabriel
 */
public class ValidacaoDadosFuncionario {

    private ValidacaoDadosFuncionario() {
    }
    
    public static boolean validaMatricula(String matricula) throws Exception {
        if (!matricula.matches("[0-9]+")) {
            throw new Exception("A matricula deve ser um numero positivo!");
        }
        return true;
    }
    
    public static boolean validaNome(String nome) throws Exception {
        if (!nome.matches("[a-zA-Z ]+")) {
            throw new Exception("Você deve digitar um nome!");
        }
        return true;
    }
    
    public static boolean validaDataNascimento(String dataNascimento) throws Exception {
        if (!dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new Exception("Você deve digitar uma data no formato dd/mm/aaaa");
        }
        return true;
    }
    
    public static boolean validaTelefone(String telefone) throws Exception {
        if (!telefone.matches("\\d{8,}+")) {
            throw new Exception("Você deve informar seu telefone contendo no mínimo 8 digitos!");
        }
        return true;
    }
    
    public static boolean validaCargo(String cargo) throws Exception {
        if (!cargo.matches("1|2")) {
            throw new Exception("Você deve escolher o cargo 1 ou 2!");
        }
        return true;
    }
}
