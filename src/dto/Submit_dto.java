package dto;

public class Submit_dto {
	private int count;
	private int chap;

    public Submit_dto(int count,int chap) {
        this.count = count;
        this.chap = chap;
    }
    

    public int getCount() {
        return count;
    }

    public int getChap() {
        return chap;
    }
    
}
