package exception;

public class ExceptionRegistry extends Exception{

	public ExceptionRegistry(String name) {
		super("Ya existe"+name);
	}
}
