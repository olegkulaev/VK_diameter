import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import exceptions.FriendsChainException;
import exporters.CSVChainExporter;
import models.ChainElement;
import models.UserCredentials;
import services.Crawler;
import services.FriendsChainService;
import services.IdService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EntryPoint {

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, FriendsChainException, IOException {
        UserCredentials userCredentials = UserCredentials.getDefault();
        IdService idService = new IdService();
        Crawler crawler = new Crawler(userCredentials.getLogin(), userCredentials.getPassword());
        FriendsChainService friendsChainService = new FriendsChainService(idService, crawler);
        CSVChainExporter csvChainExporter = new CSVChainExporter();

        int chainsCount = 2;
        boolean ignoreExceptions = true;

        List<List<ChainElement>> chains = friendsChainService
                .getRandomFriendsChains(chainsCount, ignoreExceptions)
                .stream()
                .filter(chainElements -> chainElements != null)
                .collect(Collectors.toList());
        csvChainExporter.export("output1.csv", chains);
    }
}
