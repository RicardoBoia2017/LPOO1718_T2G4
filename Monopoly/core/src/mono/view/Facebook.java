package mono.view;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

/**
 * Class responsible for Facebook information
 */
public class Facebook {
    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder; 
    /**
     * Creates a Facebook object
     */
    public Facebook() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient(Version.VERSION_2_9);
    }
    /**
     * Logs in Facebook
     */
    public void login() {
        String appId = "219828565488329";
        String redirectUrl = "https://www.facebook.com/connect/login_success.html";
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUrl, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);
    }
}