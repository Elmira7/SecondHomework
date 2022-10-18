package example;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.*;

import static java.util.Objects.nonNull;

@WebListener
public class SessionAttributesChangedListener implements HttpSessionAttributeListener {

    private static final Map<String, List<Message>> SESSION_HISTORY = new HashMap<>();

    public static List<Message> getSessionHistory(HttpSession session) {
        List<Message> history = SESSION_HISTORY.get(session.getId());
        if (nonNull(history)) {
            return new LinkedList<>(history);
        }
        return Collections.emptyList();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        Message message = (Message) event.getValue();
        putValueToHistory(event.getSession().getId(), message);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        Message message = (Message) event.getValue();
        putValueToHistory(event.getSession().getId(), message);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        Message message = (Message) formPredefinedHistoryMessage("REPLACED", event.getName(),
                (Message) event.getSession().getAttribute(event.getName()));
        putValueToHistory(event.getSession().getId(), message);
    }

    private void putValueToHistory(String sessionId, Message message) {
        SESSION_HISTORY.computeIfAbsent(sessionId, key -> new LinkedList<>()).add(message);
    }

    private Message formPredefinedHistoryMessage(String operationPrefix, String name, Message value) {
        return value;
    }
}
