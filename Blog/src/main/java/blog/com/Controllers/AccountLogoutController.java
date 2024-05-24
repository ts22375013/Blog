package blog.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLogoutController {
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private HttpSession session;
	
	//ログアウト処理
	@GetMapping("/account/logout")
	public String accountLogout() {
		//セッションの無効化
		//ログイン画面(/account/login)にリダイレクトする
		session.invalidate();
		return "redirect:/account/login";
	}
	
}
