package jsp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.board.dto.MemberDto;


public class MemberDao 
{
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDao instance = new MemberDao();
	DataSource dataSource;
    
    public MemberDao() {
    	try {
    		Context context = new InitialContext();
    		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static MemberDao getInstance() {
    	return instance;
    }
    
    public int insertMember(MemberDto dto) {
    	int ri = 0;	// 0 = MemberDAO.MEMBER_JOIN_FAIL    	
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	String query = "insert into mvc_member values (?,?,?,?,?,?)";
    	
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getId());
    		pstmt.setString(2, dto.getPw());
    		pstmt.setString(3, dto.getName());
    		pstmt.setString(4, dto.geteMail());
    		pstmt.setTimestamp(5, dto.getrDate());
    		pstmt.setString(6, dto.getAddress());
    		pstmt.executeUpdate();
    		ri = MemberDao.MEMBER_JOIN_SUCCESS;		// 1
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(pstmt != null) pstmt.close();
    			if(con != null) con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	return ri;
    }
    
    public int confirmId(String id) {
    	int ri = 0;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet set = null;
    	String query = "select id from mvc_member where id = ?";
    	
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, id);
    		set = pstmt.executeQuery();
    		if(set.next()) {
    			ri = MemberDao.MEMBER_EXISTENT;		// 1
    		} else {
    			ri = MemberDao.MEMBER_NONEXISTENT;	// 0
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			set.close();
    			pstmt.close();
    			con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	return ri;
    }
    
    public int userCheck(String id, String pw) {
    	int ri = 0;
    	String dbPw;
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet set = null;
    	String query = "select pw from mvc_member where id = ?";
    	
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, id);
    		set = pstmt.executeQuery();
    		if(set.next()) {
    			dbPw = set.getString("pw");
    			if(dbPw.equals(pw)) {
    				System.out.println("login ok");
    				ri = MemberDao.MEMBER_LOGIN_SUCCESS;	// 로그인 OK, 1
    			} else {
    				System.out.println("login fail");
    				ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;	// 비밀번호 X, 0
    			}
    		} else {
    			System.out.println("login fail");
    			ri = MemberDao.MEMBER_LOGIN_IS_NOT;			// 아이디 X, -1
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			set.close();
    			pstmt.close();
    			con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	return ri;
    }
    
    public MemberDto getMember(String id) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet set = null;
    	String query = "select * from mvc_member where id = ?";
    	MemberDto dto = null;
    	
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, id);
    		set = pstmt.executeQuery();
    		
    		if(set.next()) {
    			dto = new MemberDto();
    			dto.setId(set.getString("id"));
    			dto.setPw(set.getString("pw"));
    			dto.setName(set.getString("name"));
    			dto.seteMail(set.getString("eMail"));
    			dto.setrDate(set.getTimestamp("rDate"));
    			dto.setAddress(set.getString("address"));
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			set.close();
    			pstmt.close();
    			con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	
    	return dto;
    }
    
    public int updateMember(MemberDto dto) {
    	int ri = 0;
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	String query = "update mvc_member set pw=?, eMail=?, address=? where id=?";
    	
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getPw());
    		pstmt.setString(2, dto.geteMail());
    		pstmt.setString(3, dto.getAddress());
    		pstmt.setString(4, dto.getId());
    		ri = pstmt.executeUpdate();
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			pstmt.close();
    			con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	
    	return ri;
    }
    
    public ArrayList<MemberDto> list() {
    	
    	ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "select * from mvc_member where id != 'MANAGER' order by id desc";
    		pstmt = con.prepareStatement(query);
    		resultSet = pstmt.executeQuery();
    		
    		while(resultSet.next()) {
    			String id = resultSet.getString("id");
    			String pw = resultSet.getString("pw");
    			String name = resultSet.getString("name");
    			String eMail = resultSet.getString("eMail");
    			Timestamp rDate = resultSet.getTimestamp("rDate");
    			String address = resultSet.getString("address");
    			
    			MemberDto dto = new MemberDto(id, pw, name, eMail, rDate, address);
    			dtos.add(dto);
    		}
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(resultSet != null) resultSet.close();
    			if(pstmt != null) pstmt.close();
    			if(con != null) con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	
    	return dtos;
    }
    
    public int leaveMember(MemberDto dto) {
    	int ri = 0;    	
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	try {
    		String query = "select * from mvc_member where id = ?";
    		con = getConnection();
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getId());
    		resultSet = pstmt.executeQuery();
    		
    		resultSet.next();
    		String upw = resultSet.getString(2);
    		String pw = dto.getPw();
    		
    		if(pw.equals(upw)) {
    			query = "delete from mvc_member where id = ?";
            	con = getConnection();
            	pstmt = con.prepareStatement(query);
            	pstmt.setString(1, dto.getId());
            	pstmt.executeUpdate();
            	ri = 1;
    		} else {
    			ri = 0;
    		}    		
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			if(pstmt != null) pstmt.close();
    			if(con != null) con.close();
    		} catch(Exception e2) {
    			e2.printStackTrace();
    		}
    	}
    	return ri;
    }    
    
    private Connection getConnection() {
    	
    	Context context = null;
    	DataSource dataSource = null;
    	Connection con = null;
    	try {
    		// lookup 함수의 파라메터는 context.xml에 설정된 
    		// name(jdbc/Oracle11g)과 동일해야 한다.
    		context = new InitialContext();
    		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
    		con = dataSource.getConnection();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return con;
    }
}
