package com.example.soapserver;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.soapserver.OwnerEntity;

import java.util.Map;
import java.util.HashMap;

import com.example.owners.Vehicle;

import org.springframework.util.Assert;


@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity,String>{

    public static final Map<String, Vehicle> vehicles = new HashMap<>();

    public VehicleEntity findByRefNo(String refNo);

}