package com.smoothstack.lms.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.lms.admin.entity.LibraryBranch;

public interface BranchDAO extends JpaRepository<LibraryBranch, Integer> {

}
