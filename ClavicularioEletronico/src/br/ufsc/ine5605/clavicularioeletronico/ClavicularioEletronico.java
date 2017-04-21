/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.clavicularioeletronico;

import br.ufsc.ine5605.clavicularioeletronico.controladores.ControladorSistema;

/**
 *
 * @author Flávio
 */
public class ClavicularioEletronico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorSistema.getInstance().inicia();
    }
    
}
