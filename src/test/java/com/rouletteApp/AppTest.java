/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rouletteApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouletteApp.model.ColorBet;
import com.rouletteApp.model.NumberBet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;

    private String id;


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
    public void shouldBeAddABetWithCorrectColor() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
        ColorBet bet = new ColorBet();
        bet.setMoney(100);
        bet.setColor("black");
        mock.perform(post("/roulette/"+id+"/bets")
                .content(mapper.writeValueAsBytes(bet)).contentType("application/json").header("Authorization","app")).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void shouldBeAddABetWithCorrectNumber() throws Exception{
        mock.perform(post("/roulette")).andDo(result ->{
            id= result.getResponse().getContentAsString();
        });
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
}