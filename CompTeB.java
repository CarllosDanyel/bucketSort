import java.util.Arrays;
import java.util.Random;

public class CompTeB {
    public static void main(String[] args) {
        int[] dados = gerarDados(500000);

        int[] dadosBucketSort = Arrays.copyOf(dados, dados.length);
        long tempoInicioBucketSort = System.currentTimeMillis();
        bucketSort(dadosBucketSort);
        long tempoFimBucketSort = System.currentTimeMillis();
        long tempoExecucaoBucketSort = tempoFimBucketSort - tempoInicioBucketSort;
        int comparacoesBucketSort = getComparacoes();
        int movimentacoesBucketSort = getMovimentacoes();

        int[] dadosTimSort = Arrays.copyOf(dados, dados.length);
        long tempoInicioTimSort = System.currentTimeMillis();
        Arrays.sort(dadosTimSort);
        long tempoFimTimSort = System.currentTimeMillis();
        long tempoExecucaoTimSort = tempoFimTimSort - tempoInicioTimSort;
        int comparacoesTimSort = 0;
        int movimentacoesTimSort = 0;

        System.out.println("BucketSort:");
        System.out.println("Tempo de Execução: " + tempoExecucaoBucketSort + "ms");
        System.out.println("Quantidade de comparações: " + comparacoesBucketSort);
        System.out.println("Quantidade de Movimentações: " + movimentacoesBucketSort);
        System.out.println();

        System.out.println("TimSort:");
        System.out.println("Tempo de Execução: " + tempoExecucaoTimSort + "ms");
        System.out.println("Quantidade de comparações: " + comparacoesTimSort);
        System.out.println("Quantidade de Movimentações: " + movimentacoesTimSort);
        System.out.println();

        System.out.println("[Dados Ordenados]");
        System.out.println(Arrays.toString(dadosBucketSort));
    }

    public static void bucketSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        int valorMin = arr[0];
        int valorMax = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < valorMin) {
                valorMin = arr[i];
            } else if (arr[i] > valorMax) {
                valorMax = arr[i];
            }
        }

        int numBuckets = (valorMax - valorMin) / n + 1;
        int[][] buckets = new int[numBuckets][n];
        int[] tamanhosBuckets = new int[numBuckets];

        for (int i = 0; i < numBuckets; i++) {
            tamanhosBuckets[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            int indiceBucket = (arr[i] - valorMin) / n;
            buckets[indiceBucket][tamanhosBuckets[indiceBucket]] = arr[i];
            tamanhosBuckets[indiceBucket]++;
        }

        int indiceAtual = 0;
        for (int i = 0; i < numBuckets; i++) {
            if (tamanhosBuckets[i] > 0) {
                insertionSort(buckets[i], tamanhosBuckets[i]);
                for (int j = 0; j < tamanhosBuckets[i]; j++) {
                    arr[indiceAtual] = buckets[i][j];
                    indiceAtual++;
                }
            }
        }
    }

    public static void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int chave = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > chave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = chave;
        }
    }

    public static int[] gerarDados(int tamanho) {
        int[] dados = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            dados[i] = random.nextInt(tamanho);
        }
        return dados;
    }

    public static int getComparacoes() {
        return 0;
    }

    public static int getMovimentacoes() {
        return 0;
    }
}
