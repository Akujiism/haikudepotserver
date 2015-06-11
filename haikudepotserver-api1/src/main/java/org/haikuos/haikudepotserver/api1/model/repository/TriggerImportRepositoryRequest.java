/*
 * Copyright 2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.api1.model.repository;

import java.util.Set;

public class TriggerImportRepositoryRequest {

    public String repositoryCode;

    /**
     * @since 2015-06-11
     */

    public Set<String> repositorySourceCodes;

}
