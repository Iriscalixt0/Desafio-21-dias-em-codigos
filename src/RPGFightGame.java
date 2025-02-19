import java.util.Random;
import java.util.Scanner;

public class RPGFightGame {

    // Função que permite o usuário escolher seu ataque
    static int ataqueUsuario(Scanner leitor) {
        System.out.println("Escolha seu ataque: ");
        System.out.println("(1) - Soco");
        System.out.println("(2) - Especial");
        return leitor.nextInt(); // Retorna a escolha do ataque
    }

    // Função que gera um ataque aleatório para o computador
    static int ataqueComputador(int i) {
        Random gerador = new Random();
        return gerador.nextInt(3) + 1; // Retorna um número aleatório entre 1 e 3
    }

    // Função para imprimir os pontos de vida (HP) de ambos os jogadores
    static void imprimeHP(int hpUsuario, int hpComputador, int contagemEspecial) {
        System.out.println("===============");
        System.out.println("HP Usuario: " + hpUsuario);
        System.out.println("HP Computador: " + hpComputador);
        System.out.println("Contagem especiais: " + contagemEspecial);
        System.out.println("===============");
    }

    // Função para controlar a batalha entre o usuário e o computador
    static int batalha(Scanner leitor) {
        int hpUsuario = 100; // HP inicial do usuário
        int hpComputador; // HP do computador
        int contagemEspecial = 5; // Contagem de ataques especiais que o usuário pode usar
        int escolhaAtaque; // Armazena a escolha do ataque do usuário
        int i = 1; // Contador de inimigos

        // Enquanto o usuário ainda tiver HP, a batalha continua
        while (hpUsuario > 0) {
            hpComputador = 99 + i; // HP inicial do computador, aumentando com o nível do inimigo

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("INIMIGO: " + i); // Exibe o número do inimigo atual
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-\n");

            // O combate começa aqui
            while (hpUsuario > 0 && hpComputador > 0) {
                imprimeHP(hpUsuario, hpComputador, contagemEspecial); // Mostra os status do combate
                escolhaAtaque = ataqueUsuario(leitor); // Lê a escolha de ataque do usuário
                switch (escolhaAtaque) {
                    case 1: // Se o usuário escolheu o ataque 'Soco'
                        System.out.println("Usuario socou o inimigo!");
                        hpComputador -= 7; // Diminui o HP do inimigo
                        break;
                    case 2: // Se o usuário escolheu o ataque 'Especial'
                        System.out.println("Usuario usou um ataque especial!");
                        hpComputador -= 20; // Diminui mais HP do inimigo
                        contagemEspecial--; // Reduz a contagem de ataques especiais
                        break;
                    default: // Se o usuário escolher uma opção inválida
                        System.out.println("Opção Inválida!");
                        break;
                }

                // O computador ataca de volta, caso o inimigo ainda tenha HP
                if (hpComputador > 0) {
                    escolhaAtaque = ataqueComputador(i); // O computador escolhe um ataque aleatório
                    switch (escolhaAtaque) {
                        case 1:
                            System.out.println("Computador socou o inimigo!");
                            hpUsuario -= 5 + (int) (i / 10); // Computador tira uma quantidade de HP do usuário
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
                    System.out.println("Inimigo derrotado!"); // Exibe quando o inimigo é derrotado
                }
            }

            // Se o usuário ainda estiver vivo após derrotar o inimigo
            if (hpUsuario > 0) {
                hpUsuario += 5; // O usuário recupera 5 pontos de vida
                if (hpUsuario > 100) {
                    hpUsuario = 100; // Garante que o HP do usuário não ultrapasse 100
                }
                if (i % 10 == 0) {
                    contagemEspecial++; // A cada 10 inimigos derrotados, o usuário ganha mais um ataque especial
                    if (contagemEspecial > 5) {
                        contagemEspecial = 5; // Limita o número máximo de ataques especiais
                    }
                }
            }

            // Impede que o HP do inimigo ultrapasse 100, a menos que ele vença o usuário
            if (hpComputador > 100 && hpUsuario > 0) {
                hpComputador = 100; // Garante que o inimigo não tenha mais de 100 HP
            }

            i++; // Avança para o próximo inimigo
        }

        return i; // Retorna o número de inimigos derrotados
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
