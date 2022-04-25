package cn.king.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: wjl@king.cn
 * @time: 2020/4/5 0:01
 * @version: 1.0.0
 * @description: 基础的实体类;
 */
@Data
@MappedSuperclass
// 该注解能配合@CreatedDate、@LastModifiedDate等注解使用。https://www.jianshu.com/p/30aef87f3171
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "唯一主键ID | Unique primary key ID")
    private Long id;

    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(value = "名称 | name")
    private String name;

    // 默认会将驼峰转下划线定义为表字段名
    @ApiModelProperty(value = "排序值 | Record sort order")
    private Integer sortOrder;

    @ApiModelProperty(value = "状态 | Record sort order")
    private Integer status;

    @CreatedDate
    // 响应到前台的格式
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台需要传递的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间 | Record created time")
    private Date createdTime;

    @LastModifiedDate
    // 响应到前台的格式
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台需要传递的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间 | Record updated time")
    private Date updatedTime;

    @CreatedBy
    @ApiModelProperty(value = "创建者 | Record Created by")
    private String createdBy;

    @LastModifiedBy
    @ApiModelProperty(value = "更新者 | Record updated by")
    private String updatedBy;

    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "起始创建时间，用于范围查询")
    private Date createdTimeBegin;

    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束创建时间，用于范围查询")
    private Date createdTimeEnd;

    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "起始最后更新时间，用于范围查询")
    private Date updatedTimeBegin;

    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束最后更新时间，用于范围查询")
    private Date updatedTimeEnd;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
