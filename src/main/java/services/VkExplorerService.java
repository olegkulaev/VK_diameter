package services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import models.CrawledUserInfo;

import java.util.ArrayList;
import java.util.List;

public class VkExplorerService {
    private final IdService idService;
    private final Crawler crawler;

    public VkExplorerService(IdService idService, Crawler crawler) {
        this.idService = idService;
        this.crawler = crawler;
    }

    public List<String> getFriendsChain(String from, Integer to) throws InterruptedException, ClientException, ApiException {
        ArrayList<String> friendsChain = new ArrayList<>();
        String currentUser = from;
        while (true) {
            CrawledUserInfo userInfo = crawler.getUserInfo(currentUser);
            Integer userId = idService.extractIdFromFriendsUrl(userInfo.getFriendsPageUrl());
            friendsChain.add("id" + userId);
            if (userInfo.hasCommonFriends()) {
                friendsChain.add(idService.extractIdFromUserUrl(userInfo.getCommonFriendUrl()));
                break;
            }

            String firstFriendUrl = userInfo.getFirstFriendUrl();
            currentUser = idService.extractIdFromUserUrl(firstFriendUrl);
        }
        return friendsChain;
    }
}
