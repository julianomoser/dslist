package com.moser.exception;

import java.io.Serial;

/**
 * @author Juliano Moser
 */
public class GameNaoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6678699318547496716L;

    public GameNaoEncontradoException(String message) {
        super(message);
    }

    public GameNaoEncontradoException(Long cidadeId) {
        this(String.format("Não existe cadastro de game com código %d", cidadeId));
    }
}
