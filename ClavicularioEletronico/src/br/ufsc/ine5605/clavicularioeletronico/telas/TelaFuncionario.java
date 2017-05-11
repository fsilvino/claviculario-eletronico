package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
import br.ufsc.ine5605.clavicularioeletronico.validacoes.ValidacaoDadosFuncionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        for (ItemListaCadastro item: ControladorFuncionario.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
        solicitaEnterParaContinuar();
    }
    
    public DadosFuncionario entradaDadosFuncionario() throws Exception {
        return new DadosFuncionario(inputMatricula(), inputNome(), inputDataNascimento(), inputTelefone(), inputCargo(), false);
    }
    
   private int inputMatricula() throws Exception {
        System.out.println("Digite a matricula: ");
        String input = this.teclado.nextLine();
        int matricula = -1;
        if (ValidacaoDadosFuncionario.validaMatricula(input)) {
            matricula = Integer.parseInt(input);
        }
        return matricula;
    }
    
    private String inputNome() throws Exception {
        System.out.println("Digite o nome completo do funcionario: ");
        String nome = this.teclado.nextLine();
        ValidacaoDadosFuncionario.validaNome(nome);
        return nome;
    }
    
    private Date inputDataNascimento() throws Exception {
        System.out.println("Digite a data de nascimento do funcionário (dd/mm/aaaa): ");
        String data = this.teclado.nextLine();
        ValidacaoDadosFuncionario.validaDataNascimento(data);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date nascimento = null;
        try {
            nascimento = formato.parse(data);
        } catch (ParseException e) {            
        }
        
        return nascimento;
    }
    
    private String inputTelefone() throws Exception {
        System.out.println("Digite o telefone do funcionário: ");
        String telefone = this.teclado.nextLine();
        ValidacaoDadosFuncionario.validaTelefone(telefone);
        return telefone;
        
    }

    
    
    private Cargo inputCargo() throws Exception{
        System.out.println("Digite o cargo do funcionário");
        System.out.println("1 - Diretoria");       
        System.out.println("2 - Motorista:");
        String input = this.teclado.nextLine();
        int opcao = 0;
        if (ValidacaoDadosFuncionario.validaCargo(input)) {
            opcao = Integer.parseInt(input);
        }
        switch (opcao) {
            case 1: return Cargo.DIRETORIA;
            
            case 2: return Cargo.MOTORISTA;
        }   
        
        return null;
    }

}
