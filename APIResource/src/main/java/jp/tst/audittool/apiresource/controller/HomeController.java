package jp.tst.audittool.apiresource.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
    final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showWelcomeMessage() {
        logger.info("Show index page");
        return "<html><body><h1>APIResource is running!</h1></body></html>";
    }
}
