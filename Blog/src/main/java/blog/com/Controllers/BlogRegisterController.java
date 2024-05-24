package blog.com.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	
	//blogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private HttpSession session;
	
	//ブログ画面の表示
	@GetMapping("/blog/register")
	public String getblogRegisterPage(Model model) {
		
		//セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
		//もし、account=nullログイン画面にリダイレクトする
		//そうでない場合は、ログインしている人の名前を画面に出す
		//ブログ登録のhtmlを表示させる
		if(account == null) {
			return "redirect:/account/login";
		}else {
			model.addAttribute("accountName" , account.getAccountName());
			return "blog_register.html";
		}
	}
	
	//ブログの登録処理
	@PostMapping("/blog/register/process")
	public String blogRegiserProcess(@RequestParam String blogTitle 
			, @RequestParam String blogDate
			, @RequestParam String blogCategory
			, @RequestParam MultipartFile blogImage
			, @RequestParam String blogArticle) {
		
		//セッションからログインしてい人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
		//もし、account=nullログイン画面にリダイレクトする
		//そうでない場合は、画像のファイル名を取得、画像のアップロード
		if(account == null) {
			return "redirect:/account/login";
		}else {
			//ファイル名を取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
			//ファイルの保存作業
			try {
				Files.copy(blogImage.getInputStream() , Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//もし、同じファイルの名前がなかった場合ブログの一覧画面にリダイレクトする
			//そうでない場合は商品登録画面にとどまる。
			if(blogService.createBlog(blogTitle, blogCategory, blogDate, fileName, blogArticle, account.getAccountId())) {
				return "redirect:/blog/list";
			}else {
				return "blog_register.html";
			}
			
		}
		
		
	} 
	
	
}
