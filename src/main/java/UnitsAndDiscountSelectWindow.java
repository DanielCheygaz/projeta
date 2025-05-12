import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UnitsAndDiscountSelectWindow extends JDialog {
    private JPanel mainPanel;
    private JSpinner unitsSpinner;
    private JSpinner discountSpinner;
    private JButton okButton;
    private JLabel labelUnitsSelector;

    private int selectedUnits = 0;
    private double selectedDiscount = 0.0;

    public UnitsAndDiscountSelectWindow(JFrame parent) {
        super(parent, "Detalhes do Produto", true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();

        unitsSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1)); // mínimo 1 unidade
        discountSpinner.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.5)); // 0% a 100% em intervalos de 0.5

        okButton.addActionListener(this::okButtonPerformed);
    }

    private void okButtonPerformed(ActionEvent e) {
        selectedUnits = (int) unitsSpinner.getValue();
        selectedDiscount = (double) discountSpinner.getValue();
        dispose();
    }

    public int getSelectedUnits() {
        return selectedUnits;
    }

    public double getSelectedDiscount() {
        return selectedDiscount;
    }
}
