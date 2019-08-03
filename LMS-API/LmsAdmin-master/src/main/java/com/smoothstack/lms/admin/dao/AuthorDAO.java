package com.smoothstack.lms.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.lms.admin.entity.Author;

public interface AuthorDAO extends JpaRepository<Author, Integer>{

}
