import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductAddWindow extends JFrame{
    private JFormattedTextField formattedTextNome;
    private JSpinner unitsSpinner;
    private JFormattedTextField formattedTextPrice;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 500;
    private static final int STEP = 1;
    private static final int START_VALUE = 0;

    public ProductAddWindow() throws HeadlessException {
        super("Adicionar Produto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

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
        String productName = formattedTextNome.getText();
        String productPriceString = formattedTextPrice.getText();

        if(productName==null){
            new ErrorWindow("O campo nome não pode estar vazio!").setVisible(true);
            return;
        }

        if(productPriceString ==null){
            new ErrorWindow("O campo preço não pode estar vazio!").setVisible(true);
            return;
        }

        Product product = new Product(productName,Double.valueOf(productPriceString));
        Stock stock = new Stock(product,(Integer)unitsSpinner.getValue());

        AppData.getInstance().addStock(stock);

        new StockManagerWindow().setVisible(true);
        dispose();
    }
}
