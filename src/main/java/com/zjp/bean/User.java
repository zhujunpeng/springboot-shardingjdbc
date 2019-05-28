package com.zjp.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 对应数据库t_user表
 * 对应数据库字段转换成驼峰模式  如果是userName就会转换为user_name
 */
@Data
@Table(name = "t_user")
@NameStyle(Style.camelhumpAndLowercase)
public class User implements Serializable {

	// 标记这个字段为主键
	@Id
	// 申明字段与数据库的对应
	@Column(name = "id")
	// 根据主键查询或者更新一定要加上主键策略
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	// 指定数据库与实体类的对应字段
//	@Column(name = "username")
	@NotBlank
	private String username;

	private Integer userId;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

	private Date createTime;

	@NotNull
	private Integer age;
	private BigDecimal balance;
	// 指定这个字段不与数据库对应
	@Transient
	private String testStr;
	// 乐观锁
	@Version
	private Integer version;

	@Transient
	private List<String> userIds;
}
