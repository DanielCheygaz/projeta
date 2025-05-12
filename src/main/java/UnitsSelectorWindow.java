import javax.swing.*;
import java.awt.event.ActionEvent;

public class UnitsSelectorWindow extends JDialog {
    private JPanel mainPanel;
    private JSpinner spinner1;
    private JButton okButton;
    private JPanel JPanel;
    private JLabel labelUnitsSelector;
    private int selectedUnits = -1;

    public UnitsSelectorWindow(JFrame parent) {
        super(parent, "Selecionar Unidades", true); // janela modal
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);

        // Configurar spinner
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 1000, 1); // mínimo 1, máximo 1000
        spinner1.setModel(spinnerModel);

        okButton.addActionListener(this::okButtonPerformed);
    }

    private void okButtonPerformed(ActionEvent e) {
        selectedUnits = (int) spinner1.getValue();
        dispose();
    }

    public int getSelectedUnits() {
        return selectedUnits;
    }
}
