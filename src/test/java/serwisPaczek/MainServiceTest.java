package serwisPaczek;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import serwisPaczek.model.Adress;
import serwisPaczek.model.User;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.security.Encoder;
import serwisPaczek.security.Encryption;
import serwisPaczek.service.UserService;
import serwisPaczek.utils.SceneType;
import serwisPaczek.utils.TextFieldUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static serwisPaczek.utils.TextFieldUtils.isCorrect;

public class MainServiceTest {

    @InjectMocks
    private SceneType sceneType;
    @InjectMocks
    private Encoder encoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User(1L,"username","password",
                true,10, roleRepository.findByRoleName("ROLE_USER"));

    }

    @Test
    void passwordEncoder_Test(){
        assertNotNull(encoder.passwordEncoder());
    }

}
