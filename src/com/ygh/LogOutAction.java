package com.ygh;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* LogOutAction 登出操作
 * */

public class LogOutAction extends ActionSupport{
	public String execute() throws Exception{	
		ActionContext.getContext().getSession().clear();;
		return SUCCESS;
	}
}
