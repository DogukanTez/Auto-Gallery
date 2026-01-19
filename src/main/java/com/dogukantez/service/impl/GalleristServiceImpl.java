package com.dogukantez.service.impl;

import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoGallerist;
import com.dogukantez.dto.DtoGalleristIU;
import com.dogukantez.entities.Address;
import com.dogukantez.entities.Gallerist;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import com.dogukantez.repository.AddressRepository;
import com.dogukantez.repository.GalleristRepository;
import com.dogukantez.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {
    private final GalleristRepository galleristRepository;
    private final AddressRepository addressRepository;

    public GalleristServiceImpl(GalleristRepository galleristRepository, AddressRepository addressRepository) {
        this.galleristRepository = galleristRepository;
        this.addressRepository = addressRepository;
    }

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist=new Gallerist();
        gallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoGalleristIU,gallerist);

        gallerist.setAddress(optAddress.get());

        return gallerist;

    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;

    }
}
