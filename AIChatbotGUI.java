import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

// ===== Step 1: ChatbotBrain -> saari "intelligence" yaha hai =====
class ChatbotBrain {

    // Knowledge base -> keywords (pattern) -> response
    // Ye "training data" hai jisse bot FAQs ka jawab deta hai
    private final LinkedHashMap<List<String>, String> knowledgeBase = new LinkedHashMap<>();

    ChatbotBrain() {
        trainBot();
    }

    // ===== Step 2: Training -> FAQs aur unke responses define karna =====
    void trainBot() {
        addRule(new String[]{"hello", "hi", "hey"},
                "Hello! Main aapka chatbot hoon. Aap mujhse kuch bhi pooch sakte hain.");

        addRule(new String[]{"name", "your name"},
                "Mera naam JavaBot hai! Main ek rule-based chatbot hoon.");

        addRule(new String[]{"how are you", "kaise ho"},
                "Main bilkul theek hoon, dhanyavaad! Aap kaise hain?");

        addRule(new String[]{"time"},
                "Mujhe abhi real-time clock access nahi hai, lekin aap apne system clock check kar sakte hain.");

        addRule(new String[]{"help", "what can you do"},
                "Main FAQs ka jawab de sakta hoon jaise: greetings, mera naam, Java ke baare me, aur bahut kuch!");

        addRule(new String[]{"java"},
                "Java ek object-oriented programming language hai, jo 1995 me Sun Microsystems ne banaya tha.");

        addRule(new String[]{"who made you", "creator", "developer"},
                "Mujhe ek Java developer ne banaya hai OOP concepts use karke!");

        addRule(new String[]{"bye", "goodbye", "exit"},
                "Alvida! Phir milte hain. Have a great day!");

        addRule(new String[]{"thank you", "thanks"},
                "Aapka swagat hai! Koi aur sawaal ho toh pooch sakte hain.");

        addRule(new String[]{"weather"},
                "Maaf kijiye, mujhe real-time weather data access nahi hai abhi. Weather app check kar lijiye!");
    }

    void addRule(String[] keywords, String response) {
        knowledgeBase.put(Arrays.asList(keywords), response);
    }

    // ===== Step 3: NLP Preprocessing -> text ko clean karna =====
    private String preprocess(String input) {
        // Lowercase karna aur punctuation hatana - basic NLP normalization
        return input.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").trim();
    }

    // ===== Step 4: Matching Logic -> best response dhundhna =====
    String getResponse(String userInput) {
        String cleanInput = preprocess(userInput);

        if (cleanInput.isEmpty()) {
            return "Kuch toh type kijiye!";
        }

        String bestResponse = null;
        int maxMatches = 0;

        // Har rule ke keywords ko user input se compare karte hain
        for (Map.Entry<List<String>, String> entry : knowledgeBase.entrySet()) {
            int matches = 0;

            for (String keyword : entry.getKey()) {
                if (cleanInput.contains(keyword)) {
                    matches++;
                }
            }

            // Jitne zyada keywords match honge, utna accha response
            if (matches > maxMatches) {
                maxMatches = matches;
                bestResponse = entry.getValue();
            }
        }

        // Agar koi match nahi mila -> default fallback response
        if (bestResponse == null) {
            return "Mujhe samajh nahi aaya. Kya aap 'help' type karke dekhenge ki main kya kar sakta hoon?";
        }

        return bestResponse;
    }
}

// ===== Step 5: GUI class -> Swing se chat window banana =====
public class AIChatbotGUI extends JFrame {

    private final ChatbotBrain brain = new ChatbotBrain();
    private final JTextArea chatArea;
    private final JTextField inputField;

    AIChatbotGUI() {
        // Window ki basic settings
        setTitle("Java AI Chatbot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // screen ke center me open hoga

        // Chat history dikhane wala area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        chatArea.append("JavaBot: Hello! Kuch bhi pooch sakte hain. (type 'bye' to exit)\n\n");

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input field jaha user type karega
        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Send button
        JButton sendButton = new JButton("Send");

        // Jab button click ho ya Enter dabaye -> message process ho
        sendButton.addActionListener(this::handleSend);
        inputField.addActionListener(this::handleSend);

        // Layout set karna
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ===== Step 6: User ka message handle karna aur bot ka reply dikhana =====
    private void handleSend(ActionEvent e) {
        String userText = inputField.getText().trim();

        if (userText.isEmpty()) {
            return;
        }

        chatArea.append("You: " + userText + "\n");

        String response = brain.getResponse(userText);
        chatArea.append("JavaBot: " + response + "\n\n");

        // Auto-scroll neeche jaye latest message ke saath
        chatArea.setCaretPosition(chatArea.getDocument().getLength());

        inputField.setText(""); // input field khali kar do

        if (userText.toLowerCase().contains("bye") || userText.toLowerCase().contains("exit")) {
            inputField.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        // GUI hamesha Event Dispatch Thread pe chalani chahiye (Swing best practice)
        SwingUtilities.invokeLater(() -> {
            AIChatbotGUI chatbot = new AIChatbotGUI();
            chatbot.setVisible(true);
        });
    }
}