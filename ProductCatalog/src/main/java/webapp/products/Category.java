package webapp.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webapp.connect.ConnectClass;

public class Category {

	private String catName;
	private String description;
	private List<Products> productList; // = new ArrayList<Products>();

	public Category() {

	}

	public Category(String catName, String description) {
		super();
		this.catName = catName;
		this.description = description;
		this.productList = new ArrayList<Products>();
	}

	/*
	 * public Category(String catName, String description) { super(); this.catName =
	 * catName; this.description = description; }
	 */

	/*
	 * public void addProduct(String pxtName) { Products newProduct = new
	 * Products(pxtName); productList.add(newProduct); System.out.println(newProduct
	 * + "has been added under " + catName); }
	 */

	public List<Products> makeProductList(String name) {
		try {

			Connection conn = new ConnectClass().connect();
			PreparedStatement pst;

			String sql = "SELECT * FROM product_tbl WHERE catName = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet result = pst.executeQuery();

			while (result.next()) {

				addProduct(result.getString("pxtName"), result.getString("pxtCategory"), result.getString("owner"));

			}

			return productList;

		} catch (SQLException e) {

			System.out.println("SQL error = " + e);

			return null;
		}
	}

	@Override
	public String toString() {
		return String.format("Category [catName=%s, description=%s, productList=%s]", catName, description,
				productList);
	}

	public boolean addProduct(String pxtName, String pxtCategory, String pxtOwner) {

		Products newPxt = new Products(pxtName, pxtCategory, pxtOwner);
		if (findProduct(pxtName) == null) {

			this.productList.add(newPxt);
			System.out.println(newPxt.getPxtName() + "has been added under " + catName);
			return true;

		}

		System.out.println(newPxt.getPxtName() + " Already exist under " + catName);
		return false;
	}

	public void printProductList() {
		System.out.println("You have " + productList.size() + "items in " + catName + "Category");
		for (int i = 0; i < this.productList.size(); i++) {
			System.out.println((i + 1) + ". " + this.productList.get(i).getPxtName());

		}
	}

	public boolean updateProduct(String oldPxtName, String newPxtName, String newPxtCat, String newPxtOwner) {
		int foundPosition = searchProduct(oldPxtName);
		if (foundPosition < 0) {
			System.out.println(oldPxtName + ", was not found.");
			return false;
		}

		this.productList.set(foundPosition, new Products(newPxtName, newPxtCat, newPxtOwner));
		System.out.println(oldPxtName + ", was replaced with " + newPxtName);
		return true;
	}

	/*
	 * public void modifyProduct(int position, Products newPxt) {
	 * productList.set(position, newPxt); System.out.println("Item " + (position+1)
	 * + " has been modified"); }
	 * 
	 */
	public boolean deleteProduct(String pxtName) {
		int foundPosition = searchProduct(pxtName);
		if (foundPosition < 0) {
			System.out.println(pxtName + ", was not found.");
			return false;
		}

		this.productList.remove(foundPosition);
		System.out.println(pxtName + " has been deleted");
		return true;
	}

	/*
	 * public void deleteProduct(int position) { Products theProduct =
	 * productList.get(position); productList.remove(position);
	 * System.out.println(theProduct.getPxtName() + " has been deleted"); }
	 */

	private int searchProduct(String searchItem) {
		return this.productList.indexOf(findProduct(searchItem));
	}

	private Products findProduct(String searchItem) {

		for (int i = 0; i < this.productList.size(); i++) {
			Products product = this.productList.get(i);
			if (product.getPxtName().equals(searchItem)) {
				return product;
			}
		}

		return null;
	}

	public String queryProduct(String pxt) {
		if (searchProduct(pxt) >= 0) {
			return findProduct(pxt).getPxtName();
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

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

}
