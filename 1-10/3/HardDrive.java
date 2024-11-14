import java.util.Objects;

public class HardDrive {
    private int size; // Размер винчестера в GB

    // Конструктор
    public HardDrive(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HardDrive hardDrive = (HardDrive) o;
        return size == hardDrive.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "HardDrive{" +
                "size=" + size +
                '}';
    }
}
