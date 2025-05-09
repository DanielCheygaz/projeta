import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BundleManagerWindow extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable bundlesTable;
    private JButton addBundleButton;
    private JButton removeBundleButton;
    private JButton editBundleButton;

    public BundleManagerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        backButton.addActionListener(this::backButtonPerformed);
        addBundleButton.addActionListener(this::addBundleButtonPerformed);
        removeBundleButton.addActionListener(this::removeBundleButtonPerformed);
        editBundleButton.addActionListener(this::editBundleButtonPerformed);

        scrollPane.getViewport().setBackground(Color.decode("2894892"));
        String[] columns = {"ID do Bundle", "Nome do Bundle", "Preço do Bundle", "Produtos no Bundle"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (Bundle bundle : AppData.getInstance().getBundleList()) {
            Object[] row = {bundle.getId(), bundle.getName(), bundle.getPrice(), "Pipocas, Bebidas"};
            tableModel.addRow(row);
        }
        bundlesTable.setModel(tableModel);
    }
    private void backButtonPerformed(ActionEvent e){
        new SalesManagerWindow().setVisible(true);
        dispose();
    }

    private void addBundleButtonPerformed(ActionEvent e){
        new BundleCreateTicketSelectWindow().setVisible(true);
        dispose();
    }

    private void removeBundleButtonPerformed(ActionEvent e){
        int selectedRow = bundlesTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) bundlesTable.getModel();

            int bundleId = (int) model.getValueAt(selectedRow, 0);

            AppData.getInstance().getBundleList().removeIf(bundle -> bundle.getId() == bundleId);

            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Bundle removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um bundle para remover.");
        }
    }

    private void editBundleButtonPerformed(ActionEvent e){
        new BundleCreateTicketSelectWindow().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new BundleManagerWindow().setVisible(true);
    }
}



