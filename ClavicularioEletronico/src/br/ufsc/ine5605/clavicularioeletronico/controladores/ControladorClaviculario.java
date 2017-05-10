
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
 * **/
 
public class ControladorClaviculario {

    private static ControladorClaviculario instance;
    private List<EventoClaviculario> log;
    private List<SaidaVeiculo> veiculosFora;
    private TelaClaviculario tela;
    
    private ControladorClaviculario() {
        log = new ArrayList<>();
        veiculosFora = new ArrayList();
        tela = new TelaClaviculario();
    }

    public static ControladorClaviculario getInstance() {
        if (instance == null) {
            instance = new ControladorClaviculario();
        }
        return instance;
    }

    public void inicia() {
        tela.exibeMenu();
       // retirar chave, devolver chave
       //relatorio de acessos
    }
    
    public void retirarChave() throws Exception {
        
        int matricula = tela.pedeMatricula();
        if (matricula == 0) {
            
            throw new Exception("Matricula inválida");
        }
        Funcionario funcionario = null;
        
        try {
            funcionario = ControladorFuncionario.getInstance().getFuncionarioPelaMatricula(matricula);
        } catch (MatriculaNaoCadastradaException e) {
            this.novoEvento(Evento.MATRICULA_INVALIDA, matricula, "");
            throw e;
        }
               
        if (funcionario.isBloqueado()) {
            this.novoEvento(Evento.USUARIO_BLOQUEADO, matricula, "");
            throw new Exception("Usuario encontra-se bloqueado!");
        }
 
        String placa = tela.pedePlaca();
        Veiculo veiculo = ControladorVeiculo.getInstance().getVeiculoPelaPlaca(placa);
        
        if (this.veiculoDisponivel(placa)) {
            if (funcionario.getCargo() != Cargo.DIRETORIA) {
                
                 if (!ControladorPermissaoUsoVeiculo.getInstance().permissaoExiste(funcionario, veiculo)) {
                    funcionario.incrementaNumeroTentativasSemPermissao();
                    this.novoEvento(Evento.PERMISSAO_INSUFICIENTE, matricula, "");
                    if (funcionario.getNumeroTentativasSemPermissao() >= 3) {
                        funcionario.setBloqueado(true);
                        this.novoEvento(Evento.ACESSO_BLOQUEADO, matricula, placa);
                        throw new Exception("Usuario bloqueado por excesso de tentativas sem permissao!");
                    }
                    throw new Exception("Voce nao possui permissoes de acesso a este veiculo! (" + funcionario.getNumeroTentativasSemPermissao() + "/3)");
                }
            }
            funcionario.resetNumeroTentativasSemPermissao();
            this.novaSaida(veiculo, funcionario);
        } else {
            this.novoEvento(Evento.VEICULO_INDISPONIVEL, matricula, placa);
            throw new Exception("Veiculo indisponivel no momento");
        }
    }
        
    
    
    public void devolverVeiculo(int matricula, String placa, int quilometragem) {   
        //validar matricula
        
        for (SaidaVeiculo veiculoFora : veiculosFora) {
            if((veiculoFora.getVeiculo().getPlaca().equals(placa))) {
                veiculoFora.getVeiculo().setQuilometragemAtual(quilometragem);
                this.veiculosFora.remove(veiculoFora);
                this.novoEvento(Evento.VEICULO_DEVOLVIDO, matricula, placa);
                break;
            }
        } 
    }
        
    private boolean veiculoDisponivel(String placa) {
        
        for (SaidaVeiculo veiculoFora : veiculosFora) {
            if ((veiculoFora.getVeiculo().getPlaca().equals(placa))) {
                return false;
            }
        }
        return true;
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
        this.novoEvento(Evento.ACESSO_PERMITIDO, funcionario.getMatricula(), veiculo.getPlaca());
    } 
}
