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
			writer.println("		alert(\"���̵� Ʋ�Ƚ��ϴ�.\");");
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
				// html ���
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"��й�ȣ�� Ʋ�Ƚ��ϴ�.\");");
				writer.println("		history.back();");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
			} else if (ri == 1) {
				// html ���
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"Ż��Ǿ����ϴ�. �̿����ּż� �����մϴ�.\");");
				writer.println("		document.location.href=\"login.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
				
			} else {
				// html ���
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"Ż�� �����߽��ϴ�.\");");
				writer.println("		document.location.href=\"myInfoPage.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
		
	}
}
