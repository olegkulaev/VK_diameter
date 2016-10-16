package services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import exceptions.LoopedFriendChainException;
import exceptions.TooLongFriendsChain;
import models.CrawledUserInfo;

import java.util.ArrayList;
import java.util.List;

public class VkExplorerService {
    private final IdService idService;
    private final Crawler crawler;
    private static final int maxChainSize = 10;

    public VkExplorerService(IdService idService, Crawler crawler) {
        this.idService = idService;
        this.crawler = crawler;
    }

    public List<String> getFriendsChain(String from) throws InterruptedException, ClientException, ApiException, LoopedFriendChainException, TooLongFriendsChain {
        ArrayList<String> friendsChain = new ArrayList<>();
        String currentUser = from;
        while (true) {
            if (friendsChain.size() > maxChainSize) {
                throw new TooLongFriendsChain(friendsChain);
            }

            CrawledUserInfo userInfo = crawler.getUserInfo(currentUser);
            Integer userId = idService.extractIdFromFriendsUrl(userInfo.getFriendsPageUrl());
            String literalUserId = "id" + userId;
            if (friendsChain.contains(literalUserId)) {
                throw new LoopedFriendChainException(friendsChain);
            }

            friendsChain.add(literalUserId);
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
