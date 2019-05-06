package serwisPaczek.utils;

public enum SceneType {
    MAIN("main"),
    ABOUT("about"),
    CHOOSE_COURIER("chooseCourierCompany"),
    COURIER_LIST("courierCompaniesList"),
    COURIER_RANKING("courierRanking"),
    FILL_ADRESSESS("fillAdressessForm"),
    FINALIZE("finalize"),
    LOGIN("login"),
    ORDER("order"),
    PRICE_LIST("priceList"),
    REGISTER("register"),
    WORKER_PANEL("workerPanel"),
    ORDER_LIST("orderList"),
    ADD_COURIER("addCourier"),
    EDIT_USER("editUserProfile"),
    USER_MENU("mainUser"),
    ADMIN_MENU("mainAdmin"),
    ADMIN_USERS_LIST("usersList"),
    WORKER_MENU("mainWorker"),
    MENU("main"),
    ADD_OPINION("addOpinion"),
    MY_OPINIONS("myOpinions"),
    COURIER_OPINIONS("courierOpinions"),
    EDIT_COURIER("editCourier"),
    USERS_LIST("usersList"),
    EDIT_ABOUT("editAbout"),
    GIFT_ORDER("giftOrder"),
    EDIT_GIFT("editGift"),
    ORDER_GIFT("orderGift");

    private String fxmlPath;

    SceneType(String path) {
        this.fxmlPath = path;
    }

    String getFxmlPath() {
        return this.fxmlPath;
    }
}
