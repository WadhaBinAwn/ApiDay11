import org.example.dao.JobDAO;
import org.example.models.Jobs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestJobDao {

    @InjectMocks
    JobDAO dao;

    @Test
    void testUpdateJob() throws SQLException, ClassNotFoundException {

        Jobs Jobs = new Jobs(19,10000,4000,"Test");

        Assertions.assertDoesNotThrow(()-> dao.updateJob(Jobs));

        Jobs newJ = dao.selectJob(Jobs.getJob_id());

        Assertions.assertNotNull(newJ);
        Assertions.assertEquals(newJ.getJob_id(),Jobs.getJob_id());
        Assertions.assertEquals(newJ.getJob_title(),Jobs.getJob_title());
    }
}


