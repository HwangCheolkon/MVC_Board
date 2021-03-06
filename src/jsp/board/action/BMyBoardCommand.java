package jsp.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.dao.BDao;
import jsp.board.dto.BDto;


public class BMyBoardCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{	
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		} catch(Exception e) {
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		BDao dao = BDao.getInstance();
		String id = (String)session.getAttribute("id");
		ArrayList<BDto> dtos = dao.myBoard(id, nPage);
		request.setAttribute("myBoard", dtos);	

		BPageInfo pinfo = dao.myBoardPage(id, nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
//		HttpSession session = request.getSession();
//		String id = (String)session.getAttribute("id");
		
//		BDao dao = BDao.getInstance();
//		ArrayList<BDto> dtos = dao.myBoard(id);
//		request.setAttribute("myBoard", dtos);		
	}
}
