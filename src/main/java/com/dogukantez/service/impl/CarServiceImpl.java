package com.dogukantez.service.impl;

import com.dogukantez.dto.DtoCar;
import com.dogukantez.dto.DtoCarIU;
import com.dogukantez.entities.Car;
import com.dogukantez.repository.CarRepository;
import com.dogukantez.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private Car createCar(DtoCarIU dtoCarIU){
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU,car);
        return car;

    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));
        BeanUtils.copyProperties(savedCar,dtoCar);


        return dtoCar;
    }
}
