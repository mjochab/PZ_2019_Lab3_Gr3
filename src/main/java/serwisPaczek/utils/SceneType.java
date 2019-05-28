package serwisPaczek.utils;

public enum SceneType {
    // APPLICATION
    MAIN("application/main"),
    LOGIN("application/login"),
    REGISTER("application/register"),

    // ADMIN
    ADMIN_MAIN("admin/mainAdmin"),
    ADMIN_MANAGE_USERS("admin/adminManageUsers"),
    ADMIN_ADD_COURIER("admin/adminAddCourier"),
    ADMIN_EDIT_ABOUT("admin/adminEditAbout"),
    ADMIN_EDIT_COURIER("admin/adminEditCourier"),
    ADMIN_MANAGE_WORKERS("admin/adminManageWorkers"),

    // WORKER
    WORKER_MAIN("worker/mainWorker"),
    WORKER_MANAGE_PARCELS("worker/workerManageParcels"),
    WORKER_MANAGE_GIFT_ORDERS("worker/workerManageGiftOrders"),
    WORKER_EDIT_GIFT("worker/workerEditGift"),
    WORKER_EDIT_COUPON("worker/workerEditCoupons"),

    // USER
    USER_MAIN("user/mainUser"),

    // USER - PROFILE
    USER_PROFILE_EDIT_PROFILE("user.profile/userProfileEditProfile"),
    USER_PROFILE_MY_OPINIONS("user.profile/userProfileMyOpinions"),
    USER_PROFILE_ORDER_LIST("user.profile/userProfileOrderList"),
    USER_PROFILE_WALLET("user.profile/userProfileWallet"),

    // USER - ORDER
    USER_ORDER_MAIN("user.order/userOrderMain"),
    USER_ORDER_CHOOSE_COURIER("user.order/userOrderChooseCourier"), //FXML loader
    USER_ORDER_FILL_ADDRESSES("user.order/userOrderFillAddressesForm"), //FXML loader
    USER_ORDER_FINALIZE("user.order/userOrderFinalize"),
    USER_ORDER_ADD_OPINION("user.order/userOrderAddOpinion"),

    // USER - GIFT
    USER_GIFT_ORDER("user.gift/userGiftOrder"),
    USER_CANCEL_GIFT_ORDER("user.gift/userCancelGiftOrder"),

    // USER - OTHER
    USER_COURIER_COMPANIES_LIST("user.other/userCourierCompaniesList"),
    USER_ABOUT("user.other/userAbout"),
    USER_COURIER_COMPANY_PRICING("user.other/userCourierCompanyPricing"),
    USER_COURIER_OPINIONS("user.other/userCourierOpinions"), //FXML loader
    USER_COURIER_RANKING("user.other/userCouriersRanking");

    private String fxmlPath;

    SceneType(String path) {
        this.fxmlPath = path;
    }

    String getFxmlPath() {
        return this.fxmlPath;
    }
}
