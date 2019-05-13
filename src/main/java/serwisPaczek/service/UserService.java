package serwisPaczek.service;

import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import serwisPaczek.model.User;
import serwisPaczek.model.dto.UserLoginDto;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.security.Encryption;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;

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

    @Autowired
    private SceneManager sceneManager;

    public void login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {

            UserLoginDto.setLoggedUser(user);

            if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("USER_ROLE"))
                sceneManager.show(SceneType.MAIN_USER);
            else if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("WORKER_ROLE"))
                sceneManager.show(SceneType.WORKER_MAIN);
            else if (UserLoginDto.getLoggedUser().getRole().getRoleName().equals("ADMIN_ROLE"))
                sceneManager.show(SceneType.ADMIN_MAIN);
            else {
                showDialog("Podałeś zły username lub hasło");
            }
        }
    }

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
            passwordWarning.setText("Hasło musi zawierać pomiędzy 6 a 18 znaków");
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
}


