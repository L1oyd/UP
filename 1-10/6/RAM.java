import java.util.Objects;

public class RAM {
    private int capacity; // Объем оперативной памяти в GB

    // Конструктор
    public RAM(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAM ram = (RAM) o;
        return capacity == ram.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    @Override
    public String toString() {
        return "RAM{" +
                "capacity=" + capacity +
                '}';
    }
}
