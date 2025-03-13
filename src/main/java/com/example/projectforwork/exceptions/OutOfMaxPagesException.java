package com.example.projectforwork.exceptions;

public class OutOfMaxPagesException extends RuntimeException {
    public OutOfMaxPagesException(int pages) {
        super("Out of max counts pages. Max pages: " + pages);
    }

    public OutOfMaxPagesException() {
        super("заказов нет");
    }
}
