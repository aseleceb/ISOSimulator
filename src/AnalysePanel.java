import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JLabel lblResult;
    private JProgressBar progress;
    private JTextArea txtGapAnalysis;

    public AnalysePanel(MainFrame frame) {
        super(frame);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Step 5: Final Evaluation & Gap Analysis", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));


        lblResult = new JLabel("Overall Quality Score: 0.0 / 5.0", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(lblResult);


        progress = new JProgressBar(0, 50);
        progress.setStringPainted(true);
        centerPanel.add(progress);


        txtGapAnalysis = new JTextArea();
        txtGapAnalysis.setEditable(false);
        txtGapAnalysis.setLineWrap(true);
        txtGapAnalysis.setBorder(BorderFactory.createTitledBorder("Gap Analysis Result"));
        centerPanel.add(new JScrollPane(txtGapAnalysis));

        add(centerPanel, BorderLayout.CENTER);
    }


    public void updateResults(double score, String weakestDimension) {
        lblResult.setText("Overall Quality Score: " + score + " / 5.0");
        progress.setValue((int)(score * 10));

        String analysis = "The measurement is complete.\n\n";
        analysis += "Weakest Area Identified: " + weakestDimension + "\n";
        analysis += "Recommendation: Focus on improving the metrics under " + weakestDimension + " to increase overall quality.";

        txtGapAnalysis.setText(analysis);
    }

    @Override public void next() {}
    @Override public void previous() { frame.previousStep(); }
}