import javax.swing.*;
import java.awt.*;

public abstract class StepPanel extends JPanel {
    protected MainFrame frame;

    public StepPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
    }

    public abstract void next();
    public abstract void previous();
}