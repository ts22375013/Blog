package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
	// 保存処理と更新処理 insert と update
	Account save(Account account);

	// 管理者の登録処理をする時に、同じメールアドレスがあったならば登録させないようにする
	Account findByAccountEmail(String accountEmail);

	// ログイン処理に使用。入力したメールアドレスとパスワードが一致いているときだけデータを取得
	Account findByAccountEmailAndPassword(String accountEmail, String password);

}
