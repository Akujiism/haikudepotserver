package org.haikuos.haikudepotserver.model.auto;

import java.util.List;

import org.haikuos.haikudepotserver.model.Pkg;
import org.haikuos.haikudepotserver.model.PkgScreenshotImage;
import org.haikuos.haikudepotserver.model.support.AbstractDataObject;

/**
 * Class _PkgScreenshot was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _PkgScreenshot extends AbstractDataObject {

    public static final String CODE_PROPERTY = "code";
    public static final String ORDERING_PROPERTY = "ordering";
    public static final String PKG_PROPERTY = "pkg";
    public static final String PKG_SCREENSHOT_IMAGES_PROPERTY = "pkgScreenshotImages";

    public static final String ID_PK_COLUMN = "id";

    public void setCode(String code) {
        writeProperty(CODE_PROPERTY, code);
    }
    public String getCode() {
        return (String)readProperty(CODE_PROPERTY);
    }

    public void setOrdering(Integer ordering) {
        writeProperty(ORDERING_PROPERTY, ordering);
    }
    public Integer getOrdering() {
        return (Integer)readProperty(ORDERING_PROPERTY);
    }

    public void setPkg(Pkg pkg) {
        setToOneTarget(PKG_PROPERTY, pkg, true);
    }

    public Pkg getPkg() {
        return (Pkg)readProperty(PKG_PROPERTY);
    }


    public void addToPkgScreenshotImages(PkgScreenshotImage obj) {
        addToManyTarget(PKG_SCREENSHOT_IMAGES_PROPERTY, obj, true);
    }
    public void removeFromPkgScreenshotImages(PkgScreenshotImage obj) {
        removeToManyTarget(PKG_SCREENSHOT_IMAGES_PROPERTY, obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<PkgScreenshotImage> getPkgScreenshotImages() {
        return (List<PkgScreenshotImage>)readProperty(PKG_SCREENSHOT_IMAGES_PROPERTY);
    }


}