package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {
	//AccountDao呼び出し
	@Autowired
	private AccountDao accountDao;
	
	// 保存処理(登録処理)
	public boolean createAccount(String accountEmail, String accountName,  String password) {
		if(accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account( accountEmail, accountName, password));
			return true;
		}else{
			return false;
		}
	}
	// ログイン処理
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if (account == null) {
			return null;
		} else {
			return account;
		}

	}
}