package jsp.board.comment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.action.BCommand;
import jsp.board.dao.CoDao;
import jsp.board.dto.CoDto;

public class CommentModify implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String coNo = request.getParameter("coNo");
		String coContent = request.getParameter("coContent");
		
		CoDao dao = CoDao.getInstance();
		dao.coModify(coNo, coContent);
		
		ArrayList<CoDto> dtos = dao.commentList(coNo);
		request.setAttribute("cList", dtos);
	}
}
