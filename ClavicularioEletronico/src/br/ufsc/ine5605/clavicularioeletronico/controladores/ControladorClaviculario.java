package br.ufsc.ine5605.clavicularioeletronico.controladores;

import br.ufsc.ine5605.clavicularioeletronico.entidades.EventoClaviculario;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa a funcionalidade do Claviculario propriamente dita:
 * a liberação e a recepção das chaves conforme as configurações
 * do sistema e salva o log de eventos
 * @author Flávio
 */
public class ControladorClaviculario {

    private static ControladorClaviculario instancia;

    private List<EventoClaviculario> log;
    
    private ControladorClaviculario() {
        log = new ArrayList<>();
    }

    public static ControladorClaviculario getInstance() {
        if (instancia == null) {
            instancia = new ControladorClaviculario();
        }
        return instancia;
    }

    public void inicia() {
        
    }
    
}