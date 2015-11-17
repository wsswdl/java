package com.pubnub.api;

import org.slf4j.LoggerFactory;


class Logger extends AbstractLogger {
    private Class _class;
    private org.slf4j.Logger log;

    public Logger(Class _class) {
        this._class = _class;
        this.log = LoggerFactory.getLogger(this._class);
    }

    @Override
    protected void nativeDebug(String s) {
        log.debug(s);
        System.out.println(s);
    }

    @Override
    protected void nativeVerbose(String s) {
        log.trace(s);
        System.out.println(s);
    }

    @Override
    protected void nativeError(String s) {
        log.error(s);
    }

    @Override
    protected void nativeInfo(String s) {
        log.info(s);
        System.out.println(s);
    }
}
