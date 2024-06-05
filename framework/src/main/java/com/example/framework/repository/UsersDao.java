package com.example.framework.repository;

import com.example.framework.entity.UsersRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsersDao {
    List<UsersRecord> findAll();

    UsersRecord findById(String login_id);
}
