package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.entidades.EventoClaviculario;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Funcionario;
import br.ufsc.ine5605.clavicularioeletronico.entidades.SaidaVeiculo;
import br.ufsc.ine5605.clavicularioeletronico.entidades.Veiculo;
import br.ufsc.ine5605.clavicularioeletronico.enums.Cargo;
import br.ufsc.ine5605.clavicularioeletronico.enums.Evento;
import br.ufsc.ine5605.clavicularioeletronico.excecoes.MatriculaNaoCadastradaException;
import br.ufsc.ine5605.clavicularioeletronico.telas.TelaClaviculario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Implementa a funcionalidade do Claviculario propriamente dita:
 * a liberação e a recepção das chaves conforme as configurações
 * do sistema e salva o log de eventos
 * @author Flávio
 */
public class ControladorClaviculario {

    private static ControladorClaviculario instance;

    private List<EventoClaviculario> log;
    private List<SaidaVeiculo> veiculosFora;
    
    private ControladorClaviculario() {
        log = new ArrayList<>();
        veiculosFora = new ArrayList();
    }

    public static ControladorClaviculario getInstance() {
        if (instance == null) {
            instance = new ControladorClaviculario();
        }
        return instance;
    }

    public void inicia() {
        //tela claviculario eletronico
       // retirar chave, devolver chave
       //relatorio de acessos
    }
    
    public boolean retirarChave(int matricula) throws Exception {
        
        //validar matricula
        //Como saber o carro que o diretor escolheu?
        Funcionario funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);
            //diretor
        if (funcionario.getCargo() == Cargo.DIRETORIA) {
            if (this.disponibilidadeVeiculo(placa)) {
                this.novaSaida(veiculo, funcionario);
                this.novoEvento(Evento.ACESSO_PERMITIDO, matricula, placa);
                return true;
            } else {
                this.novoEvento(Evento.VEICULO_INDISPONIVEL, matricula, placa);
            }
        }
            //funcionario
            
        List permissoes = ControladorPermissaoUsoVeiculo.getInstance().getPermissoes(funcionario);
        
        if (funcionario.isBloqueado()) {
            return false;  //melhorar
        }
        
        //tentativas
        if (permissoes.isEmpty()) {
            this.novoEvento(Evento.PERMISSAO_INSUFICIENTE, matricula, "");
            return false;
        } else {
            String placa = tela.PedePlaca();   ///verificar tela
            Veiculo veiculo = ControladorVeiculo.getInstance().getVeiculoPelaPlaca(placa);
                if (!this.disponibilidadeVeiculo(placa)) {
                     this.novoEvento(Evento.VEICULO_INDISPONIVEL, matricula, placa);
                     return false;
                }
            this.novaSaida(veiculo, funcionario);
            this.novoEvento(Evento.ACESSO_PERMITIDO, matricula, placa);
            
        }
        return true;
    }
  
    
    
    public void devolverVeiculo(int matricula, String placa, int quilometragem) {   
        //validar matricula
        
        for (SaidaVeiculo veiculoFora : veiculosFora) {
            if((veiculoFora.getVeiculo().getPlaca().equals(placa))) {
                veiculoFora.getVeiculo().setQuilometragemAtual(quilometragem);
                this.veiculosFora.remove(veiculoFora);
                
                this.novoEvento(Evento.VEICULO_DEVOLVIDO, matricula, placa);
            }
        } 
    }
        
    private boolean disponibilidadeVeiculo(String placa) {
        
        for (SaidaVeiculo veiculoFora : veiculosFora) {
            return (veiculoFora.getVeiculo().getPlaca().equals(placa));
        }
        return false;
    }
    
    private void novoEvento(Evento evento, int matricula, String placa) {
        Calendar dataHora = Calendar.getInstance();           //verificar como pegar a hora
        EventoClaviculario novoEvento = new EventoClaviculario(evento,dataHora, matricula, placa);
        this.log.add(novoEvento);
        
    }
    
    private void novaSaida(Veiculo veiculo, Funcionario funcionario){
        Calendar dataHora = Calendar.getInstance();         //verificar como pegar a hora
        SaidaVeiculo novaSaida = new SaidaVeiculo(veiculo, funcionario, dataHora);
        this.veiculosFora.add(novaSaida);
        
    }
    
    /*
    private boolean validaMatricula(int matricula) {
        try { 
            Funcionario funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);   
        } catch (MatriculaNaoCadastradaException e) {
            throw e;
        }
    }
    */
    
    
    
}