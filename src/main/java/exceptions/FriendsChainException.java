package exceptions;

import java.util.List;

public abstract class FriendsChainException extends Exception {
    private List<String> friendsList;

    protected FriendsChainException(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }
}
