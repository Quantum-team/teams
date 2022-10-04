package com.liu.pojo;

/**
 *
 * @author 刘老师
 *
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 * - User 用户类
 */
//@Getter
//@Entity
//@Table(name = "t_user")
public class User extends Bean {

    private String username;
    private String password;
    private String type;
    
//	@JsonFormat(pattern="dd MMM yyyy hh:mm:ss")
//	private LocalDateTime lastLogin;
//	
//	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSXXX", locale = "en_SG", timezone = "Asia/Singapore")
//	private ZonedDateTime zonedDateTime;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

}
