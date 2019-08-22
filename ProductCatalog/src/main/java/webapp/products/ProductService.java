package webapp.products;

import java.util.ArrayList;
import java.util.List;

//import webapp.in28minutes.todo.Todo;

public class ProductService {
	
	private static List<Category> categoryList = new ArrayList<Category>();

	
	

	public boolean addProduct(String catName, String Description) {
		
		Category newCat = new Category(catName, Description);
		if(findCategory(catName) == null) {
			
			categoryList.add(newCat);
			System.out.println(newCat.getCatName() + "has been added under " + catName);
			return true;
			
		}
		
		System.out.println(newCat.getCatName() +" Already exist under " + catName);
		return false;
	}
	
	
	public void printCategoryList() {
		
		System.out.println("You have " + categoryList.size() + " items in your categoryList Category");
		for(int i=0; i<categoryList.size(); i++) {
			System.out.println((i+1) + ". " + categoryList.get(i).getCatName());
			
		}
	}
	
	public boolean updateCategory(String oldCatName, String newCatName, String newDescription) {
		int foundPosition = searchCategory(oldCatName);
		if(foundPosition < 0 ) {
			System.out.println(oldCatName + ", was not found.");
			return false;
		}
		
		categoryList.set(foundPosition, new Category(newCatName, newDescription));
		System.out.println(oldCatName + ", was replaced with " + newCatName);
		return true;
	}

/*
	public void modifyProduct(int position, Products newPxt) {
		productList.set(position, newPxt);
		System.out.println("Item " + (position+1) + " has been modified");
	}
	
*/	
	public boolean deleteProduct(String catName) {
		int foundPosition = searchCategory(catName);
		if(foundPosition < 0) {
			System.out.println(catName + ", was not found.");
			return false;
		}
		
		categoryList.remove(foundPosition);
		System.out.println(catName + " has been deleted");
		return true;
	}
	
/*
	public void deleteProduct(int position) {
		Products theProduct = productList.get(position);
		productList.remove(position);
		System.out.println(theProduct.getPxtName() + " has been deleted");
	}
*/	
	
	private int searchCategory(String searchItem) {
		return categoryList.indexOf(findCategory(searchItem));
	}
	
    private Category findCategory(String searchItem) {
    	
    	
    	for(int i=0; i<categoryList.size(); i++) {
    		Category category = categoryList.get(i);
    		if(category.getCatName().equals(searchItem)) {
    			return category;
    		}
    	}
    		
    		return null;
    }
    
    
    public String queryCategory(String cat) {
    	if(searchCategory(cat) >= 0) {
    		return findCategory(cat).getCatName();
    	}
    	return null;
    }
    
    
    


   

	
	

}

