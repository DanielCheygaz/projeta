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

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 500;
    private static final int STEP = 1;

    public ProductEditWindow(Product product) throws HeadlessException {
        super("Editar: " + product.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.product = product;

        textFieldName.setText(product.getName());

        // definir o comportamento do spinner
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(product.getUnits(),MIN_VALUE,MAX_VALUE,STEP);
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

        if(price<=0){
            new ErrorWindow("O preço tem de ser superior a zero").setVisible(true);
            return;
        }

        if(units<0){
            new ErrorWindow("As unidades não podem ser negativas").setVisible(true);
            return;
        }

        product.editProduct(productName,price,units);
        new StockManagerWindow().setVisible(true);
        dispose();
    }

}
