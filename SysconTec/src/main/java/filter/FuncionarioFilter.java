package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import model.Funcionario;
import model.Usuario;

/**
 * Servlet Filter implementation class FuncionarioFilter
 */
//@WebFilter("/protected/funcionario/*")
public class FuncionarioFilter extends AbstractFilter implements Filter {

    /**
     * Default constructor. 
     */
    public FuncionarioFilter() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		Usuario user= (Usuario) req.getSession(true).getAttribute("user");
		if(user==null){
			accessDenied(request, response, req);
			return;
		}
		if(user.getClass()!= Funcionario.class){
			System.out.println("nao funcionario");
			accessDenied(request, response, req);
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
