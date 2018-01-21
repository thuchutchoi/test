package jp.tst.audittool.apiresource.repositoryimpl;


import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.tst.audittool.apiresource.entity.UserInfo;
import jp.tst.audittool.apiresource.repository.AbstractRepository;
import jp.tst.audittool.apiresource.repository.UserInfoRepository;

@Repository("UserInfoRepository")
@Transactional
public class UserInfoRepositoryImp extends AbstractRepository<Integer, UserInfo> implements UserInfoRepository {
	@Override
	public UserInfo findById(int id) {
		UserInfo userInfo = getByKey(id);

		if (userInfo != null) {
			Hibernate.initialize(userInfo.getUserRole());
		}
		return userInfo;
	}

	@Override
	public UserInfo findUserByUserName(String username) {
		try {
			Query query = getSession().createSQLQuery("select * from userinfo where username = 'admin'")
					.addEntity(UserInfo.class);

			UserInfo uniqueResult = (UserInfo) query.uniqueResult();
			return uniqueResult;
		} catch (Exception e) {
			return null;
		}

	}
}
