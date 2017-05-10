package br.ufsc.ine5605.clavicularioeletronico.entidades;

import br.ufsc.ine5605.clavicularioeletronico.enums.Evento;
import java.util.Calendar;

public class EventoClaviculario {

    private Evento evento;

    private Calendar datahora;

    private int matricula;

    private String placa;

    public EventoClaviculario(Evento evento, Calendar datahora, int matricula, String placa) {
        this.evento = evento;
        this.datahora = datahora;
        this.matricula = matricula;
        this.placa = placa;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    
}