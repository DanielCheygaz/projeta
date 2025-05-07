import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductEditWindow extends JFrame{
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JFormattedTextField formattedTextNome;
    private JFormattedTextField formattedTextUnits;
    private JFormattedTextField formattedTextPrice;
    private JSpinner unitsSpinner;
    private Product product;
    private Stock stock;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 500;
    private static final int STEP = 1;

    public ProductEditWindow(Stock stock, int index) throws HeadlessException {
        super("Editar " + stock.getProduct().getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.stock = stock;
        this.product = stock.getProduct();

        formattedTextNome.setText(product.getName());

        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(stock.getUnits(),MIN_VALUE,MAX_VALUE,STEP);
        unitsSpinner.setModel(spinnerNumberModel);

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
