package com.snake.game.tools;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;


public class Facebook {

    private String appId = "611409215906032";
    private String appSecret = "adde3a263fc61f995ab94080ff807ab1";
    private String accessToken = "EAAIsEs0gYPABAOwxYgVm7uV6ZCag2GZA14Af3zD3Ky9fmsH1zX9cZBuunES2LfLZCngno4MJvufHDKxb1ZAKZBWIdstPOZBp9RJRczrftrj6zQrlZAPf0SoRLPH7ZCGjcT4izAR4r2UgBnk68dEaXB2PPbxpoyGs9IDqIucLdfNlrPp4ZC5y4RO6sr8ej4Vbk3AIcZD";
    private String redirectUri = "https://www.facebook.com/";
    private static  Page page;
    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder;


    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
        scopeBuilder.addPermission(FacebookPermissions.MANAGE_PAGES);
        scopeBuilder.addPermission(FacebookPermissions.PUBLISH_PAGES);
        facebookClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_9);
        //page=facebookClient.fetchObject("171321993537921",Page.class);


    }

    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);

    }

    public void publishing(int score){
        String aux =facebookClient.obtainAppAccessToken(appId,appSecret).getAccessToken();
        facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", "I just scored " + score + " in Snake Smash!"));
    //   out.println("Published message ID: " + publishMessageResponse.getId());
    }




    public String getAccessToken(){
        return accessToken;
    }


}