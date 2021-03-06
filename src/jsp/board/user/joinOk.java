package jsp.board.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class joinOk implements Service {

    public joinOk() {
    	
    }
    
    @Override
    public void execute(HttpServletRequest request,
    					HttpServletResponse response) 
    			throws ServletException, IOException 
    {
    	System.out.println("joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		MemberDao dao = MemberDao.getInstance();
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
			// html ���			
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("		alert(\"���̵� �̹� �����մϴ�.\");");
			writer.println("		history.back();");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else {
			int ri = dao.insertMember(dto);
			if (ri == MemberDao.MEMBER_JOIN_SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				
				// html ���
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"ȸ�������� �����մϴ�.\");");
				writer.println("		document.location.href=\"login.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			} else {
				// html ���
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("		alert(\"ȸ�����Կ� �����߽��ϴ�.\");");
				writer.println("		document.location.href=\"join.jsp\";");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
    }
}
