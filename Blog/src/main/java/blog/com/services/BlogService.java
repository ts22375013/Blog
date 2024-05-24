package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	//ブログ一覧のチェック
	public List<Blog>selectAllAccountList(Long accountId){
		if(accountId == null) {
			return null;
		}else {
			return blogDao.findAll();
		}
	}
	
	//商品の登録処理チェック
	public boolean createBlog(String blogTitle, 
			String blogCategoryname , 
			String blogDate , 
			String blogImage , 
			String blogArticle ,
			Long accountId) {
		if(blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle , blogCategoryname , blogDate , blogImage , blogArticle ,accountId));
			return true;
		}else {
			return false;
		}
	}
	
	//編集画面を表示する時のチェック
	public Blog BlogEditCack(Long blogId){
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	//更新処理のチェック
	public boolean blogUpdate(Long blogId , 
			String blogTitle, 
			String blogCategoryname , 
			String blogDate , 
			String blogImage , 
			String blogArticle ,
			Long accountId) {
		if(blogId == null){
			return false;
		}else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setBlogCategoryname(blogCategoryname);
			blog.setBlogDate(blogDate);
			blog.setBlogImage(blogImage);
			blog.setBlogArticle(blogArticle);
			blog.setAccountId(accountId);
			blogDao.save(blog);
			return true;
		}
	}
	
	//削除処理のチェック
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
	
	
}
