package com.letseat.form;

import lombok.Data;

import java.util.Date;

/**
 * @author zpc
 * @date 2018/11/2 11:24
 */
@Data
public class CategoryForm {
    /** 类目Id*/
    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;
    /**
     * 类目创建时间
     */
    private Date createTime;

}
