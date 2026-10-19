package com.mycompany.prog_poe_part1;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cell;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[^a-zA-Z0-9].*");
    }

    public boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27\\d{8,9}$");
    }

    public String registerUser(String fn, String ln, String un, String pw, String cell) {
        if (!checkUserName(un)) return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        if (!checkPasswordComplexity(pw)) return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        if (!checkCellPhoneNumber(cell)) return "Cell phone number incorrectly formatted or does not contain international code.";
        this.firstName = fn; this.lastName = ln; this.username = un; this.password = pw; this.cell = cell;
        return "User registered successfully.";
    }

    public boolean loginUser(String un, String pw) {
        return un.equals(this.username) && pw.equals(this.password);
    }

    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        else return "Username or password incorrect, please try again.";
    }
}