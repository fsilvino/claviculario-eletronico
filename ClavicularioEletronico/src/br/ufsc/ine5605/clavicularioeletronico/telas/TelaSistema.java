package br.ufsc.ine5605.clavicularioeletronico.telas;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorClaviculario;
import java.util.Scanner;

/**
 * 
 * @author Flávio
 */
public class TelaSistema {
    
    public Scanner teclado;
    
    public TelaSistema() {
        teclado = new Scanner(System.in);
    }

    public void exibeMenu() {
        
        int opcao;
        do {
            System.out.println("CLAVICULARIO ELETRONICO");
            System.out.println("2) Claviculario");
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 0:
                    
                    break;
                case 1:
                    
                    break;
                case 2:
                    abreClaviculario();
                    break;
            }
            
        } while (opcao != 0);
    }
    
    public void abreClaviculario() {
        ControladorClaviculario.getInstance().inicia();
    }

}
