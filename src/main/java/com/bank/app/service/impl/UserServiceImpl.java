package com.bank.app.service.impl;

import com.bank.app.entity.Role;
import com.bank.app.entity.BankUser;
import com.bank.app.dto.UserDTO;
import com.bank.app.dto.adapter.RoleAdapter;
import com.bank.app.dto.adapter.UserAdapter;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Collection<UserDTO> getAllUsers() throws Exception {
        Collection<UserDTO> result = new ArrayList<>();
        Collection<BankUser> users = userRepository.findAll();
        for(BankUser user: users) {
            result.add(UserAdapter.getUserDTOFromUser(user));
        }
        return result;
    }

    @Override
    public UserDTO getUser(long memberId) {
        BankUser user = userRepository.findById(memberId);
        return UserAdapter.getUserDTOFromUser(user);
    }

    @Transactional
    @Override
    public UserDTO updateUser(long userId, UserDTO userDTO) throws Exception {
        BankUser user = null;
        try{
            user = userRepository.findById(userId);
            List<Role> roles = new ArrayList<>();
            user.setRoles(RoleAdapter.getRoleListFromRoleDTOList(userDTO.getRoles()));
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getLastName());
            user = userRepository.save(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Unable to update user");
        }
        return UserAdapter.getUserDTOFromUser(user);
    }

    @Override
    public void deleteUser(long memeberId) {
        userRepository.deleteById(memeberId);
    }

    @Override
    public UserDTO addUser(BankUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserAdapter.getUserDTOFromUser(user);
    }
}
