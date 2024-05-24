package blog.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	
	//blogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private HttpSession session;
	
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		
		//accountServiceのloginCheckメソッドを呼び出してその結果をaccount変数に格納
		Account account =  (Account) session.getAttribute("loginAccountInfo");
		
		//もし、account=nullログイン画面にリダイレクトする
		if(account == null) {
			return "redirect:/account/login";
		}else {
			//もし、deleteBlogの結果がtrueだったら
			if(blogService.deleteBlog(blogId)) {
				//ブログ一覧ページにリダイレクトする
				return "redirect:/blog/list";
			}else {
				//そうでない場合編集画面にリダイレクトする
				return "redirect:/blog/edit"+blogId;
			}
			
		}
	}
}
