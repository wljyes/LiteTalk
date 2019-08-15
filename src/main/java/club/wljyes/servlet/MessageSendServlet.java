package club.wljyes.servlet;

import club.wljyes.bean.Message;
import club.wljyes.bean.User;
import club.wljyes.chat.ChatRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageSendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Message msg = gson.fromJson(req.getParameter("message"), Message.class);
        System.out.println(req.getParameter("message"));
        ChatRepository.sendMessage(msg);
    }
}
