package cn.daniel.repository;

import org.springframework.data.repository.CrudRepository;

import cn.daniel.entity.User;

public interface UserDao extends CrudRepository<User, String> {
}
