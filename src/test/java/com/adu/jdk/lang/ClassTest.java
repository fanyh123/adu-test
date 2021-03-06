package com.adu.jdk.lang;

import java.net.URL;
import java.net.URLDecoder;

import org.junit.Test;

import com.adu.BaseTest;
import com.google.common.base.Charsets;

public class ClassTest extends BaseTest {

    private Class<?> clazz = this.getClass();

    @Test
    public void getClassTest() {
        logger.debug("res={}", clazz);
    }

    @Test
    public void getName() {
        String res = clazz.getName();
        logRes(res);
    }

    @Test
    public void getResource() {
        logger.info("{}", clazz.getResource(""));
        logger.info("{}", clazz.getResource("/"));
    }

    @Test
    public void getJarFile() throws Exception {
        Class<?> cla = Class.forName("com.google.common.reflect.ClassPath");
        String res = cla.getProtectionDomain().getCodeSource().getLocation().getFile();
        res = URLDecoder.decode(res, Charsets.UTF_8.name());
        logger.debug("res={}", res);
    }

}
