package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;
import br.ufsc.ine5605.clavicularioeletronico.validacoes.ValidacaoDadosFuncionario;
import br.ufsc.ine5605.clavicularioeletronico.validacoes.ValidacaoDadosVeiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Tela base para funções básicas que todas as telas usam
 * @author Flávio
 */
public abstract class TelaBase {
    
    protected Scanner teclado;
    
    public TelaBase() {
        teclado = new Scanner(System.in);
    }
    
    public void solicitaEnterParaContinuar() {
        System.out.println("Aperte ENTER para continuar...");
        this.teclado.nextLine();
    }
    
    public int inputOpcao() {
        System.out.println("-------------------------------------------------");
        System.out.print("Opção escolhida: ");
        int opcao;
        try {
            opcao = Integer.parseInt(this.teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("A opcao precisa ser um numero!");
            opcao = -1;
        }
        return opcao;
    }
    
    public int inputMatricula() throws Exception {
        System.out.println("Digite a matricula: ");
        String input = this.teclado.nextLine();
        int matricula = -1;
        if (ValidacaoDadosFuncionario.validaMatricula(input)) {
            matricula = Integer.parseInt(input);
        }
        return matricula;
    }
    
    public String inputNome() throws Exception {
        System.out.println("Digite o nome completo do funcionario: ");
        String nome = this.teclado.nextLine();
        ValidacaoDadosFuncionario.validaNome(nome);
        return nome;
    }
    
    public Date inputDataNascimento() throws Exception {
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
    
    public String inputTelefone() throws Exception {
        System.out.println("Digite o telefone do funcionário: ");
        String telefone = this.teclado.nextLine();
        ValidacaoDadosFuncionario.validaTelefone(telefone);
        return telefone;
        
    }
    
    public Cargo inputCargo() throws Exception{
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

    public String inputPlaca() throws Exception{
        System.out.println("Digite a placa do veiculo. Ex: (AAA-9999): ");
        String placa = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaPlaca(placa);
        return placa;
    }
    
    public String inputModelo() throws Exception{
        System.out.println("Digite o modelo do veiculo: ");
        String modelo = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaModelo(modelo);
        return modelo;
    }
    
    public String inputMarca() throws Exception{
        System.out.println("Digite a marca do veiculo: ");
        String marca = this.teclado.nextLine();
        ValidacaoDadosVeiculo.validaMarca(marca);
        return marca;
    }
    
    public int inputAno() throws Exception{
        System.out.println("Digite o ano do veiculo: ");
        String input = this.teclado.nextLine();
        int ano = 0;
        if (ValidacaoDadosVeiculo.validaAno(input)) {
            ano = Integer.parseInt(input);
        }
        return ano;
    }
    
    public int inputQuilometragemAtual() throws Exception{
        System.out.println("Digite a quilometragem atual do veiculo: ");
        String input = this.teclado.nextLine();
        int quilometragemAtual = 0;
        if (ValidacaoDadosVeiculo.validaQuilometragem(input)) {
            quilometragemAtual = Integer.parseInt(input);
        }
        return quilometragemAtual;
    }
    
    public String inputConfirmacao() {
        System.out.println("(S/N):");
        return this.teclado.nextLine();
    }
    
    protected boolean pedeConfirmacao() {
        String opcao = inputConfirmacao().toLowerCase();
        return opcao.matches("s|sim|y|yes");
    }
    
}
