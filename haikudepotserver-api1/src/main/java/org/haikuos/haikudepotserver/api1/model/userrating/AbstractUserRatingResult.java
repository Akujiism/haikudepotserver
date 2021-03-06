/*
 * Copyright 2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.api1.model.userrating;

public class AbstractUserRatingResult {

    public static class PkgVersion {

        public String architectureCode;

        public String major;

        public String minor;

        public String micro;

        public String preRelease;

        public Integer revision;

        public Pkg pkg;

    }

    public static class Pkg {

        public String name;

    }

    public static class User {

        public String nickname;

    }

}
