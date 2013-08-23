import javax.annotation.Resource;

import org.junit.Test;

import com.pousheng.demo.dao.user.UserDao;
import com.pousheng.demo.model.user.User;


public class Demo extends BaseDaoTest {

	@Resource
	private UserDao userDao;
	@Test
	public void test(){
		User u = new User();
		u.setUserId("1111111111111111111");
		userDao.deleteByPk("11");
		System.out.println(u.getUserId());
	}
}
