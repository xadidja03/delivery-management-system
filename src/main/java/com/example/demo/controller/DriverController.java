package com.example.demo.controller;
import com.example.demo.dto.request.DriverRequestDto;
import com.example.demo.dto.response.DriverResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Slf4j
public class DriverController {
    private final DriverService driverService;
    @PostMapping("/drivers")
    public ResponseEntity<ResponseDto> createDriver(@RequestBody DriverRequestDto driverRequestDto) {
        log.info("Creating a new driver");
        ResponseEntity<ResponseDto> responseEntity = driverService.create(driverRequestDto);
        log.info("Driver created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }
    @GetMapping("/all/drivers")
    public ResponseEntity<List<DriverResponseDto>> getAllDrivers() {
        log.info("Fetching all drivers");
        List<DriverResponseDto> drivers = driverService.findAll();
        log.info("Fetched {} drivers", drivers.size());
        return ResponseEntity.ok(drivers);
    }
    @GetMapping("/drivers/{id}")
    public ResponseEntity<DriverResponseDto> getDriverById(@PathVariable Long id) {
        log.info("Fetching driver with ID: {}", id);
        DriverResponseDto driver = driverService.findById(id);
        log.info("Fetched driver with ID: {}", id);
        return ResponseEntity.ok(driver);
    }
    @PutMapping("/drivers/{id} ")
    public ResponseEntity<ResponseDto> updateDriver(@PathVariable Long id, @RequestBody DriverRequestDto driverRequestDto) {
        log.info("Updating driver with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = driverService.update(driverRequestDto);
        log.info("Driver with ID: {} updated successfully", id);
        return ResponseEntity.ok(responseEntity.getBody());
    }
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<ResponseDto> deleteDriver(@PathVariable Long id) {
        log.info("Deleting driver with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = driverService.delete(id);
        log.info("Driver with ID: {} deleted successfully", id);
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
