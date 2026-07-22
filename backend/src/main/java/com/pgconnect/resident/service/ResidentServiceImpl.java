package com.pgconnect.resident.service;

import com.pgconnect.pg.entity.Bed;
import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.pg.repository.BedRepository;
import com.pgconnect.pg.repository.PgPropertyRepository;
import com.pgconnect.resident.dto.ResidentDto;
import com.pgconnect.resident.entity.Resident;
import com.pgconnect.resident.repository.ResidentRepository;
import com.pgconnect.user.entity.User;
import com.pgconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final PgPropertyRepository pgPropertyRepository;
    private final BedRepository bedRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResidentDto.Response addResident(ResidentDto.AddRequest request) {
        PgProperty property = pgPropertyRepository.findById(request.getPgPropertyId())
                .orElseThrow(() -> new RuntimeException("PG Property not found"));

        Bed bed = bedRepository.findById(request.getBedId())
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (bed.getIsOccupied()) {
            throw new RuntimeException("Bed is already occupied");
        }

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        Resident resident = Resident.builder()
                .pgProperty(property)
                .bed(bed)
                .user(user)
                .emergencyContact(request.getEmergencyContact())
                .agreedRent(request.getAgreedRent())
                .moveInDate(request.getMoveInDate())
                .build();

        resident = residentRepository.save(resident);

        // Update bed status
        bed.setIsOccupied(true);
        bedRepository.save(bed);

        return mapToResponse(resident);
    }

    @Override
    public List<ResidentDto.Response> getResidentsByPg(UUID pgPropertyId) {
        return residentRepository.findByPgPropertyId(pgPropertyId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeResident(UUID residentId) {
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        // Free up the bed
        Bed bed = resident.getBed();
        bed.setIsOccupied(false);
        bedRepository.save(bed);

        resident.setMoveOutDate(LocalDate.now());
        residentRepository.save(resident);
    }

    private ResidentDto.Response mapToResponse(Resident resident) {
        ResidentDto.Response response = new ResidentDto.Response();
        response.setId(resident.getId());
        response.setPgPropertyId(resident.getPgProperty().getId());
        response.setBedId(resident.getBed().getId());
        if (resident.getUser() != null) {
            response.setUserId(resident.getUser().getId());
            response.setFullName(resident.getUser().getFullName());
        }
        response.setEmergencyContact(resident.getEmergencyContact());
        response.setAgreedRent(resident.getAgreedRent());
        response.setMoveInDate(resident.getMoveInDate());
        response.setMoveOutDate(resident.getMoveOutDate());
        return response;
    }
}
