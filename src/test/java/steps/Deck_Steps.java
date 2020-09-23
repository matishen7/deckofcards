package steps;

import Interfaces.ILog;
import base.Load;
import base.Log;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import pojo.Deck_Response;

import static io.restassured.RestAssured.given;

public class Deck_Steps {
    private String url;
    private Deck_Response deck_response;
    private ILog log = new Log();
    Response response;
    @Given("Endpoint to Open a brand new deck of cards")
    public void endpoint_to_open_a_brand_new_deck_of_cards() {
        url = Load.deck.getProperty("deck.base") + Load.deck.getProperty("deck.new");
    }
    @When("I send a request with GET Method to open a brand new deck of cards")
    public void i_send_a_request_with_get_method_to_open_a_brand_new_deck_of_cards() {
        response = given().when().get(url);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Gson gson = new Gson();
        deck_response = gson.fromJson(bodyAsString, Deck_Response.class);
    }
    @Then("I should get a brand new deck of cards")
    public void i_should_get_a_brand_new_deck_of_cards() {
        Assert.assertNotNull(deck_response);
        Assert.assertEquals(true,deck_response.isSuccess());
        log.Print(deck_response);
    }
    @Then("Number of remaining cards should be {int}")
    public void number_of_remaining_cards_should_be(Integer remaining) {
        Assert.assertEquals(remaining.intValue(), deck_response.getRemaining());
    }
    @When("I send a request with GET Method to open a brand new deck of cards with Jokers")
    public void i_send_a_request_with_get_method_to_open_a_brand_new_deck_of_cards_with_jokers() {
        response = given().param("jokers_enabled", true).when().get(url);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Gson gson = new Gson();
        deck_response = gson.fromJson(bodyAsString, Deck_Response.class);
    }
    @When("I send a request to draw a card from a newly opened deck with {int}")
    public void i_send_a_request_to_draw_a_card_from_a_newly_opened_deck_with(int count) {
        url = Load.deck.getProperty("deck.base") + deck_response.getDeck_id() +
                Load.deck.getProperty("deck.draw") + "?count=" + count ;
        System.out.println(url);
        Response response = given().when().get(url);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println(bodyAsString);
        Gson gson = new Gson();
        deck_response = gson.fromJson(bodyAsString, Deck_Response.class);
    }
    @Then("I should get a deck")
    public void i_should_get_a_deck() {
        Assert.assertNotNull(deck_response);
        Assert.assertNotNull(deck_response.getCards());
        Assert.assertNotNull(deck_response.isSuccess());
        Assert.assertNotNull(deck_response.getDeck_id());
        Assert.assertNotNull(deck_response.getRemaining());
        log.Print(deck_response);
    }

    @And("I send a request with GET Method to open a brand new deck of cards without Jokers")
    public void iSendARequestWithGETMethodToOpenABrandNewDeckOfCardsWithoutJokers() {
        response = given().param("jokers_enabled", false).when().get(url);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Gson gson = new Gson();
        deck_response = gson.fromJson(bodyAsString, Deck_Response.class);
    }
}
