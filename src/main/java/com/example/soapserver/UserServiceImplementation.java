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
public class UserServiceImplementation implements UserService {

    public enum Status {
		SUCCESS, FAILURE;
	}

    private UserRepository2 repository;

    public UserServiceImplementation() {

    }

    @Autowired
    public UserServiceImplementation(UserRepository2 repository) {
        this.repository = repository;
    }

    @Override
    public OwnerEntity getEntityByRefNo(String refNo) {
        return repository.findByRefNo(refNo);
    }


    @Override
    public OwnerEntity getEntityByMsg(String msg) {
        return this.repository.findByMsg(msg);
    }

    @Override
    public OwnerEntity getEntityByMsgCode(String msgCode) {
        return this.repository.findByMsgCode(msgCode);
    }

    @Override
    public List <OwnerEntity> getAllEntities() {

        List <OwnerEntity> list = new ArrayList<> ();
        repository.findAll().forEach(e -> list.add(e));
        System.out.print(list.toString());
        return list;

    }

    @Override
    public OwnerEntity addEntity(OwnerEntity entity){
        try{
            return this.repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateEntity(OwnerEntity entity) {
        try {
            this.repository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
