package com.mycompany.prog_poe_part1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLength_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        String longText = "a".repeat(265);
        Message msg = new Message(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 15; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipient_Success() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipient_Failure() {
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash_TestCase1() {
        Message msg1 = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertEquals("00:0:HITONIGHT", msg1.createMessageHash());
    }

    @Test
    public void testMessageIdCreated() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageSent_Options() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}