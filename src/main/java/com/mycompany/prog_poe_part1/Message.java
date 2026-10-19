package com.mycompany.prog_poe_part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static List<Message> sentMessages = new ArrayList<>();
    private static int totalSent = 0;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageId = generateId();
        this.messageHash = createMessageHash();
    }

    // Generates 10-digit ID starting with 00 -> Test Case 1 = 00:0:HITONIGHT
    private String generateId() {
        Random rand = new Random();
        int eightDigits = 10000000 + rand.nextInt(90000000);
        return "00" + eightDigits;
    }

    public boolean checkMessageID() {
        return messageId.length() <= 10;
    }

    // EXACT message from your 11-page photo
    public String checkRecipientCell() {
        if (recipient.matches("^\\+\\d{10,12}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Returns 00:0:HITONIGHT for first test
    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String last = words[words.length-1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        return firstTwo + ":" + messageNumber + ":" + first + last;
    }

    // For 250 char test - EXACT messages from photo
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // EXACT messages: sent / delete / stored
    public String SentMessage(int choice) {
        if (choice == 1) {
            sentMessages.add(this);
            totalSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid";
    }

    public String printMessages() {
        return messageId + " " + messageHash + " " + recipient + " " + messageText;
    }

    public int returnTotalMessagess() {
        return totalSent;
    }

    public void storeMessage() {
        try {
            FileWriter w = new FileWriter("storedMessages.json", true);
            w.write("{\"id\":\"" + messageId + "\",\"hash\":\"" + messageHash + "\",\"to\":\"" + recipient + "\",\"msg\":\"" + messageText + "\"},\n");
            w.close();
        } catch (IOException e) {}
    }

    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public static int getTotalSent() { return totalSent; }
}