package com.group15.voting;

public class Userdata {
    private static String username = null;
    private static int status = 0;
    private static boolean isLoggedIn = false;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Userdata.username = username;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Userdata.status = status;
    }

    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        Userdata.isLoggedIn = isLoggedIn;
    }
}
