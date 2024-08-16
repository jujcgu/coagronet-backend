package com.coagronet.userRole.models;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserRoleId implements Serializable {

    private Long user;
    private Long role;

}
