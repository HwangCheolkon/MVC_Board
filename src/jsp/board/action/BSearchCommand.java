package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.dao.BDao;
import jsp.board.dto.BDto;


public class BSearchCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{	
		String b_opt = request.getParameter("b_opt");
		String keyword = request.getParameter("keyword");
		
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.bSearch(b_opt, keyword);
		request.setAttribute("bSearch", dtos);
	}
}
