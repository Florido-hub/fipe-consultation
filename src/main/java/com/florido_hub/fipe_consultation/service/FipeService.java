package com.florido_hub.fipe_consultation.service;

import com.florido_hub.fipe_consultation.dto.ConsultaFipeDTO;
import com.florido_hub.fipe_consultation.repository.ReferenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class FipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FipeService.class);

    private final ReferenciaRepository referenciaRepository;

    public FipeService(ReferenciaRepository referenciaRepository) {
        this.referenciaRepository = referenciaRepository;
    }

    @Cacheable
    public ConsultaFipeDTO consultar(Long modeloId, Integer anoModelo) {
        LOGGER.debug("Cache MISS - consultando Postgress: modelo={}, ano={}", modeloId, anoModelo);

        return referenciaRepository
                .findReferencias(modeloId, anoModelo)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Consulta FIPE n]ao encontrada: modelo=%d, ano=%d"
                                .formatted(modeloId,anoModelo)));
    }

    @CacheEvict
    public void invalidar(Long modeloId, Integer anoModelo) {
        LOGGER.debug("Cache invalidado: modelo={}, ano={}", modeloId, anoModelo);
    }

}
