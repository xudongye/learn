package me.own.learn.pubaccount.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.wechat.pubaccount.menu.MenuService;
import me.own.commons.wechat.pubaccount.menu.impl.MenuServiceImpl;
import me.own.commons.wechat.pubaccount.menu.model.PubAccountButton;
import me.own.commons.wechat.pubaccount.menu.model.PubAccountMenu;
import me.own.learn.pubaccount.service.MenuCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MenuCreateServiceImpl implements MenuCreateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuCreateServiceImpl.class);

    private MenuService menuService = new MenuServiceImpl();

    @Autowired
    private LishuConfigurationService configurationService;

    @Autowired
    private PubAccountMenuService pubAccountMenuService;

    @Override
    public void createMenu(String appId) {
        try {
            menuService.createMenu(appId, readMenu(appId));
        } catch (IOException e) {
            LOGGER.error("read menu IO error.", e);
            throw new RuntimeException(e);
        }
    }

    private PubAccountMenu readMenu(String appId) throws IOException {
        String menuContent = pubAccountMenuService.queryMenuContentByAppId(appId);
        PubAccountMenu menu = new ObjectMapper().readValue(menuContent, PubAccountMenu.class);
        setMenuButtonAppId(appId, menu);
        return menu;
    }

    @SuppressWarnings("unused")
    private PubAccountMenu readMenuFromFile(String appId) throws IOException {
        //File menuFile = new File(Thread.currentThread().getContextClassLoader().getResource("pub-account.menu.json").getFile());
        // @see ref link: http://www.cnblogs.com/0616--ataozhijia/p/4094952.html
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pub-account.menu.json");
        PubAccountMenu menu = new ObjectMapper().readValue(inputStream, PubAccountMenu.class);
        setMenuButtonAppId(appId, menu);
        return menu;
    }

    private void setMenuButtonAppId(String appId, PubAccountMenu menu){
        for(PubAccountButton menuButton : menu.getButton()){
            setMenuAccountButton(appId, menuButton);
        }
    }

    private void setMenuAccountButton(String appId, PubAccountButton menuButton){
        String domain = configurationService.getConfiguration().getDomain();
        String url = menuButton.getUrl();
        if(StringUtils.isNotEmpty(url)){
            url = url.replace("{{APPID}}", appId).replace("{{DOMAIN}}", domain);
            menuButton.setUrl(url);
        }
        if(menuButton.getSub_button() != null){
            for(PubAccountButton subMenuButton : menuButton.getSub_button()){
                setMenuAccountButton(appId, subMenuButton);
            }
        }
    }
}
