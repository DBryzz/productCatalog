package webapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class loginRequiredFilter
 */

@WebFilter(urlPatterns = "*.se")
public class loginRequiredFilter implements Filter {

    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) 
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		System.out.println(request.getRequestURI());
		if(request.getSession().getAttribute("userName") != null) {
		chain.doFilter(servletRequest, servletResponse);
		} else {
			servletRequest.getRequestDispatcher("/login-page.html?choice=1").forward(servletRequest, servletResponse);
		}
		
	}

	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	
}
