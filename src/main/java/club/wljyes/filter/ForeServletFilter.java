package club.wljyes.filter;

import club.wljyes.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForeServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        String contextPath = req.getServletContext().getContextPath();
        uri = StringUtil.remove(uri, contextPath);
        if (uri.startsWith("/method")) {
            String servletPath = "/" + StringUtil.subStringBetween(uri, "_", "_") + "Servlet";
            String method = StringUtil.subStringAfterLast(uri, "_");
            req.setAttribute("method", method);
            req.getRequestDispatcher(servletPath).forward(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }
}
