import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class SessionEditWindow extends JFrame{
    private JFormattedTextField formattedTextDate;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JComboBox comboBoxFilme;
    private JComboBox comboBoxSala;

    public SessionEditWindow(Session session) throws HeadlessException {
        super("Edit Session");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        AppData appData = AppData.getInstance();
        int index = appData.getSessionList().indexOf(session);
        comboBoxFilme.setSelectedItem(session.getMovie());
        for(Movie movie: AppData.getInstance().getMovieList()){
            comboBoxFilme.addItem(movie.getName());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        formattedTextDate.setText(dateFormat.format(session.getData()));


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
