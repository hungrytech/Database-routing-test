package com.example.template;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public void addUser() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.close();
        }


    }
}
