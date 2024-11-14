public class Main {
    public static void main(String[] args) {
        HardDrive hd = new HardDrive(512);
        DiskDrive dd = new DiskDrive("DVD");
        RAM ram = new RAM(16);
        Processor proc = new Processor("Intel Core i7", 3.8);

        Computer computer = new Computer(hd, dd, ram, proc);

        computer.turnOn();
        computer.displayHardDriveSize();
        computer.checkForViruses();
        computer.turnOff();
    }
}
