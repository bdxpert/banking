package com.bank.app.service;

import com.bank.app.entity.BankUser;
import com.bank.app.dto.UserDTO;
import java.util.Collection;

public interface UserService {
    public Collection<UserDTO> getAllUsers() throws Exception;
    public UserDTO getUser(long userId);
    public UserDTO updateUser(long userId, UserDTO userDTO) throws Exception;
    public void deleteUser(long userId);
    public UserDTO addUser(BankUser userDTO);
}
