package com.example.framework.entity;

public record UsersRecord
        (int id, String login_id, String password, String name, int role,String created_at,String updated_at) {
}
