package com.example.demo.config;
import com.example.demo.dto.request.CategoryRequestDto;
import com.example.demo.dto.request.DriverRequestDto;
import com.example.demo.dto.request.FoodRequestDto;
import com.example.demo.dto.request.OrderRequestDto;
import com.example.demo.dto.response.CategoryResponseDto;
import com.example.demo.dto.response.DriverResponseDto;
import com.example.demo.dto.response.FoodResponseDto;
import com.example.demo.dto.response.OrderResponseDto;
import com.example.demo.entity.model.Category;
import com.example.demo.entity.model.Driver;
import com.example.demo.entity.model.Food;
import com.example.demo.entity.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper
    public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);
    FoodResponseDto foodToFoodDtoResponse(Food food);
    Food foodDtoRequestToFood(FoodRequestDto foodRequestDto);
    CategoryResponseDto categoryToCategoryDtoResponse(Category category);
    Category categoryDtoRequestToCategory(CategoryRequestDto categoryRequestDto);
    DriverResponseDto driverToDriverDtoResponse(Driver driver);
    Driver driverDtoRequestToDriver(DriverRequestDto driverRequestDto);
    OrderResponseDto orderToOrderDtoResponse(Order order);
    Order orderDtoRequestToOrder(OrderRequestDto orderRequestDto);
    void updateOrderFromDtoRequest(OrderRequestDto orderRequestDto, @MappingTarget Order order);
    void updateFoodFromDtoRequest(FoodRequestDto foodRequestDto, @MappingTarget Food food);
    void updateCategoryFromDtoRequest(CategoryRequestDto categoryRequestDto, @MappingTarget Category category);
    void updateDriverFromDtoRequest(DriverRequestDto driverRequestDto, @MappingTarget Driver driver);
    void updateDriverFromDtoRequest(OrderRequestDto orderRequestDto,@MappingTarget Order order);
}
