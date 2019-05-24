package serwisPaczek.service;

import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.security.Encryption;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static serwisPaczek.utils.DialogsUtils.showDialog;
import static serwisPaczek.utils.TextFieldUtils.isCorrect;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encryption encryption;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    private SceneManager sceneManager;

    /**
     * This method is used to log in the user and check correctness of the given data by user.
     *
     * @param all Data needed to log in.
     */
    public void login(String username, String password, Text usernameWarning, Text passwordWarning) {

        if (username.length() <= 3 || !isCorrect(username)) {
            usernameWarning.setText("Username musi posiadać conajmniej 4 znaki!");
            usernameWarning.setVisible(true);
        }
        else usernameWarning.setVisible(false);

        if (password.length() <= 5 || !isCorrect(password)) {
            passwordWarning.setText("Password musi posiadać conajmniej 6 znaków!");
            passwordWarning.setVisible(true);
        }
        else passwordWarning.setVisible(false);

        if (isCorrect(username) && isCorrect(password) && password.length() >= 6 && username.length() >= 4) {
            User user = userRepository.findByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {

                if (user.getActive()) {
                    UserLoginDto.setLoggedUser(user);
                    if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("USER_ROLE"))
                        sceneManager.show(SceneType.USER_MAIN);
                    else if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("WORKER_ROLE"))
                        sceneManager.show(SceneType.WORKER_MAIN);
                    else if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("ADMIN_ROLE"))
                        sceneManager.show(SceneType.ADMIN_MAIN);
                } else {
                    showDialog("Twoje konto jest zablokowane!");
                }
            } else {
                showDialog("Podałeś złą nazwę użytkownika lub hasło!");
            }
        }
    }


    /**
     * This method is used to create a new user and check correctness of the given data by user.
     *
     * @param all Data needed to create an account.
     */
    public void createUser(String username, String password, String repeatPassword,
                           Text usernameWarning, Text passwordWarning, Text repeatPasswordWarning) {
        usernameWarning.setVisible(false);
        passwordWarning.setVisible(false);
        repeatPasswordWarning.setVisible(false);

        if (!isCorrect(username)) usernameWarning.setVisible(true);
        if (!isCorrect(password)) passwordWarning.setVisible(true);
        if (!isCorrect(repeatPassword)) repeatPasswordWarning.setVisible(true);

        if (!password.equals(repeatPassword)) {
            passwordWarning.setText("Hasła się różnią");
            repeatPasswordWarning.setText("Hasła się różnią");
            passwordWarning.setVisible(true);
            repeatPasswordWarning.setVisible(true);
        }

        if (password.length() < 6 || password.length() > 18) {
            passwordWarning.setText("Hasło musi zawierać pomiędzy 6 a 18 znaków!");
            passwordWarning.setVisible(true);
        }

        if (isCorrect(username) && isCorrect(password) && isCorrect(repeatPassword)
                && password.equals(repeatPassword)
                && password.length() >= 6
                && password.length() <= 18) {

            if (userRepository.findByUsername(username) != null) {
                showDialog("Użytkownik o podanej nazwie użytkownika istnieje!");
            } else {
                User user = new User(username, encryption.encode(password), roleRepository.findByRoleName(
                        "USER_ROLE"));
                userRepository.save(user);
                sceneManager.show(SceneType.LOGIN);
            }
        }
    }

    /**
     * This method is used to withdraw funds from user wallet.
     *
     * @param user  The user who wants withdraw funds from his wallet.
     * @param funds The value of withdrawn funds.
     */
    public User withdrawFunds(User user, double funds) {
        double accountBalance = user.getAccountBalance() - funds;
        accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
        user.setAccountBalance(accountBalance);
        return userRepository.save(user);
    }

    /**
     * This method is used to deposit funds to user wallet.
     *
     * @param user  The user who wants deposit funds to his wallet.
     * @param funds The value of deposited funds.
     */
    public User depositFunds(User user, double funds) {
        double accountBalance = user.getAccountBalance() + funds;
        accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
        user.setAccountBalance(accountBalance);
        return userRepository.save(user);
    }

    public User saveAddressToLoggedUser(User user, Adress adress){
        user.setAdress(adress);
        return userRepository.save(user);
    }
    @Autowired
    public void setSceneManager(SceneManager sceneManager) { this.sceneManager = sceneManager; }
}


