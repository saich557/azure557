/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.bean;

import io.swagger.annotations.*;
import io.swagger.sample.data.UserData;
import io.swagger.sample.model.User;
import io.swagger.sample.exception.ApiException;
import io.swagger.sample.exception.NotFoundException;
import io.swagger.sample.resource.UserResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Component
public class UserResourceBean implements UserResource {
  static UserData userData = new UserData();

  @Override
  public Response createUser(User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @Override
  public Response createUsersWithArrayInput(User[] users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @Override
  public Response createUsersWithListInput(java.util.List<User> users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @Override
  public Response updateUser(String username, User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @Override
  public Response deleteUser(String username) {
    userData.removeUser(username);
    return Response.ok().entity("").build();
  }

  @Override
  public Response getUserByName(String username)
    throws ApiException {
    User user = userData.findUserByName(username);
    if (null != user) {
      return Response.ok().entity(user).build();
    } else {
      throw new NotFoundException(404, "User not found");
    }
  }

  @Override
  public Response loginUser(String username, String password) {
    return Response.ok()
        .entity("logged in user session:" + System.currentTimeMillis())
        .build();
  }

  @Override
  public Response logoutUser() {
    return Response.ok().entity("").build();
  }
}