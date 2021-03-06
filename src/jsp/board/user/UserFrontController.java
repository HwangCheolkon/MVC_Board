package jsp.board.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.action.BCommand;


@WebServlet("*.doU")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("actionDo");		
		
		String viewPage = null;
		BCommand cmd = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		if(command.equals("/00user/loginOk.doU")) {			
			Service service = new loginOk();
			service.execute(request, response);
			
		} else if(command.equals("/00user/modifyOk.doU")) {
			Service service = new modifyOk();
			service.execute(request, response);
		
		} else if(command.equals("/00user/joinOk.doU")) {
			Service service = new joinOk();
			service.execute(request, response);
		
		} else if(command.equals("/00user/logoutOk.doU")) {
			logoutOk(request, response);
			
		} else if(command.equals("/00user/userList.doU")) {
			cmd = new UserListCommand();
			cmd.execute(request, response);
			viewPage = "userList.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} else if(command.equals("/00user/leaveOk.doU")) {
			Service service = new leaveOk();
			service.execute(request, response);
			
		} 
			
	}
	
	protected void logoutOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("00user/logout.jsp");
	}
}
