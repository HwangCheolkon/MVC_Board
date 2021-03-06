package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.BDao;
import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class BWriteCommand implements BCommand {
    
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
//    	request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(id);
		String bName = mdto.getId();
		
//    	String bName = request.getParameter("bName");
    	String bTitle = request.getParameter("bTitle");
    	String bContent = request.getParameter("bContent");
    	
    	BDao dao = BDao.getInstance();
    	dao.write(bName, bTitle, bContent);
    }
}
