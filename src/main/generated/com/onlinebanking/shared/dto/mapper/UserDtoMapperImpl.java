package com.onlinebanking.shared.dto.mapper;

import com.onlinebanking.backend.persistent.domain.User;
import com.onlinebanking.backend.persistent.domain.UserHistory;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.shared.dto.UserDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T09:37:26-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setVersion( user.getVersion() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setCreatedBy( user.getCreatedBy() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setUpdatedBy( user.getUpdatedBy() );
        userDto.setPublicId( user.getPublicId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setEnabled( user.isEnabled() );
        userDto.setVerificationToken( user.getVerificationToken() );
        Set<UserRole> set = user.getUserRoles();
        if ( set != null ) {
            userDto.setUserRoles( new HashSet<UserRole>( set ) );
        }
        Set<UserHistory> set1 = user.getUserHistories();
        if ( set1 != null ) {
            userDto.setUserHistories( new HashSet<UserHistory>( set1 ) );
        }

        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setVersion( userDto.getVersion() );
        user.setCreatedAt( userDto.getCreatedAt() );
        user.setCreatedBy( userDto.getCreatedBy() );
        user.setUpdatedAt( userDto.getUpdatedAt() );
        user.setUpdatedBy( userDto.getUpdatedBy() );
        user.setPublicId( userDto.getPublicId() );
        user.setUsername( userDto.getUsername() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setPhone( userDto.getPhone() );
        user.setVerificationToken( userDto.getVerificationToken() );
        user.setEnabled( userDto.isEnabled() );
        Set<UserRole> set = userDto.getUserRoles();
        if ( set != null ) {
            user.setUserRoles( new HashSet<UserRole>( set ) );
        }
        Set<UserHistory> set1 = userDto.getUserHistories();
        if ( set1 != null ) {
            user.setUserHistories( new HashSet<UserHistory>( set1 ) );
        }

        return user;
    }
}
