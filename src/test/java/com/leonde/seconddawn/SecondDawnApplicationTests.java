package com.leonde.seconddawn;

import com.leonde.seconddawn.entity.Empires;
import com.leonde.seconddawn.support.BaseTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

//@TestPropertySource(locations="classpath:application-test.yaml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // allows us to override application.yaml properties.
@Sql(scripts = {
        "classpath:schemas/Second_Dawn.sql",
        "classpath:schemas/second_dawn_data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
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

        assertThat(empire.getEmpireName()).isEqualTo("Obiwan");
        assertThat(empire.getSector()).isEqualTo("republic");
        assertThat(empire.getAlliance()).isEqualTo("Tano");
    }

    public String createOrderBody() {
        return "{ " +
                "  \"empireName\":\"Obiwan\",\n " +
                " \"sector\":\"republic\",\n" +
                "  \"alliance\":\"Tano\"\n" + "}";
    }
}
