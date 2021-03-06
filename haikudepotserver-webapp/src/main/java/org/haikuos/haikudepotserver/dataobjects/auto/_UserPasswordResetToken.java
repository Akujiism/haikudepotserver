package org.haikuos.haikudepotserver.dataobjects.auto;

import java.util.Date;

import org.haikuos.haikudepotserver.dataobjects.User;
import org.haikuos.haikudepotserver.dataobjects.support.AbstractDataObject;

/**
 * Class _UserPasswordResetToken was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _UserPasswordResetToken extends AbstractDataObject {

    public static final String CODE_PROPERTY = "code";
    public static final String CREATE_TIMESTAMP_PROPERTY = "createTimestamp";
    public static final String USER_PROPERTY = "user";

    public static final String ID_PK_COLUMN = "id";

    public void setCode(String code) {
        writeProperty(CODE_PROPERTY, code);
    }
    public String getCode() {
        return (String)readProperty(CODE_PROPERTY);
    }

    public void setCreateTimestamp(Date createTimestamp) {
        writeProperty(CREATE_TIMESTAMP_PROPERTY, createTimestamp);
    }
    public Date getCreateTimestamp() {
        return (Date)readProperty(CREATE_TIMESTAMP_PROPERTY);
    }

    public void setUser(User user) {
        setToOneTarget(USER_PROPERTY, user, true);
    }

    public User getUser() {
        return (User)readProperty(USER_PROPERTY);
    }


}
