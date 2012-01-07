package cn.cameron;

import java.io.File;
import java.io.FileFilter;

/**
 * Bala bala.
 *
 * @author Cameron Ke
 * @version 1.0 1/7/12
 */
public class MusicFileFilter implements FileFilter {
    public boolean accept(File pathName) {
        String pathNameStr = pathName.getName().toLowerCase();
        return pathNameStr.endsWith(".mp3") || pathNameStr.endsWith(".flac") || pathNameStr.endsWith(".wma") || pathNameStr.endsWith(".m4a");
    }
}
