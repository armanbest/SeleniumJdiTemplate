package com.selenium.entities;

import lombok.Data;

@Data
public class User {
    private final String bin;
    private final String fullName;
    private final String role;
    private final String group;
    private final String desc;
}
