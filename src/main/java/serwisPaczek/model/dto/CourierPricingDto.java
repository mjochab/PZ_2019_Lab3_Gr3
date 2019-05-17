package serwisPaczek.model.dto;

import lombok.Data;
import serwisPaczek.model.Courier;

@Data
public class CourierPricingDto {
    private Courier courier;
    private String courier_name;
    boolean isBlocked;
    private String txtEnvelope_up_to_1;
    private String txtPack_up_to_1;
    private String txtPack_up_to_2;
    private String txtPack_up_to_5;
    private String txtPack_up_to_10;
    private String txtPack_up_to_15;
    private String txtPack_up_to_20;
    private String txtPack_up_to_30;
    private String txtPallet_up_to_300;
    private String txtPallet_up_to_500;
    private String txtPallet_up_to_800;
    private String txtPallet_up_to_1000;
    private float envelope_up_to_1;
    private float pack_up_to_1;
    private float pack_up_to_2;
    private float pack_up_to_5;
    private float pack_up_to_10;
    private float pack_up_to_15;
    private float pack_up_to_20;
    private float pack_up_to_30;
    private float pallet_up_to_300;
    private float pallet_up_to_500;
    private float pallet_up_to_800;
    private float pallet_up_to_1000;

    public CourierPricingDto(Courier courier, String courier_name, boolean isBlocked, String txtEnvelope_up_to_1, String txtPack_up_to_1, String txtPack_up_to_2, String txtPack_up_to_5, String txtPack_up_to_10, String txtPack_up_to_15, String txtPack_up_to_20, String txtPack_up_to_30, String txtPallet_up_to_300, String txtPallet_up_to_500, String txtPallet_up_to_800, String txtPallet_up_to_1000) {
        this.courier = courier;
        this.courier_name = courier_name;
        this.isBlocked = isBlocked;
        this.txtEnvelope_up_to_1 = txtEnvelope_up_to_1;
        this.txtPack_up_to_1 = txtPack_up_to_1;
        this.txtPack_up_to_2 = txtPack_up_to_2;
        this.txtPack_up_to_5 = txtPack_up_to_5;
        this.txtPack_up_to_10 = txtPack_up_to_10;
        this.txtPack_up_to_15 = txtPack_up_to_15;
        this.txtPack_up_to_20 = txtPack_up_to_20;
        this.txtPack_up_to_30 = txtPack_up_to_30;
        this.txtPallet_up_to_300 = txtPallet_up_to_300;
        this.txtPallet_up_to_500 = txtPallet_up_to_500;
        this.txtPallet_up_to_800 = txtPallet_up_to_800;
        this.txtPallet_up_to_1000 = txtPallet_up_to_1000;
    }

    public CourierPricingDto(String courier_name, String txtEnvelope_up_to_1, String txtPack_up_to_1, String txtPack_up_to_2, String txtPack_up_to_5, String txtPack_up_to_10, String txtPack_up_to_15, String txtPack_up_to_20, String txtPack_up_to_30, String txtPallet_up_to_300, String txtPallet_up_to_500, String txtPallet_up_to_800, String txtPallet_up_to_1000) {
        this.courier_name = courier_name;
        this.txtEnvelope_up_to_1 = txtEnvelope_up_to_1;
        this.txtPack_up_to_1 = txtPack_up_to_1;
        this.txtPack_up_to_2 = txtPack_up_to_2;
        this.txtPack_up_to_5 = txtPack_up_to_5;
        this.txtPack_up_to_10 = txtPack_up_to_10;
        this.txtPack_up_to_15 = txtPack_up_to_15;
        this.txtPack_up_to_20 = txtPack_up_to_20;
        this.txtPack_up_to_30 = txtPack_up_to_30;
        this.txtPallet_up_to_300 = txtPallet_up_to_300;
        this.txtPallet_up_to_500 = txtPallet_up_to_500;
        this.txtPallet_up_to_800 = txtPallet_up_to_800;
        this.txtPallet_up_to_1000 = txtPallet_up_to_1000;
    }

    public boolean validate() {
        if (courier_name.length() > 0
                && txtEnvelope_up_to_1.matches("\\d+(\\.\\d{1,2})?") && txtEnvelope_up_to_1.length() > 0
                && txtPack_up_to_1.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_1.length() > 0
                && txtPack_up_to_2.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_2.length() > 0
                && txtPack_up_to_5.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_5.length() > 0
                && txtPack_up_to_10.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_10.length() > 0
                && txtPack_up_to_15.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_15.length() > 0
                && txtPack_up_to_20.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_20.length() > 0
                && txtPack_up_to_30.matches("\\d+(\\.\\d{1,2})?") && txtPack_up_to_30.length() > 0
                && txtPallet_up_to_300.matches("\\d+(\\.\\d{1,2})?") && txtPallet_up_to_300.length() > 0
                && txtPallet_up_to_500.matches("\\d+(\\.\\d{1,2})?") && txtPallet_up_to_500.length() > 0
                && txtPallet_up_to_800.matches("\\d+(\\.\\d{1,2})?") && txtPallet_up_to_800.length() > 0
                && txtPallet_up_to_1000.matches("\\d+(\\.\\d{1,2})?") && txtPallet_up_to_1000.length() > 0) {
            envelope_up_to_1 = Float.parseFloat(txtEnvelope_up_to_1);
            pack_up_to_1 = Float.parseFloat(txtPack_up_to_1);
            pack_up_to_2 = Float.parseFloat(txtPack_up_to_2);
            pack_up_to_5 = Float.parseFloat(txtPack_up_to_5);
            pack_up_to_10 = Float.parseFloat(txtPack_up_to_10);
            pack_up_to_15 = Float.parseFloat(txtPack_up_to_15);
            pack_up_to_20 = Float.parseFloat(txtPack_up_to_20);
            pack_up_to_30 = Float.parseFloat(txtPack_up_to_30);
            pallet_up_to_300 = Float.parseFloat(txtPallet_up_to_300);
            pallet_up_to_500 = Float.parseFloat(txtPallet_up_to_500);
            pallet_up_to_800 = Float.parseFloat(txtPallet_up_to_800);
            pallet_up_to_1000 = Float.parseFloat(txtPallet_up_to_1000);
            return true;
        } else {
            return false;
        }
    }
}