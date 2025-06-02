import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

        Date date = new Date(year-1900,month-1,day,hour,min);

        int roomIndex = comboBoxRoom.getSelectedIndex();
        Room room = AppData.getInstance().getRoomList().get(roomIndex);

        if(!isDateValid(date, room)){
            return;
        }

        int sessionId = AppData.getInstance().getSessionList().getLast().getID() + 1;

        AppData.getInstance().addSession(new Session(sessionId,date,movie,room));

        new SessionManagerWindow().setVisible(true);
        dispose();
    }

    private boolean isDateValid(Date date, Room room){
        long currentTime = System.currentTimeMillis();
        if((date.getTime()-currentTime)<0){
            new ErrorWindow("Não pode inserir uma data anterior à data de hoje").setVisible(true);
            return false;
        }

        for(Session session : AppData.getInstance().getSessionList()){
            if(session.getRoom() == room && !isDateDifferenceValid(session, date)){
                new ErrorWindow("A data inserida coincide com outra sessão na mesma sala").setVisible(true);
                return false;
            }
        }

        return true;
    }

    private boolean isDateDifferenceValid(Session session, Date newDate){
        long diffMillis = Math.abs(session.getDate().getTime()-newDate.getTime());
        long diff = TimeUnit.MINUTES.convert(diffMillis, TimeUnit.MILLISECONDS);

        int movieDuration = session.getMovie().getDuration();

        if(diff < movieDuration){
            return false;
        }

        return true;
    }

    private void cancelButtonPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        dispose();
    }
}