/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rouletteApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouletteApp.model.Bet;
import com.rouletteApp.model.BetList;
import com.rouletteApp.model.ColorBet;
import com.rouletteApp.model.NumberBet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import redis.embedded.RedisServer;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.redis.host=localhost",
        "spring.redis.port=3032",
        "spring.redis.password=",
        "spring.redis.database=0"
})
public class AppTest {
    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;
    private String id;
    private static RedisServer server;
    @BeforeClass
    public static void before(){
        try {
            server = new RedisServer(3032);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void shouldBeCreateANewRoulette() throws Exception {
        mock.perform(post("/roulette")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shouldBeOpenRoulette() throws Exception {
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shoudntBeOpenRolette() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is4xxClientError());
    }
    @Test
    public void shoudBeCloseARoulette() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        mock.perform(patch("/roulette/"+id+"/close")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shoudntBeCloseARoulette() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/close")).andExpect(status().is4xxClientError());
    }
    @Test
    public void shouldBeFindAnWinnerWithColor() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        ColorBet bet = new ColorBet();
        bet.setMoney(100);
        bet.setColor("black");
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
        bet.setColor("red");
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
        mock.perform(patch("/roulette/"+id+"/close").header("Authorization","app")).andDo((result)->{
            BetList list = mapper.readValue(result.getResponse().getContentAsString(),BetList.class);
            assertTrue(true);
            assertTrue((list.get(0).getWinMoney()==(double)180 || list.get(1).getWinMoney()==(double)180) && !(list.get(0).getWinMoney()>0 && list.get(1).getWinMoney()>0));
        });

    }
    @Test
    public void shouldBeFindAnWinnerWithNumber() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        for(int i=0;i<37;i++){
            NumberBet bet = new NumberBet(100,i);
            mock.perform(post("/roulette/"+id+"/bets")
                    .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
        }
        mock.perform(patch("/roulette/"+id+"/close").header("Authorization","app")).andDo((result)->{
            BetList list = mapper.readValue(result.getResponse().getContentAsString(),BetList.class);
            Bet bet = null;
            for(Bet b:list){
                if(b.getMoney()==0 && b.getWinMoney()>0) bet = b;
            }
            assertTrue(bet.getWinMoney()==(double) 500);
        });

    }
    @Test
    public void shouldntBeAddedABeatInCloseRoulette() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        ColorBet bet = new ColorBet();
        bet.setMoney(100);
        bet.setColor("black");
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        mock.perform(post("/roulette/"+id+"/bets").content(mapper.writeValueAsString(bet)).
                header("Content-Type","application/json")).andExpect(status().is4xxClientError());
    }
    @Test
    public void shouldBeAddABetWithCorrectColor() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        ColorBet bet = new ColorBet();
        bet.setMoney(100);
        bet.setColor("black");
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shouldBeAddABetWithCorrectNumber() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        mock.perform(patch("/roulette/"+id+"/open")).andExpect(status().is2xxSuccessful());
        NumberBet bet = new NumberBet();
        bet.setMoney(100);
        bet.setNumber(5);
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shouldBeSendA403ErrorIfNotHeader() throws Exception {
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        NumberBet bet = new NumberBet();
        bet.setMoney(100);
        bet.setNumber(5);
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json")).andExpect(status().is4xxClientError());
    }
    @AfterClass
    public static void after(){
        server.stop();
    }
}
