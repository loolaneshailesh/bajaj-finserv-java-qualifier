package com.bajajfinserv.webhooksolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlSolutionService {

    private static final Logger logger = LoggerFactory.getLogger(SqlSolutionService.class);

    public String solveSqlProblem(String regNo) {
        // Extract last two digits from regNo
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        logger.info("RegNo: {}, Last two digits: {}", regNo, lastTwoDigits);
        
        if (lastTwoDigitsInt % 2 == 1) {
            // Odd - Question 1
            logger.info("Solving Question 1 (Odd regNo)");
            return solveQuestion1();
        } else {
            // Even - Question 2
            logger.info("Solving Question 2 (Even regNo)");
            return solveQuestion2();
        }
    }

    private String solveQuestion1() {
        String sql = """
            SELECT 
                p.amount AS SALARY,
                CONCAT(e.first_name, ' ', e.last_name) AS NAME,
                FLOOR(DATEDIFF(CURRENT_DATE, e.dob) / 365) AS AGE,
                d.department_name AS DEPARTMENT_NAME
            FROM payments p
            JOIN employee e ON p.emp_id = e.emp_id
            JOIN department d ON e.department = d.department_id
            WHERE DAY(p.payment_time) != 1
              AND p.amount = (
                SELECT MAX(amount)
                FROM payments
                WHERE DAY(payment_time) != 1
            )
        """;
        return sql;
    }
    
    private String solveQuestion2() {
        String sql = """
            SELECT 
                e.emp_id AS EMP_ID,
                e.first_name AS FIRST_NAME,
                e.last_name AS LAST_NAME,
                d.department_name AS DEPARTMENT_NAME,
                COUNT(y.emp_id) AS YOUNGER_EMPLOYEES_COUNT
            FROM employee e
            JOIN department d ON e.department = d.department_id
            LEFT JOIN employee y ON y.department = e.department AND y.dob > e.dob
            GROUP BY e.emp_id, e.first_name, e.last_name, d.department_name
            ORDER BY e.emp_id DESC
        """;
        return sql;
    }
    
    }
}
