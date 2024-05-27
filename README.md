# JavaEmailPDF

The PDF File Chooser is a Java application that allows users to select a PDF file from their computer and send it as an email attachment. This program provides a simple graphical user interface (GUI) for file selection and integrates with the JavaMail API to send emails.

## Features

- **File Selection**: Users can browse their computer's file system to choose a PDF file using a file chooser dialog.
- **Email Sending**: Selected PDF files can be sent as email attachments to specified recipients.
- **Gmail Integration**: The program supports sending emails through Gmail SMTP server. Users need to provide their Gmail email address and password.

## Prerequisites

To run the PDF File Chooser program, you need:

- Java Development Kit (JDK) installed on your computer.
- An internet connection for sending emails through SMTP server.

## Usage

1. **Clone Repository**: Clone this repository to your local machine using Git:

    ```
    git clone https://github.com/your_username/pdf-file-chooser.git
    ```

2. **Compile**: Navigate to the project directory and compile the Java source files:

    ```
    javac PDFFileChooser.java
    ```

3. **Run**: Run the compiled Java program:

    ```
    java PDFFileChooser
    ```

4. **Select PDF File**: Click on the "Choose File" button to open the file chooser dialog. Navigate to the location of the PDF file you want to send and select it.

5. **Send Email**: After selecting the PDF file, click on the "Send Email" button. Enter the recipient's email address in the prompted dialog box and click "OK". The program will attempt to send the selected PDF file as an email attachment.

## Configuration

Before running the program, you need to configure your Gmail email address and password in the source code:

```java
String myEmail = "your_email@gmail.com";
String emailPassword = "your_password";
```

Ensure that your Gmail account settings allow access from less secure apps. If you have two-factor authentication enabled, you may need to generate an app-specific password.

## Dependencies

- JavaMail API: [Download](https://javaee.github.io/javamail/)
- Java Development Kit (JDK): [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.
