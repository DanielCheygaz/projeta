import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductBuyWindow extends JFrame{
    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JSpinner spinner;
    private JLabel titleLabel;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 5000;
    private static final int STEP = 1;
    private static final int START_VALUE = 0;
    private Product product;
    private StockManagerWindow previousWindow;

    public ProductBuyWindow(StockManagerWindow previousWindow, Product product) throws HeadlessException {
        super("Comprar Produto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        this.previousWindow = previousWindow;
        this.product = product;

        titleLabel.setText(product.getName());

        // definir o comportamento do spinner
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(START_VALUE,MIN_VALUE,MAX_VALUE,STEP);
        spinner.setModel(spinnerNumberModel);

        this.cancelButton.addActionListener(this::cancelButtonPerformed);
        this.saveButton.addActionListener(this::saveButtonPerformed);
    }

    private void cancelButtonPerformed(ActionEvent e){
        previousWindow.setVisible(true);
        dispose();
    }
    private void saveButtonPerformed(ActionEvent e){
        product.addUnits(Integer.valueOf(spinner.getValue().toString()));
        previousWindow.refreshData();
        previousWindow.setVisible(true);
        dispose();
    }
}
