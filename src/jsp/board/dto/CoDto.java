package jsp.board.dto;

import java.sql.Timestamp;

public class CoDto {    

	private int bId;
	private int coNo;
	private String coName;
	private String coContent;
	private Timestamp coDate;
    
    public CoDto() {
    	
    }

    public CoDto(int bId, int coNo, String coName, String coContent, Timestamp coDate) 
    {
    	this.bId = bId;
    	this.coNo = coNo;
    	this.coName = coName;
    	this.coContent = coContent;
    	this.coDate = coDate;
    }

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getCoNo() {
		return coNo;
	}

	public void setCoNo(int coNo) {
		this.coNo = coNo;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getCoContent() {
		return coContent;
	}

	public void setCoContent(String coContent) {
		this.coContent = coContent;
	}

	public Timestamp getCoDate() {
		return coDate;
	}

	public void setCoDate(Timestamp coDate) {
		this.coDate = coDate;
	}
    
	
}
