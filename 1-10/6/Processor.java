import java.util.Objects;

public class Processor {
    private String model; // Модель процессора
    private double frequency; // Частота процессора в GHz

    // Конструктор
    public Processor(String model, double frequency) {
        this.model = model;
        this.frequency = frequency;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Double.compare(processor.frequency, frequency) == 0 &&
                Objects.equals(model, processor.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, frequency);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "model='" + model + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
