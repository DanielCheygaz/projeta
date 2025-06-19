import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesMainWindow extends JFrame{
    private JPanel mainPanel;
    private JButton sellTicketButton;
    private JButton sellBarProductsButton;
    private JButton manageBundlesButton;
    private JButton editSaleButton;
    private JButton backButton;
    private JButton salesHistoryButton;
    public SalesMainWindow() {
        super("Gestor de Vendas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.manageBundlesButton.addActionListener(this::manageBundlesButtonPerformed);
        this.sellTicketButton.addActionListener(this::sellTicketButtonPerformed);
        this.sellBarProductsButton.addActionListener(this::sellBarProductsButtonPerformed);
        this.editSaleButton.addActionListener(this::editSaleButtonPerformed);
        this.salesHistoryButton.addActionListener(this::salesHistoryButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private void sellTicketButtonPerformed(ActionEvent e){
        new SessionSelectWindow(this).setVisible(true);
        setVisible(false);
    }

    private void sellBarProductsButtonPerformed(ActionEvent e){
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    private void manageBundlesButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    private void editSaleButtonPerformed(ActionEvent e){
        new ReceiptEditWindow(this).setVisible(true);
        dispose();
    }

    private void salesHistoryButtonPerformed(ActionEvent e){
        new SalesHistoryWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new SalesMainWindow().setVisible(true);
    }
}
