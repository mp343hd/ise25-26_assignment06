package de.seuhd.campuscoffee.tests.system;

import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersSystemTests extends AbstractSysTest {

    //DONE: Uncomment once user endpoint is implemented

   @Test
   void createUser() {
       User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
       User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

       assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
   }

    //DONE: Add at least two additional tests for user operations
    @Test
    void getAllCreatedUsers() {
        List<User> createdUsersList = TestFixtures.createUsers(userService);

        List<User> retrievedUsers = userRequests.retrieveAll()
                .stream()
                .map(userDtoMapper::toDomain)
                .toList();

        assertEqualsIgnoringTimestamps(retrievedUsers, createdUsersList);
    }

    @Test
    void getUserById() {
        List<User> createdUsersList = TestFixtures.createUsers(userService);
        User createdUser = createdUsersList.getFirst();

        User retrievedUser = userDtoMapper.toDomain(
                userRequests.retrieveById(createdUser.id())
        );

        assertEqualsIgnoringTimestamps(retrievedUser, createdUser);
    }
}