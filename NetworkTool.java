import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class NetworkTool extends JFrame {
    private JTextArea logArea;
    private JTextField inputField;
    private PrintWriter out;

    public NetworkTool() {
        setTitle("OpenStack Controller (Server)");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);

        inputField = new JTextField(20);
        JButton btnSend = new JButton("Send to Node");
        
        btnSend.addActionListener(e -> {
            if (out != null) {
                out.println(inputField.getText());
                logArea.append("YOU: " + inputField.getText() + "\n");
                inputField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(btnSend);

        add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        new Thread(this::setupServer).start();
    }

    private void setupServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            logArea.append(">>> System Online. Waiting for Node...\n");
            Socket socket = serverSocket.accept();
            logArea.append(">>> NODE CONNECTED!\n");
            
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                logArea.append("NODE SAYS: " + msg + "\n");
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetworkTool().setVisible(true));
    }
}