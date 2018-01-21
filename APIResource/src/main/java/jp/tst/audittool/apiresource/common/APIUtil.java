package jp.tst.audittool.apiresource.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import jp.tst.audittool.apiresource.model.CheckResult;

/**
 * The Class Util.
 */
public class APIUtil {

  private APIUtil() {
    throw new IllegalStateException("Utility class");
  }

  /** The logger. */
  private final static Logger logger = LoggerFactory.getLogger(APIUtil.class);

  /**
   * Check HTTP Request.
   *
   * @param req HttpServletRequest
   * @return the check result
   */
  public static CheckResult checkRequestMessageCommon(HttpServletRequest req) {
    logger.info("IN - checkRequestMessageCommon()");

    CheckResult result = new CheckResult(true, CheckResult.MSG_OK);
    if (!MediaType.APPLICATION_JSON_VALUE.equals(req.getContentType()) || (!req.getMethod().equals(RequestMethod.GET.toString())) && !req.getMethod().equals(RequestMethod.DELETE.toString())) {
      result.setSuccess(false);
      result.setMessage(CheckResult.MSG_HEADER_NG);
      logger.debug("OUT - checkRequestMessageCommon()");
      return result;
    }

    logger.info("OUT - checkRequestMessageCommon()");
    return result;
  }

  /**
   * Gets the base directory.
   *
   * @return the baseDirectory
   * @throws ExceptionInInitializerError the exception in initializer error
   */
  public static String getBaseDirectory() throws ExceptionInInitializerError {
    logger.info("IN - getBaseDirectory()");
    String baseDirectory = null;
    Properties baseProperties = new Properties();
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();

    // Read base directory
    try (InputStream inputStream = classloader.getResourceAsStream("apiresource.conf")) {
      baseProperties.load(inputStream);
      if (baseProperties.containsKey(APIConstant.BASE_DIRECTORY)) {
        baseDirectory = baseProperties.getProperty(APIConstant.BASE_DIRECTORY);
        logger.info("Base directory is: {}", baseDirectory);
        logger.info("OUT - getBaseDirectory()");
      } else {
        logger.error("Have no setting {}", APIConstant.BASE_DIRECTORY);
        throw new ExceptionInInitializerError();
      }
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new ExceptionInInitializerError();
    }
    return baseDirectory;
  }

  /**
   * Read properties file.
   *
   * @param filePath the file path
   * @return the properties
   */
  public static Properties readPropertiesFile(String filePath) {
    logger.info("IN - readPropertiesFile()");
    Properties properties = null;

    try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
      properties = new Properties();
      properties.load(fileInputStream);

      logger.info("OUT - readPropertiesFile()");
    } catch (Exception e) {
      logger.error("readPropertiesFile error" + e.getMessage());
    }
    return properties;
  }
}
