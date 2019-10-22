package com.mhc.springboot.dao.entity;

import com.mhc.springboot.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mhc
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String description;

    private Boolean disabled;

    private String email;

    private String mobile;

    private String nickname;

    private String password;

    private String username;

    private String userType;


}
