import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SessionEditWindow extends JFrame{
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel mainPanel;
    private JComboBox comboBoxMovie;
    private JComboBox comboBoxRoom;
    private JTextField textFieldDay;
    private JTextField textFieldMonth;
    private JTextField textFieldYear;
    private JTextField textFieldHour;
    private JTextField textFieldMinute;
    private final AppData appData;
    private final Session session;

    public SessionEditWindow(Session session) throws HeadlessException {
        super("Edit Session");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        this.session = session;
        appData = AppData.getInstance();

        // preencher a comboBoxMovie com todos os filmes disponíveis
        for(Movie movie: AppData.getInstance().getMovieList()){
            comboBoxMovie.addItem(movie.getName());
        }
        int movieIndex = appData.getMovieList().indexOf(session.getMovie());
        comboBoxMovie.setSelectedIndex(movieIndex); // deixar selecionado o filme que está associado à sessão

        Date sessionDate = session.getDate();
        int day = sessionDate.getDate(), month = sessionDate.getMonth()+1, year = sessionDate.getYear()+1900,
                hour = sessionDate.getHours(), minute = sessionDate.getMinutes();

        textFieldDay.setText(Integer.toString(day));
        textFieldMonth.setText(Integer.toString(month));
        textFieldYear.setText(Integer.toString(year));
        textFieldHour.setText(Integer.toString(hour));
        textFieldMinute.setText(Integer.toString(minute));

        for(Room room: AppData.getInstance().getRoomList()){
            comboBoxRoom.addItem(room.getRoomNumber());
        }
        int roomIndex = appData.getRoomList().indexOf(session.getRoom());
        comboBoxRoom.setSelectedIndex(roomIndex); // deixar selecionada a sala que está associada à sessão

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

        if(!isDateValid(movie.getDuration(), date, room, session)){
            return;
        }

        session.updateSession(date,movie,room);

        new SessionManagerWindow().setVisible(true);
        dispose();
    }

    private boolean isDateValid(int duration, Date date, Room room, Session currentSession){
        long currentTime = System.currentTimeMillis();
        if((date.getTime()-currentTime)<0){
            new ErrorWindow("Não pode inserir uma data anterior à data de hoje").setVisible(true);
            return false;
        }

        for(Session session : AppData.getInstance().getSessionList()){
            if(session.equals(currentSession)){
                continue;
            }
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

        return diff >= movieDuration;
    }

    private void cancelButtonPerformed(ActionEvent e){
        new SessionManagerWindow().setVisible(true);
        dispose();
    }
}
