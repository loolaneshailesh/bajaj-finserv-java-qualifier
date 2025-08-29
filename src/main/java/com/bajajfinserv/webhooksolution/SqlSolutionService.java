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
        // TODO: Replace this with actual SQL solution for Question 1
        // Access the Google Drive link: https://drive.google.com/file/d/1IeSI6l6KoSQAFfRihIT9tEDICtoz-G/view?usp=sharing
        
        String sql = "SELECT * FROM employees WHERE department = 'IT' ORDER BY salary DESC";
        
        logger.info("Question 1 SQL Solution: {}", sql);
        return sql;
    }

    private String solveQuestion2() {
        // TODO: Replace this with actual SQL solution for Question 2
        // Access the Google Drive link: https://drive.google.com/file/d/143MR5cLFrlNEuHzzWJ5RHnEWuijuM9X/view?usp=sharing
        
        String sql = "SELECT customer_id, COUNT(*) as order_count FROM orders GROUP BY customer_id HAVING COUNT(*) > 5";
        
        logger.info("Question 2 SQL Solution: {}", sql);
        return sql;
    }
}
