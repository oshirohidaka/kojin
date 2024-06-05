package com.example.framework.service;

import com.example.framework.entity.UsersRecord;
import com.example.framework.repository.PgUsersDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PgUsersService implements UsersService {
    public PgUsersDao pgUsersDao;
    public PgUsersService(PgUsersDao pgUsersDao) {
        this.pgUsersDao = pgUsersDao;
    }
    @Override
    public List<UsersRecord> findAll() {
        return pgUsersDao.findAll();
    }

    public UsersRecord findById(String login_id){
        return pgUsersDao.findById(login_id);
    }

}
