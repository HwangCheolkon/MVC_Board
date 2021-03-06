package jsp.board.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.action.BCommand;
import jsp.board.dao.MemberDao;
import jsp.board.dto.MemberDto;



public class UserListCommand implements BCommand {
       
    public UserListCommand() {
    	
    }

	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{		
		MemberDao dao = MemberDao.getInstance();
		ArrayList<MemberDto> dtos = dao.list();
		request.setAttribute("userList", dtos);
	}

}
