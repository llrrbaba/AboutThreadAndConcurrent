package cn.rocker.concurrency;

import cn.rocker.concurrency.example.threadLocal.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rocker
 * @date 2019/01/10 14:37
 * @since V1.0
 */
public class HttpFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        logger.info("<HttpFilter> 请求线程id：{}，请求路径：{}", Thread.currentThread().getId(), request.getServletPath());

        //将请求参数或者需要放入到 ThreadLocal 中的参数进行set
        RequestHolder.add(Thread.currentThread().getId());

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
