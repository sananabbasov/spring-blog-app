package az.edu.compar.security;

import az.edu.compar.entities.User;
import az.edu.compar.exceptions.ResourceNotFoundException;
import az.edu.compar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).filter(x->x.getEmailConfirmed() == true).orElseThrow(()-> new ResourceNotFoundException("User", "email: " + username, 0L));
        return user;
    }
}
