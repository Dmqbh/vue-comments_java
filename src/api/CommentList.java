package api;

import bean.Comment;
import com.google.gson.Gson;
import dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/comment/list")

public class CommentList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDAO dao = new CommentDAO();
        try {
            List<Comment> commentList = dao.getAllComments();
            String json = new Gson().toJson(commentList);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出错了");
        }
    }
}
