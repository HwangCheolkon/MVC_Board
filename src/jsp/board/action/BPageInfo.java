package jsp.board.action;

public class BPageInfo {
	
    public BPageInfo() {
    	
    }
    
    int totalCount;		// �� �Խù��� ����
    int listCount;		// �� �������� ������ �Խù��� ��
    int totalPage;		// �� ������
    int curPage;		// ���� ������
    int pageCount;		// �ϴܿ� ������ ������ ����Ʈ�� ��
    int startPage;		// ���� ������
    int endPage;		// �� ������
    
    
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
    
}
