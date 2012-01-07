package cn.cameron;

/**
 * Bala bala.
 *
 * @author Cameron Ke
 * @version 1.0 1/7/12
 */
public class Encoder {

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     * @author yue
     */
    public String toUtf8String(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;

                    b = Character.toString(c).getBytes("utf-8");

                    for (byte aB : b) {
                        int k = aB;
                        if (k < 0)
                            k += 256;
                        sb.append("%").append(Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sb.toString();
    }
}
