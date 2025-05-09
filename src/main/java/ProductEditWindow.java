import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductEditWindow extends JFrame{
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JSpinner unitsSpinner;
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private Product product;
    private Stock stock;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 500;
    private static final int STEP = 1;

    public ProductEditWindow(Stock stock) throws HeadlessException {
        super("Editar " + stock.getProduct().getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.stock = stock;
        this.product = stock.getProduct();

        textFieldName.setText(product.getName());

        // definir o comportamento do spinner
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(stock.getUnits(),MIN_VALUE,MAX_VALUE,STEP);
        unitsSpinner.setModel(spinnerNumberModel);

        textFieldPrice.setText(String.valueOf(product.getPrice()));

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
        // caso a string tenha vírgula, troca-se por ponto para poder fazer a conversão para double
        productPriceString = productPriceString.replace(',','.');

        // verificar que os valores do preço e unidades inseridos contêm apenas números
        try {
            price = Double.valueOf(productPriceString);
            units = Integer.valueOf(unitsSpinner.getValue().toString());
        }catch(NumberFormatException ex){
            new ErrorWindow("O preço ou as unidades têm de ser um número." + ex.getMessage()).setVisible(true);
            return;
        }

        stock.editStock(productName,units,price);
        new StockManagerWindow().setVisible(true);
        dispose();
    }

}
