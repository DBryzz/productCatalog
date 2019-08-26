package webapp.products;

public class Products {
	
	private String pxtName;
	private String pxtCategory;
	private String pxtOwner;
	
	
	
	public Products(String pxtName, String pxtCategory, String pxtOwner) {
		super();
		this.pxtName = pxtName;
		this.pxtCategory = pxtCategory;
		this.pxtOwner = pxtOwner;
		//this.pxtCategory = pxtCategory;
	}
	
	
	
	//return "Todo [name=" + name + ", category=" + category + "]";
	
	
	

	@Override
	public String toString() {
		return String.format("Products [pxtName=%s, pxtCategory=%s, pxtOwner=%s]", pxtName, pxtCategory, pxtOwner);
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

	
/*	
	public Category getPxtCategory() {
		return pxtCategory;
	}
	
	public void setPxtCategory(Category pxtCategory) {
		this.pxtCategory = pxtCategory;
	}
*/	
	
	
	

}
