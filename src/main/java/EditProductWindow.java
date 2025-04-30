import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditProductWindow extends JFrame{
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField priceTextField;
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JFormattedTextField formattedTextFieldNome;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private Product product;

    public EditProductWindow(Product product, int index) throws HeadlessException {
        super("Editar " + product.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.product = product;

        nameTextField.setText(product.getName());
        quantityTextField.setText(String.valueOf(product.units));
        priceTextField.setText(String.valueOf(product.price));

        this.cancelButton.addActionListener(this::cancelButtonPerformed);
        this.saveButton.addActionListener(this::saveButtonPerformed);
    }
    private void cancelButtonPerformed(ActionEvent e){
        new StockManagerWindow().setVisible(true);
        dispose();
    }
    private void saveButtonPerformed(ActionEvent e){
        new StockManagerWindow().setVisible(true);
        dispose();
    }

}
