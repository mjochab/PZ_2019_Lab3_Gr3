package serwisPaczek.utils;

public enum SceneType {
    MAIN("main"),
    ABOUT("about"),
    ADMIN_MAIN("adminPanel/adminMain"),
    ADMIN_MANAGE_USERS("adminPanel/adminManageUsers"),
    ADMIN_MANAGE_PARCELS("adminPanel/adminManageParcels"),
    ADMIN_ADD_COURIER("adminPanel/adminAddCourier"),
    ADMIN_EDIT_ABOUT("adminPanel/adminEditAbout"),
    ADMIN_EDIT_COURIER("adminPanel/adminEditCourier"),


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

    EDIT_USER("editUserProfile"),
    USER_MENU("mainUser"),

    ADMIN_USERS_LIST("usersList"),
    WORKER_MENU("mainWorker"),
    MENU("main"),
    ADD_OPINION("addOpinion"),
    MY_OPINIONS("myOpinions"),
    COURIER_OPINIONS("courierOpinions"),


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
