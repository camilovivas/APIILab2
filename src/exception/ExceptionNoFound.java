package exception;

public class ExceptionNoFound extends Exception{

	public ExceptionNoFound(String id) {
		super("el id "+id+" no fue encontrado");
	}
}
