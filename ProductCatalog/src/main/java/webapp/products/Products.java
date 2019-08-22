package webapp.products;

public class Products {
	
	private String pxtName;
	//private Category pxtCategory;
	
	
	
	public Products(String pxtName) {
		super();
		this.pxtName = pxtName;
		//this.pxtCategory = pxtCategory;
	}
	
	
	
	//return "Todo [name=" + name + ", category=" + category + "]";
	
	
	@Override
	public String toString() {
		return String.format("Products [pxtName=%s]", pxtName);
	}






	public String getPxtName() {
		return pxtName;
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
