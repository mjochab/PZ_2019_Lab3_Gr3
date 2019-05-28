package serwisPaczek.controller.user.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.*;
import serwisPaczek.repository.*;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.io.IOException;
import java.util.Date;

import static serwisPaczek.model.dto.UserLoginDto.getLoggedUser;
import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.SceneManager.stage;

@Controller
public class UserOrderFillAddressesFormController {
    private SceneManager sceneManager;
    private ApplicationContext context;
    private Parcel parcel;
    private Courier courier;
    private double price;

    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private SenderAddressRepository senderAddressRepository;
    @Autowired
    private RecipientAddressRepository recipientAddressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CourierRepository courierRepository;

    @FXML
    private TextField TFsenderSurname;
    @FXML
    private TextField TFhouseNumber;
    @FXML
    private TextField TFsenderHouseNumber;
    @FXML
    private TextField TFsenderStreet;
    @FXML
    private TextField TFemail;
    @FXML
    private TextField TFzipCode;
    @FXML
    private CheckBox fillAddressCheckbox;
    @FXML
    private TextField TFnr;
    @FXML
    private TextField TFsenderCity;
    @FXML
    private TextField TFspot;
    @FXML
    private TextField TFsenderName;
    @FXML
    private TextField TFsenderEmail;
    @FXML
    private TextField TFname;
    @FXML
    private TextField TFstreet;
    @FXML
    private TextField TFsenderNr;
    @FXML
    private TextField TFsurname;
    @FXML
    private TextField TFsenderZipCode;

    @FXML
    public void initialize(Parcel parcel, Courier courier, double price) {
        this.parcel = parcel;
        this.courier = courier;
        this.price = price;
        fillAddressCheckbox.setVisible(false);
        if (getLoggedUser().getAddress() != null) {
            fillAddressCheckbox.setVisible(true);
        }
    }


    /**
     * Method responsible for filling in given addresses
     *
     * @throws IOException
     */
    @FXML
    public void openFinalizePanel() throws IOException {

        StringBuilder message = new StringBuilder();
        try {

            if (checkEmptyFields()) {
                showDialog("Pozostawiłeś puste pola!");
                return;
            }

            if (TFname.getText().length() <= 2) {
                message.append("Imię odbiorcy musi zawierać więcej niż dwa znaki! \n");
            }
            if (!(TFname.getText().matches("[a-zA-Z]+"))) {
                message.append("Imię odbiorcy musi zawierać wyłącznie małe oraz duże litery! \n");
            }
            if (TFsurname.getText().length() <= 2) {
                message.append("Nazwisko odbiorcy musi zawierać więcej niż dwa znaki! \n");
            }
            if (!(TFsurname.getText().matches("[a-zA-Z]+"))) {
                message.append("Nazwisko odbiorcy musi zawierać wyłącznie małe oraz duże litery!\n");
            }
            if (TFspot.getText().length() <= 2) {
                message.append("Nazwa miasta odbiorcy musi zawierać więcej niż dwa znaki!\n");
            }
            if (!TFspot.getText().matches("[a-zA-Z]+")) {
                message.append("Nazwa miasta odbiorcy musi posiadać wyłącznie małe oraz duże litery!\n");
            }
            if (!TFemail.getText().matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")) {
                message.append("Email musi byc w formacie x@x.pl o długosci pomiędzy 2 a 30 znaków\n");
            }
            if (!TFzipCode.getText().matches("[0-9]{2}+-[0-9]{3}")) {
                message.append("Kod pocztowy odbiorcy musi byc w formule XX-XXX, gdzie X jest cyfrą!\n");
            }
            if (TFhouseNumber.getText().length() <= 0) {
                message.append("Numer domu odbiorcy nie może być ujemny\n");
            }
            if (!TFhouseNumber.getText().matches("\\d+")) {
                message.append("Numer domu odbiorcy musi być liczbą!\n");
            }
            if (TFnr.getText().length() != 9) {
                message.append("Numer telefonu odbiorcy musi posiadać 9 cyfr!\n");
            }
            if (!TFnr.getText().matches("\\d+")) {
                message.append("Numer telefonu odbiorcy musi być składać się wyłącznie z cyfr!\n");
            }
            if (!TFstreet.getText().matches("[A-Z0-9a-z._%+-]{2,64}")) {
                message.append("Ulica odbiorcy musi zawierać się pomiędzy 2 a 64 znakami!\n");
            }
            if (TFsenderName.getText().length() <= 2) {
                message.append("Imię nadawcy musi zawierać więcej niż dwa znaki! \n");
            }
            if (!(TFsenderName.getText().matches("[a-zA-Z]+"))) {
                message.append("Imię nadawcy musi zawierać wyłącznie małe oraz duże litery! \n");
            }
            if (TFsenderSurname.getText().length() <= 2) {
                message.append("Nazwisko nadawcy musi zawierać więcej niż dwa znaki! \n");
            }
            if (!(TFsenderSurname.getText().matches("[a-zA-Z]+"))) {
                message.append("Nazwisko nadawcy musi zawierać wyłącznie małe oraz duże litery!\n");
            }
            if (TFsenderCity.getText().length() <= 2) {
                message.append("Nazwa miasta nadawcy musi zawierać więcej niż dwa znaki!\n");
            }
            if (!TFsenderCity.getText().matches("[a-zA-Z]+")) {
                message.append("Nazwa miasta nadawcy musi posiadać wyłącznie małe oraz duże litery!\n");
            }
            if (!TFsenderEmail.getText().matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}")) {
                message.append("Email musi byc w formacie x@x.pl o długosci pomiędzy 2 a 30 znaków\n");
            }
            if (!TFsenderZipCode.getText().matches("[0-9]{2}+-[0-9]{3}")) {
                message.append("Kod pocztowy nadawcy musi byc w formule XX-XXX, gdzie X jest cyfrą!\n");
            }
            if (TFsenderHouseNumber.getText().length() <= 0) {
                message.append("Numer domu nadawcy nie może być ujemny\n");
            }
            if (!TFsenderHouseNumber.getText().matches("\\d+")) {
                message.append("Numer domu nadawcy musi być liczbą!\n");
            }
            if (TFsenderNr.getText().length() != 9) {
                message.append("Numer telefonu nadawcy musi posiadać 9 cyfr!\n");
            }
            if (!TFsenderNr.getText().matches("\\d+")) {
                message.append("Numer telefonu nadawcy musi być składać się wyłącznie z cyfr!\n");
            }
            if (!TFsenderStreet.getText().matches("[A-Z0-9a-z._%+-]{2,64}")) {
                message.append("Ulica nadawcy musi zawierać się pomiędzy 2 a 64 znakami!\n");
            }
            if (message.length() == 0) {

                Address sender = new Address(TFname.getText(), TFsurname.getText(), TFspot.getText(), TFstreet.getText(),
                        Integer.parseInt(TFhouseNumber.getText()), TFzipCode.getText(), Long.parseLong(TFnr.getText()),
                        TFemail.getText());

                Address received = new Address(TFsenderName.getText(), TFsenderSurname.getText(), TFsenderCity.getText(),
                        TFsenderStreet.getText(), Integer.parseInt(TFsenderHouseNumber.getText()), TFsenderZipCode.getText(),
                        Long.parseLong(TFsenderNr.getText()), TFsenderEmail.getText());

                addressRepository.save(received);
                addressRepository.save(sender);

                RecipientAddress recipientaddress = recipientAddressRepository.save(new RecipientAddress(received));
                SenderAddress senderaddress = senderAddressRepository.save(new SenderAddress(sender));

                UserOrder order = orderRepository.save(new UserOrder(price, new Date(), getLoggedUser(),
                        courier,
                        Status.WYSLANO_ZGLOSZENIE, senderaddress, recipientaddress));

                userService.withdrawFunds(getLoggedUser(), price);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user.order/userOrderFinalize.fxml"));
                    loader.setControllerFactory(context::getBean);
                    Parent root = loader.load();
                    UserOrderFinalizeController finalizeController = loader.getController();
                    finalizeController.initialize(order, sender, received, courier, parcel);
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showDialog(message.toString());
        }
    }

    /**
     * Method is autocompleting senderAddress if its not null
     */
    @FXML
    public void fillAddress() {
        if (fillAddressCheckbox.isSelected()) {
            TFname.setText(getLoggedUser().getAddress().getName());
            TFsurname.setText(getLoggedUser().getAddress().getSurname());
            TFstreet.setText(getLoggedUser().getAddress().getStreet());
            TFnr.setText(getLoggedUser().getAddress().getTelephoneNumber().toString());
            TFemail.setText(getLoggedUser().getAddress().getEmail());
            TFhouseNumber.setText(getLoggedUser().getAddress().getHouseNumber().toString());
            TFspot.setText(getLoggedUser().getAddress().getCity());
            TFzipCode.setText(getLoggedUser().getAddress().getZipCode());
        } else {
            TFname.setText("");
            TFsurname.setText("");
            TFstreet.setText("");
            TFnr.setText("");
            TFemail.setText("");
            TFhouseNumber.setText("");
            TFspot.setText("");
            TFzipCode.setText("");
        }
    }

    //to test
    public boolean checkEmptyFields() {
        return TFname.getText().isEmpty() || TFsurname.getText().isEmpty()
                || TFspot.getText().isEmpty() ||
                TFstreet.getText().isEmpty() ||
                TFhouseNumber.getText().isEmpty() || TFzipCode.getText().isEmpty() ||
                TFnr.getText().isEmpty() ||
                TFemail.getText().isEmpty() ||
                TFsenderName.getText().isEmpty() || TFsenderSurname.getText().isEmpty()
                || TFsenderCity.getText().isEmpty()
                || TFsenderStreet.getText().isEmpty() ||
                TFsenderHouseNumber.getText().isEmpty() || TFsenderZipCode.getText().isEmpty() ||
                TFsenderNr.getText().isEmpty() ||
                TFsenderEmail.getText().isEmpty();
    }

    @FXML
    public void openUserMain(ActionEvent event) {
        sceneManager.show(SceneType.USER_MAIN);
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
