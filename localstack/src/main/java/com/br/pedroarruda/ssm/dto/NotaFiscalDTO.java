package com.br.pedroarruda.ssm.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;

public record NotaFiscalDTO(String valor, String estabelecimento, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime data, String usuario) {
}
