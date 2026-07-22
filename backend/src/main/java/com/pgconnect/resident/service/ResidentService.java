package com.pgconnect.resident.service;

import com.pgconnect.resident.dto.ResidentDto;
import java.util.List;
import java.util.UUID;

public interface ResidentService {
    ResidentDto.Response addResident(ResidentDto.AddRequest request);
    List<ResidentDto.Response> getResidentsByPg(UUID pgPropertyId);
    void removeResident(UUID residentId);
}
