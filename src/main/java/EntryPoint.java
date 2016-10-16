import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import exceptions.LoopedFriendChainException;
import exceptions.TooLongFriendsChain;
import models.ChainElement;
import models.UserCredentials;
import services.Crawler;
import services.IdService;
import services.VkExplorerService;

import java.util.List;

public class EntryPoint {

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, LoopedFriendChainException, TooLongFriendsChain {
        UserCredentials userCredentials = UserCredentials.getDefault();
        IdService idService = new IdService();
        Crawler crawler = new Crawler(userCredentials.getLogin(), userCredentials.getPassword());
        VkExplorerService explorerService = new VkExplorerService(idService, crawler);

        String from = "id1";
        List<ChainElement> friendsChain = explorerService.getFriendsChain(from);
        friendsChain.forEach(System.out::println);
    }
}
