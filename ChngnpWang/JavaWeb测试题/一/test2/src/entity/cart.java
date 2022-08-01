package entity;
/*
 * 购物车商品实体类
 * */
public class cart {

	private int id; //商品编号
	private String name; //商品名称 
	private float price; //商品单价 
	private int count; //商品数量
	private String createTime; //添加时间 
	private String desc; //商品描述 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public cart(int id, String name, float price, int count, String createTime, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
		this.createTime = createTime;
		this.desc = desc;
	}
	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
