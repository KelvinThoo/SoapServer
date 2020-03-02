package com.example.soapserver;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import com.example.owners.Owner;
import com.example.soapserver.OwnerEntity;
import com.example.soapserver.UserRepository2;
import com.example.owners.ServerResponse;

@Service
@Component
@Transactional
public class VehicleServiceImplementation implements VehicleService {


    private VehicleRepository repository;

    public VehicleServiceImplementation() {

    }

    @Autowired
    public VehicleServiceImplementation(VehicleRepository repository) {
        this.repository = repository;
    }
    
    @Override 
    public List<VehicleEntity> getVehicleEntity(String refNo) {

        List<VehicleEntity> list = new ArrayList<>();
        repository.findAll().forEach(e -> list.add(e));
        return list;

    }

}
