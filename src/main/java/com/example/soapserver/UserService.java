package com.example.soapserver;

import java.util.List;

public interface UserService {

    public OwnerEntity getEntityByRefNo(String refNo);
    public OwnerEntity getEntityByMsg(String msg);
    public OwnerEntity getEntityByMsgCode(String msgCode);
    public List<OwnerEntity> getAllEntities();
    public boolean updateEntity(OwnerEntity entity);
    public OwnerEntity addEntity(OwnerEntity entity);    

}

