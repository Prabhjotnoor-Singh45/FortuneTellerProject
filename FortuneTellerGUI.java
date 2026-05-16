import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The FortuneTellerGUI class provides a graphical user interface for interacting 
 * with the Fortune Teller backend logic. It ensures exception handling on all user inputs.
 * * @author Prabhjotnoor Singh
 * @version 1.0
 * @date May 16, 2026
 */
public class FortuneTellerGUI extends JFrame {

    /** Reference to the application backend logic. */
    private FortuneTellerBackend backend;

    /** UI Components */
    private JTextArea displayArea;
    private JTextField inputField;
    private JTextField indexField;

    /**
     * Constructs the GUI window, sets up the layout, and connects action listeners.
     */
    public FortuneTellerGUI() {
        backend = new FortuneTellerBackend();
        setupGUI();
    }

    /**
     * Initializes and positions all Swing UI elements inside the frame.
     */
    private void setupGUI() {
        setTitle("Mystic Fortune Teller");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Header Section ---
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel("🔮 Mystic Fortune Teller 🔮", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        JLabel instructionsLabel = new JLabel("Generate your destiny, view the catalog, or alter fate by adding/removing fortunes.", JLabel.CENTER);
        instructionsLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        headerPanel.add(titleLabel);
        headerPanel.add(instructionsLabel);
        add(headerPanel, BorderLayout.NORTH);

        // --- Display Area (Center) ---
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        displayArea.setText(backend.getAllFortunesFormatted());
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // --- Control Panel (South) ---
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        // Row 1: Read Actions
        JPanel row1 = new JPanel();
        JButton btnRandom = new JButton("🔮 Tell My Fortune!");
        JButton btnViewAll = new JButton("📋 Show All Fortunes");
        row1.add(btnRandom);
        row1.add(btnViewAll);

        // Row 2: Add Action
        JPanel row2 = new JPanel();
        row2.add(new JLabel("New Fortune:"));
        inputField = new JTextField(25);
        JButton btnAdd = new JButton("➕ Add Fortune");
        row2.add(inputField);
        row2.add(btnAdd);

        // Row 3: Remove Action
        JPanel row3 = new JPanel();
        row3.add(new JLabel("Fortune Index to Remove:"));
        indexField = new JTextField(5);
        JButton btnRemove = new JButton("❌ Remove Fortune");
        row3.add(indexField);
        row3.add(btnRemove);

        controlPanel.add(row1);
        controlPanel.add(row2);
        controlPanel.add(row3);
        add(controlPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---

        // Random Fortune Action
        btnRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fortune = backend.getRandomFortune();
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, fortune, "Your Destiny", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View All Action
        btnViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(backend.getAllFortunesFormatted());
            }
        });

        // Add Fortune Action
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = inputField.getText();
                    backend.addFortune(text);
                    displayArea.setText(backend.getAllFortunesFormatted());
                    inputField.setText(""); // Reset field on success
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, "Fortune added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Remove Fortune Action
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idx = Integer.parseInt(indexField.getText().trim());
                    backend.removeFortune(idx);
                    displayArea.setText(backend.getAllFortunesFormatted());
                    indexField.setText(""); // Reset field on success
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, "Fortune removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, "Please enter a valid numeric integer code.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(FortuneTellerGUI.this, ex.getMessage(), "Index Out of Bounds", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Execution main entry point to start up the app window frame.
     * * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FortuneTellerGUI().setVisible(true);
            }
        });
    }
}
