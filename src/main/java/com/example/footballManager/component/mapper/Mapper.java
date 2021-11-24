package com.example.footballManager.component.mapper;

public interface Mapper <D, E> {
	
	E toEntity(D d);
	D toDto(E e);

}
