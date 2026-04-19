public class Metric {
    private String name, direction, unit;
    private double coefficient, minValue, maxValue, value, score;

    public Metric(String name, double coeff, String dir, double min, double max, String unit) {
        this.name = name;
        this.coefficient = coeff;
        this.direction = dir;
        this.minValue = min;
        this.maxValue = max;
        this.unit = unit;
    }

    public void calculateScore(double inputValue) {
        this.value = inputValue;
        if (direction.contains("Higher")) {
            score = 1 + ((value - minValue) / (maxValue - minValue)) * 4;
        } else {
            score = 5 - ((value - minValue) / (maxValue - minValue)) * 4;
        }
        score = Math.max(1.0, Math.min(5.0, score));
        score = Math.round(score * 2) / 2.0;
    }

    public String getName() { return name; }
    public String getDirection() { return direction; }
    public String getRange() { return minValue + "-" + maxValue; }
    public double getScore() { return score; }
    public double getCoefficient() { return coefficient; }
    public String getUnit() { return unit; }
}