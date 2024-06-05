package com.example.framework.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsertForm {
    @NotEmpty(message = "商品コードは必須です")
    private String product_id;

    @NotEmpty(message = "商品名は必須です")
    private String name;

    @NotNull(message = "{NotNull_PRICE}")
    private int price;

    private int category_id;
}
