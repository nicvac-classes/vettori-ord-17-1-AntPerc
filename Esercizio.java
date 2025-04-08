import java.util.Scanner;

public class Esercizio {
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n, rand, nV, nW, nX, i, idx, val;

        System.out.println("Inserire il numero di numeri interi casuali da generare: ");
        n = input.nextInt();
        int[] v = new int[n];
        int[] w = new int[n];

        nV = 0;
        nW = 0;
        for (i = 0; i <= n - 1; i++) {
            rand = random.nextInt(n);
            if (rand % 2 == 0) {
                v[nV] = rand;
                nV = nV + 1;
            } else {
                w[nW] = rand;
                nW = nW + 1;
            }
        }
        System.out.println("------ VETTORE V NON ORDINATO ------");
        visualizzaVettore(v, nV);
        System.out.println("------ VETTORE W NON ORDINATO ------");
        visualizzaVettore(w, nW);
        bubbleSort(v, nV);
        bubbleSort(w, nW);
        System.out.println("------ VETTORE V ORDINATO ------");
        visualizzaVettore(v, nV);
        System.out.println("------ VETTORE W ORDINATO ------");
        visualizzaVettore(w, nW);
        nX = nV + nW;
        int[] x = new int[nX];

        merge(v, nV, w, nW, x);
        System.out.println("------ VETTORE MERGE ORDINATO ------");
        visualizzaVettore(x, nX);
        System.out.println("Inserire il valore da cercare: ");
        val = input.nextInt();
        idx = ricercaBinaria(x, nX, val);
        if (idx != -1) {
            System.out.println("Il valore " + val + " è stato trovato alla posizione: " + idx);
        } else {
            System.out.println("Il valore " + val + " non è stato trovato.");
        }
    }
    
    public static void bubbleSort(int[] v, int n) {
        int i, j, temp;
        boolean scambio;

        scambio = true;
        i = 0;
        while (i < n && scambio) {
            scambio = false;
            j = 0;
            while (j < n - 1 - i) {
                if (v[j] > v[j + 1]) {
                    temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                    scambio = true;
                }
                j = j + 1;
            }
            i = i + 1;
        }
    }
    
    public static void merge(int[] v, int nV, int[] w, int nW, int[] x) {
        int i, j, k;

        i = 0;
        j = 0;
        k = 0;
        while (i < nV && j < nW) {
            if (v[i] < w[j]) {
                x[k] = v[i];
                i = i + 1;
            } else {
                x[k] = w[j];
                j = j + 1;
            }
            k = k + 1;
        }
        while (i < nV) {
            x[k] = v[i];
            i = i + 1;
            k = k + 1;
        }
        while (j < nW) {
            x[k] = w[j];
            j = j + 1;
            k = k + 1;
        }
    }
    
    public static int ricercaBinaria(int[] v, int n, int val) {
        int i, idx, iStart, iEnd, centro;

        iStart = 0;
        iEnd = n - 1;
        centro = iStart + (double) (iEnd - iStart) / 2;
        idx = -1;
        while (idx == -1 && iStart <= iEnd) {
            if (val > v[centro]) {
                iStart = centro + 1;
            } else {
                if (val < v[centro]) {
                    iEnd = centro - 1;
                } else {
                    idx = centro;
                }
            }
            centro = iStart + (double) (iEnd - iStart) / 2;
        }
        
        return idx;
    }
    
    public static void visualizzaVettore(int[] v, int n) {
        int i;

        for (i = 0; i <= n - 1; i++) {
            System.out.println(v[i]);
        }
    }
}