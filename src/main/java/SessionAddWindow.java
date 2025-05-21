import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SessionAddWindow extends JFrame{
    private JComboBox comboBoxFilme;
    private JComboBox comboBoxSala;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JTextField textFieldDay;
    private JTextField textFieldMonth;
    private JTextField textFieldYear;
    private JTextField textFieldHour;
    private JTextField textFieldMinute;

    public SessionAddWindow() throws HeadlessException {
        super("Adicionar Sessão");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        for(Movie movie: AppData.getInstance().getMovieList()){
            comboBoxFilme.addItem(movie.getName());
        }

        for(Room room: AppData.getInstance().getRoomList()){
            comboBoxSala.addItem(room.getRoomNumber());
        }

        this.cancelButton.addActionListener(this::cancelButtonPerformed);
    }

    private void cancelButtonPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        dispose();
    }
}