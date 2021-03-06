package jsp.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.action.BCommand;
import jsp.board.dao.CoDao;
import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class CommentWrite implements BCommand {
    
    public void execute(HttpServletRequest request, HttpServletResponse response)
    {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(id);
		
		String coName = mdto.getId();		
		String bId = request.getParameter("bId");
    	String coContent = request.getParameter("coContent");
    	
    	CoDao dao = CoDao.getInstance();
    	dao.coWrite(bId, coName, coContent);
    }
}
