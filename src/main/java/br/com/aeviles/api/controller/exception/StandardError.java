package br.com.aeviles.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    /**
     * Os códigos de status das respostas HTTP  indicou
     * {
     *     "timestamp": "2022-04-09T15:25:59.682+00:00",
     *
     *     "status": 500,
     *
     *     "error": "Internal Server Error",
     *
     *     "path": "/user/3"
     * }
     */
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
