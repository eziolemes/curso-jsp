package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import connection.SingleConnection;

public class Filter implements javax.servlet.Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SingleConnection.getConnection();
	}

}
