package com.moba.backend.repositories;

import com.moba.backend.models.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should find users by name using case-insensitive search")
    void findByFullNameContainingIgnoreCase() {
        // Given
        UserData user1 = new UserData(
                "Mohammed Salah",
                "mosalah@test.com",
                "pass123",
                "MALE",
                LocalDate.of(1997, 1, 10)
        );

        UserData user2 = new UserData(
                "MoBa Developer",
                "moba@test.com",
                "pass123",
                "MALE",
                LocalDate.of(2000, 5, 22)
        );

        userRepository.save(user1);
        userRepository.save(user2);

        // When (search keyword)
        List<UserData> result = userRepository.findByFullNameContainingIgnoreCase("moba");

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFullName()).isEqualTo("MoBa Developer");
    }
}

