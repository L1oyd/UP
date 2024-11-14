import java.util.Scanner;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод текста пользователем
        System.out.println("Введите текст:");
        String input = scanner.nextLine();

        // Обработка текста: удаление всех символов, кроме букв и пробелов
        String processedText = input.replaceAll("[^a-zA-Zа-яА-Я ]", "").replaceAll("\\s+", " ");

        // Вывод результата
        System.out.println("Результат:");
        System.out.println(processedText.trim());
    }
}
