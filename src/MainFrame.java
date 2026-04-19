import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panels = new JPanel(cardLayout);
    private int currentStep = 1;
    private boolean productQuality = true;
    private CollectPanel collectPanel;
    private AnalysePanel analysePanel;

    public MainFrame() {
        setTitle("ISO 15939 Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        collectPanel = new CollectPanel(this);
        analysePanel = new AnalysePanel(this);

        panels.add(new ProfilePanel(this), "1");
        panels.add(new DefinePanel(this), "2");
        panels.add(new PlanPanel(this), "3");
        panels.add(collectPanel, "4");
        panels.add(analysePanel, "5");

        add(panels);

        currentStep = 1;
        cardLayout.show(panels, "1");

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setProductQuality(boolean product) {
        this.productQuality = product;
    }

    public boolean isProductQuality() {
        return productQuality;
    }

    public void nextStep() {
        if (currentStep < 5) {
            currentStep++;
            cardLayout.show(panels, String.valueOf(currentStep));
            if (currentStep == 4) {
                collectPanel.reloadRows();
            }
        }
    }

    /** Collect → fills analysis then shows step 5 */
    public void completeCollectStep(double overallScore, String weakestMetric) {
        analysePanel.updateResults(overallScore, weakestMetric);
        if (currentStep < 5) {
            currentStep++;
            cardLayout.show(panels, String.valueOf(currentStep));
        }
    }

    public void previousStep() {
        if (currentStep > 1) {
            currentStep--;
            cardLayout.show(panels, String.valueOf(currentStep));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
