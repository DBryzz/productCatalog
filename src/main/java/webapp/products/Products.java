package webapp.products;


public class Products {
	private int pxtID;
	private String pxtName;
	private String pxtCategory;
	private String pxtOwner;
	private String pxtImage;
	
	
	public Products() {
		
	}
	
	public Products(String pxtName, String pxtCategory, String pxtOwner, String pxtImage) {
		super();
		this.pxtID = 0000;
		this.pxtName = pxtName;
		this.pxtCategory = pxtCategory;
		this.pxtOwner = pxtOwner;
		this.pxtImage = pxtImage;
		//this.pxtCategory = pxtCategory;
	}
	
	
	
	
	
	//return "Todo [name=" + name + ", category=" + category + "]";
	
	
	

	public int getPxtID() {
		return pxtID;
	}



	public void setPxtID(int pxtID) {
		this.pxtID = pxtID;
	}



	@Override
	public String toString() {
		return String.format("Products [pxtName=%s, pxtCategory=%s, pxtOwner=%s ]", pxtName, pxtCategory, pxtOwner);
	}




	public String getPxtName() {
		return pxtName;
	}
	
	



	public String getPxtCategory() {
		return pxtCategory;
	}



	public void setPxtCategory(String pxtCategory) {
		this.pxtCategory = pxtCategory;
	}



	public String getPxtOwner() {
		return pxtOwner;
	}



	public void setPxtOwner(String pxtOwner) {
		this.pxtOwner = pxtOwner;
	}



	public void setPxtName(String pxtName) {
		this.pxtName = pxtName;
	}



	public String getPxtImage() {
		return pxtImage;
	}



	public void setPxtImage(String pxtImage) {
		this.pxtImage = pxtImage;
	}

	
	
	
	

}
