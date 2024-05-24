package blog.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLoginController {
	
	//accountServiceが使えるように宣言
	@Autowired
	private AccountService accountService;
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private HttpSession session;
	
	//ログイン画面の表示
	@GetMapping("/account/login")
	public String getAccountLoginPage() {
		//account_login.html(ログイン画面を表示)
		return "account_login.html";
	}
	
	//ログイン処理
	@PostMapping("/account/login/process")
	public String accountLoginProcess(@RequestParam String accountEmail , @RequestParam String password) {
		
		//accountServiceのloginCheckメソッドを呼び出してその結果をaccount変数に格納
		Account account = accountService.loginCheck(accountEmail, password);
		
		//もし、account==nullだったらログイン画面にとどまる
		//そうでない場合はsessionにログイン情報に保存
		//ブログ一覧画面(/account/list)にリダイレクトする
		if(account == null) {
			return "account_login.html";
		}else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";
		}
	}
	
}
