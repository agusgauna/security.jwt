package ar.com.ada.sb.security.jwt.model.mapper.circular.dependency;

import ar.com.ada.sb.security.jwt.model.dto.UserDto;
import ar.com.ada.sb.security.jwt.model.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface UserCycleMapper extends DataCycleMapper<UserDto, User> {
    UserCycleMapper MAPPER = Mappers.getMapper(UserCycleMapper.class);
}