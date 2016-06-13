package com.cwjcsu.common.exec;

import java.io.OutputStream;

/**
 * Created by cwjcsu@gmail.com on 15/11/19.
 */
public interface CommandOutputStreamHandler {
    void handle(OutputStream out);
}
