package jsp.board.dto;

public class LikeDto {    

	private int bId;
	private String bName;
    
    public LikeDto() {
    	
    }

    public LikeDto(int bId, String bName) 
    {
    	this.bId = bId;
    	this.bName = bName;
    }
    
    public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}
	
	 public String getbName() {
			return bName;
		}

		public void setbName(String bName) {
			this.bName = bName;
		}

}