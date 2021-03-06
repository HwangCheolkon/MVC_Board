package jsp.board.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class leaveOk implements Service {

    public leaveOk() {
    	
    }
    
    @Override
    public void execute(HttpServletRequest request,
    					HttpServletResponse response) 
    			throws ServletException, IOException 
    {
    	System.out.println("leaveOk");
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String uid = request.getParameter("uid");
		String pw = request.getParameter("pw");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(!uid.equals(id)) {
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("		alert(\"아이디가 틀렸습니다.\");");
			writer.println("		history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
			
		} else {
			MemberDto dto = new MemberDto();
			dto.setId(id);
			dto.setPw(pw);
			
			MemberDao dao = MemberDao.getInstance();
			int ri = dao.leaveMember(dto);		
			
			if (ri == 0) {
				// html 출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"비밀번호가 틀렸습니다.\");");
				writer.println("		history.back();");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
			} else if (ri == 1) {
				// html 출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"탈퇴되었습니다. 이용해주셔서 감사합니다.\");");
				writer.println("		document.location.href=\"login.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
			} else {
				// html 출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"탈퇴에 실패했습니다.\");");
				writer.println("		document.location.href=\"myInfoPage.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
		
	}
}
