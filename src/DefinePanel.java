import javax.swing.*;
import java.awt.*;

public class DefinePanel extends StepPanel {
    private JRadioButton rbProduct, rbProcess;

    public DefinePanel(MainFrame frame) {
        super(frame);
        JPanel pnl = new JPanel(new GridLayout(3, 1));
        rbProduct = new JRadioButton("Product Quality");
        rbProcess = new JRadioButton("Process Quality");

        ButtonGroup group = new ButtonGroup();
        group.add(rbProduct); group.add(rbProcess);

        pnl.add(rbProduct); pnl.add(rbProcess);
        add(pnl, BorderLayout.CENTER);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(e -> next());
        add(btnNext, BorderLayout.SOUTH);
    }

    @Override
    public void next() {
        if (!rbProduct.isSelected() && !rbProcess.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a quality type.");
        } else {
            frame.setProductQuality(rbProduct.isSelected());
            frame.nextStep();
        }
    }
    @Override public void previous() {}
}