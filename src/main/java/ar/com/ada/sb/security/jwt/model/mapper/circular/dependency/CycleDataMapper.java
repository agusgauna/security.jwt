package ar.com.ada.sb.security.jwt.model.mapper.circular.dependency;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface CycleDataMapper<D, E> {
    E toEntity(D dto, @Context CycleAvoidingMappingContext context);

    List<E> toEntity(List<D> dtoList, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    D toDto(E entity, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<D> toDto(List<E> entityList, @Context CycleAvoidingMappingContext context);
}
