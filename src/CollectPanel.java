import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CollectPanel extends StepPanel {
    private final JTable table;
    private final DefaultTableModel model;

    public CollectPanel(MainFrame frame) {
        super(frame);
        String[] cols = {"Metric", "Direction", "Range", "Value", "Score (1-5)"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3;
            }
        };
        table = new JTable(model);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnNext = new JButton("Analyze Results");
        btnNext.addActionListener(e -> next());
        add(btnNext, BorderLayout.SOUTH);

        reloadRows();
    }

    public void reloadRows() {
        model.setRowCount(0);
        if (frame.isProductQuality()) {
            model.addRow(new Object[]{"System Usability Scale", "Higher is better", "0-100", "", ""});
            model.addRow(new Object[]{"Defect density", "Lower is better", "0-100 per KLOC", "", ""});
            model.addRow(new Object[]{"Customer satisfaction", "Higher is better", "1-5", "", ""});
        } else {
            model.addRow(new Object[]{"Lead time", "Lower is better", "1-90 days", "", ""});
            model.addRow(new Object[]{"Process cycle efficiency", "Higher is better", "0-100%", "", ""});
            model.addRow(new Object[]{"Rework rate", "Lower is better", "0-50%", "", ""});
        }
    }

    @Override
    public void next() {
        double sum = 0;
        int count = 0;
        double minScore = Double.POSITIVE_INFINITY;
        String weakest = "";

        for (int i = 0; i < model.getRowCount(); i++) {
            String metric = String.valueOf(model.getValueAt(i, 0));
            Object raw = model.getValueAt(i, 4);
            if (raw == null) {
                continue;
            }
            String scoreStr = raw.toString().trim();
            if (scoreStr.isEmpty()) {
                continue;
            }
            try {
                double s = Double.parseDouble(scoreStr.replace(',', '.'));
                if (s < 1 || s > 5) {
                    JOptionPane.showMessageDialog(this,
                            "Scores must be between 1 and 5 (row " + (i + 1) + ").");
                    return;
                }
                sum += s;
                count++;
                if (s <= minScore) {
                    minScore = s;
                    weakest = metric;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid score in row " + (i + 1) + ". Use a number like 3 or 3.5.");
                return;
            }
        }

        if (count == 0) {
            JOptionPane.showMessageDialog(this,
                    "Enter a score (1-5) in the \"Score (1-5)\" column for at least one metric.");
            return;
        }

        double overall = Math.round((sum / count) * 10.0) / 10.0;
        frame.completeCollectStep(overall, weakest);
    }

    @Override public void previous() {}
}
