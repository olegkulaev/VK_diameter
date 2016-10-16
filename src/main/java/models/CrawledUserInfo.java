package models;

public class CrawledUserInfo {
    public CrawledUserInfo(String firstFriendUrl, String friendsPageUrl, Boolean hasCommonFriends, String commonFriendUrl, Integer friendsCount) {
        this.firstFriendUrl = firstFriendUrl;
        this.friendsPageUrl = friendsPageUrl;
        this.hasCommonFriends = hasCommonFriends;
        this.commonFriendUrl = commonFriendUrl;
        this.friendsCount = friendsCount;
    }

    private String firstFriendUrl;
    private String friendsPageUrl;
    private Boolean hasCommonFriends;
    private String commonFriendUrl;
    private Integer friendsCount;

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

    public Integer getFriendsCount() {
        return friendsCount;
    }
}
