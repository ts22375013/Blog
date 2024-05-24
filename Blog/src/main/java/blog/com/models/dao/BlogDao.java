package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	//保存処理と更新処理
	Blog save(Blog blog);
	
	// ブログの一覧を表示させる時に使用
	List<Blog>findAll();

	// ブログの登録チェックに使用（同じブログが登録されないようにチェック）
	Blog findByBlogTitle(String blogTitle);

	// 編集画面を表示する際に使用
	Blog findByBlogId(Long blogId);

	// 削除に使用
	void deleteByBlogId(Long blogId);

}
