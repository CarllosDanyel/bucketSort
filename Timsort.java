
import java.util.concurrent.TimeUnit;

public class Timsort {

    private static final int RUN = 32;

    public static void timSort(int[] arr, int n) {
        for (int i = 0; i < n; i += RUN) {
            insertionSort(arr, i, Math.min((i + RUN - 1), (n - 1)));
        }

        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++) {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++) {
            right[x] = arr[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }

        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[500000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }
        int n = arr.length;
        System.out.println("Iniciando a ordenação de " + n + " números...");

        long tempoInicio = System.nanoTime();
        timSort(arr, n);
        long tempoFim = System.nanoTime();

        long duracao = tempoFim - tempoInicio;

        long horas = TimeUnit.NANOSECONDS.toHours(duracao);
        long minutos = TimeUnit.NANOSECONDS.toMinutes(duracao) % 60;
        long segundos = TimeUnit.NANOSECONDS.toSeconds(duracao) % 60;
        long milissegundos = TimeUnit.NANOSECONDS.toMillis(duracao) % 1000;

        System.out.printf("Ordenação concluída.\nTempo de execução: %02d:%02d:%02d:%03d\n", horas, minutos, segundos, milissegundos);
    }
}
