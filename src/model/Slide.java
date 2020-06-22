package model;

public class Slide {
	private int id;
	private String name;
	private String picture;
	private int counter;
	private int cat_id;
	private String category;
	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slide(int id, String name, String picture, int counter, int cat_id, String category) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
		this.category = category;
	}
	
	public Slide(int id, String name, String picture, int counter, String category) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.counter = counter;
		this.category = category;
	}
	
	public Slide(int id, String name, String picture, int counter, int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
	}
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
