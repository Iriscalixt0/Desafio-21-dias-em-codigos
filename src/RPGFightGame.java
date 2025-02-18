import java.util.Random;
import java.util.Scanner;

public class RPGFightGame {

    //Usuario escolher ataque.
    static int ataqueUsuario(Scanner leitor) {
        System.out.println("Escolha seu ataque: ");
        System.out.println("(1) - Soco");
        System.out.println("(2) - Especial");
        return leitor.nextInt();
    }

    //Gerar um ataque aleatório do computador.
    static int ataqueComputador(int i) {
        Random gerador = new Random();
        return gerador.nextInt(3) + 1; //retorna número entre 1 e 3.
    }

    //Imprime os pontos de vida de cada jogador.
    static void imprimeHP(int hpUsuario, int hpComputador, int contagemEspecial) {
        System.out.println("===============");
        System.out.println("HP Usuario: " + hpUsuario);
        System.out.println("HP Computador: " + hpComputador);
        System.out.println("Contagem especiais: " + contagemEspecial);
        System.out.println("===============");
    }

    //Iniciar a batalha.
    static int batalha(Scanner leitor) {
        int hpUsuario = 100; //Pontos de vida
        int hpComputador;
        int contagemEspecial = 5;
        int escolhaAtaque;
        int i = 1; //Cria um novo inimigo a cada rodada.

        while (hpUsuario > 0) {
            hpComputador = 99 + i;

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("INIMIGO: " + i);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-\n");

            //Mostra qual ataque o usuario escolheu.
            while (hpUsuario > 0 && hpComputador > 0) {
                imprimeHP(hpUsuario, hpComputador, contagemEspecial);
                escolhaAtaque = ataqueUsuario(leitor);
                switch (escolhaAtaque) {
                    case 1:
                        System.out.println("Usuario socou o inimigo!");
                        hpComputador -= 7;
                        break;
                    case 2:
                        System.out.println("Usuario usou um ataque especial!");
                        hpComputador -= 20;
                        contagemEspecial--;
                        break;
                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }
                if (hpComputador > 0) {
                    escolhaAtaque = ataqueComputador(i);
                    switch (escolhaAtaque) {
                        case 1:
                            System.out.println("Computador socou o inimigo!");
                            hpUsuario -= 5 + (int) (i / 10);
                            break;
                        case 2:
                            System.out.println("Computador chutou o inimigo!");
                            hpUsuario -= 7 + (int) (i / 10);
                            break;
                        case 3:
                            System.out.println("Computador usou um ataque especial!");
                            hpUsuario -= 10 + (int) (i / 20);
                            break;
                    }
                } else {
                    System.out.println("Inimigo derrotado!");
                }
            }

            if (hpUsuario > 0) {
                hpUsuario += 5;
                if (hpUsuario > 100) {
                    hpUsuario = 100;
                }
                if (i % 10 == 0) {
                    contagemEspecial++;
                    if (contagemEspecial > 5) {
                        contagemEspecial = 5;
                    }
                }
            }

            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in); // Cria o objeto Scanner para ler entradas do usuário
        int continua = 1; // Variável que controla se o jogo continua ou não
        int recorde = 0; // Variável que armazena o recorde de inimigos derrotados

        // Loop principal do jogo, que continua até o usuário escolher parar
        while (continua == 1) {
            int pontos = batalha(leitor); // Chama o método de batalha e armazena o número de inimigos derrotados
            if (pontos > recorde) { // Se o número de inimigos derrotados for maior que o recorde, atualiza o recorde
                recorde = pontos;
                System.out.println("Usuario chegou a " + pontos + " pontos."); // Exibe o novo recorde
            }

            // Pergunta ao usuário se ele deseja continuar jogando
            System.out.println("Fim de jogo! Deseja continuar? (01) Sim, vamos lá! \n (02) Não, irei recompor minhas forças.");
            continua = leitor.nextInt(); // Lê a resposta do usuário (1 ou 2)
        }

        leitor.close(); // FECHAR O SCANNER APÓS O USO.
    }
}
