package exceptions;

import models.ChainElement;

import java.util.List;

public abstract class FriendsChainException extends Exception {
    private List<ChainElement> friendsChain;

    FriendsChainException(List<ChainElement> friendsChain) {
        this.friendsChain = friendsChain;
    }

    public List<ChainElement> getFriendsChain() {
        return friendsChain;
    }
}
