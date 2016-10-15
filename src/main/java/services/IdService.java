package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdService {
    private final Pattern friendsPageUrlPattern = Pattern.compile("id=(.*?)&");
    private final Pattern userUrlPattern = Pattern.compile("/([^/]+)$");

    /**
     * extract Integer Vk Id from friends page url
     * @param url url of friends page, always have a format 'https://vk.com/friends?id=19299070&section=all'
     * @return integer id of user
     */
    public int extractIdFromFriendsUrl(String url){
        Matcher matcher = friendsPageUrlPattern.matcher(url);
        return matcher.find()
                ? Integer.parseInt(matcher.group(1))
                : -1;
    }

    public String extractIdFromUserUrl(String url){
        Matcher matcher = userUrlPattern.matcher(url);
        return matcher.find()
                ? matcher.group(1)
                : null;
    }
}
