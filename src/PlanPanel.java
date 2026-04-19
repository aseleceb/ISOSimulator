import javax.swing.*;
import java.awt.BorderLayout;

public class PlanPanel extends StepPanel {
    public PlanPanel(MainFrame frame) {
        super(frame);
        add(new JLabel("Step 3: Measurement Plan", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton btn = new JButton("Next");
        btn.addActionListener(e -> next());
        add(btn, BorderLayout.SOUTH);
    }

    @Override public void next() { frame.nextStep(); }
    @Override public void previous() {}
}
