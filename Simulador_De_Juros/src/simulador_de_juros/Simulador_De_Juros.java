
package simulador_de_juros;

import java.util.Scanner;


public class Simulador_De_Juros {

    
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in, "UTF-8");
        int opcao = 0,  tempo;
        double capital, taxaJuros, juros;
        boolean executando = true; 
        
        while(executando){
           
            System.out.println("Simulador de Juros");
            System.out.println("Escolha o tipo de Juros: ");
            System.out.println("1- Juros Simples");
            System.out.println("2- Juros Compostos");
            System.out.println("3- Sair");
            opcao = sc.nextInt();
        
            switch (opcao) {
                case 1:
                    System.out.println("Digite o Capital Inicial: ");
                    capital = sc.nextDouble();
                    System.out.println("Digite a taxa de Juros(%): ");
                    taxaJuros = sc.nextDouble();
                    double i = taxaJuros / 100;
                    System.out.println("Digite o tempo(em Meses): ");
                    tempo = sc.nextInt();
                    juros = capital * i * tempo;
                    System.out.printf("Total dos Juros: R$ %.2f\n", juros);
                    break;
                case 2:
                    System.out.println("Digite o Capital Inicial: ");
                    capital = sc.nextDouble();
                    System.out.println("Digite a taxa de Juros: ");
                    taxaJuros = sc.nextDouble();
                    i = taxaJuros / 100;
                    System.out.println("Digite o tempo(em Meses): ");
                    tempo = sc.nextInt();
                    double montante = capital * Math.pow(1 + i, tempo);
                    juros = montante - capital;
                    System.out.printf("Montante Final: R$ %.2f\n", montante);
                    System.out.printf("Total dos Juros: R$ %.2f\n", juros);
                    break;
                case 3: 
                    System.out.println("Saindo do programa...");
                    Thread.sleep(3000);
                    executando = false;
                    break;
                    default:
                        System.out.println("Opção inválida");
                        break;
            }
        }
                      sc.close();
                      System.out.println("Programa Encerrado!");
    }
    
}
