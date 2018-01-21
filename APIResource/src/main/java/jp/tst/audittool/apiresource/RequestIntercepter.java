package jp.tst.audittool.apiresource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jp.tst.audittool.apiresource.common.APIUtil;
import jp.tst.audittool.apiresource.model.CheckResult;

/**
 * The Class RequestIntercepter.
 */
public class RequestIntercepter implements HandlerInterceptor {
  final Logger logger = LoggerFactory.getLogger(RequestIntercepter.class);

  public RequestIntercepter() {
    super();
  }

  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      Exception arg3) throws Exception {
    // TODO Auto-generated method stub
    logger.info("After completion handle");

  }

  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub
    logger.info("arg1.getStatus(): {}", res.getStatus());
  }

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj)
      throws Exception {
    logger.info("IN - preHandle()");
    // Check common request
    CheckResult result = APIUtil.checkRequestMessageCommon(req);
    if (!result.isSuccess()) {
      // set error code to res
      res.setStatus(HttpStatus.BAD_REQUEST.value());
      logger.warn(result.getMessage());
      return false;
    }
    return true;
  }
}
