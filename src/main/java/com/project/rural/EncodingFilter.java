package com.project.rural;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 각 페이지 별 인코딩 자동 필터링 클래스
 * 
 * @author 김영혁
 */
public class EncodingFilter implements Filter {

    String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);

    }

	@Override
	public void destroy() {
		
	}

}