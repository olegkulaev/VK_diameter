package models;

public class ChainElement {
    public ChainElement(String id, int friendsCount) {
        this.id = id;
        this.friendsCount = friendsCount;
    }

    private String id;
    private int friendsCount;

    public String getId() {
        return id;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChainElement that = (ChainElement) o;

        return friendsCount == that.friendsCount && id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + friendsCount;
        return result;
    }

    @Override
    public String toString() {
        return "User Id: " + getId() + ", friends count: " + getFriendsCount();
    }
}
