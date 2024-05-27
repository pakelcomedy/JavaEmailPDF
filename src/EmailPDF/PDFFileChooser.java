package EmailPDF;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class PDFFileChooser extends JFrame {
    private JLabel titleLabel;
    private JLabel fileLabel;
    private JButton chooseButton;
    private JButton sendButton;

    private File selectedFile;

    public PDFFileChooser() {
        setTitle("PDF File Chooser");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        titleLabel = new JLabel("Select a PDF file:");
        fileLabel = new JLabel("No file selected");
        fileLabel.setForeground(Color.GRAY);

        chooseButton = new JButton("Choose File");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choosePDFFile();
            }
        });

        sendButton = new JButton("Send Email");
        sendButton.setEnabled(false); // Disable send button initially
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendEmail();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titleLabel);
        panel.add(fileLabel);
        panel.add(chooseButton);
        panel.add(sendButton);

        add(panel);

        setVisible(true);
    }

    private void choosePDFFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            fileLabel.setText(selectedFile.getName());
            fileLabel.setForeground(Color.BLACK); // Change color to indicate selection
            sendButton.setEnabled(true); // Enable send button when file selected
        }
    }

    private void sendEmail() {
    String myEmail = "your_email";
    String emailPassword = "your_password";

        String recipient = JOptionPane.showInputDialog(this, "Enter recipient email address:");
        if (recipient == null || recipient.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Recipient email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String subject = "PDF File Attached";
        String body = "Please find the attached PDF file.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, emailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(selectedFile);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(selectedFile.getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while sending email: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PDFFileChooser();
            }
        });
    }
}
