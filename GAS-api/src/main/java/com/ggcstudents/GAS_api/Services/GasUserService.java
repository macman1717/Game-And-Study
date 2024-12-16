package com.ggcstudents.GAS_api.Services;

import com.ggcstudents.GAS_api.Models.GasUser;
import com.ggcstudents.GAS_api.Repositories.GasUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GasUserService implements UserDetailsService {
    private final GasUserRepository repository;
    private final PasswordEncoder encoder;
    public GasUserService(GasUserRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<GasUser> user = repository.findUserByUsername(username);
        return user.map(GasUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(GasUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User Added Successfully";
    }
}
