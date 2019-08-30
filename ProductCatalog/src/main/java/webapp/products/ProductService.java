package webapp.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webapp.connect.*;

//import webapp.in28minutes.todo.Todo;

public class ProductService {

	private static List<Category> categoryList = new ArrayList<Category>();

	public List<Category> makeCategoryList() {
		
		
		try {

			Connection conn = new ConnectClass().connect();
			PreparedStatement pst;

			String sql = "SELECT * FROM category_tbl";
			pst = conn.prepareStatement(sql);
			ResultSet result = pst.executeQuery();

			while (result.next()) {
				
				
				addCategory(result.getString("catName"), result.getString("catDescription"));

			}
			
			return categoryList;

		} catch (SQLException e) {

			System.out.println("SQL error = " + e);
			return null;
		}
	}
	
	public void showCatPxt() {
		
		List<Category> myCatList = makeCategoryList();
		for(int i=0; i<myCatList.size(); i++) {
			String catName = myCatList.get(i).getCatName();
			myCatList.get(i).makeProductList(catName);
		}
	}

	
	public boolean addCategory(String catName, String Description) {

		Category newCat = new Category(catName, Description);
		if (findCategory(catName) == null) {

			categoryList.add(newCat);
			System.out.println(newCat.getCatName() + "has been added as a category");
			return true;

		}

		System.out.println(newCat.getCatName() + " Already exist under " + catName);
		return false;
	}

	public void printCategoryList() {

		System.out.println("You have " + categoryList.size() + " items in your categoryList");
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println((i + 1) + ". " + categoryList.get(i).getCatName());

		}
	}

	public boolean updateCategory(String oldCatName, String newCatName, String newDescription) {
		int foundPosition = searchCategory(oldCatName);
		if (foundPosition < 0) {
			System.out.println(oldCatName + ", was not found.");
			return false;
		}

		categoryList.set(foundPosition, new Category(newCatName, newDescription));
		System.out.println(oldCatName + ", was replaced with " + newCatName);
		return true;
	}

	/*
	 * public void modifyProduct(int position, Products newPxt) {
	 * productList.set(position, newPxt); System.out.println("Item " + (position+1)
	 * + " has been modified"); }
	 * 
	 */
	public boolean deleteProduct(String catName) {
		int foundPosition = searchCategory(catName);
		if (foundPosition < 0) {
			System.out.println(catName + ", was not found.");
			return false;
		}

		categoryList.remove(foundPosition);
		System.out.println(catName + " has been deleted");
		return true;
	}

	/*
	 * public void deleteProduct(int position) { Products theProduct =
	 * productList.get(position); productList.remove(position);
	 * System.out.println(theProduct.getPxtName() + " has been deleted"); }
	 */

	private int searchCategory(String searchItem) {
		return categoryList.indexOf(findCategory(searchItem));
	}

	private Category findCategory(String searchItem) {

		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryList.get(i);
			if (category.getCatName().equals(searchItem)) {
				return category;
			}
		}

		return null;
	}

	public String queryCategory(String cat) {
		if (searchCategory(cat) >= 0) {
			return findCategory(cat).getCatName();
		}
		return null;
	}

}
