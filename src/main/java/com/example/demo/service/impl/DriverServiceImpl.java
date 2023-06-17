package com.example.demo.service.impl;

import com.example.demo.config.EntityMapper;
import com.example.demo.dto.request.DriverRequestDto;
import com.example.demo.dto.response.DriverResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.model.Driver;
import com.example.demo.exception.DriverNotFoundException;
import com.example.demo.repository.DriverRepository;
import com.example.demo.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final EntityMapper entityMapper;

    @Override
    public ResponseEntity<ResponseDto> create(DriverRequestDto driverDtoRequest) {
        Driver driver = entityMapper.driverDtoRequestToDriver(driverDtoRequest);
        Driver createdDriver = driverRepository.save(driver);
        ResponseDto responseDto = new ResponseDto("Driver created successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    public List<DriverResponseDto> findAll() {
        List<Driver> drivers = driverRepository.findAll();
        List<DriverResponseDto> driverDtoResponses = drivers.stream()
                .map(entityMapper::driverToDriverDtoResponse)
                .collect(Collectors.toList());
        return driverDtoResponses;
    }

    @Override
    public DriverResponseDto findById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(DriverNotFoundException::new);
        return entityMapper.driverToDriverDtoResponse(driver);
    }



    @Override
    public ResponseEntity<ResponseDto> update(DriverRequestDto driverRequestDto) {
        Long driverId = driverRequestDto.getId();
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(DriverNotFoundException::new);

        entityMapper.updateDriverFromDtoRequest(driverRequestDto, existingDriver);
        Driver updatedDriver = driverRepository.save(existingDriver);
        ResponseDto responseDto = new ResponseDto("Driver updated successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto> delete(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(DriverNotFoundException::new);

        driverRepository.delete(driver);
        ResponseDto responseDto = new ResponseDto("Driver deleted successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}


