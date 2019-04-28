package serwisPaczek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serwisPaczek.repository.UserRepository;
@Service
public class FillAdressService {
    @Autowired
    private UserRepository userRepository;

    void fillUserAdress(){

    }

}
