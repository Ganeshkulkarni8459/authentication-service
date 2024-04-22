package org.dnyanyog.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.encryption.EncryptionUtil;
import org.dnyanyog.entity.Users;
import org.dnyanyog.enums.ResponseCode;
import org.dnyanyog.repo.UsersRepository;
import org.dnyanyog.utilities.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

  Logger logger = LoggerFactory.getLogger(UserManagementService.class);

  @Autowired UsersRepository userRepo;

  @Autowired AddUserResponse userResponse;

  @Autowired private List<String> userIds;

  @Autowired EncryptionUtil encryption;

  @Override
  public Optional<AddUserResponse> addUpdateUser(AddUserRequest request) throws Exception {

    Users usersTable =
        Users.getInstance()
            .setUserId("USR" + RandomStringGenerator.generateRandomString(5))
            .setAge(request.getAge())
            .setEmail(request.getEmail())
            .setPassword(encryption.encrypt(request.getPassword()))
            .setUsername(request.getUsername());

    usersTable = userRepo.save(usersTable);

    userResponse.setMessage(ResponseCode.SUCCESS_USER_ADD.getMessage());
    userResponse.setStatus(ResponseCode.SUCCESS_USER_ADD.getStatus());
    userResponse.setUserId(usersTable.getUserId());
    //		userResponse.setStatus("Success");
    //		userResponse.setMessage("User found");

    userResponse.getUserData().setEmail(usersTable.getEmail());
    userResponse.getUserData().setUsername(usersTable.getUsername());
    userResponse.getUserData().setAge(usersTable.getAge());

    return Optional.of(userResponse);
  }

  @Override
  public AddUserResponse getSingleUser(Long userId) throws Exception {

    Optional<Users> receivedData = userRepo.findById(userId);

    if (receivedData.isEmpty()) {
      userResponse.setStatus("Fail");
      userResponse.setMessage("User not found");

    } else {
      Users user = receivedData.get();

      userResponse.setStatus(ResponseCode.SUCCESS_USER_GET.getStatus());
      userResponse.setMessage(ResponseCode.SUCCESS_USER_GET.getMessage());
      userResponse.setUserId(user.getUserId());
      userResponse.getUserData().setEmail(user.getEmail());
      userResponse.getUserData().setUsername(user.getUsername());
      //	userResponse.getUserData().setPassword(encryption.decrypt(user.getPassword()));
      userResponse.getUserData().setAge(user.getAge());
    }
    return userResponse;
  }

  @Override
  public List<Users> getAllUser() {
    return userRepo.findAll();
  }

  @Override
  public List<String> getAllUserIds() {

    List<Users> users = userRepo.findAll();

    for (Users user : users) {
      if (nonNull(user)) {
        userIds.add(user.getUserId());
      }
    }
    return userIds;
  }
}
