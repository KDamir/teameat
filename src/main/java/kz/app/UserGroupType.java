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
    USER("USER"), SUPERVISOR("SUPERVISOR"), ADMINISTRATOR("ADMINISTRATOR");

    public String getCode() {
        return code;
    }

    String code;

    UserGroupType(String code) {
         this.code = code;
    }





}
