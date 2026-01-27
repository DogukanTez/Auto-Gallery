package com.dogukantez.service.impl;

import com.dogukantez.dto.*;
import com.dogukantez.entities.Car;
import com.dogukantez.entities.Customer;
import com.dogukantez.entities.SoldCar;
import com.dogukantez.enums.CarStatusType;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import com.dogukantez.repository.CarRepository;
import com.dogukantez.repository.CustomerRepository;
import com.dogukantez.repository.GalleristRepository;
import com.dogukantez.repository.SoldCarRepository;
import com.dogukantez.service.ICurrencyRatesService;
import com.dogukantez.service.ISoldCarService;
import com.dogukantez.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SoldCarServiceImpl implements ISoldCarService {

    private final CustomerRepository customerRepository;
    private final GalleristRepository galleristRepository;
    private final CarRepository carRepository;
    private final ICurrencyRatesService currencyRatesService;
    private final SoldCarRepository soldCarRepository;

    public SoldCarServiceImpl(CustomerRepository customerRepository,GalleristRepository galleristRepository,CarRepository carRepository,ICurrencyRatesService currencyRatesService,SoldCarRepository soldCarRepository){
        this.carRepository=carRepository;
        this.galleristRepository=galleristRepository;
        this.customerRepository=customerRepository;
        this.currencyRatesService=currencyRatesService;
        this.soldCarRepository=soldCarRepository;
    }

    public BigDecimal convertCustomerAmountToUSD(Customer customer){
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd,2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId){
        Optional<Car> optCar = carRepository.findById(carId);
        if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SOLD.name())){
            return false;
        }
            return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer,Car car){
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

       return  remainingCustomerUSDAmount.multiply(usd);

    }



    public boolean checkAmount(DtoSoldCarIU dtoSoldCarIU){
        Long carId = dtoSoldCarIU.getCarId();
        Long customerId= dtoSoldCarIU.getCustomerId();

        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        if(optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,customerId.toString()));
        }

        Optional<Car> optCar = carRepository.findById(carId);
        if(optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,carId.toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
        if(customerUSDAmount.compareTo(optCar.get().getPrice())>=0){
                return true;
        }
            return false;
    }

    private SoldCar createSoldCar(DtoSoldCarIU dtoSoldCarIU){
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(new Date());
        soldCar.setCustomer(customerRepository.findById(dtoSoldCarIU.getCustomerId()).orElse(null));
        soldCar.setGallerist(galleristRepository.findById(dtoSoldCarIU.getGalleristId()).orElse(null));
        soldCar.setCar(carRepository.findById(dtoSoldCarIU.getCarId()).orElse(null));

        return soldCar;
    }

    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {
        if (!checkAmount(dtoSoldCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH,""));
        }
            if(!checkCarStatus(dtoSoldCarIU.getCarId())){
                throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_ALREADY_SOLD,dtoSoldCarIU.getCarId().toString()));
            }


        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(dtoSoldCarIU));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);
        carRepository.save(car);


        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);

        return toDTO(savedSoldCar);
    }

    public DtoSoldCar toDTO(SoldCar soldCar){
        DtoSoldCar dtoSoldCar = new DtoSoldCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(soldCar,dtoSoldCar);
        BeanUtils.copyProperties(soldCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(soldCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(soldCar.getCar(),dtoCar);

        dtoSoldCar.setCustomer(dtoCustomer);
        dtoSoldCar.setGallerist(dtoGallerist);
        dtoSoldCar.setCar(dtoCar);
        return dtoSoldCar;

    }

}
