package com.snake.game.tools;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.Page;
import com.restfb.types.User;

import static java.lang.System.out;


public class Facebook {

    private String appId = "611409215906032";
    private String appSecret = "adde3a263fc61f995ab94080ff807ab1";
    private String accessToken = "EAAIsEs0gYPABAOwxYgVm7uV6ZCag2GZA14Af3zD3Ky9fmsH1zX9cZBuunES2LfLZCngno4MJvufHDKxb1ZAKZBWIdstPOZBp9RJRczrftrj6zQrlZAPf0SoRLPH7ZCGjcT4izAR4r2UgBnk68dEaXB2PPbxpoyGs9IDqIucLdfNlrPp4ZC5y4RO6sr8ej4Vbk3AIcZD";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";


    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder;
    //private static Page page;

    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_9);
       // page = facebookClient.fetchObject("<page id>", Page.class);
    }

    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);

    }

    public void publishing(int score){

       facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", "I just scored " + score + " in Snake Smash!"));
       //out.println("Published message ID: " + publishMessageResponse.getId());
    }

    public String getAccessToken(){
        return accessToken;
    }


}
