package com.moussi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public class TestJSONPatch {

  public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, JsonPatchException {
    // TODO Auto-generated method stub
    ObjectMapper mapper = new ObjectMapper();
    String move = "[{\"op\": \"move\",\"from\": \"/0/d\",\"path\": \"/0/c\"}]";
    String add = "[{\"op\": \"add\",\"path\": \"/0/hello\",\"value\": \"world\"}]";
    String replace = "[{\"op\": \"replace\",\"path\": \"/0/a\",\"value\": \"world héhé\"}]";
    // Feature 1
    JsonNode node = mapper.readValue(replace, JsonNode.class);
    //convert the json string back to object
    final JsonPatch patch = JsonPatch.fromJson(node);

    // orig is also a JsonNode
    String json = "[{ \"a\": \"b\", \"d\": \"f\"}]";

    // Feature 1
    JsonNode orig = mapper.readValue(json, JsonNode.class);
    final JsonNode patched = patch.apply(orig);
    System.out.println(patched.toString());
  }
  

}
