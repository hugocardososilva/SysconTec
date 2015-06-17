package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.TestCad;
import dao.DAOObject;
import dao.DAOUser;
import model.Usuario;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends AbstractFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		DAOUser dao= new DAOUser();
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpSession session= req.getSession();
		
		
		
			if(session.isNew()){
				doLogin(request, response, req);
				return;
			}
			Usuario user= (Usuario) session.getAttribute("user");
			
			if(user == null){
				doLogin(request, response, req);
				return;
			}
		
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
}
