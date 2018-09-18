//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.tool.FilterTool;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(
        filterName = "EncodingFilter"
)
public class EncodingFilter implements Filter {
    String encoding = null;

    public EncodingFilter() {
    }

    public void destroy() {
        this.encoding = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将数据的编码方式进行过滤并改编码方式
        if (this.encoding != null) {
            request.setCharacterEncoding(this.encoding);
            response.setContentType("application/json;charset=" + this.encoding);
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }
}
