
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BucketSort {

    public static void bucketSort(int[] arr, int numBaldes) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int valorMinimo = arr[0];
        int valorMaximo = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < valorMinimo) {
                valorMinimo = arr[i];
            } else if (arr[i] > valorMaximo) {
                valorMaximo = arr[i];
            }
        }

        int tamanhoBalde = (valorMaximo - valorMinimo) / numBaldes + 1;
        ArrayList<ArrayList<Integer>> baldes = new ArrayList<>(numBaldes);
        for (int i = 0; i < numBaldes; i++) {
            baldes.add(new ArrayList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            int indiceBalde = (arr[i] - valorMinimo) / tamanhoBalde;
            baldes.get(indiceBalde).add(arr[i]);
        }

        int indiceAtual = 0;
        for (int i = 0; i < numBaldes; i++) {
            Collections.sort(baldes.get(i));
            for (int j = 0; j < baldes.get(i).size(); j++) {
                arr[indiceAtual++] = baldes.get(i).get(j);
            }
        }
    }

    public static void main(String[] args) {
        int numElementos = 500000;
        int[] arr = new int[numElementos];
        Random rand = new Random();

        for (int i = 0; i < numElementos; i++) {
            arr[i] = rand.nextInt(1000000);
        }

        int numBaldes = 1000;

        System.out.println("Iniciando a ordenação de " + numElementos + " números...");

        long tempoInicio = System.nanoTime();
        bucketSort(arr, numBaldes);
        long tempoFim = System.nanoTime();

        long duracao = tempoFim - tempoInicio;

        long horas = TimeUnit.NANOSECONDS.toHours(duracao);
        long minutos = TimeUnit.NANOSECONDS.toMinutes(duracao) % 60;
        long segundos = TimeUnit.NANOSECONDS.toSeconds(duracao) % 60;
        long milissegundos = TimeUnit.NANOSECONDS.toMillis(duracao) % 1000;

        System.out.printf("Ordenação concluída.\nTempo de execução: %02d:%02d:%02d:%03d\n", horas, minutos, segundos, milissegundos);
    }
}
