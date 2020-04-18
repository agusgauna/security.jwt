package ar.com.ada.sb.security.jwt.services;

import ar.com.ada.sb.security.jwt.component.BusinessLogicExceptionComponent;
import ar.com.ada.sb.security.jwt.model.dto.UserDto;
import ar.com.ada.sb.security.jwt.model.entity.security.User;
import ar.com.ada.sb.security.jwt.model.mapper.circular.dependency.CycleAvoidingMappingContext;
import ar.com.ada.sb.security.jwt.model.mapper.circular.dependency.UserCycleMapper;
import ar.com.ada.sb.security.jwt.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userServices")
public class UserServices implements Services<UserDto> {

@Autowired @Qualifier("businessLogicExceptionComponent")
private BusinessLogicExceptionComponent logicExceptionComponent;

@Autowired @Qualifier("userRepository")
private UserRepository userRepository;

private UserCycleMapper userCycleMapper = UserCycleMapper.MAPPER;

@Autowired @Qualifier("cycleAvoidingMappingContext")
private CycleAvoidingMappingContext context;

@Override
public List<UserDto> findAll() {
        List<User> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = userCycleMapper.toDto(userEntityList, context);
        return userDtoList;
        }

public UserDto findUserById(Long id) {
        Optional<User> byIdOptional = userRepository.findById(id);
        UserDto userDto= null;

        if(byIdOptional.isPresent()) {
        User userById = byIdOptional.get();
        userDto = userCycleMapper.toDto(userById, context);
        } else {
        logicExceptionComponent.throwExceptionEntityNotFound("User", id);
        }
        return userDto;
        }

@Override
public UserDto save(UserDto dto) {
        User userToSave = userCycleMapper.toEntity(dto, context);
        User userSaved = userRepository.save(userToSave);
        UserDto userDtoSaved = userCycleMapper.toDto(userSaved, context);
        return userDtoSaved;
        }

public UserDto updateUser(UserDto userDtoToUpdate, Long id){
        Optional<User> byIdOptional = userRepository.findById(id);
        UserDto userDtoUpdated = null;

        if(byIdOptional.isPresent()) {
        User userById = byIdOptional.get();
        userDtoToUpdate.setId(userById.getId());
        User userToUpdate = userCycleMapper.toEntity(userDtoToUpdate, context);
        User userUpdated = userRepository.save(userToUpdate);
        userDtoUpdated = userCycleMapper.toDto(userUpdated, context);

        } else {
        logicExceptionComponent.throwExceptionEntityNotFound("User", id);
        }
        return userDtoUpdated;
    }

@Override
public void delete(Long id) {
        Optional<User> byIdOptional = userRepository.findById(id);
        if (byIdOptional.isPresent()){
        User userToDelete = byIdOptional.get();
        userRepository.delete(userToDelete);
        }  else {
        logicExceptionComponent.throwExceptionEntityNotFound("User", id);
        }
    }
}
