package tec.ada.nuclea.logicaProgramacao.JogoDeBingo;

import java.util.*;
import java.lang.Integer;

public class JogoDeBingo {
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("Bem-vindos ao Bingo Ser + Tech Programa 50+ | Ada Tech!!");
        System.out.println("========================================================\n");

        // Insere os nomes dos jogadores.
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome dos jogadores separados por hífen e sem espaço:");
        String jogadores = sc.next();

        String[] arrayJogadores = jogadores.split("-");

        // Gera as cartela
        int[][] matrizCartelas = new int[arrayJogadores.length][5];
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("\nComo deseja gerar as cartelas? Digite 1-(Automaticamente), 2-(Informar manualmente) ou X-" +
                    "(Sair):");
            String input = sc.next();
            if (input.equalsIgnoreCase("X")) {
                System.out.println("O jogo foi encerrado!");
                System.exit('X');
            }
            try {
                int gerarCartelas = Integer.parseInt(input);

                if (gerarCartelas == 1) {
                    System.out.println("Você escolheu gerar as cartelas automaticamente\n");
                    matrizCartelas = gerarCartelasAutomaticamente(arrayJogadores);
                    entradaValida = true;
                } else if (gerarCartelas == 2) {
                    System.out.println("Você escolheu gerar as cartelas manualmente");
                    matrizCartelas = gerarCartelasManualmente(arrayJogadores, sc);
                    entradaValida = true;
                } else {
                    System.out.println("Entrada inválida. Digite apenas 1, 2 ou X.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro ou X.");
            }
        }

        System.out.println("\nTudo certo! Vamos começar o jogo!\n");

        // Escolhe a forma de fazer os sorteios
        int tipoSorteio = 0;
        entradaValida = false;
        while (!entradaValida) {
            System.out.println("\nComo deseja fazer os sorteios: Digite 1-(Automaticamente), 2-(Manualmente) ou X-(Sair:");
            String input = sc.next();
            if (input.equalsIgnoreCase("X")) {
                System.out.println("O jogo foi encerrado!");
                System.exit('X');
            }
            try {
                tipoSorteio = Integer.parseInt(input);
                if (tipoSorteio == 1) {
                    System.out.println("ótimo! Você escolheu fazer os sorteio automaticamente. Então vamos começar.\n");
                    entradaValida = true;
                } else if (tipoSorteio == 2) {
                    System.out.println("ótimo! Você escolheu fazer os sorteio manualmente. Então vamos começar.\n");
                    entradaValida = true;
                } else {
                    System.out.println("Entrada inválida. Digite apenas 1, 2 ou X.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro ou X.");
            }
        }

        // Faz os Sorteios
        int[] arrayNumerosASortear = new int[60];
        for (int i = 0; i < 60; i++) {
            arrayNumerosASortear[i] = i + 1;
        }
        for (int i = arrayNumerosASortear.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = arrayNumerosASortear[i];
            arrayNumerosASortear[i] = arrayNumerosASortear[j];
            arrayNumerosASortear[j] = temp;
        }
        int rodada = 1;
        int cont = 0;
        boolean bingo = false;
        int[][] matrizNumerosSorteados = new int[12][5];
        int[][] matrizMarcacaoCartelas = new int[arrayJogadores.length][5];
        for (int i = 0; i < arrayJogadores.length; i++) {
            Arrays.fill(matrizMarcacaoCartelas[i], 0);
        }
        String[] ganhadores = new String[arrayJogadores.length];
        while (!bingo) {
            entradaValida = false;
            while (!entradaValida) {

                System.out.printf("Digite S - Para fazer o %dº sorteio ou X (Sair)\n", rodada);
                String input = sc.next();
                if (input.equalsIgnoreCase("X")) {
                    System.out.println("O jogo foi encerrado!");
                    System.exit('X');
                }
                try {
                    String continuar = String.format(input);
                    if (continuar.equalsIgnoreCase("S") && tipoSorteio == 1) {
                        realizarSorteioAutomaticamente(arrayNumerosASortear, matrizNumerosSorteados, rodada, cont);
                        imprimirSorteios(matrizNumerosSorteados, rodada);
                        cont += 5;
                        entradaValida = true;

                    } else if (continuar.equalsIgnoreCase("S") && tipoSorteio == 2) {
                        realizarSorteioManualmente(matrizNumerosSorteados, rodada, sc);
                        imprimirSorteios(matrizNumerosSorteados, rodada);
                        entradaValida = true;

                    } else if (!continuar.equalsIgnoreCase("S")) {
                        System.out.println("Entrada inválida! Informe S ou X.");

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "Entrada inválida! Informe apenas as letras S ou X.");
                    sc.nextLine();
                }
            }

            // Confere as cartelas e calcula a pontuação dos jogadores
            conferirCartelas(matrizNumerosSorteados, arrayJogadores, matrizCartelas, matrizMarcacaoCartelas, rodada);

            int[] arrayPontuacao = calcularPontuacao(matrizMarcacaoCartelas);

            //int[] arrayRanking = new int[3];

            Pair[] paresOrdenados = ordenarPontuacao(arrayPontuacao);

            // Imprime o ranking dos jogadores top 3 em pontuação
            System.out.print("\nranking dos top 3:    ");
            int colunas = 4;
            int n = paresOrdenados.length;
            for (int i = n - 1; i >= Math.max(0, n - 3); i--) {
                System.out.print(arrayJogadores[paresOrdenados[i].indice] + " => " + paresOrdenados[i].valor + "        ");
                if ((i + 3) % colunas == 0) {
                    System.out.println();
                }
            }
            System.out.println("\n");

            // Verifica se já tem ganhador
            for (int i = 0; i < arrayPontuacao.length; i++) {
                if (arrayPontuacao[i] == 5) {
                    ganhadores[i] = arrayJogadores[i];
                    bingo = true;
                }
            }

            rodada++;
        }

        // Apresenta as estatísticas do jogo
        System.out.println("B I N G O!!\n");
        System.out.println("===========");

        System.out.printf("● Quantidade de rodadas : %d\n", rodada - 1);
        for (int i = 0; i < ganhadores.length; i++) {
            if(ganhadores[i] != null) {
                System.out.print("● Cartela premiada : " + Arrays.toString(matrizCartelas[i]) + "  ");
                System.out.println("Vencedor : " + ganhadores[i]);
            }

        }
        System.out.print("● Números sorteados : ");
        Integer[] arrayNumerosSorteados = new Integer[matrizNumerosSorteados.length * matrizNumerosSorteados[0].length];
        int index = 0;
        for (int[] NumerosSorteado : matrizNumerosSorteados) {
            for (int i : NumerosSorteado) {
                arrayNumerosSorteados[index++] = i;
            }
        }
        Arrays.sort(arrayNumerosSorteados);
        for (int NumerosSorteado : arrayNumerosSorteados) {
            if (NumerosSorteado != 0) {
                System.out.print(NumerosSorteado + " ");
            }
        }
        System.out.println();

        int[] arrayPontuacao = calcularPontuacao(matrizMarcacaoCartelas);
        Pair[] paresOrdenados = ordenarPontuacao(arrayPontuacao);
        System.out.println("\nRanking geral do jogo : ");
        System.out.println("=======================");
        int colunas = 5;
        int n = paresOrdenados.length;
        for (int i = n - 1; i >= 0; i--) {
            System.out.printf("%-15s => %02d    ", arrayJogadores[paresOrdenados[i].indice], paresOrdenados[i].valor);
            if ((i + 4) % colunas == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");

        System.out.println("Marcação das cartelas :");
        System.out.println("======================");
        for (int i = 0; i < arrayJogadores.length; i++) {
            System.out.printf("%-15s", arrayJogadores[i]);
            for (int j = 0; j < 5; j++) { // j - coluna
                System.out.printf("%02d ", matrizCartelas[i][j]);
            }
            System.out.print("\n                ");
            for (int j = 0; j < 5; j++) {
                System.out.print(matrizMarcacaoCartelas[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("\n+------------------------------------+");
        System.out.println("|  F i n a l   d a   p a r t i d a.  |");
        System.out.println("+------------------------------------+");
    }

    private static int[][] gerarCartelasAutomaticamente(String[] arrayJogadores) {
        int[][] matrizCartelas = new int[arrayJogadores.length][5];
        int elementosCartela;
        Random random = new Random();
        for (int i = 0; i < arrayJogadores.length; i++) { // cartela - linha
            Set<Integer> numerosGerados = new HashSet<>();

            for (int j = 0; j < 5; j++) { // j - coluna
                do {
                    elementosCartela = random.nextInt(1, 61);
                } while (numerosGerados.contains(elementosCartela));

                numerosGerados.add(elementosCartela);
                matrizCartelas[i][j] = elementosCartela;
            }
            Arrays.sort(matrizCartelas[i]);
        }
        imprimirCartelas(arrayJogadores, matrizCartelas);
        return matrizCartelas;
    }

    private static int[][] gerarCartelasManualmente(String[] arrayJogadores, Scanner sc) {
        int[][] matrizCartelas = new int[arrayJogadores.length][5];
        boolean cartelasGeradas = false;

        System.out.println("Informe os números das cartelas em sequencia separados por hífen e sem espaço ou informe" +
                " X para Sair:");
        String numerosCartelasRecebidos = sc.next();
        if (numerosCartelasRecebidos.equalsIgnoreCase("X")) {
            char sair = 'X';
            System.out.println("O jogo foi encerrado!");
            System.exit(sair);
        }
        while (!cartelasGeradas) {

            String[] arrayNumeroCartelas = numerosCartelasRecebidos.split("-");

            if (arrayNumeroCartelas.length == arrayJogadores.length) {

                String[] rows = numerosCartelasRecebidos.split("-");
                for (int i = 0; i < rows.length; i++) {
                    String[] elements = rows[i].split(",");
                    for (int j = 0; j < elements.length; j++) {
                        matrizCartelas[i][j] = Integer.parseInt(elements[j]);
                    }
                    Arrays.sort(matrizCartelas[i]);
                }
                imprimirCartelas(arrayJogadores, matrizCartelas);
                cartelasGeradas = true;
            } else {
                System.out.println("A string informada não contém o número correto de elementos.");
                System.out.println("Informe Novamente os números das cartelas em sequencia separados vírgulas e por" +
                        " hífen e sem espaço ou informe X para Sair:");
                numerosCartelasRecebidos = sc.next();
                if (numerosCartelasRecebidos.equalsIgnoreCase("X")) {
                    //char sair = 'X';
                    System.out.println("O jogo foi encerrado!");
                    System.exit(0);
                }
            }
        }
        return matrizCartelas;
    }

    public static void imprimirCartelas(String[] arrayJogadores, int[][] matrizCartelas) {
        System.out.println("\nAqui estão as cartelas geradas:\n");
        int colunas = 5;
        for (int i = 0; i < arrayJogadores.length; i++) {
            System.out.printf("%-15s", arrayJogadores[i]);
            for (int j = 0; j < 5; j++) { // j - coluna
                System.out.printf("%02d ", matrizCartelas[i][j]);
            }
            System.out.print("   ");
            if ((i + 1) % colunas == 0) {
                System.out.println();
            }
        }
    }

    public static void realizarSorteioAutomaticamente(int[] arrayNumerosASortear, int[][] matrizNumerosSorteados, int rodada, int cont) {
        System.arraycopy(arrayNumerosASortear, cont, matrizNumerosSorteados[rodada - 1], 0, 5);
        Arrays.sort(matrizNumerosSorteados[rodada - 1]);
    }

    private static void realizarSorteioManualmente(int[][] matrizNumerosSorteados, int rodada, Scanner sc) {
        System.out.println("Informe os cinco números sorteados, separados por vírgula:");
        String numerosSorteados = sc.next();

        String[] elements = numerosSorteados.split(",");
        for (int i = 0; i < 5; i++) {
            matrizNumerosSorteados[rodada - 1][i] = Integer.parseInt(elements[i]);
        }
        Arrays.sort(matrizNumerosSorteados[rodada - 1]);
    }

    public static void imprimirSorteios(int[][] matrizNumerosSorteados, int rodada) {
        System.out.printf("\nOs números sorteados no %dº sorteio foram: ", rodada);
        for (int i = 0; i < 5; i++) {
            System.out.print(matrizNumerosSorteados[rodada - 1][i] + " ");
        }
        System.out.println();
    }

    private static void conferirCartelas(int[][] matrizNumerosSorteados, String[] arrayJogadores, int[][] matrizCartelas, int[][] matrizMarcacaoCartelas, int rodada) {
        for (int i = 0; i < arrayJogadores.length; i++) {
            for (int j = 0; j < 5; j++) {
                for (int l = 0; l < 5; l++) {
                    if (matrizNumerosSorteados[rodada - 1][j] == matrizCartelas[i][l]) {
                        matrizMarcacaoCartelas[i][l] = 1;
                    }
                }
            }
        }
    }

    private static int[] calcularPontuacao(int[][] matrizMarcacaoCartelas) {
        int[] arrayPontuacao = new int[matrizMarcacaoCartelas.length];
        for (int i = 0; i < matrizMarcacaoCartelas.length; i++) {
            for (int j = 0; j < matrizMarcacaoCartelas[i].length; j++) {
                arrayPontuacao[i] += matrizMarcacaoCartelas[i][j];
            }
        }
        return arrayPontuacao;
    }

    public static Pair[] ordenarPontuacao(int[] arrayPontuacao) {

        Pair[] pares = new Pair[arrayPontuacao.length];

        for (int i = 0; i < arrayPontuacao.length; i++) {
            pares[i] = new Pair(arrayPontuacao[i], i);
        }

        Arrays.sort(pares, Comparator.comparingInt((Pair pair) -> pair.valor));

        return pares;
    }

    static class Pair {
        int valor;
        int indice;

        public Pair(int valor, int indice) {
            this.valor = valor;
            this.indice = indice;
        }
    }
}
