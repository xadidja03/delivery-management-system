package com.example.demo.service;
import com.example.demo.dto.request.DriverRequestDto;
import com.example.demo.dto.response.DriverResponseDto;
import com.example.demo.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DriverService {
    List<DriverResponseDto> findAll();
    DriverResponseDto findById(Long id);

    ResponseEntity<ResponseDto> create(DriverRequestDto dto);

    ResponseEntity<ResponseDto> update(DriverRequestDto dto);
    ResponseEntity<ResponseDto> delete(Long id);
}
