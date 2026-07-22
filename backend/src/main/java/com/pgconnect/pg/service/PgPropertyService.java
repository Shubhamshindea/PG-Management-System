package com.pgconnect.pg.service;

import com.pgconnect.pg.dto.PgPropertyDto;
import java.util.List;
import java.util.UUID;

public interface PgPropertyService {
    PgPropertyDto.Response createPg(PgPropertyDto.Request request, UUID ownerId);
    List<PgPropertyDto.Response> getAllPgsForOwner(UUID ownerId);
    PgPropertyDto.Response getPgById(UUID pgId, UUID ownerId);
}
