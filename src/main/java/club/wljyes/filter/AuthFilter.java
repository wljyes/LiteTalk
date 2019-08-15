package club.wljyes.filter;

import club.wljyes.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        String[] whiteList = {"login.jsp", "login", "register.jsp", "register", ".css", ".js"};
        for (String whiteUrl : whiteList) {
            if (uri.endsWith(whiteUrl)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (null == user) {
            ((HttpServletResponse) servletResponse).sendRedirect("login.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
