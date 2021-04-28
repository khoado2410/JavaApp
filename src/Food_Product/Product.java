package Food_Product;

public class Product {
	private String productID;
	private String nameProduct;
	private int price;
	private int mass;
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String s) {
		this.productID = s;
	}
	public int getAmount() {
		return mass;
	}
	public void setAmount(int m) {
		this.mass = m;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
