package com.github.apro;

import com.github.apro.config.AppConstants;
import com.github.apro.statistics.StatisticService;
import com.github.apro.statistics.StatisticsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by achoudh on 19/03/2017.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StatisticsApplication.class})
public class StatisticsControllerTests {

    @Autowired
    private StatisticService service;

    @Test
    public void whenGetStatistics_then200Ok() {
        given().
                standaloneSetup(new StatisticsController(service)).
                when().
                async().get(AppConstants.STATISTICS_PATH).
                then().
                statusCode(200).content("count", equalTo(0));
    }
}
