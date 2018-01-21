package jp.tst.audittool.apiresource.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jp.tst.audittool.apiresource.entity.UserInfo;
import jp.tst.audittool.apiresource.repository.UserInfoRepository;
import jp.tst.audittool.apiresource.service.UserInfoService;

/**
 * The Class UserInfoServiceImpl.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	/** The UserInfoServiceImpl logger. */
  private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public ResponseEntity<UserInfo> findUserInfoById(HttpServletRequest req, int id) {
		logger.info("IN - findUserInfoById()");
		logger.info("id = {}", id);

		// Find user by id
		UserInfo userInfo = userInfoRepository.findById(id);

		try {
			if (userInfo != null) {
				logger.info("OUT - findUserInfoById()");
				return new ResponseEntity<>(userInfo, HttpStatus.OK);
			}
			logger.info("OUT - findUserInfoById()");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Exception: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


  @Override
  public UserInfo findUserInfoByUsername(String username) {
    logger.info("IN - findUserInfoByUsername()");
    logger.info("id = {}", username);
    // Find user by username
    UserInfo userInfo = userInfoRepository.findUserByUserName(username);
    try {
      if (userInfo != null) {
        logger.info("userInfo = {}", userInfo.toString());
        logger.info("OUT - findUserInfoById()");
        return userInfo;
      }

    } catch (Exception e) {
      logger.error("Exception: {}", e.getMessage());
      return null;
    }
		return userInfo;
  }
}
