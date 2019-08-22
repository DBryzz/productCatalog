package webapp.products;

import java.util.ArrayList;

public class ProductService {
	
	
	private static ArrayList<Category> categoryList = new ArrayList<Category>();
	
	
	public ProductService(String catName, String description) {
		super();
		this.catName = catName;
		this.description = description;
	}
	
	
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
	
	public void modifyProduct(int position, Products newPxtName) {
		productList.set(position, newPxtName);
		System.out.println("Item " + (position+1) + " has been modified");
	}
	
	
	public void deleteProduct(int position) {
		Products theProduct = productList.get(position);
		productList.remove(position);
		System.out.println(theProduct.getPxtName() + " has been deleted");
	}
	
	
    public Products findProduct(Products searchItem) {
    	boolean exists = productList.contains(searchItem);
    	
    	int position = productList.indexOf(searchItem);
    	if(position >= 0) {
    		return productList.get(position);
    		
    	}
    	
    	return null;
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


	public static ArrayList<Products> getProductList() {
		return productList;
	}


	public static void setProductList(ArrayList<Products> productList) {
		ProductService.productList = productList;
	}
	
	
	
}
