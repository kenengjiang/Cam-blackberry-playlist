package cn.cameron;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Esther
 * Date: 12-1-6
 * Time: 下午9:42
 * To change this template use File | Settings | File Templates.
 */
public class App {

    private final static String slashOrBackSlash = "/";
    private final static Encoder encoder = new Encoder();

    public static void main(String[] args) {
        File rootPath = new File("/media/BLACKBERRY/BlackBerry/music");
        System.out.println("Starting generating");
        doGenerate(rootPath);
    }

    public static void doGenerate(File root) {
        if (root.exists() && root.isDirectory()) {
            if (containsSubFolder(root)) {
                for (File eachPath : root.listFiles(new FolderFilter())) {
                    doGenerate(eachPath);
                }

                writePlaylistForRoot(root);
            } else {
                if (!containsPlaylist(root)) {
                    writePlaylistForLeaf(root);
                }
            }
        }
    }

    public static void writePlaylistForLeaf(File path) {
        File playlist = new File(path.getAbsolutePath() + slashOrBackSlash + path.getName() + "_generated.m3u");
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(playlist), "UTF8"));
            for (File song : path.listFiles(new MusicFileFilter())) {
                out.append(encoder.toUtf8String(song.getName())).append("\r\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }


    }

    public static void writePlaylistForRoot(File path) {
        File playlist = new File(path.getAbsolutePath() + slashOrBackSlash + path.getName() + "_generated.m3u");
        Writer out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(playlist), "UTF8"));
            //find all playlists in sub folders, use relative path
            for (File subFolder : path.listFiles(new FolderFilter())) {
                for (File eachPlaylist : subFolder.listFiles(new PlaylistFileFilter())) {
                    String line = "." + slashOrBackSlash + subFolder.getName() + slashOrBackSlash + eachPlaylist.getName();
                    out.append(encoder.toUtf8String(line)).append("\r\n");
                }
            }

            //add all songs in the current folder
            for (File song : path.listFiles(new MusicFileFilter())) {
                out.append(encoder.toUtf8String(song.getName())).append("\r\n");
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    public static boolean containsSubFolder(File path) {
        if (path.exists() && path.isDirectory()) {
            for (File eachChildPath : path.listFiles()) {
                if (eachChildPath.isDirectory()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean containsPlaylist(File path) {
        if (path.exists() && path.isDirectory()) {
            File[] files = path.listFiles(new PlaylistFileFilter());
            List<File> fileList = Arrays.asList(files);
            return !fileList.isEmpty();
        }
        return false;
    }
}
