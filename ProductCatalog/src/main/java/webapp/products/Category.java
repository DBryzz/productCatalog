package webapp.products;

import java.util.ArrayList;

public class Category {
	
	private String catName;
	private String description;
	private ArrayList<Products> productList; //= new ArrayList<Products>();
	
	
	
public Category(String catName, String description) {
		super();
		this.catName = catName;
		this.description = description;
		this.productList = new ArrayList<Products>();
	}


/*
	public Category(String catName, String description) {
		super();
		this.catName = catName;
		this.description = description;
	}
*/	
	
	public void addProduct(String pxtName) {
		Products newProduct = new Products(pxtName);
		productList.add(newProduct);
		System.out.println(newProduct + "has been added under " + catName);
	}
	
	
	public void printProductList() {
		System.out.println("You have " + productList.size() + "items in " + catName + "Category");
		for(int i=0; i<productList.size(); i++) {
			System.out.println((i+1) + ". " + productList.get(i));
			
		}
	}
	
	public boolean updateProduct(Products oldPxt, Products newPxt) {
		int foundPosition = findProduct(oldPxt);
		if(foundPosition < 0) {
			System.out.println(oldPxt.getPxtName() + ", was not found.");
			return false;
		}
		
		this.productList.set(foundPosition, newPxt);
		System.out.println(oldPxt.getPxtName() + ", was replaced with " + newPxt.getPxtName());
		return true;
	}
	
	public void modifyProduct(int position, Products newPxt) {
		productList.set(position, newPxt);
		System.out.println("Item " + (position+1) + " has been modified");
	}
	
	
	public void deleteProduct(int position) {
		Products theProduct = productList.get(position);
		productList.remove(position);
		System.out.println(theProduct.getPxtName() + " has been deleted");
	}
	
	
	private int findProduct(Products product) {
		return this.productList.indexOf(product);
	}
	
    private int findProduct(String searchItem) {
    	
    	
    	for(int i=0; i<this.productList.size(); i++) {
    		Products product = this.productList.get(i);
    		if(product.getPxtName().equals(searchItem)) {
    			return i;
    		}
    	}
    		
    		return -1;
    }
    


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Products> getProductList() {
		return productList;
	}


	public void setProductList(ArrayList<Products> productList) {
		this.productList = productList;
	}
   
	


	
}
