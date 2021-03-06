package jsp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.board.action.BPageInfo;
import jsp.board.dto.BDto;


public class BDao {
	
	private static BDao instance = new BDao();
	DataSource dataSource;
	
	int listCount = 10;		// 한 페이지당 보여줄 게시물의 수
    int pageCount = 5;		// 하단에 보여줄 페이지 리스트의 수
	
	private BDao() {
    	try {
    		// lookup 함수의 파라미터는 context.xml에 설정 된
    		// name(jdbc/Oracle11g)과 동일해야 한다.
    		Context context = new InitialContext();
    		dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static BDao getInstance() {
    	return instance;
    }
    
    public void write(String bName, String bTitle, String bContent) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "insert into mvc_board " +
    					   " (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, like_cnt)" +
    					   " values " +
    					   "(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, 0)";
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, bName);
    		pstmt.setString(2, bTitle);
    		pstmt.setString(3, bContent);
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
    
    public ArrayList<BDto> list(int curPage) {
    	
    	ArrayList<BDto> dtos = new ArrayList<BDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	int nStart = (curPage - 1) * listCount + 1;
    	int nEnd = (curPage - 1) * listCount + listCount;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "select * " +
    					   "  from ( " + 
    					   "   select rownum num, A.* " +
    					   "     from ( " +
    					   "        select * " +
    					   "          from mvc_board " +
    					   "         order by bgroup desc, bstep asc ) A " +
    					   "    where rownum <= ? ) B " +
    					   " where B.num >= ? ";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, nEnd);
    		pstmt.setInt(2, nStart);
    		resultSet = pstmt.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			int bId = resultSet.getInt("bId");
    			String bName = resultSet.getString("bName");
    			String bTitle = resultSet.getString("bTitle");
    			String bContent = resultSet.getString("bContent");
    			Timestamp bDate = resultSet.getTimestamp("bDate");
    			int bHit = resultSet.getInt("bHit");
    			int bGroup = resultSet.getInt("bGroup");
    			int bStep = resultSet.getInt("bStep");
    			int bIndent = resultSet.getInt("bIndent");
    			int like_cnt = resultSet.getInt("like_cnt");
    			
    			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
    								bHit, bGroup, bStep, bIndent, like_cnt);
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
    
    public BDto contentView(String strID) {
    	upHit(strID);
    	
    	BDto dto = null;
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "select * from mvc_board where bId = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(strID));
    		resultSet = pstmt.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			int bId = resultSet.getInt("bId");
    			String bName = resultSet.getString("bName");
    			String bTitle = resultSet.getString("bTitle");
    			String bContent = resultSet.getString("bContent");
    			Timestamp bDate = resultSet.getTimestamp("bDate");
    			int bHit = resultSet.getInt("bHit");
    			int bGroup = resultSet.getInt("bGroup");
    			int bStep = resultSet.getInt("bStep");
    			int bIndent = resultSet.getInt("bIndent");
    			int like_cnt = resultSet.getInt("like_cnt");
    			
    			dto = new BDto(bId, bName, bTitle, bContent, bDate, 
    								bHit, bGroup, bStep, bIndent, like_cnt);
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
    	
    	return dto;
    }
    
    public void modify(String bId, String bTitle, String bContent) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "update mvc_board set bTitle = ?, bContent = ? where bId = ?";
    		pstmt = con.prepareStatement(query);
//    		pstmt.setString(1, bName);
    		pstmt.setString(1, bTitle);
    		pstmt.setString(2, bContent);
    		pstmt.setInt(3, Integer.parseInt(bId));
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
    
    private void upHit(String bId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, bId);    		
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
    
    public void delete(String bId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	try {
    		con = dataSource.getConnection();
    		String query = "delete from mvc_board where bId = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(bId));
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
    
    public BDto reply_view(String str) {
    	BDto dto = null;
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	try {
    		con = dataSource.getConnection();
    		String query = "select * from mvc_board where bId = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(str));
    		resultSet = pstmt.executeQuery();
    		
    		if(resultSet.next()) {
    			
    			int bId = resultSet.getInt("bId");
    			String bName = resultSet.getString("bName");
    			String bTitle = resultSet.getString("bTitle");
    			String bContent = resultSet.getString("bContent");
    			Timestamp bDate = resultSet.getTimestamp("bDate");
    			int bHit = resultSet.getInt("bHit");
    			int bGroup = resultSet.getInt("bGroup");
    			int bStep = resultSet.getInt("bStep");
    			int bIndent = resultSet.getInt("bIndent");
    			int like_cnt = resultSet.getInt("like_cnt");
    			
    			dto = new BDto(bId, bName, bTitle, bContent, bDate, 
							   bHit, bGroup, bStep, bIndent, like_cnt);
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
    	
    	return dto;
    }
    
    public void reply(String bId, String bName, String bTitle, String bContent,
    				  String bGroup, String bStep, String bIndent)
    {
    	replyShape(bGroup, bStep);
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "insert into mvc_board " +
    					   " (bId, bName, bTitle, bContent, bGroup, bStep, bIndent, like_cnt) " +
    					   " values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?, 0)";
    		pstmt = con.prepareStatement(query);
    		
    		pstmt.setString(1, bName);
    		pstmt.setString(2, bTitle);
    		pstmt.setString(3, bContent);
    		pstmt.setInt(4, Integer.parseInt(bGroup));
    		pstmt.setInt(5, Integer.parseInt(bStep) + 1);
    		pstmt.setInt(6, Integer.parseInt(bIndent) + 1);    		
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
    
    private void replyShape(String strGroup, String strStep) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "update mvc_board " +
    					   "   set bStep = bStep + 1 " +
    					   " where bGroup = ? and bStep = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(strGroup));
    		pstmt.setInt(2, Integer.parseInt(strStep)); 		
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
    
    public ArrayList<BDto> bSearch(String b_opt, String keyword) {
    	
    	ArrayList<BDto> dtos = new ArrayList<BDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
//    	int nStart = (curPage - 1) * listCount + 1;
//    	int nEnd = (curPage - 1) * listCount + listCount;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		if(b_opt.equals("1")) {
    			String query = "select * from mvc_board " + 
    						   " where bTitle like ? or bContent like ? " +
    						   " order by bDate desc";
		 		pstmt = con.prepareStatement(query);
		 		pstmt.setString(1, "%"+keyword+"%");
		 		pstmt.setString(2, "%"+keyword+"%");
//		 		pstmt.setInt(3, nEnd);
//		 		pstmt.setInt(4, nStart);
		 		resultSet = pstmt.executeQuery();
		 		
		 		while(resultSet.next()) {
		 			
		 			int bId = resultSet.getInt("bId");
		 			String bName = resultSet.getString("bName");
		 			String bTitle = resultSet.getString("bTitle");
		 			String bContent = resultSet.getString("bContent");
		 			Timestamp bDate = resultSet.getTimestamp("bDate");
		 			int bHit = resultSet.getInt("bHit");
		 			int bGroup = resultSet.getInt("bGroup");
		 			int bStep = resultSet.getInt("bStep");
		 			int bIndent = resultSet.getInt("bIndent");
		 			int like_cnt = resultSet.getInt("like_cnt");
		 			
		 			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
		 								bHit, bGroup, bStep, bIndent, like_cnt);
		 			dtos.add(dto);
		 		}
    		} else if(b_opt.equals("2")) {
    			String query = "select * from mvc_board where bTitle like ?";
 		 		pstmt = con.prepareStatement(query);
 		 		pstmt.setString(1, "%"+keyword+"%");
// 		 		pstmt.setInt(2, nEnd);
// 		 		pstmt.setInt(3, nStart);
 		 		resultSet = pstmt.executeQuery();
 		 		
 		 		while(resultSet.next()) {
 		 			
 		 			int bId = resultSet.getInt("bId");
 		 			String bName = resultSet.getString("bName");
 		 			String bTitle = resultSet.getString("bTitle");
 		 			String bContent = resultSet.getString("bContent");
 		 			Timestamp bDate = resultSet.getTimestamp("bDate");
 		 			int bHit = resultSet.getInt("bHit");
 		 			int bGroup = resultSet.getInt("bGroup");
 		 			int bStep = resultSet.getInt("bStep");
 		 			int bIndent = resultSet.getInt("bIndent");
 		 			int like_cnt = resultSet.getInt("like_cnt");
 		 			
 		 			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
 		 								bHit, bGroup, bStep, bIndent, like_cnt);
 		 			dtos.add(dto);
 		 		}
     		} else if(b_opt.equals("3")) {
    			String query = "select * from mvc_board where bContent like ?";
  		 		pstmt = con.prepareStatement(query);
  		 		pstmt.setString(1, "%"+keyword+"%");
//  		 		pstmt.setInt(2, nEnd);
//  		 		pstmt.setInt(3, nStart);
  		 		resultSet = pstmt.executeQuery();
  		 		
  		 		while(resultSet.next()) {
  		 			
  		 			int bId = resultSet.getInt("bId");
  		 			String bName = resultSet.getString("bName");
  		 			String bTitle = resultSet.getString("bTitle");
  		 			String bContent = resultSet.getString("bContent");
  		 			Timestamp bDate = resultSet.getTimestamp("bDate");
  		 			int bHit = resultSet.getInt("bHit");
  		 			int bGroup = resultSet.getInt("bGroup");
  		 			int bStep = resultSet.getInt("bStep");
  		 			int bIndent = resultSet.getInt("bIndent");
  		 			int like_cnt = resultSet.getInt("like_cnt");
  		 			
  		 			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
  		 								bHit, bGroup, bStep, bIndent, like_cnt);
  		 			dtos.add(dto);
  		 		}
      		} else if(b_opt.equals("4")) {
    			String query = "select * from mvc_board where bName like ?";
  		 		pstmt = con.prepareStatement(query);
  		 		pstmt.setString(1, "%"+keyword+"%");
//  		 		pstmt.setInt(2, nEnd);
//  		 		pstmt.setInt(3, nStart);
  		 		resultSet = pstmt.executeQuery();
  		 		
  		 		while(resultSet.next()) {
  		 			
  		 			int bId = resultSet.getInt("bId");
  		 			String bName = resultSet.getString("bName");
  		 			String bTitle = resultSet.getString("bTitle");
  		 			String bContent = resultSet.getString("bContent");
  		 			Timestamp bDate = resultSet.getTimestamp("bDate");
  		 			int bHit = resultSet.getInt("bHit");
  		 			int bGroup = resultSet.getInt("bGroup");
  		 			int bStep = resultSet.getInt("bStep");
  		 			int bIndent = resultSet.getInt("bIndent");
  		 			int like_cnt = resultSet.getInt("like_cnt");
  		 			
  		 			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
  		 								bHit, bGroup, bStep, bIndent, like_cnt);
  		 			dtos.add(dto);
  		 		}
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
    
    public ArrayList<BDto> noticeList() {
    	
    	ArrayList<BDto> dtos = new ArrayList<BDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	try {
    		con = dataSource.getConnection();
    		
    		String query = "select * from mvc_board where bName = 'MANAGER' " +
    					   " order by bDate desc";
    		pstmt = con.prepareStatement(query);
    		resultSet = pstmt.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			int bId = resultSet.getInt("bId");
    			String bName = resultSet.getString("bName");
    			String bTitle = resultSet.getString("bTitle");
    			String bContent = resultSet.getString("bContent");
    			Timestamp bDate = resultSet.getTimestamp("bDate");
    			int bHit = resultSet.getInt("bHit");
    			int bGroup = resultSet.getInt("bGroup");
    			int bStep = resultSet.getInt("bStep");
    			int bIndent = resultSet.getInt("bIndent");
    			int like_cnt = resultSet.getInt("like_cnt");
    			
    			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
    								bHit, bGroup, bStep, bIndent, like_cnt);
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
    
    public ArrayList<BDto> myBoard(String id, int curPage) {
    	
    	ArrayList<BDto> dtos = new ArrayList<BDto>();
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	int nStart = (curPage - 1) * listCount + 1;
    	int nEnd = (curPage - 1) * listCount + listCount;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "select * " +
					   "  from ( " + 
					   "   select rownum num, A.* " +
					   "     from ( " +
					   "        select * " +
					   "          from mvc_board where bName = ?" +
					   "         order by bgroup desc, bstep asc ) A " +
					   "    where rownum <= ? ) B " +
					   " where B.num >= ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
    		
//    		String query = "select * from mvc_board where bName = ?";
//    		pstmt = con.prepareStatement(query);
//    		pstmt.setString(1, id);
//    		resultSet = pstmt.executeQuery();
    		
    		while(resultSet.next()) {
	 			
	 			int bId = resultSet.getInt("bId");
	 			String bName = resultSet.getString("bName");
	 			String bTitle = resultSet.getString("bTitle");
	 			String bContent = resultSet.getString("bContent");
	 			Timestamp bDate = resultSet.getTimestamp("bDate");
	 			int bHit = resultSet.getInt("bHit");
	 			int bGroup = resultSet.getInt("bGroup");
	 			int bStep = resultSet.getInt("bStep");
	 			int bIndent = resultSet.getInt("bIndent");
	 			int like_cnt = resultSet.getInt("like_cnt");
	 			
	 			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
	 								bHit, bGroup, bStep, bIndent, like_cnt);
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
    
    public void like(String bId) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try {
    		con = dataSource.getConnection();
    		String query = "update mvc_board set like_cnt = like_cnt + 1 where bId = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setInt(1, Integer.parseInt(bId));    		
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
    
    public BPageInfo articlePage(int curPage) {
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	int listCount = 10;		// 한 페이지당 보여줄 게시물의 수
        int pageCount = 5;		// 하단에 보여줄 페이지 리스트의 수
        
        // 총 게시물의 수
        int totalCount = 0;
        try {
        	con = dataSource.getConnection();
    		String query = "select count(*) as total from mvc_board";
    		pstmt = con.prepareStatement(query);
    		resultSet = pstmt.executeQuery();
    		
    		if(resultSet.next()) {
    			totalCount = resultSet.getInt("total");
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
        
        // 총 페이지 수 
        int totalPage = totalCount / listCount;
        if(totalCount % listCount > 0)
        	totalPage++;
        
        // 현재 페이지
        int myCurPage = curPage;
        if(myCurPage > totalPage)
        	myCurPage = totalPage;
        if(myCurPage < 1)
        	myCurPage = 1;
        
        // 시작 페이지
        int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
        
        // 끝 페이지
        int endPage = startPage + pageCount - 1;
        if(endPage > totalPage)
        	endPage = totalPage;
        
        BPageInfo pinfo = new BPageInfo();
        pinfo.setTotalCount(totalCount);
        pinfo.setListCount(listCount);
        pinfo.setTotalPage(totalPage);
        pinfo.setCurPage(curPage);
        pinfo.setPageCount(pageCount);
        pinfo.setStartPage(startPage);
        pinfo.setEndPage(endPage);
        
        return pinfo;
    }
    
    public BPageInfo myBoardPage(String id, int curPage) {
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	int listCount = 10;
        int pageCount = 5;
        
        // 총 게시물의 수
        int totalCount = 0;
        try {
        	con = dataSource.getConnection();
    		String query = "select count(*) as total from mvc_board where bName = ?";
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, id);
    		resultSet = pstmt.executeQuery();
    		
    		if(resultSet.next()) {
    			totalCount = resultSet.getInt("total");
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
        
        // 총 페이지 수 
        int totalPage = totalCount / listCount;
        if(totalCount % listCount > 0)
        	totalPage++;
        
        // 현재 페이지
        int myCurPage = curPage;
        if(myCurPage > totalPage)
        	myCurPage = totalPage;
        if(myCurPage < 1)
        	myCurPage = 1;
        
        // 시작 페이지
        int startPage = ((myCurPage - 1) / pageCount) * pageCount + 1;
        
        // 끝 페이지
        int endPage = startPage + pageCount - 1;
        if(endPage > totalPage)
        	endPage = totalPage;
        
        BPageInfo pinfo = new BPageInfo();
        pinfo.setTotalCount(totalCount);
        pinfo.setListCount(listCount);
        pinfo.setTotalPage(totalPage);
        pinfo.setCurPage(curPage);
        pinfo.setPageCount(pageCount);
        pinfo.setStartPage(startPage);
        pinfo.setEndPage(endPage);
        
        return pinfo;
    }
}
