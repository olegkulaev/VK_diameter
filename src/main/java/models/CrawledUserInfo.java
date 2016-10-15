package models;

public class CrawledUserInfo {
    public CrawledUserInfo(String firstFriendUrl, String friendsPageUrl, Boolean hasCommonFriends, String commonFriendUrl) {
        this.firstFriendUrl = firstFriendUrl;
        this.friendsPageUrl = friendsPageUrl;
        this.hasCommonFriends = hasCommonFriends;
        this.commonFriendUrl = commonFriendUrl;
    }

    private String firstFriendUrl;
    private String friendsPageUrl;
    private Boolean hasCommonFriends;
    private String commonFriendUrl;

    public String getFirstFriendUrl() {
        return firstFriendUrl;
    }

    public String getFriendsPageUrl() {
        return friendsPageUrl;
    }

    public Boolean hasCommonFriends() {
        return hasCommonFriends;
    }

    public String getCommonFriendUrl() {
        return commonFriendUrl;
    }
}
