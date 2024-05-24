package blog.com.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller
public class AccountRegisterController {
	
	//accountServiceが使えるように宣言
	@Autowired
	private AccountService accountService;
	
	// 登録画面の表示
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		return "account_register.html";
	}
	
	//登録処理
	@PostMapping("/account/register/process")
	public String accountRegisterprocess(@RequestParam String accountName , @RequestParam String accountEmail , @RequestParam String password) {
		//もし、createAccountがtrueならaccount_login.htmlに遷移
		//そうでない場合、account_register.htmlにとどまる
		if(accountService.createAccount(accountEmail, accountName, password)) {
			return "account_login.html";
		}else {
			return "account_register.html";
		}
	}
}
