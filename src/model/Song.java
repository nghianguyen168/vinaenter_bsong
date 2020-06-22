package model;

public class Song {
	private int id;
	private String name;
	private String 	preview_text;
	private String 	detail_text;
	private String date_create;
	private String picture;
	private int counter;
	private int cat_id;
	private String category;
	
	public String getCategoy() {
		return category;
	}
	public void setCategoy(String category) {
		this.category = category;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Song(int id, String name, String picture, int counter, String categoy) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.counter = counter;
		this.category = categoy;
	}
	public Song(int id, String name, String preview_text, String detail_text, String date_create, String picture,
			int counter, int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
	}
	
	
	public Song(int id, String name, String preview_text, String detail_text, String date_create, String picture,
			int counter, String category) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.counter = counter;
		this.category = category;
	}
	
	public Song(int id, String name, String preview_text, String detail_text, String date_create, String picture,
			int counter, int cat_id, String category) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.counter = counter;
		this.cat_id = cat_id;
		this.category = category;
	}
	public Song(int id, String name, int cat_id) {
		super();
		this.id = id;
		this.name = name;
		this.cat_id = cat_id;
	}
	public Song(int id, String name, String preview_text, String date_create, String picture, int counter, int cat_id
			) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.date_create = date_create;
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
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public String getDate_create() {
		return date_create;
	}
	public void setDate_create(String date_create) {
		this.date_create = date_create;
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
	
	
	
}
