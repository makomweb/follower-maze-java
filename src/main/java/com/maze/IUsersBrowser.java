package com.maze;

import java.util.Collection;

public interface IUsersBrowser {
	User get(int id);
	Collection<User> getAll();
}
