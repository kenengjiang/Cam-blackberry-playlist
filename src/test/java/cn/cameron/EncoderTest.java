package cn.cameron;

import org.junit.Test;

/**
 * Bala bala.
 *
 * @author Cameron Ke
 * @version 1.0 1/7/12
 */
public class EncoderTest {
    @Test
    public void testToUtf8String(){
        System.out.println(new Encoder().toUtf8String("恁娘"));
        System.out.println(new Encoder().toUtf8String("hello 恁娘"));
    }
}
