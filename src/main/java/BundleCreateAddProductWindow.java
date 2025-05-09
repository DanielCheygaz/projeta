import javax.swing.*;
import java.awt.event.ActionEvent;

public class BundleCreateAddProductWindow extends JFrame{
    private JPanel mainPanel;
    private JSpinner unitsSpinner;
    private JButton cancelButton;
    private JButton saveButton;
    private JSpinner discountSpinner;

    public BundleCreateAddProductWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        cancelButton.addActionListener(this::cancelButtonPerformed);
        saveButton.addActionListener(this::saveButtonPerformed);
    }

    private void cancelButtonPerformed(ActionEvent e){
        new BundleCreateBarProductsSelectWindow().setVisible(true);
        dispose();
    }

    private void saveButtonPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(this, "Produto adicionado ao bundle!");
        new BundleCreateBarProductsSelectWindow().setVisible(true);
        dispose();
    }

}
