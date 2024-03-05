import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Main extends JFrame{

    public Main() {   ///생성자
        setTitle("첫번째 프레임");

        Container c = getContentPane();

        c.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JLabel jLabel = new JLabel("수식입력");
        JTextField jTextField = new JTextField(20);
        p1.add(jLabel);
        p1.add(jTextField);
        add(p1, BorderLayout.NORTH);
        p1.setBackground(Color.GRAY);

        JPanel p2 = new JPanel(new GridLayout(4, 4));
        JButton jButton = null;
        for (int i = 0; i < 10; i++) {
            jButton = new JButton(String.valueOf(i));
            p2.add(jButton);
        }
        JButton j1 = new JButton("CE");
        JButton j2 = new JButton("계산");
        j2.addActionListener(new MyActionListener());
        JButton j3 = new JButton("+");
        j3.setBackground(Color.cyan);
        JButton j4 = new JButton("-");
        j4.setBackground(Color.cyan);
        JButton j5 = new JButton("x");
        j5.setBackground(Color.cyan);
        JButton j6 = new JButton("/");
        j6.setBackground(Color.cyan);
        p2.add(j1);
        p2.add(j2);
        p2.add(j3);
        p2.add(j4);
        p2.add(j5);
        p2.add(j6);
        add(p2, BorderLayout.CENTER);

        JPanel p3 = new JPanel(new FlowLayout());
        JLabel jLabel1 = new JLabel("결과");
        JTextField jTextField1 = new JTextField(20);
        p3.add(jLabel1);
        p3.add(jTextField1);
        add(p3, BorderLayout.SOUTH);
        p3.setBackground(Color.yellow);


        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main mf = new Main();
    }

    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("계산"))
                b.setText("Action");
            else
                b.setText("Action");
            }
        }
    }
