package Contracts.Statics;

public class UserStatics {
    private static String currentUserName;
    private static int currentUserId;
    private static int userOffset;

    public static String getCurrentUserName() { return currentUserName; }
    public static int getCurrentUserId() { return currentUserId; }
    public static int getUserOffset() { return userOffset; }

    public static void setCurrentUserName(String userName) {
        currentUserName = userName;
    }

    public static void setCurrentUserId(int id){
        currentUserId = id;
    }

    public static void setUserOffset(int offset) { userOffset = offset; }
}
