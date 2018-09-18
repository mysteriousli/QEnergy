//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.tool.FilterTool;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
        filterName = "AccessControlFilter"
)
public class AccessControlFilter implements Filter {
    String accessControl = null;

    public AccessControlFilter() {
    }

    public void destroy() {
        this.accessControl = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if (this.accessControl != null) {
            //设置前后端分离跨域问题
            httpServletResponse.addHeader("P3P", "CP=CAO PSA OUR");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,Cookies");
            httpServletResponse.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
            //Expires:过时期限值
            httpServletResponse.setDateHeader("Expires", new Date().getTime() + 20000);
            //Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
            httpServletResponse.addHeader("Cache-Control", "public");
            //设置前后端分离跨域问题
            if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
                httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                httpServletResponse.addHeader("Access-Control-Max-Age", "7200");
                httpServletResponse.setStatus(200);
                httpServletResponse.getWriter().write("OK");
                return;
            }

            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.accessControl = filterConfig.getInitParameter("accessControl");
    }
}
