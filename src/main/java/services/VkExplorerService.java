package services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import exceptions.LoopedFriendChainException;
import exceptions.TooLongFriendsChain;
import models.ChainElement;
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

    public List<ChainElement> getFriendsChain(String from) throws InterruptedException, ClientException, ApiException, LoopedFriendChainException, TooLongFriendsChain {
        ArrayList<ChainElement> friendsChain = new ArrayList<>();
        String currentUser = from;
        while (true) {
            if (friendsChain.size() > maxChainSize) {
                throw new TooLongFriendsChain(friendsChain);
            }

            CrawledUserInfo userInfo = crawler.getUserInfo(currentUser);
            Integer userId = idService.extractIdFromFriendsUrl(userInfo.getFriendsPageUrl());
            ChainElement chainElement = new ChainElement("id" + userId, userInfo.getFriendsCount());

            if (friendsChain.contains(chainElement)) {
                throw new LoopedFriendChainException(friendsChain);
            }

            friendsChain.add(chainElement);
            if (userInfo.hasCommonFriends()) {
                chainElement = new ChainElement(idService.extractIdFromUserUrl(userInfo.getCommonFriendUrl()), -1);
                friendsChain.add(chainElement);
                break;
            }

            String firstFriendUrl = userInfo.getFirstFriendUrl();
            currentUser = idService.extractIdFromUserUrl(firstFriendUrl);
        }
        return friendsChain;
    }
}