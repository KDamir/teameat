package kz.app.dao;

import kz.app.entity.UsersEntity;

import java.util.List;

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
