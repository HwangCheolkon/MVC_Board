package jsp.board.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class loginOk implements Service {

    public loginOk() {
    	
    }
    
    @Override
    public void execute(HttpServletRequest request,
    					HttpServletResponse response) 
    			throws ServletException, IOException 
    {
    	System.out.println("loginOk");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDto dto = new MemberDto();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == -1) {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("		alert(\"아이디가 존재하지 않습니다.\");");
			writer.println("		history.go(-1);");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		} else if(checkNum == 0) {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("		alert(\"비밀번호가 틀립니다.\");");
			writer.println("		history.go(-1);");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		} else if(checkNum == 1) {
			dto = dao.getMember(id);
			String name = dto.getName();
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("main.do");
			
		}    	
    }
}
