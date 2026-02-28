import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class NetworkNode extends JFrame {
    private JTextArea statusArea;
    private JTextField inputField;
    private PrintWriter out;

    public NetworkNode() {
        setTitle("OpenStack Compute Node (Client)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setBackground(new Color(30, 30, 30));
        statusArea.setForeground(Color.CYAN);

        inputField = new JTextField(20);
        JButton btnSend = new JButton("Send to Controller");

        btnSend.addActionListener(e -> {
            if (out != null) {
                out.println(inputField.getText());
                statusArea.append("YOU: " + inputField.getText() + "\n");
                inputField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(btnSend);

        add(new JScrollPane(statusArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        new Thread(this::connectToController).start();
    }

    private void connectToController() {
        try {
            Socket socket = new Socket("localhost", 5000);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                statusArea.append("CONTROLLER SAYS: " + msg + "\n");
            }
        } catch (IOException e) { statusArea.append("Connection Failed\n"); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetworkNode().setVisible(true));
    }
}