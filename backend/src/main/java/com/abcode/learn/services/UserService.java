package com.abcode.learn.services;

import com.abcode.learn.dto.UserDTO;
import com.abcode.learn.entities.User;
import com.abcode.learn.repositories.UserRepository;
import com.abcode.learn.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            logger.error("User not found " + username);
            throw new UsernameNotFoundException("User not found");
        }

        logger.info("User found " + username);
        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }
}
