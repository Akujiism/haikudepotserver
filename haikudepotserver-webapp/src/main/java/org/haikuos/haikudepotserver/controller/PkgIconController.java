/*
 * Copyright 2013, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.controller;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.haikuos.haikudepotserver.model.Pkg;
import org.haikuos.haikudepotserver.services.PkgIconService;
import org.haikuos.haikudepotserver.services.model.BadPkgIconException;
import org.haikuos.haikudepotserver.support.Closeables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>This controller vends the package icon.  This may be provided by data stored in the database, or it may be
 * deferring to the default icon.  This controller is also able to take an HTTP PUT request that is able to
 * update a packages icon.</p>
 */

@Controller
@RequestMapping("/pkgicon")
public class PkgIconController {

    protected static Logger logger = LoggerFactory.getLogger(WebResourceGroupController.class);

    public final static String KEY_PKGNAME = "pkgname";
    public final static String KEY_FORMAT = "format";
    public final static String KEY_SIZE = "s";

    @Resource
    ServerRuntime serverRuntime;

    @Resource
    PkgIconService pkgIconService;

    @RequestMapping(value = "/{"+KEY_PKGNAME+"}.{"+KEY_FORMAT+"}", method = RequestMethod.GET)
    public void fetch(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = KEY_SIZE, required = true) int size,
            @PathVariable(value = KEY_FORMAT) String format,
            @PathVariable(value = KEY_PKGNAME) String pkgName)
            throws IOException {

        if(size != 16 && size != 32) {
            throw new BadSize();
        }

        if(Strings.isNullOrEmpty(pkgName) || !Pkg.NAME_PATTERN.matcher(pkgName).matches()) {
            throw new MissingPkgName();
        }

        if(Strings.isNullOrEmpty(format) || !"png".equals(format)) {
            throw new MissingOrBadFormat();
        }

        ObjectContext context = serverRuntime.getContext();
        Optional<Pkg> pkg = Pkg.getByName(context, pkgName);

        if(!pkg.isPresent()) {
            throw new PkgNotFound();
        }

        response.setContentType(MediaType.PNG.toString());

        pkgIconService.writePkgIconImage(
                response.getOutputStream(),
                context,
                pkg.get(),
                size);
    }

    /**
     * <p>This handler will take-up an HTTP PUT that provides an con image for the package.</p>
     */

    @RequestMapping(value = "/{"+KEY_PKGNAME+"}.{"+KEY_FORMAT+"}", method = RequestMethod.PUT)
    public void put(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable(value = KEY_FORMAT) String format,
            @PathVariable(value = KEY_PKGNAME) String pkgName) throws IOException {

        if(Strings.isNullOrEmpty(pkgName) || !Pkg.NAME_PATTERN.matcher(pkgName).matches()) {
            throw new MissingPkgName();
        }

        if(Strings.isNullOrEmpty(format) || !"png".equals(format)) {
            throw new MissingOrBadFormat();
        }

        ObjectContext context = serverRuntime.getContext();
        Optional<Pkg> pkg = Pkg.getByName(context, pkgName);

        if(!pkg.isPresent()) {
            throw new PkgNotFound();
        }

        try {
            pkgIconService.storePkgIconImage(
                    request.getInputStream(),
                    context,
                    pkg.get());
        }
        catch(BadPkgIconException badIcon) {
            throw new MissingOrBadFormat();
        }

        context.commitChanges();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    // these are the various errors that can arise in supplying or providing a package icon.

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="the size must be 16 or 32")
    public class BadSize extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="the package name must be supplied")
    public class MissingPkgName extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="the format must be supplied and must (presently) be 'png'")
    public class MissingOrBadFormat extends RuntimeException {}

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="the requested package was unable to found")
    public class PkgNotFound extends RuntimeException {}

}