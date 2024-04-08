package com.shok.planets.model.vo;

import com.shok.planets.model.domain.User;
import lombok.Data;
import java.io.Serializable;


@Data
public class FriendsRecordVO implements Serializable {

    private Long id;

    /**
     * 申请状态 默认0 （0-未通过 1-已同意 2-已过期）
     */
    private Integer status;

    /**
     * 好友申请备注信息
     */
    private String remark;

    /**
     * 申请用户
     */
    private User applyUser;
}
