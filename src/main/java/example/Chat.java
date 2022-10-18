package example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(value = "/homepage/chat/room")
public class Chat extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionCreatedListener.registeredRooms.add(req.getParameter("id"));

        List<HttpSession> sessions = SessionCreatedListener.getSessionsList();
        List<Message> sessionHistory = new LinkedList<>();
        for (HttpSession s : sessions) {
            sessionHistory.addAll(SessionAttributesChangedListener.getSessionHistory(s));
        }

        String roomId = req.getParameter("id");
        Set<String> roomIds = SessionCreatedListener.registeredRooms;
        for (String id : roomIds) {
            if (roomId.equals(id)) {
                List<String> filtered =  sessionHistory.stream()
                        .filter(i -> Objects.equals(i.getID(), roomId))
                        .map(Message::getValue).collect(Collectors.toList());


                req.setAttribute("messageHistory", filtered);
                req.getRequestDispatcher("/WEB-INF/jsp/chat.jsp").forward(req, resp);
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String roomId = req.getParameter("id");

        String message = req.getParameter("message");
        if (!Objects.equals(message, "")) {
            session.setAttribute("message", new Message(roomId, req.getParameter("message")));
        }

        resp.sendRedirect("/homepage/chat/room?id=" + roomId);
    }
}
