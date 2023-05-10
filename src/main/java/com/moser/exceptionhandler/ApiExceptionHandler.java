package com.moser.exceptionhandler;

import com.moser.exception.GameNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

/**
 * @author Juliano Moser
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o "
            + "probelema persistir, entre em contato com o admnistrador do sistema.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        log.error(ex.getMessage(), ex);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder detail = new StringBuilder();
        detail.append(ex.getMethod());
        detail.append(
                " é um método não suportado para essa requisição. Métodos suportados são: ");
        ex.getSupportedHttpMethods().forEach(t -> detail.append(t).append(" "));

        ProblemType problemType = ProblemType.METODO_NAO_SUPORTADO;


        Problem problem = createProblemBuilder(status, problemType, detail.toString())
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(GameNaoEncontradoException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(GameNaoEncontradoException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status,
                                                        ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

}
