package club.wljyes.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessFilter implements Filter {
    private BufferedWriter out;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("../log/access.txt"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsq = (HttpServletRequest) servletRequest;
        HttpServletResponse hsp = (HttpServletResponse) servletResponse;

        String ip = hsq.getRemoteAddr();
        String url = hsq.getRequestURL().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        out.write(String.format("%s %s 访问了 %s%n", sdf.format(date), ip, url));
        out.flush();
        filterChain.doFilter(hsq, hsp);
    }

    @Override
    public void destroy() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
