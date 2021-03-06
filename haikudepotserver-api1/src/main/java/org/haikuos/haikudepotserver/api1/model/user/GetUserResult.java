/*
 * Copyright 2013-2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.api1.model.user;

public class GetUserResult {

    public String nickname;

    public String email;

    public Boolean active;

    public Boolean isRoot;

    public Long createTimestamp;

    public Long modifyTimestamp;

    public String naturalLanguageCode;

}
