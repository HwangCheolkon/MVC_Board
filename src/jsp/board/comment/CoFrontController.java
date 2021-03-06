package jsp.board.comment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.action.BCommand;

@WebServlet("*.doC")
public class CoFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CoFrontController() {
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
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}		
		
		if(com.equals("/00board/coWrite.doC")) {
			command = new CommentWrite();
			command.execute(request, response);
			viewPage = "content_view.do";
			
		} else if(com.equals("/00board/coDelete.doC")) {			
			command = new CommentDelete();
			command.execute(request, response);	
			viewPage = "content_view.do";
			
		} else if(com.equals("/00board/coModify.doC")) {
			command = new CommentModify();
			command.execute(request, response);
			viewPage = "content_view.do";
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
