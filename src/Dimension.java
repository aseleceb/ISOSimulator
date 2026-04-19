import java.util.ArrayList;
import java.util.List;

public class Dimension {
    private String name;
    private double coefficient;
    private List<Metric> metrics = new ArrayList<>();

    public Dimension(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public void addMetric(Metric m) { metrics.add(m); }

    public double calculateDimensionScore() {
        double totalWeightedScore = 0;
        double totalMetricCoeff = 0;
        for (Metric m : metrics) {
            totalWeightedScore += (m.getScore() * m.getCoefficient());
            totalMetricCoeff += m.getCoefficient();
        }
        return totalMetricCoeff == 0 ? 0 : totalWeightedScore / totalMetricCoeff;
    }

    public String getName() { return name; }
}