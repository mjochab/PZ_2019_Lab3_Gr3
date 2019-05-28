package serwisPaczek.model.dto;

import serwisPaczek.model.User;

public class UserLoginDto {

    private static User loggedUser;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }
}
