package cn.cameron;

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Bala bala.
 *
 * @author Cameron Ke
 * @version 1.0 1/7/12
 */
public class AppTest {
    @Test
    public void testContainsSubFolder() {
        Assert.assertTrue(App.containsSubFolder(new File("/tmp")));
        Assert.assertFalse(App.containsSubFolder(new File("/etc/sysconfig/network-scripts")));
        Assert.assertFalse(App.containsSubFolder(new File("/etc/sysconfig/network-scripts/ifcfg-dummy0")));
        Assert.assertFalse(App.containsSubFolder(new File("/etc/sysconfig/network-scripts/ifcfg-dummy0")));

    }

    @Test
    public void testWritePlaylistForLeaf() {
        App.writePlaylistForLeaf(new File("/data/home/camkee/Desktop/music/eminem/thrid-1"));
    }

    @Test
    public void testWritePlaylistForRoot() {
        App.writePlaylistForRoot(new File("/data/home/camkee/Desktop/music/"));
    }

    @Test
    public void testContainsPlaylist() {
        Assert.assertTrue(App.containsPlaylist(new File("/data/home/camkee/Desktop/music/eminem/thrid-1")));
        Assert.assertFalse(App.containsPlaylist(new File("/media/BLACKBERRY/BlackBerry/music/achi")));
    }

    @Test
    public void testReadPlaylist() {
        App.readPlaylist(new File("H:\\BlackBerry\\music\\music_generated.m3u"));
    }
}
