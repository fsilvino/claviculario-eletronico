package br.ufsc.ine5605.clavicularioeletronico.telas;

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
    
}
