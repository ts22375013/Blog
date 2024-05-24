package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//blogテーブル
@Entity
public class Blog {
	//blog_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	//blog_titleの設定
	private String blogTitle;
	//blog_categorynameの設定
	private String blogCategoryname;
	//blog_dateの設定
	private String blogDate;
	//blog_imageの設定
	private String blogImage;
	//blog_articleの設定
	private String blogArticle;
	
	//account_idの設定
	private Long accountId;

	//空のコンストラクタ
	public Blog() {

	}

	//コンストラクタ
	public Blog(String blogTitle, String blogCategoryname, String blogDate, String blogImage, String blogArticle,
			Long accountId) {
		this.blogTitle = blogTitle;
		this.blogCategoryname = blogCategoryname;
		this.blogDate = blogDate;
		this.blogImage = blogImage;
		this.blogArticle = blogArticle;
		this.accountId = accountId;
	}

/* ---ゲッターセッター----------------------------------------------------------------------------------------------- */	
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogCategoryname() {
		return blogCategoryname;
	}

	public void setBlogCategoryname(String blogCategoryname) {
		this.blogCategoryname = blogCategoryname;
	}

	public String getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(String blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getBlogArticle() {
		return blogArticle;
	}

	public void setBlogArticle(String blogArticle) {
		this.blogArticle = blogArticle;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	} 
	
	
	
	
	
	
}
