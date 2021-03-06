package jsp.board.comment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.action.BCommand;
import jsp.board.dao.CoDao;
import jsp.board.dto.CoDto;

public class CommentList implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{	
		String bId = request.getParameter("bId");
		
		CoDao dao = CoDao.getInstance();
		ArrayList<CoDto> dtos = dao.commentList(bId);
		request.setAttribute("cList", dtos);
	}

}
