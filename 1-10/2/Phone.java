import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Phone {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String creditCardNumber;
    private double debit;
    private double credit;
    private int localCallDuration;
    private int longDistanceCallDuration;

    // Конструктор с параметрами
    public Phone(int id, String lastName, String firstName, String middleName, String address,
                 String creditCardNumber, double debit, double credit,
                 int localCallDuration, int longDistanceCallDuration) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = debit;
        this.credit = credit;
        this.localCallDuration = localCallDuration;
        this.longDistanceCallDuration = longDistanceCallDuration;
    }

    // Конструктор по умолчанию
    public Phone() {
        this(0, "Неизвестно", "Неизвестно", "Неизвестно", "Неизвестно", "Неизвестно", 0, 0, 0, 0);
    }

    // Методы доступа (get) и установки (set)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getLocalCallDuration() {
        return localCallDuration;
    }

    public void setLocalCallDuration(int localCallDuration) {
        this.localCallDuration = localCallDuration;
    }

    public int getLongDistanceCallDuration() {
        return longDistanceCallDuration;
    }

    public void setLongDistanceCallDuration(int longDistanceCallDuration) {
        this.longDistanceCallDuration = longDistanceCallDuration;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", Фамилия='" + lastName + '\'' +
                ", Имя='" + firstName + '\'' +
                ", Отчество='" + middleName + '\'' +
                ", Адрес='" + address + '\'' +
                ", Номер кредитной карточки='" + creditCardNumber + '\'' +
                ", Дебет=" + debit +
                ", Кредит=" + credit +
                ", Время городских разговоров=" + localCallDuration +
                ", Время междугородных разговоров=" + longDistanceCallDuration +
                '}';
    }
}

public class PhoneArray {

    public static void main(String[] args) {
        // Создание массива объектов Phone
        Phone[] phones = {
                new Phone(1, "Иванов", "Иван", "Иванович", "ул. Ленина", "1234-5678-9123", 5000, 1000, 50, 10),
                new Phone(2, "Петров", "Петр", "Петрович", "ул. Крылова", "2345-6789-1234", 7000, 3000, 70, 0),
                new Phone(3, "Сидоров", "Сидор", "Сидорович", "ул. Гоголя", "3456-7891-2345", 3000, 500, 30, 5),
                new Phone(4, "Кузнецов", "Алексей", "Алексеевич", "ул. Чехова", "4567-8912-3456", 8000, 4000, 20, 50)
        };

        // a) Сведения об абонентах, у которых время внутригородских разговоров превышает заданное значение
        int threshold = 30;
        System.out.println("Абоненты с временем внутригородских разговоров более " + threshold + ":");
        for (Phone phone : phones) {
            if (phone.getLocalCallDuration() > threshold) {
                System.out.println(phone);
            }
        }

        // b) Сведения об абонентах, которые пользовались междугородной связью
        System.out.println("\nАбоненты, пользовавшиеся междугородной связью:");
        for (Phone phone : phones) {
            if (phone.getLongDistanceCallDuration() > 0) {
                System.out.println(phone);
            }
        }

        // c) Сведения об абонентах в алфавитном порядке
        System.out.println("\nАбоненты в алфавитном порядке:");
        Arrays.sort(phones, Comparator.comparing(Phone::getLastName));
        for (Phone phone : phones) {
            System.out.println(phone);
        }
    }
}
