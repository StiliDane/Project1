import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;


public class MainGUI extends JFrame {
    private JTextField idField;
    private JTextField titleField;
    private JTextField mentorField;
    private JTextField dateField;
    private JTextField locationField;
    private JTextField maxField;

    private JTextArea outputArea;

    // there should be a private member variable named `sessions` :
    // private SomethingOrOther sessions;

    // the constructor for the class. This will initialize
    // the class's member variables:
    public MainGUI() {
        // Uncommented: This actually builds and shows the window
        setTitle("Employee Mentorship and Inclusion Manager");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGUI();
        setVisible(true);
    }

    // Create all the display elements in the frame:
    private void createGUI() {
        // first, the input panel contains all the field entry elements:
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8,2,5,5));

        // main screen setup
        getContentPane().setBackground(Color.BLACK);

        // these are all the input fields that will be in the frame:
        idField = new JTextField();
        titleField = new JTextField();
        mentorField = new JTextField();
        dateField = new JTextField();
        locationField = new JTextField();
        maxField = new JTextField();
        inputPanel.add(new JLabel("Session ID"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Title"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Mentor"));
        inputPanel.add(mentorField);
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Location"));
        inputPanel.add(locationField);
        inputPanel.add(new JLabel("Max Participants"));
        inputPanel.add(maxField);
        add(inputPanel, BorderLayout.NORTH);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setOpaque(false);

        // next, the lower half of the window contains an output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Session");
        styleButton(addButton, new Color(46, 204, 113), new Color(19, 154, 76));
        JButton displayButton = new JButton("Display");
        styleButton(displayButton, new Color(52, 152, 219), new Color(21, 108, 165));
        JButton searchButton = new JButton("Search");
        styleButton(searchButton, new Color(155, 89, 182), new Color(122, 48, 153));
        JButton removeButton = new JButton("Remove");
        styleButton(removeButton, new Color(231, 76, 60), new Color(172, 37, 23));
        JButton registerButton = new JButton("Register");
        styleButton(registerButton, new Color(241, 196, 15), new Color(223, 136, 0));
        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, new Color(149, 165, 166), new Color(107, 120, 121));
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setOpaque(false);


        // Button Actions
        addButton.addActionListener(e -> addSession());
        displayButton.addActionListener(e -> displaySessions());
        searchButton.addActionListener(e -> searchSession());
        removeButton.addActionListener(e -> removeSession());
        registerButton.addActionListener(e -> registerParticipant());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void styleButton(JButton button, Color mainColor, Color shadowColor) {
        button.setBackground(mainColor);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setFocusPainted(false);

        button.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Shadow on the bottom
        javax.swing.border.Border unpressedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 4, 1, shadowColor),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        );

        // Shifts button down to simulate pressed
        javax.swing.border.Border pressedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(4, 1, 1, 1, shadowColor),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        );

        button.setBorder(unpressedBorder);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(shadowColor);
                button.setBorder(pressedBorder);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(mainColor);
                button.setBorder(unpressedBorder);
            }
        });
    }

    // set all input fields to empty strings, give focus to the first
    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        mentorField.setText("");
        dateField.setText("");
        locationField.setText("");
        maxField.setText("");
        // Put the cursor back in the first field
        idField.requestFocus();
    }

    // the action of the Add Session button
    private void addSession() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String mentor = mentorField.getText();
            String date = dateField.getText();
            String location = locationField.getText();
            int maxParticipants = Integer.parseInt(maxField.getText());

            // TO DO: construct a session object, insert it into
            // the list of sessions

            outputArea.setText("Session Added Successfully\n");
            // Clear the input fields
            clearFields();
        }
        catch(Exception e) {
            outputArea.setText("Invalid input");
        }
    }

    // display all sessions in the output area
    private void displaySessions() {
        outputArea.setText("");

        // iterate over sessions; display each one
        // to the output window, using the `append`
        // method of the outputArea.

        // between each one, print a separator line,
        // as e.g.

        outputArea.append("\n--------------------\n");
    }

    // search by ID if present, mentor otherwise, display results
    private void searchSession() {
        // Search by ID if the ID field is not empty
        if (!idField.getText().trim().isEmpty()) {
            int id = Integer.parseInt(idField.getText().trim());
            // find session by ID, using a `searchByID` method
            // ... code here ...
            /* if (result != null)
                // display session to the output area...
            else
                outputArea.setText("Session not found.");
             */
        }
        // Otherwise, search by mentor if the Mentor field is not empty
        else if (!mentorField.getText().trim().isEmpty()) {
            String mentor = mentorField.getText().trim();
            // find session by mentor. In this case, the result
            // may be a list of sessions...
            // ... code here ...
            /*
            if (result != null)
                // display all sessions in the list
            else
                outputArea.setText("No session found for mentor: " + mentor);
             */
        }
        // Nothing entered
        else {
            outputArea.setText("Please enter a Session ID or Mentor name.");
        }
    }

    // given an id, remove that session from the list
    private void removeSession() {
        int id = Integer.parseInt(idField.getText());
        // remove the session, print an error to the outputArea
        // if it's not found
        // ... code here ...
    }

    // add one to the count of the specified session.
    // MUTATES participant count of session.
    private void registerParticipant() {
        int id = Integer.parseInt(idField.getText());
        // increment participants field of session,
        // print success or failure message.
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(MainGUI::new);

    }
}
