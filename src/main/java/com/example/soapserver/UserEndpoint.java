package com.example.soapserver;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.owners.MTHMSBRegVerificationRes;
import com.example.owners.MTHSMBRegVerificationReq;
import com.example.owners.GetUserRequest;
import com.example.owners.GetUserResponse;
import com.example.owners.UpdateUserRequest;
import com.example.owners.UpdateUserResponse;
import com.example.owners.Vehicle;
import com.example.owners.AddUserRequest;
import com.example.owners.AddUserResponse;
import com.example.owners.GetAllUserRequest;
import com.example.owners.GetAllUserResponse;
import com.example.owners.Owner;
import com.example.owners.Msg;
import com.example.owners.ServerResponse;


import com.example.soapserver.UserService;
import com.example.soapserver.OwnerEntity;

@Endpoint
public class UserEndpoint {
	protected static final String NAMESPACE_URI = "http://example.com/owners";

	//private UserRepository userRepository;
	private UserService service;
	private VehicleService service2;

	//@Autowired
	//public UserEndpoint(UserRepository userRepository) {
	//	this.userRepository = userRepository;
	//}

	public UserEndpoint() {

	}

	@Autowired
	public UserEndpoint(UserService service) {
		this.service = service;
	}
	public UserEndpoint(VehicleService service2) {
		this.service2 = service2;
	}

	

	// @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MTHSMBRegVerificationReq")
	// @ResponsePayload
	// public MTHMSBRegVerificationRes getUser(@RequestPayload MTHSMBRegVerificationReq request) {
	// 	MTHMSBRegVerificationRes response = new MTHMSBRegVerificationRes();
	// 	response.setOwner(userRepository.findOwner(request.getRefNo()));

	// 	return response;
	// }

	//handle user's request provided with refNo
	 @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getUserRequest")
	 @ResponsePayload
	 public GetUserResponse getUserResponse(@RequestPayload GetUserRequest request) {
	 	GetUserResponse response = new GetUserResponse();
	 	System.out.println("Requesting from client, requested refNo is: " + request.getRefNo());
		 OwnerEntity user = service.getEntityByRefNo(request.getRefNo());
		 List <Vehicle> vehicleList = new ArrayList<Vehicle>();
		 List <VehicleEntity> vehicles = service2.getVehicleEntity(request.getRefNo());
		 for(VehicleEntity vehicleEntity : vehicles) {
			 Vehicle vehicle = new Vehicle();
			 BeanUtils.copyProperties(vehicleEntity, vehicle);
			 vehicleList.add(vehicle);
		 }
		 response.getVehicle().addAll(vehicleList);
		 Msg msg = new Msg();
		 BeanUtils.copyProperties(user, msg);
		 response.setMsg(msg);
	 	return response;
	 } 

	 //handle user's request that seeks for all user data
	@PayloadRoot(namespace = NAMESPACE_URI,localPart = "getAllUserRequest")
	@ResponsePayload
	public GetAllUserResponse getAllUsers(@RequestPayload GetAllUserRequest request) {
		GetAllUserResponse response = new GetAllUserResponse();
		List <Owner> ownersList = new ArrayList<Owner>();
		List <OwnerEntity> userList = service.getAllEntities();
		for (OwnerEntity user : userList) {
			System.out.println(user.toString());
			Owner owner = new Owner();
			BeanUtils.copyProperties(user, owner);
			ownersList.add(owner);
		}
		response.getOwner().addAll(ownersList);
		return response;
	}

	//add user's data with data provided by the users
	@PayloadRoot(namespace = NAMESPACE_URI,localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();
		Owner owner = new Owner();
		ServerResponse serverResponse = new ServerResponse();

		OwnerEntity newOwnerEntity = new OwnerEntity(request.getRefNo(),request.getMsg(),request.getMsgCode());
		OwnerEntity savedOwnerEntity = service.addEntity(newOwnerEntity);
		
		if (savedOwnerEntity == null) {
			serverResponse.setServerResponse("FAILED!");
			serverResponse.setServerMessage("Unable to add data into database");
		} else {
			BeanUtils.copyProperties(savedOwnerEntity, owner);
			serverResponse.setServerResponse("SUCCESS!");
			serverResponse.setServerMessage("Content added successfully");
		}

		response.setOwner(owner);
		response.setServerResponse(serverResponse);
		return response;

	}

}