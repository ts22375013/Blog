package blog.com.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	
	//blogServiceが使えるように宣言
	@Autowired
	private HttpSession session;
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private BlogService blogService;
	
	//ブログ一覧画面を表示
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		
		//セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
		//もし、account==nullならログイン画面にリダイレクトする
		//そうでない場合、ログインしている人の名前とAccountIdの情報を画面に渡してブログ一覧のhtmlを表示。
		if(account == null) {
			return "redirect:/account/login";
		}else {
			//AccountIdの情報を取得しblogListに格納
			List<Blog> blogList = blogService.selectAllAccountList(account.getAccountId());
			//画面に渡したいデータをModelオブジェクトに追加
			model.addAttribute("blogList" , blogList);
			model.addAttribute("accountName" , account.getAccountName());
			return "blog_list.html";
		}
	}
	
}
