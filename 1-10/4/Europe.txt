import java.util.ArrayList;
import java.util.List;

public class Europe {

    // Внутренний класс StateChange
    public class StateChange {
        private String year; // Год изменения
        private String description; // Описание изменения

        // Конструктор
        public StateChange(String year, String description) {
            this.year = year;
            this.description = description;
        }

        // Методы доступа (get) и установки (set)
        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Год: " + year + ", Изменение: " + description;
        }
    }

    // Список для хранения изменений
    private List<StateChange> stateChanges;

    // Конструктор для инициализации списка изменений
    public Europe() {
        stateChanges = new ArrayList<>();
    }

    // Метод для добавления изменений
    public void addStateChange(String year, String description) {
        StateChange stateChange = new StateChange(year, description);
        stateChanges.add(stateChange);
    }

    // Метод для вывода всех изменений
    public void printStateChanges() {
        System.out.println("История изменений территориального деления Европы:");
        for (StateChange change : stateChanges) {
            System.out.println(change);
        }
    }

    public static void main(String[] args) {
        // Создание объекта Europe
        Europe europe = new Europe();

        // Добавление изменений
        europe.addStateChange("1914", "Начало Первой мировой войны");
        europe.addStateChange("1918", "Конец Первой мировой войны, распад Австро-Венгерской и Османской империй");
        europe.addStateChange("1991", "Распад СССР и Югославии");
        europe.addStateChange("2004", "Расширение Евросоюза");

        // Вывод истории изменений
        europe.printStateChanges();
    }
}

