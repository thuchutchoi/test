package jp.tst.audittool.apiresource.repository;

import jp.tst.audittool.apiresource.entity.UserInfo;

public interface UserInfoRepository {

	/**
	 * Get user info by id
	 *
	 * @param id
	 * @return
	 * @return UserInfo
	 */
	UserInfo findById(int id);

	/**
	 * nghia.nguyen
	 * 
	 * @param username
	 * @return UserInfo
	 */
	UserInfo findUserByUserName(String username);
}
