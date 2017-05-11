package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.DadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.transferencias.ItemListaCadastro;
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
        DadosFuncionario novoFuncionario = entradaDadosFuncionario();
        
        try {
            ControladorFuncionario.getInstance().inclui(novoFuncionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }

    @Override
    public void exibeTelaAltera() {
        DadosFuncionario alteraFuncionario = entradaDadosFuncionario();
        
        try {
            ControladorFuncionario.getInstance().altera(alteraFuncionario);
            System.out.println("Funcionário alterado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }

    @Override
    public void exibeTelaExclui() {
        try {
            ControladorFuncionario.getInstance().exclui(inputMatricula());
            System.out.println("Funcionário excluido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }
    
    @Override
    public void exibeLista() {
        System.out.println("------------Funcionarios cadastrados-------------");
        for (ItemListaCadastro item: ControladorFuncionario.getInstance().getListaItensCadastro()) {
            System.out.println(item.getDescricao());
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }
    
    public Date dataNascimento() {
        System.out.println("Digite a data de nascimento do funcionário (dd/mm/aaaa): ");
        String data = this.teclado.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date nascimento = null;
        try {
            nascimento = formato.parse(data);
        } catch (ParseException ex) {
            System.out.println("Você deve digitar uma data no formato dd/mm/aaaa!");
            dataNascimento();
        }
        return nascimento;
    }
    
    public DadosFuncionario entradaDadosFuncionario() {
        return new DadosFuncionario(inputMatricula(), inputNome(), inputDataNascimento(), inputTelefone(), inputCargo(), false);
    }
    
   private int inputMatricula() {
        System.out.println("Digite a matricula: ");
        String input = this.teclado.nextLine();
        int matricula = -1;
        if (input.matches("[0-9]")) {
            matricula = Integer.parseInt(input);
        }
        return matricula;
    }
    
    private String inputNome() {
        System.out.println("Digite o nome do funcionario: ");
        return this.teclado.nextLine();
    }
    
    private Date inputDataNascimento() {
        System.out.println("Digite a data de nascimento do funcionário (dd/mm/aaaa): ");
        String data = this.teclado.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date nascimento = null;
        try {
            nascimento = formato.parse(data);
        } catch (ParseException e) {            
        }
        
        return nascimento;
    }
    
    private String inputTelefone() {
        System.out.println("Digite o telefone do funcionário: ");
        return this.teclado.nextLine();
        
    }

    
    
    private Cargo inputCargo(){
        System.out.println("Digite o cargo do funcionário");
        System.out.println("1 - Diretoria");       
        System.out.println("2 - Motorista:");
        String input = this.teclado.nextLine();
        int opcao = 0;
        if (input.matches("[0-9]")) {
            opcao = Integer.parseInt(input);
        }
        switch (opcao) {
            case 1: return Cargo.DIRETORIA;
            
            case 2: return Cargo.MOTORISTA;
        }   
        
        return null;
    }

}
