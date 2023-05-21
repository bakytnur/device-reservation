package device.booking.system.service;

import device.booking.system.exception.RecordAlreadyExistsException;
import device.booking.system.exception.UserNotFoundException;
import device.booking.system.model.LoginUser;
import device.booking.system.repository.UserRepository;
import device.booking.system.entity.User;
import device.booking.system.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtService jwtService;

    public boolean registerUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // check if user exists
        if (checkEmailExists(user.getEmail())) {
            throw new RecordAlreadyExistsException("User with the given e-mail already exists.");
        }
        repository.save(user);
        return true;
    }

    private boolean checkEmailExists(String email) {
        User user = repository.findByEmail(email);
        return user != null;
    }

    private boolean checkPasswordMatches(String rawPassword, String loginPassword) {
        return passwordEncoder.matches(rawPassword, loginPassword);
    }

    public String authenticateUser(LoginUser loginUser) {
        User user = repository.findByEmail(loginUser.getEmail());

        if (user == null || !checkPasswordMatches(loginUser.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("User not found!");
        }

        return jwtService.createToken(user.getId(), user.getEmail());
    }
}
