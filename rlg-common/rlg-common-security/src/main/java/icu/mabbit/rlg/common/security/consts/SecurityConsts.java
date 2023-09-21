package icu.mabbit.rlg.common.security.consts;

/**
 * <h2>安全模块常量池</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/15 14:09
 */
public interface SecurityConsts
{
    /**
     * Claims存储字段名：用户uuid
     */
    String CLAIMS_KEY_UUID = "uuid";

    /**
     * Claims存储字段名：用户名
     */
    String CLAIMS_KEY_USERNAME = "username";

    /**
     * Claims存储字段名：用户手机号
     */
    String CLAIMS_KEY_PHONE = "phone";

    /**
     * Claims存储字段名：用户邮箱
     */
    String CLAIMS_KEY_EMAIL = "email";

    /**
     * Claims存储字段名：请求IP地址
     */
    String CLAIMS_KEY_IP = "ip";

    /**
     * Claims存储字段名：用户权限集合
     */
    String CLAIMS_KEY_AUTHORITIES = "authorities";

    /**
     * Claims存储字段名：token过期时间
     */
    String CLAIMS_KEY_EXPIRATION_TIME = "expirationTime";
}