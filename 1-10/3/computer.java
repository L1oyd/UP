import java.util.Objects;

public class Computer {
    private HardDrive hardDrive;
    private DiskDrive diskDrive;
    private RAM ram;
    private Processor processor;
    private boolean isOn;

    // Конструктор
    public Computer(HardDrive hardDrive, DiskDrive diskDrive, RAM ram, Processor processor) {
        this.hardDrive = hardDrive;
        this.diskDrive = diskDrive;
        this.ram = ram;
        this.processor = processor;
        this.isOn = false;
    }

    // Метод для включения компьютера
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Компьютер включен.");
        } else {
            System.out.println("Компьютер уже включен.");
        }
    }

    // Метод для выключения компьютера
    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Компьютер выключен.");
        } else {
            System.out.println("Компьютер уже выключен.");
        }
    }

    // Метод для проверки на вирусы
    public void checkForViruses() {
        if (isOn) {
            System.out.println("Проверка компьютера на вирусы...");
            // Симуляция проверки на вирусы
            System.out.println("Вирусы не найдены.");
        } else {
            System.out.println("Для проверки на вирусы включите компьютер.");
        }
    }

    // Метод для вывода размера винчестера
    public void displayHardDriveSize() {
        System.out.println("Размер винчестера: " + hardDrive.getSize() + " GB");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return isOn == computer.isOn &&
                Objects.equals(hardDrive, computer.hardDrive) &&
                Objects.equals(diskDrive, computer.diskDrive) &&
                Objects.equals(ram, computer.ram) &&
                Objects.equals(processor, computer.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hardDrive, diskDrive, ram, processor, isOn);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "hardDrive=" + hardDrive +
                ", diskDrive=" + diskDrive +
                ", ram=" + ram +
                ", processor=" + processor +
                ", isOn=" + isOn +
                '}';
    }
}
