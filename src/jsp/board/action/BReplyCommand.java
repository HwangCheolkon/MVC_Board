package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.BDao;
import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;

public class BReplyCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(id);
		String bName = mdto.getId();
		
		String bId = request.getParameter("bId");
//		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		BDao dao = BDao.getInstance();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}
}
