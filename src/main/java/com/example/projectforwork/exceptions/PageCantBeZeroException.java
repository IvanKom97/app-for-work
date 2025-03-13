package com.example.projectforwork.exceptions;

public class PageCantBeZeroException extends RuntimeException{
    public PageCantBeZeroException() {
        super("PAGE CANT BE 0");
    }
}
