package cn.cameron;

import java.io.File;
import java.io.FileFilter;

/**
 * Bala bala.
 *
 * @author Cameron Ke
 * @version 1.0 1/7/12
 */
public class FolderFilter implements FileFilter {
    public boolean accept(File pathName) {
        return pathName.isDirectory();
    }
}
