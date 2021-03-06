package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.dao.BDao;

public class BLikeCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
//		String bName = request.getParameter("bName");
		BDao dao = BDao.getInstance();
//		dao.like(bId, bName);
		dao.like(bId);
//		request.setAttribute("content_view", dto);
	}
}
