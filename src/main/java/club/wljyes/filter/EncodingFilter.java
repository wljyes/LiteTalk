package club.wljyes.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsq = (HttpServletRequest) servletRequest;
        HttpServletResponse hsp = (HttpServletResponse) servletResponse;
        hsq.setCharacterEncoding("utf-8");
        hsp.setCharacterEncoding("utf-8");

        filterChain.doFilter(hsq, hsp);
    }

    @Override
    public void destroy() {

    }
}
