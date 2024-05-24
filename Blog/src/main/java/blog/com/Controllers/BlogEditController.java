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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	
	//blogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;
	
	//sessionが使えるように宣言
	//複数のページリクエストにわたってユーザーを識別したり、Web サイトにアクセスしたり、そのユーザーに関する情報を保存したりする方法を提供
	@Autowired
	private HttpSession session;

	// 編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		
		//セッションからログインしている人の情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
		//もし、account=nullログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
			//編集画面に表示させる情報を変数に格納 blog
			Blog blog = blogService.BlogEditCack(blogId);
			
			// 編集画面を表示
			//もし、blog==nullだったら、ブログ一覧ページにリダイレクトする。
			//そうでない場合、編集画面に編集内容を渡す。
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				//画面に渡したいデータをModelオブジェクトに追加
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}
		}
	}

	// 更新処理。
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle 
			, @RequestParam String blogDate
			, @RequestParam String blogCategory
			, @RequestParam MultipartFile blogImage
			, @RequestParam String blogArticle
			, @RequestParam Long blogId) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.blogUpdate(blogId, blogTitle, blogDate, blogCategory, fileName, blogArticle ,
					account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}

	}

}
