package dev.yury.wallet.repository;

import dev.yury.wallet.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    private static final String EMAIL = "email@teste.com";

    @Autowired
    UserRepository repository;

    @BeforeEach
    public void setUp(){
        User user = new User();
        user.setName("Set up user");
        user.setPassword("Senha123");
        user.setEmail(EMAIL);

        repository.save(user);
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        user.setEmail("teste@teste.com");

        User response = repository.save(user);

        assertNotNull(response);
    }

    @Test
    public void testFindByEmail() {
        Optional<User> response = repository.findByEmailEquals(EMAIL);

        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}
