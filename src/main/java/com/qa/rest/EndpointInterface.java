package com.qa.rest;

import java.util.Optional;

public interface EndpointInterface<T extends Object> {

	Iterable<T> getAll();

	String delete(String id);

	String update(String idToUpdate, T updated);

}