package com.smoothstack.lms.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.admin.entity.*;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher, Integer> {

}
