import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import models.UserCredentials;
import services.Crawler;
import services.IdService;
import services.VkExplorerService;

import java.util.List;

public class EntryPoint {

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        UserCredentials userCredentials = UserCredentials.getDefault();
        IdService idService = new IdService();
        Crawler crawler = new Crawler(userCredentials.getLogin(), userCredentials.getPassword());
        VkExplorerService explorerService = new VkExplorerService(idService, crawler);

        String from = "id12941656";
        Integer to = userCredentials.getId();
        List<String> friendsChain = explorerService.getFriendsChain(from, to);
        for (String friendUrl :
                friendsChain) {
            System.out.println(friendUrl);
        }
    }
}
