package serwisPaczek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import serwisPaczek.model.User;
import serwisPaczek.repository.RoleRepository;
import serwisPaczek.security.Encoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import sun.security.util.Password;

public class MainServiceTest {

    User user;

    @InjectMocks
    private Encoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User(1L, "username", "password",
                true, 10, roleRepository.findByRoleName("ROLE_USER"));

    }

    @Test
    void passwordEncoder_Test() {
        assertNotNull(encoder.passwordEncoder());
    }

}
