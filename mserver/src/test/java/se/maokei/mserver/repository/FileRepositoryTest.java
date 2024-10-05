package se.maokei.mserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import se.maokei.mserver.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class FileRepositoryTest {
    @Autowired
    FileRepository fileRepository;
}
