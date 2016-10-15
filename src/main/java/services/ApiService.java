package services;

import com.google.gson.Gson;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.util.List;

public class ApiService {
    private TransportClient transportClient;
    private VkApiClient vk;
    public ApiService(){
        transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient, new Gson());
    }

    public List<Integer> getFriendsList(Integer userId) throws ClientException, ApiException {
        return vk
                .friends()
                .get()
                .userId(userId)
                .execute()
                .getItems();
    }
}
