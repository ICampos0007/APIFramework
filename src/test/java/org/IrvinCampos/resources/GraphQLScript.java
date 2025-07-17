package org.IrvinCampos.resources;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GraphQLScript {
    @Test
    public void GraphQLScriptTest() {
//        Query
        int characterID = 16085;
        String response = given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"query($characterId: Int!, $episodeId: Int!){\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 22591) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Miya\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {name: \\\"hulu\\\"} pagination:{page: 1, limit:1}) {\\n    result{\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"+characterID+",\"episodeId\":15479}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();

        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String characterName = jsonPath.getString("data.character.name");
        Assert.assertEquals(characterName, "Baskin Robin");

//        Mutations
        String newCharacter = "Cozy";
        String mutationResponse = given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"mutation($locationName: String!, $characterName: String! $episodeName: String!) {\\n  createLocation(location:{name: $locationName, type: \\\"Southzone\\\", dimension: \\\"234\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Macho\\\", status: \\\"dead\\\", species: \\\"fantasy\\\", gender: \\\"Male\\\", image: \\\"png\\\", originId: 22583, locationId: 22583}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"07/16/2025\\\", episode: \\\"Funny\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds:22585) {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"Australia\",\"characterName\":\""+newCharacter+"\",\"episodeName\":\"manifest\"}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();

        System.out.println(mutationResponse);
        JsonPath jsonPath2 = new JsonPath(mutationResponse);

    }
}
