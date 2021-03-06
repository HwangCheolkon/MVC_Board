package jsp.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.action.BCommand;
import jsp.board.dao.CoDao;

public class CommentDelete implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String coNo = request.getParameter("coNo");		
		CoDao dao = CoDao.getInstance();
		dao.coDelete(coNo);
	}
}