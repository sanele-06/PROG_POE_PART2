package com.mycompany.prog_poe_part1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    public void testCheckUserNameCorrect() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }
    @Test
    public void testCheckUserNameIncorrect() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
    @Test
    public void testCheckPasswordCorrect() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    @Test
    public void testCheckPasswordIncorrect() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }
    @Test
    public void testCheckCellCorrect() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+2783899976"));
    }
    @Test
    public void testCheckCellIncorrect() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966511"));
    }
    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login();
        String result = login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+2783899976");
        assertEquals("User registered successfully.", result);
    }
    @Test
    public void testLoginAndReturnMessage() {
        Login login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+2783899976");
        boolean logged = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String msg = login.returnLoginStatus(logged);
        assertTrue(msg.contains("Welcome"));
    }
}