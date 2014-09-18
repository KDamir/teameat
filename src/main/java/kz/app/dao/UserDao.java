package kz.app.dao;

import java.util.List;

import kz.app.entity.UsersEntity;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 16:17
 */
public interface UserDao {
	public List<UsersEntity> getUser(String name);
}
