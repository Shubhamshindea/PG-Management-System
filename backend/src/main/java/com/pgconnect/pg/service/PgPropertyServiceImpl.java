package com.pgconnect.pg.service;

import com.pgconnect.pg.dto.PgPropertyDto;
import com.pgconnect.pg.entity.PgProperty;
import com.pgconnect.pg.repository.PgPropertyRepository;
import com.pgconnect.user.entity.User;
import com.pgconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PgPropertyServiceImpl implements PgPropertyService {

    private final PgPropertyRepository pgPropertyRepository;
    private final UserRepository userRepository;

    @Override
    public PgPropertyDto.Response createPg(PgPropertyDto.Request request, UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        PgProperty property = PgProperty.builder()
                .owner(owner)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .build();

        property = pgPropertyRepository.save(property);
        return mapToResponse(property);
    }

    @Override
    public List<PgPropertyDto.Response> getAllPgsForOwner(UUID ownerId) {
        return pgPropertyRepository.findByOwnerId(ownerId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PgPropertyDto.Response getPgById(UUID pgId, UUID ownerId) {
        PgProperty property = pgPropertyRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG Property not found"));

        if (!property.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Unauthorized access to PG Property");
        }

        return mapToResponse(property);
    }

    private PgPropertyDto.Response mapToResponse(PgProperty property) {
        PgPropertyDto.Response response = new PgPropertyDto.Response();
        response.setId(property.getId());
        response.setName(property.getName());
        response.setAddress(property.getAddress());
        response.setCity(property.getCity());
        response.setState(property.getState());
        response.setPincode(property.getPincode());
        return response;
    }
}
