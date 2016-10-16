package exceptions;

import models.ChainElement;

import java.util.List;

public class LoopedFriendChainException extends FriendsChainException {
    public LoopedFriendChainException(List<ChainElement> friendsList) {
        super(friendsList);
    }
}
