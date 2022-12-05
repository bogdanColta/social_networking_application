package utils;

import network.model.UserToken;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class TokenList {
    private static ReentrantLock lock = new ReentrantLock();

    volatile static ArrayList<UserToken> tokens = new ArrayList<>();

    public static void addToken(UserToken token) {
        lock.lock();
        System.out.println("Adding token");
        tokens.add(token);
        lock.unlock();
    }

    public static void removeToken(UserToken token) {
        lock.lock();
        System.out.println("Removing token");
        tokens.remove(token);
        lock.unlock();
    }

    public static boolean isValidToken(String tokenToCheck) {
        try {
            lock.lock();
            for (UserToken token : tokens) {
                if (token.getToken().equals(tokenToCheck)) return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static String getUser(String tokenToCheck) {
        try {
            lock.lock();
            for (UserToken token : tokens) {
                if (token.getToken().equals(tokenToCheck)) return token.getUsername();
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public static void print() {
        lock.lock();
        if (tokens.isEmpty()) {
            System.out.println("Tokens empty");
        } else {
            for (UserToken token : tokens) {
                System.out.println("User: " + token.getUsername() + " Token: " + token.getToken());
            }
        }
        lock.unlock();
    }
}
