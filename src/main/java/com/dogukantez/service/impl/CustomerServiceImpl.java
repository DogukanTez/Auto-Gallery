package com.dogukantez.service.impl;

import com.dogukantez.dto.DtoAccount;
import com.dogukantez.dto.DtoAddress;
import com.dogukantez.dto.DtoCustomer;
import com.dogukantez.dto.DtoCustomerIU;
import com.dogukantez.entities.Account;
import com.dogukantez.entities.Address;
import com.dogukantez.entities.Customer;
import com.dogukantez.exception.BaseException;
import com.dogukantez.exception.ErrorMessage;
import com.dogukantez.exception.MessageType;
import com.dogukantez.repository.AccountRepository;
import com.dogukantez.repository.AddressRepository;
import com.dogukantez.repository.CustomerRepository;
import com.dogukantez.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString())) ;
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());

        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString())) ;
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);
        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        return customer;

    }


    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAccount dtoAccount= new DtoAccount();
        DtoAddress dtoAddress = new DtoAddress();
        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);


        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);



        return dtoCustomer;

    }
}
