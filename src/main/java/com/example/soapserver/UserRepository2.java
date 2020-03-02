package com.example.soapserver;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.soapserver.OwnerEntity;

import java.util.Map;
import java.util.HashMap;

import com.example.owners.Owner;
import org.springframework.util.Assert;


@Repository
public interface UserRepository2 extends CrudRepository<OwnerEntity,String>{

    public static final Map<String, Owner> owners = new HashMap<>();

    public OwnerEntity findByRefNo(String refNo);
    public OwnerEntity findByMsg(String msg);
    public OwnerEntity findByMsgCode(String msgCode);

}