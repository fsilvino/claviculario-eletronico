package br.ufsc.ine5605.clavicularioeletronico.telas;

public abstract class TelaCadastro extends TelaBase {
    
    public TelaCadastro() {
        super();
    }

    public abstract void exibeTelaInclui();

    public abstract void exibeTelaAltera();

    public abstract void exibeTelaExclui();
    
    public abstract void exibeLista();

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("1 - Incluir");       
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar cadastros");
            System.out.println("0 - Voltar ao menu inicial");
            System.out.println("-------------------------------------------------");
            System.out.print("Opção escolhida: ");
            
            try {
                opcao = Integer.parseInt(this.teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("A opcao precisa ser um numero!");
            }
            if (opcao > 4 || opcao < 0) {
                System.out.println("Informe uma opcao valida!");
            }
            else {
                switch (opcao) {
                    case 1: exibeTelaInclui();
                            break;

                    case 2: exibeTelaAltera();
                            break;

                    case 3: exibeTelaExclui();
                            break;
                            
                    case 4: exibeLista();
                            break;
                }
            }
        }             
    }
    
}