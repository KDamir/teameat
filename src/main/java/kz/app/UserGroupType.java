package kz.app;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 11.09.2014
 * Time: 23:23
 */
public enum UserGroupType {
    USER("vendor"), SUPERVISOR("SUPERVISOR"), ADMINISTRATOR("admin");

    public String getCode() {
        return code;
    }

    String code;

    UserGroupType(String code) {
         this.code = code;
    }





}
