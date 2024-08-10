package com.pranay.happ.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ApplicationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//String token = request.getParameter("Authorization");
				String token = request.getHeader("Authorization");
				String jwtToken = token.substring(7);
				System.out.println(jwtToken);
				System.out.println("Filter Called.");
				filterChain.doFilter(request, response);
				System.out.println("Filter complete.");
	}
}