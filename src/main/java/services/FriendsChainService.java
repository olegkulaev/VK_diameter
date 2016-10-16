package services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import exceptions.FriendsChainException;
import exceptions.LoopedFriendChainException;
import exceptions.TooLongFriendsChain;
import models.ChainElement;
import models.CrawledUserInfo;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

public class FriendsChainService {
    private final IdService idService;
    private final Crawler crawler;
    private static final int maxChainSize = 10;

    public FriendsChainService(IdService idService, Crawler crawler) {
        this.idService = idService;
        this.crawler = crawler;
    }

    public List<List<ChainElement>> getRandomFriendsChains(int chainsCount, boolean ignoreExceptions) throws InterruptedException, FriendsChainException, ApiException, ClientException {
        List<String> randomIds = idService.generateIds(chainsCount);
        return getFriendsChains(randomIds, ignoreExceptions);
    }

    public List<List<ChainElement>> getFriendsChains(List<String> fromIds, boolean ignoreExceptions) throws InterruptedException, FriendsChainException, ApiException, ClientException {
        ArrayList<List<ChainElement>> result = new ArrayList<>();
        for (String from :
                fromIds) {
            try {
                result.add(getFriendsChain(from));
            } catch (FriendsChainException e) {
                result.add(e.getFriendsChain());
                if (!ignoreExceptions){
                    throw e;
                }
            } catch (NoSuchElementException e) {
                System.out.println("exception occured while processing: "+from);
                System.out.println(e);
                if (!ignoreExceptions) {
                    throw e;
                }
            }
        }
        return result;
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