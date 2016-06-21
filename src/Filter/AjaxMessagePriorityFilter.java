package Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import Entity.User;

/* Ajax消息: 管理员登陆Filter
 * */

public class AjaxMessagePriorityFilter implements Filter{
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("AjaxMessagePriorityFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession hSession = req.getSession();
		if (hSession.getAttribute("user") == null){
			//没有登录
			System.out.println("没有登录");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("status", "Error");
			json.put("message", "请先登录！");
			out.write(json.toString());
			return;
		}
		//已经登录
		System.out.println("已经登录");
		User user = (User)hSession.getAttribute("user");
		if (user.getPriority() == 0){
			System.out.println("没有权限");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("status", "Error");
			json.put("message", "请使用管理员账号登陆！");
			out.write(json.toString());
			return;
		}
		System.out.println("有管理员权限");
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
