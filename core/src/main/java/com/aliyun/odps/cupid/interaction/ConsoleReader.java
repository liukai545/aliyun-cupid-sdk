/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aliyun.odps.cupid.interaction;

import jline.ConsoleOperations;
import jline.Terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class ConsoleReader implements ConsoleOperations {
    /**
     * The Terminal to use.
     */
    private final Terminal terminal;

    InputStream in;

    final Writer out;

    public ConsoleReader(final InputStream in, final Writer out) throws IOException {
        // The invoke of Terminal.getTerminal() will disable the echo
        this(in, out, Terminal.getTerminal());
    }

    public ConsoleReader(InputStream in, Writer out, Terminal term) throws IOException {
        this.terminal = term;
        this.in = in;
        this.out = out;
    }

    public Terminal getTerminal() {
        return this.terminal;
    }

    public String readLine() throws IOException {
        while (true) {
            int c = in.read();
            if (c == -1) {
                out.write(c);
                out.flush();
                return null;
            }
            // switch the BackSpace and DELETE
            if (c != 0) {
                if (c == DELETE)
                    c = '\b';
                else if (c == '\b')
                    c = DELETE;
            }
            out.write(c);
            out.flush();
        }
    }
}
