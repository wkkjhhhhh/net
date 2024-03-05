package Main;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Net extends JFrame {
    private final JTextArea jTextArea;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;

    public Net() {

        setTitle("첫번째");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new FlowLayout());
        JTextField jTextField1 = new JTextField(10);
        //jTextField1.setText("192.168.0.100");
        JTextField jTextField2 = new JTextField(10);
        //jTextField2.setText("6000");
        JButton jButton1 = new JButton("접속");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (socket == null || socket.isClosed()) { // 소켓이 없거나 닫혀있는 경우에만 실행
                    try {
                        socket = new Socket("192.168.0.100", 6000);

                /*String connectMessage = jTextField1.getText();
                sendMessage(connectMessage); // 서버에 접속 후에 텍스트 필드 값 전송*/

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JButton jButton2 = new JButton("해제");
        p1.add(jTextField1);
        p1.add(jTextField2);
        p1.add(jButton1);
        p1.add(jButton2);
        add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        jTextArea = new JTextArea(30, 50);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        p2.add(jScrollPane);
        add(p2, BorderLayout.CENTER);

        JPanel p3 = new JPanel();
        JTextField jTextField = new JTextField(30);
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton jButton = new JButton("입력");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String message = jTextField.getText();
                    sendMessage(message); // 입력 버튼 클릭 시 서버로 메시지 전송
                    jTextField.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        p3.add(jTextField);
        p3.add(jButton);
        add(p3, BorderLayout.SOUTH);

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void sendMessage(String message) {
        try {
            if (out != null) {
                out.write(message + "\n");
                out.flush();
                jTextArea.append("나: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Net net = new Net();
        //net.startClient();
    }

   /* private void startClient() {
        new Thread(() -> {
            try {
                // 서버로부터 메시지를 수신하여 JTextArea에 표시
                String message;
                while ((message = in.readLine()) != null) {
                    jTextArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }*/
}
