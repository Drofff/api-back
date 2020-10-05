package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class DtoMapper<E, D> {

    private final ModelMapper modelMapper;

    private final Type entityType;
    private final Type dtoType;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.entityType = getGenericTypeParamAtPosition(0);
        this.dtoType = getGenericTypeParamAtPosition(1);
    }

    private Type getGenericTypeParamAtPosition(int pos) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[pos];
    }

    public final List<D> toDtos(List<E> entities) {
        return entities.stream().map(this::toDto).collect(toList());
    }

    public D toDto(E entity) {
        return modelMapper.map(entity, dtoType);
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto, entityType);
    }

}
