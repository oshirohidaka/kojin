package com.example.framework.service;

import com.example.framework.entity.UsersRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    List<UsersRecord> findAll();

    UsersRecord findById(String login_id);


}
