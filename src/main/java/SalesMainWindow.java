import javax.swing.*;
import java.awt.event.ActionEvent;

public class SalesWindow extends JFrame{
    private JPanel mainPanel;
    private JButton sellTicketButton;
    private JButton sellBarProductsButton;
    private JButton manageSalesButton;
    private JButton editSaleButton;
    private JButton backButton;

    public SalesWindow(){
        super("Gestor de Vendas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.manageSalesButton.addActionListener(this::sellTicketButtonPerformed);
        this.sellTicketButton.addActionListener(this::sellTicketButtonPerformed);
        this.sellBarProductsButton.addActionListener(this::sellBarProductsButtonPerformed);
        this.editSaleButton.addActionListener(this::sellBarProductsButtonPerformed);
        this.backButton.addActionListener(this::backButtonPerformed);
    }

    private void backButtonPerformed(ActionEvent e){
        new MainWindow().setVisible(true);
        dispose();
    }

    private void sellTicketButtonPerformed(ActionEvent e){
        new TicketSalesWindow().setVisible(true);
        dispose();
    }

    private void sellBarProductsButtonPerformed(ActionEvent e){
        new BarProductsSaleWindow(this).setVisible(true);
        dispose();
    }

    public static void main(String[] args){
        new SalesWindow().setVisible(true);
    }
}
