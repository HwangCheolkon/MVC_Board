package jsp.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import jsp.board.comment.CommentList;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BFrontController() {
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
		
		if(com.equals("/00board/write_view.do")) {
			viewPage = "write_view.jsp";
			
		} else if(com.equals("/00board/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			
		} else if(com.equals("/00board/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			
			command = new NoticeListCommand();
			command.execute(request, response);			
			viewPage = "list.jsp";
			
		} else if(com.equals("/00board/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			
			command = new CommentList();
			command.execute(request, response);
			viewPage = "content_view.jsp";
			
		} else if(com.equals("/00board/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
			
		} else if(com.equals("/00board/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.do";
			
		} else if(com.equals("/00board/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page=" + curPage;
			
		} else if(com.equals("/00board/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
			
		} else if(com.equals("/00board/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do?page=" + curPage;
			
		} else if(com.equals("/00board/bSearch.do")) {
			command = new BSearchCommand();
			command.execute(request, response);
			viewPage = "boardSearchList.jsp";
			
		} else if(com.equals("/00board/notice.do")) {
			command = new NoticeListCommand();
			command.execute(request, response);
			viewPage = "notice.jsp";
			
		} else if(com.equals("/00board/bMyBoard.do")) {
			command = new BMyBoardCommand();
			command.execute(request, response);
			viewPage = "../00user/myBoardList.jsp";
			
		} else if(com.equals("/00board/like.do")) {
			command = new BLikeCommand();
			command.execute(request, response);
			viewPage = "content_view.do";
			
		} else if(com.equals("/00user/main.do")) {
			command = new BListCommand();
			command.execute(request, response);
			
			command = new NoticeListCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
