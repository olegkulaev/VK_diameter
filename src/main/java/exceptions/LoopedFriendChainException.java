package exceptions;

import java.util.List;

public class LoopedFriendChainException extends FriendsChainException {
    public LoopedFriendChainException(List<String> friendsList) {
        super(friendsList);
    }
}
