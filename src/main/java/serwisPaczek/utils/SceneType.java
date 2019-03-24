package serwisPaczek.utils;


public enum SceneType {
    MAIN("main"),
    ABOUT("about"),
    CHOOSE_COURIER("chooseCourierCompany"),
    COURIER_RANKING("courierRanking"),
    FILL_ADRESSESS("fillAdressessForm"),
    FINALIZE("finalize"),
    LOGIN("login"),
    ORDER("order"),
    PRICE_LIST("priceList"),
    REGISTER("register");

    private String fxmlPath;

    SceneType(String path) {
        this.fxmlPath = path;
    }

    String getFxmlPath() {
        return this.fxmlPath;
    }

}
