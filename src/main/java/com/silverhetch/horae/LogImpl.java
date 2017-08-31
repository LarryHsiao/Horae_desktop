package com.silverhetch.horae;

import com.silverhetch.clotho.log.Log;

public class LogImpl implements Log {
    @Override
    public void info(String s) {
        System.out.println("info: " + s);
    }

    @Override
    public void debug(String s) {
        System.out.println("debug: " + s);
    }

    @Override
    public void warning(String s) {
        System.out.println("warning: " + s);
    }

    @Override
    public void error(String s) {
        System.out.println("error: " + s);
    }
}
