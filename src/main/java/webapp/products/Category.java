package webapp.products;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import webapp.connect.ConnectClass;

public class Category {

	private int catID;
	private String catName;
	private String description;
	private String owner;
	


	private List<Products> productList; // = new ArrayList<Products>();

	public Category() {
		this.productList = new ArrayList<Products>();
	}

	public Category(String catName, String description, String owner) {
		super();
		this.catName = catName;
		this.description = description;
		this.owner = owner;
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
		List<Products> newList = new ArrayList<Products>();
		try {

			Connection conn = new ConnectClass().connect();
			PreparedStatement pst;

			String sql = "SELECT * FROM product_tbl WHERE owner = ? GROUP BY pxtID";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet result = pst.executeQuery();

			while (result.next()) {
				
				System.out.println(result.getString("pxtName"));
				
				int pxtID = result.getInt("pxtID");
				
				Blob blobImage = result.getBlob("pxtImage");
				
				InputStream inputStream = blobImage.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String pxtImage = Base64.getEncoder().encodeToString(imageBytes);
				
				inputStream.close();
				outputStream.close();
				
				Products product = new Products();
				
				product.setPxtID(pxtID);
				product.setPxtName(result.getString("pxtName"));
				product.setPxtCategory(result.getString("pxtCategory"));
				product.setPxtOwner(result.getString("owner"));
				product.setPxtImage(pxtImage);
				
				newList.add(product);
				
				//newList.add(addProduct(result.getString("pxtName"), result.getString("pxtCategory"), result.getString("owner"), pxtImage));
				
				

			}

			return newList;

		} catch (Exception e) {

			System.out.println("SQL error = " + e);

			return null;
		}
		
	}
	
	
	
	

	@Override
	public String toString() {
		return String.format("Category [catName=%s, description=%s, owner=%s]", catName, description, owner);
	}

	
	
	
	public Products addProduct(String pxtName, String pxtCategory, String pxtOwner, String pxtImage) {

		Products newPxt = new Products(pxtName, pxtCategory, pxtOwner, pxtImage);
		if (findProduct(pxtName) == null) {

			this.productList.add(newPxt);
			System.out.println(newPxt.getPxtName() + " has been added under " + pxtCategory);
			return newPxt;

		}

		System.out.println(newPxt.getPxtName() + " Already exist under " + pxtCategory);
		return null;
	}
	
	
	

	public void printProductList() {
		System.out.println("You have " + productList.size() + " items in " + catName + "Category");
		for (int i = 0; i < this.productList.size(); i++) {
			System.out.println((i + 1) + ". " + this.productList.get(i).getPxtName());

		}
	}

	
	
	
	public boolean updateProduct(String oldPxtName, String newPxtName, String newPxtCat, String newPxtOwner, String newPxtImage) {
		int foundPosition = searchProduct(oldPxtName);
		if (foundPosition < 0) {
			System.out.println(oldPxtName + ", was not found.");
			return false;
		}

		this.productList.set(foundPosition, new Products(newPxtName, newPxtCat, newPxtOwner, newPxtImage));
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

	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	} 
	


	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public List<Products> getProductList() {
		return productList;
	}

	
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

}
