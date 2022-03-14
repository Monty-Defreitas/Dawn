package com.leonde.seconddawn;

import com.leonde.seconddawn.entity.Empires;
import com.leonde.seconddawn.support.BaseTestSupport;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

//@TestPropertySource(locations="classpath:application-test.yaml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // allows us to override application.yaml properties.
@Sql(scripts = {
        "classpath:Second_Dawn.sql",
        "classpath:second_dawn_data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
@Slf4j
class SecondDawnApplicationTests extends BaseTestSupport {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Test
        void testDb() {
            int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "shields");
            System.out.println("numRows = " + numRows);
        }

    @Test
    void testThatCreateEmpireControllerReturns201() {

       String url = getBaseUriForOrders("/second-dawn/v1/create-empire");
       String body = createOrderBody();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> bodyEntity = new HttpEntity<>(body,httpHeaders);

        ResponseEntity<Empires> response = getRestTemplate().exchange(url,HttpMethod.POST,bodyEntity,Empires.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(response.getBody()).isNotNull();
        Empires empire = response.getBody();

        assertThat(empire.getEmpireName()).isEqualTo("Obi-wan");
        assertThat(empire.getSector()).isEqualTo("republic");
        assertThat(empire.getAlliance()).isEqualTo("Tano");
    }

    private String createOrderBody() {
        return "{ " +
                "  \"empireName\":\"Obi-wan\",\n " +
                " \"sector\":\"republic\",\n" +
                "  \"alliance\":\"cody\"\n" + "}";
    }

    @Test
    void testThatHashReturnsValueOtherThanGiven() {
        String url = getBaseUriForOrders("/second-dawn/v1/create-empire");
        String body = createOrderBody();

          HttpHeaders httpHeaders = new HttpHeaders();

          httpHeaders.setContentType(MediaType.APPLICATION_JSON);

          HttpEntity<String> bodyEntity = new HttpEntity<>(body,httpHeaders);

          ResponseEntity<Empires> response = getRestTemplate().exchange(url,HttpMethod.POST,bodyEntity,Empires.class);

          Empires sector = response.getBody();

        assertThat(sector.getSector()).isEqualTo("republic");

        log.debug("sector id:{} ", sector.getSector());


    }

}
