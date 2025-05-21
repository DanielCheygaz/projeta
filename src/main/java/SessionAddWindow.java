import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class SessionAddWindow extends JFrame{
    private JComboBox comboBoxMovie;
    private JComboBox comboBoxRoom;
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
            comboBoxMovie.addItem(movie.getName());
        }

        for(Room room: AppData.getInstance().getRoomList()){
            comboBoxRoom.addItem(room.getRoomNumber());
        }

        this.saveButton.addActionListener(this::saveButtonPerformed);
        this.cancelButton.addActionListener(this::cancelButtonPerformed);
    }

    private void saveButtonPerformed(ActionEvent e){
        int movieIndex = comboBoxMovie.getSelectedIndex();
        Movie movie = AppData.getInstance().getMovieList().get(movieIndex);
        int day,month,year,hour,min;
        try{
            day = Integer.valueOf(textFieldDay.getText());
            month = Integer.valueOf(textFieldMonth.getText());
            year = Integer.valueOf(textFieldYear.getText());
            hour = Integer.valueOf(textFieldHour.getText());
            min = Integer.valueOf(textFieldMinute.getText());
        }catch (NumberFormatException ex){
            new ErrorWindow("Apenas pode inserir números nas datas").setVisible(true);
            return;
        }

        // TODO: checkar se a data inserida é válida
        Date date = new Date(year-1900,month,day,hour,min);

        int roomIndex = comboBoxRoom.getSelectedIndex();
        Room room = AppData.getInstance().getRoomList().get(roomIndex);

        AppData.getInstance().addSession(new Session(123,date,movie,room));

        new SessionManagerWindow().setVisible(true);
        dispose();
    }

    private void cancelButtonPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        dispose();
    }
}