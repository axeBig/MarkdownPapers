/*
 * Copyright 2011, TAUTUA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.parser.AbstractParser;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.sink.Sink;
import org.tautua.markdownpapers.ast.Document;
import org.tautua.markdownpapers.parser.Parser;

import java.io.Reader;

/**
 *
 */
public class MarkdownParser extends AbstractParser {
    /**
     * The role hint for the {@link MarkdownParser} Plexus component.
     */
    public static final String ROLE_HINT = "markdown";

    public void parse(Reader reader, Sink sink) throws ParseException {
        Parser parser = new Parser(reader);
        Document document = null;
        try {
            document = parser.parse();
        } catch (org.tautua.markdownpapers.parser.ParseException e) {
            throw new ParseException(e, e.currentToken.beginLine, e.currentToken.beginColumn);
        }

        SinkEventEmitter emitter = new SinkEventEmitter(sink);
        document.accept(emitter);
    }
}
