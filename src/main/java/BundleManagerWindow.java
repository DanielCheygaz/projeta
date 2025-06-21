import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.stream.Collectors;

public class BundleManagerWindow extends JFrame {
    private JPanel mainPanel;
    private JButton backButton;
    private JScrollPane scrollPane;
    private JTable bundlesTable;
    private JButton addBundleButton;
    private JButton removeBundleButton;
    private JButton editBundleButton;
    private JFrame previousWindow;

    public BundleManagerWindow(JFrame previousWindow) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.previousWindow = previousWindow;
        setContentPane(mainPanel);
        pack();

        backButton.addActionListener(this::backButtonPerformed);
        addBundleButton.addActionListener(this::addBundleButtonPerformed);
        removeBundleButton.addActionListener(this::removeBundleButtonPerformed);
        editBundleButton.addActionListener(this::editBundleButtonPerformed);

        scrollPane.getViewport().setBackground(Color.decode("2894892"));

        loadBundles();
    }

    public void loadBundles() {
        String[] columns = {"ID do Bundle", "Nome do Bundle", "Produtos no Bundle"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        for (Bundle bundle : AppData.getInstance().getBundleList()) {
            String produtosStr = bundle.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", "));
            Object[] row = {bundle.getId(), bundle.getName(), produtosStr};
            tableModel.addRow(row);
        }

        bundlesTable.setModel(tableModel);
    }

    private void backButtonPerformed(ActionEvent e) {
        if (previousWindow == null) {
            new MainWindow().setVisible(true);
        } else {
            previousWindow.setVisible(true);
        }
        dispose();
    }

    private void addBundleButtonPerformed(ActionEvent e) {
        new BundleCreateBarProductsSelectWindow(this).setVisible(true);
        dispose();
    }

    private void removeBundleButtonPerformed(ActionEvent e) {
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

    private void editBundleButtonPerformed(ActionEvent e) {
        // Nota: atualmente reabre a criação de bundle, mas não carrega os dados existentes.
        // Para editar bundles reais, seria necessário passar os dados do bundle selecionado.
        JOptionPane.showMessageDialog(this, "Funcionalidade de edição ainda não implementada.");
    }

}
