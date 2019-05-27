package serwisPaczek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import serwisPaczek.model.Address;
import serwisPaczek.model.User;
import serwisPaczek.repository.UserRepository;
import serwisPaczek.service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    UserService userService;
    User user;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
    }

    @Test
    void saveAddressToLoggedUser_Test() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals(userService.saveAddressToLoggedUser(user, new Address()),
                user);
    }

    @Test
    void withdrawFunds_Test() {
        double accountBalance = user.getAccountBalance() - 10;
        accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
        user.setAccountBalance(accountBalance);
        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals(userService.withdrawFunds(user, 10), user);
    }

    @Test
    void depositFunds_Test() {
        double accountBalance = user.getAccountBalance() + 10;
        accountBalance = new BigDecimal(accountBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
        user.setAccountBalance(accountBalance);
        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals(userService.depositFunds(user, 10), user);
    }
}
