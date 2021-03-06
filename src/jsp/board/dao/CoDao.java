package jsp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.board.dto.CoDto;


public class CoDao {
	
	private static CoDao instance = new CoDao();
	DataSource dataSource;
	
	private CoDao() {
    	try {
    		Context context = new InitialContext();
    		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static CoDao getInstance() {
    	return instance;
    }
    
    public ArrayList<CoDto> commentList(String strID) {
    	
    	ArrayList<CoDto> dtos = new ArrayList<CoDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "select * from mvc_comment where bId = ? order by coNo";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(strID));
    		resultSet = pstmt.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			int bId = resultSet.getInt("bId");
    			int coNo = resultSet.getInt("coNo");
    			String coName = resultSet.getString("coName");
    			String coContent = resultSet.getString("coContent");
    			Timestamp coDate = resultSet.getTimestamp("coDate");
    			
    			CoDto dto = new CoDto(bId, coNo, coName, coContent, coDate);
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
    
    public void coWrite(String bId, String coName, String coContent) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "insert into mvc_comment " +
    					   " (bId, coNo, coName, coContent)" +
    					   " values " +
    					   "(?, mvc_comment_seq.nextval, ?, ?)";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(bId));
    		pstmt.setString(2, coName);
    		pstmt.setString(3, coContent);
    		pstmt.executeUpdate();
    		
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
    	
    }
    
    public void coDelete(String coNo) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {    		
    		con = dataSource.getConnection();
    		String query = "delete from mvc_comment where coNo = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(coNo));
    		pstmt.executeUpdate();    		
    		
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
    }
    
    public void coModify(String coNo, String coContent) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "update mvc_comment set coContent = ? where coNo = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, coContent);
    		pstmt.setInt(2, Integer.parseInt(coNo));
    		pstmt.executeUpdate();
    		
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
    }    
}
