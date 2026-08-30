# 🤖 AI Chatbot (Java + Swing GUI)

A Java-based chatbot with a graphical chat interface, using rule-based NLP techniques to respond to frequently asked questions. Built as part of my **Java Programming Internship at CodeAlpha**.

## 📌 About

This chatbot processes user input using basic NLP preprocessing (text normalization, keyword extraction) and matches it against a trained knowledge base to generate the most relevant response — all displayed in a real-time Swing GUI chat window.

## ✨ Features

- 🖥️ Java Swing GUI for real-time chat interaction
- 🧹 NLP preprocessing — lowercase conversion & punctuation removal
- 🎯 Keyword-based rule matching to find the best response
- 📚 Trained knowledge base covering common FAQs
- 🔄 Fallback response for unrecognized inputs

## 🛠️ Tech Stack

- **Language:** Java
- **Concepts Used:** OOP, Java Swing, Collections (`HashMap`), Basic NLP (text normalization, keyword matching)

## ▶️ How to Run

```bash
javac AIChatbotGUI.java
java AIChatbotGUI
```

A GUI chat window will open — type a message and hit Send or Enter.

## 💬 Try Asking

- "Hello"
- "What is your name?"
- "Tell me about Java"
- "help"
- "bye"

## 🚀 Future Improvements

- Integrate real NLP libraries (Stanford CoreNLP / OpenNLP)
- Connect to a trained ML model for intent detection
- Expand knowledge base with more FAQs
- Add voice input/output using Java Speech API

## 🙏 Acknowledgment

Built during my **Java Programming Internship at [CodeAlpha](https://www.codealpha.tech/)**.

## 📄 License

Open-source and free to use for learning purposes.
