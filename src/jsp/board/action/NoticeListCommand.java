package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.dao.BDao;
import jsp.board.dto.BDto;

public class NoticeListCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{	
		BDao dao = BDao.getInstance();
		ArrayList<BDto> dtos = dao.noticeList();
		request.setAttribute("nList", dtos);
	}
}
