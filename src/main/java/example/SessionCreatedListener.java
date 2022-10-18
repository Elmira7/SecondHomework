package example;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionCreatedListener implements HttpSessionListener {

    private static final AtomicInteger ACTIVE_SESSIONS = new AtomicInteger();
    private static final List<HttpSession> SESSIONS_LIST = new LinkedList<>();

    public static int getTotalActiveSession() {
        return ACTIVE_SESSIONS.get();
    }
    public static List<HttpSession> getSessionsList() { return SESSIONS_LIST; }

    public static Set<String> registeredRooms = new HashSet<>();

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        SESSIONS_LIST.add(event.getSession());
        ACTIVE_SESSIONS.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        ACTIVE_SESSIONS.decrementAndGet();
    }
}