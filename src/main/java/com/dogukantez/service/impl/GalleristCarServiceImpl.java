package com.dogukantez.service.impl;

import com.dogukantez.dto.*;
import com.dogukantez.entities.Car;
import com.dogukantez.entities.Gallerist;
import com.dogukantez.entities.GalleristCar;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import com.dogukantez.repository.CarRepository;
import com.dogukantez.repository.GalleristCarRepository;
import com.dogukantez.repository.GalleristRepository;
import com.dogukantez.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {
    private final GalleristCarRepository galleristCarRepository;
    private final GalleristRepository galleristRepository;

    private final CarRepository carRepository;

    public GalleristCarServiceImpl(GalleristCarRepository galleristCarRepository, GalleristRepository galleristRepository, CarRepository carRepository) {
        this.galleristCarRepository = galleristCarRepository;
        this.galleristRepository = galleristRepository;
        this.carRepository = carRepository;
    }

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){


        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());
        return galleristCar;
    }


    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar= new DtoGalleristCar();
        DtoGallerist dtoGallerist= new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(),dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }
}
