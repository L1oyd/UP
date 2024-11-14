import java.util.Objects;

public class DiskDrive {
    private String type; // Тип дисковода: CD, DVD, Blu-ray

    // Конструктор
    public DiskDrive(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiskDrive diskDrive = (DiskDrive) o;
        return Objects.equals(type, diskDrive.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "DiskDrive{" +
                "type='" + type + '\'' +
                '}';
    }
}
