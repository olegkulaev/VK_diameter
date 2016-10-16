package exceptions;

import models.ChainElement;

import java.util.List;

public abstract class FriendsChainException extends Exception {
    private List<ChainElement> friendsList;

    protected FriendsChainException(List<ChainElement> friendsList) {
        this.friendsList = friendsList;
    }

    public List<ChainElement> getFriendsList() {
        return friendsList;
    }
}
