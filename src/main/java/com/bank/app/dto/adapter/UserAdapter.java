package com.bank.app.dto.adapter;

import com.bank.app.entity.BankUser;
import com.bank.app.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdapter {
    public static UserDTO getUserDTOFromUser(BankUser user) {
        if(user == null)
            return null;
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(RoleAdapter.getRoleDTOListFromRoleList(user.getRoles()));
        return userDTO;
    }

    public static BankUser getUserFromUserDTO(UserDTO userDTO){
        if(userDTO == null) return null;
        BankUser user = new BankUser();

        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setRoles(RoleAdapter.getRoleListFromRoleDTOList(userDTO.getRoles()));

        return user;
    }

    public static List<UserDTO> getUserDTOListFromUserList (List<BankUser> users) {
        if(users == null) return null;
        return users.stream().map(user -> getUserDTOFromUser(user)).collect(Collectors.toList());
    }

    public static List<BankUser> getUserListFromUserDTOList (List<UserDTO> userDTOs) {
        if(userDTOs == null) return null;
        return userDTOs.stream().map(userDTO -> getUserFromUserDTO(userDTO)).collect(Collectors.toList());
    }
}
