import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductAddWindow extends JFrame{
    private JSpinner unitsSpinner;
    private JFormattedTextField formattedTextPrice;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JTextField textFieldName;
    private JTextField textFieldPrice;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 500;
    private static final int STEP = 1;
    private static final int START_VALUE = 0;

    public ProductAddWindow() throws HeadlessException {
        super("Adicionar Produto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        // definir o comportamento do spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(START_VALUE,MIN_VALUE,MAX_VALUE,STEP);
        unitsSpinner.setModel(spinnerModel);

        this.cancelButton.addActionListener(this::cancelButtonPerformed);
        this.saveButton.addActionListener(this::saveButtonPerformed);
    }

    private void cancelButtonPerformed(ActionEvent e){
        new StockManagerWindow().setVisible(true);
        dispose();
    }
    private void saveButtonPerformed(ActionEvent e){
        String productName = textFieldName.getText();
        String productPriceString = textFieldPrice.getText();

        if(productName==null){
            new ErrorWindow("O campo nome não pode estar vazio!").setVisible(true);
            return;
        }

        if(productPriceString==null){
            new ErrorWindow("O campo preço não pode estar vazio!").setVisible(true);
            return;
        }

        double price;
        int units;
        productPriceString = productPriceString.replace(',','.');

        // verificar que os valores do preço e unidades inseridos contêm apenas números
        try {
            price = Double.valueOf(productPriceString);
            units = Integer.valueOf(unitsSpinner.getValue().toString());
        }catch(NumberFormatException ex){
            new ErrorWindow("O preço ou as unidades têm de ser um número." + ex.getMessage()).setVisible(true);
            return;
        }

        Product product = new Product(productName, price);
        Stock stock = new Stock(product,units);

        AppData.getInstance().addStock(stock);

        new StockManagerWindow().setVisible(true);
        dispose();
    }
}
