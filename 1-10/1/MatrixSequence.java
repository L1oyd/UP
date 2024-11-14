import java.util.Random;
import java.util.Scanner;

public class MatrixSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размерности матрицы
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        // Создание и заполнение матрицы случайными числами от -n до n
        int[][] matrix = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }

        // Вывод матрицы
        System.out.println("Матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Поиск наибольшей последовательности
        int longestInc = 0;
        int longestDec = 0;

        // Проверка строк
        for (int i = 0; i < n; i++) {
            int[] result = findLongestSequence(matrix[i]);
            longestInc = Math.max(longestInc, result[0]);
            longestDec = Math.max(longestDec, result[1]);
        }

        // Проверка столбцов
        for (int j = 0; j < n; j++) {
            int[] column = new int[n];
            for (int i = 0; i < n; i++) {
                column[i] = matrix[i][j];
            }
            int[] result = findLongestSequence(column);
            longestInc = Math.max(longestInc, result[0]);
            longestDec = Math.max(longestDec, result[1]);
        }

        // Вывод результата
        System.out.println("Наибольшая последовательность возрастающих элементов: " + longestInc);
        System.out.println("Наибольшая последовательность убывающих элементов: " + longestDec);
    }

    // Метод для поиска наибольшей последовательности возрастающих и убывающих элементов
    public static int[] findLongestSequence(int[] arr) {
        int incSeq = 1, decSeq = 1;
        int longestInc = 1, longestDec = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                incSeq++;
                decSeq = 1;
            } else if (arr[i] < arr[i - 1]) {
                decSeq++;
                incSeq = 1;
            } else {
                incSeq = 1;
                decSeq = 1;
            }
            longestInc = Math.max(longestInc, incSeq);
            longestDec = Math.max(longestDec, decSeq);
        }

        return new int[]{longestInc, longestDec};
    }
}
