import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditProductWindow extends JFrame{
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JFormattedTextField formattedTextNome;
    private JFormattedTextField formattedTextUnits;
    private JFormattedTextField formattedTextPrice;
    private Product product;
    private Stock stock;

    public EditProductWindow(Stock stock, int index) throws HeadlessException {
        super("Editar " + stock.getProduct().getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.stock = stock;
        this.product = stock.getProduct();

        formattedTextNome.setText(product.getName());
        formattedTextUnits.setText(String.valueOf(stock.getUnits()));
        formattedTextPrice.setText(String.valueOf(product.getPrice()));

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
