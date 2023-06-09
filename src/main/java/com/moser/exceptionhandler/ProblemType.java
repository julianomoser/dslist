package com.moser.exceptionhandler;

import lombok.Getter;

/**
 * @author Juliano Moser
 */
@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    METODO_NAO_SUPORTADO("Método não suportado", "/metodo-nao-suportado"),
    DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
    PARAMETRO_INVALIDO("Parânetro invalido", "/parametro-invalido");

    private final String title;
    private final String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://dslist.com.br" + path;
    }
}
