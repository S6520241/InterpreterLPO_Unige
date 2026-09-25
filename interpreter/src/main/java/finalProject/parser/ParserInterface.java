package finalProject.parser;

import finalProject.parser.ast.Prog;

public interface ParserInterface extends AutoCloseable {

	Prog parseProg() throws ParserException;

}