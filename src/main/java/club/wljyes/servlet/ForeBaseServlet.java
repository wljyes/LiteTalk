package club.wljyes.servlet;

import club.wljyes.dao.RelationshipDAO;
import club.wljyes.dao.RelationshipDAOImp;
import club.wljyes.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class ForeBaseServlet extends HttpServlet {

    public abstract String add(HttpServletRequest req, HttpServletResponse resp, Page page);
    public abstract String delete(HttpServletRequest req, HttpServletResponse resp, Page page);
    public abstract String update(HttpServletRequest req, HttpServletResponse resp, Page page);
    public abstract String list(HttpServletRequest req, HttpServletResponse resp, Page page);

    protected RelationshipDAO<String> relationshipDAO = new RelationshipDAOImp();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int start = 0;
        int count = 5;
        try {
            start = Integer.parseInt(req.getParameter("page.start"));
        } catch (NumberFormatException ignored) {
        }
        try {
            count = Integer.parseInt(req.getParameter("page.count"));
        } catch (NumberFormatException ignored) {
        }
        Page page = new Page(start, count);


        try {
            String method = (String) req.getAttribute("method");
            Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class, Page.class);
            String redirect = m.invoke(this, req, resp, page).toString();
            if (redirect.startsWith("@")) {
                resp.sendRedirect(redirect.substring(1));
            } else if (redirect.startsWith("%")) {
                req.getRequestDispatcher(redirect).forward(req, resp);
            } else {
                resp.getWriter().print(redirect);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
