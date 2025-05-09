import javax.swing.*;
import java.awt.event.ActionEvent;

public class BundleCreateTicketSelectWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton finishBundleButton;
    private JButton addBarProductsButton;

    public BundleCreateTicketSelectWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        backButton.addActionListener(this::backButtonPerformed);
        finishBundleButton.addActionListener(this::finishBundleButtonPerformed);
        addBarProductsButton.addActionListener(this::addBarProductsButtonPerformed);


    }

    private void backButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    private void finishBundleButtonPerformed(ActionEvent e){
        new BundleManagerWindow().setVisible(true);
        dispose();
    }

    private void addBarProductsButtonPerformed(ActionEvent e){
        new BundleCreateBarProductsSelectWindow().setVisible(true);
        dispose();
    }
}
