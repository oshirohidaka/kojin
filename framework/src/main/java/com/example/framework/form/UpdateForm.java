package com.example.framework.form;

import lombok.Data;

@Data
public class UpdateForm {
    private int id;
    private String product_id;
    private String name;
    private int price;
    private String categories_name;
}
