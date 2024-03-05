import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

class TimerThread extends Thread {
    private JLabel timerLable;
    public TimerThread(JLabel timerLable){
        this.timerLable = timerLable;
    }
    @Override
    public void run() {
        int n=0;
        while(true) {
            Date today = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

            timerLable.setText(simpleDateFormat.format(today));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
public class  ThreadTimerEx extends JFrame {
    public ThreadTimerEx() {

        setTitle("Tread");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,80));
        container.add(timerLabel);

        TimerThread th = new TimerThread(timerLabel);

        setSize(300, 170);
        setVisible(true);

        th.start();

    }
    public static void main(String[] args) {
        new ThreadTimerEx();
    }
}

