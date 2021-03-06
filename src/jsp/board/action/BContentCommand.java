package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.dao.BDao;
import jsp.board.dto.BDto;

public class BContentCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId);
		
		request.setAttribute("content_view", dto);
	}
}
