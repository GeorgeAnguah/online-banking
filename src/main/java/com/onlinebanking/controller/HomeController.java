package com.onlinebanking.controller;

import com.onlinebanking.constant.HomeConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController class managers all incoming request to the home page.
 *
 * @author George on 6/19/2021
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(HomeConstant.INDEX_URL_MAPPING)
public class HomeController {

    /**
     * Maps index url request.
     * @return index view name
     */
    public String home() {
        return HomeConstant.INDEX_VIEW_NAME;
    }
}
