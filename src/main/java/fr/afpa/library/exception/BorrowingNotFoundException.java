package fr.afpa.library.exception;

public class BorrowingNotFoundException extends Exception {

    private String msg;

    public BorrowingNotFoundException() {
        super();
    }

    public BorrowingNotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
