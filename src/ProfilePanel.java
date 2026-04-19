import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends StepPanel {
    private JTextField txtUser = new JTextField(20);

    public ProfilePanel(MainFrame frame) {
        super(frame);
        JPanel center = new JPanel(new GridLayout(3, 2));
        center.add(new JLabel("Username:"));
        center.add(txtUser);
        add(center, BorderLayout.CENTER);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(e -> next());
        add(btnNext, BorderLayout.SOUTH);
    }

    @Override
    public void next() {
        if (txtUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username!");
        } else {
            frame.nextStep();
        }
    }

    @Override public void previous() {}
}