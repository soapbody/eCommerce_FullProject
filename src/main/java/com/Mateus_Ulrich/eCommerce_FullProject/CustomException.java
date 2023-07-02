package com.Mateus_Ulrich.eCommerce_FullProject;

public class CustomException extends Exception{
    private static final long serialVersionUID = 1L;

    public CustomException(String msgErro) {
        super(msgErro);
    }
}
