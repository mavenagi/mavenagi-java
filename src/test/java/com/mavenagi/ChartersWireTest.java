package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.charters.requests.CharterDeleteRequest;
import com.mavenagi.resources.charters.requests.CharterGetAncestorsRequest;
import com.mavenagi.resources.charters.requests.CharterGetRequest;
import com.mavenagi.resources.charters.requests.CharterPatchRequest;
import com.mavenagi.resources.charters.types.CharterAncestorsResponse;
import com.mavenagi.resources.charters.types.CharterKnowledgeBaseReference;
import com.mavenagi.resources.charters.types.CharterListChildrenRequest;
import com.mavenagi.resources.charters.types.CharterListChildrenResponse;
import com.mavenagi.resources.charters.types.CharterReferences;
import com.mavenagi.resources.charters.types.CharterRequest;
import com.mavenagi.resources.charters.types.CharterResponse;
import com.mavenagi.resources.charters.types.CharterSearchRequest;
import com.mavenagi.resources.charters.types.CharterSearchResponse;
import com.mavenagi.resources.charters.types.CharterStatus;
import com.mavenagi.resources.charters.types.CharterType;
import com.mavenagi.resources.commons.types.EntityId;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.Precondition;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChartersWireTest {
    private MockWebServer server;
    private MavenAGI client;
    private ObjectMapper objectMapper = ObjectMappers.JSON_MAPPER;
    @BeforeEach
    public void setup() throws Exception {
        server = new MockWebServer();
        server.start();
        client = MavenAGI.builder()
            .url(server.url("/").toString())
            .credentials("testuser", "testpass")
            .build();
    }
    @AfterEach
    public void teardown() throws Exception {
        server.shutdown();
    }
    @Test
    public void testCreateOrUpdate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"manual\":\"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\"childCharterIds\":[],\"references\":{\"knowledgeBases\":[],\"actionIds\":[]},\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}"));
        CharterResponse response = client.charters().createOrUpdate(
            CharterRequest
                .builder()
                .charterId(
                    EntityIdBase
                        .builder()
                        .referenceId("cancellation-flow")
                        .build()
                )
                .name("Cancellation Flow")
                .status(CharterStatus.ACTIVE)
                .references(
                    CharterReferences
                        .builder()
                        .actionIds(
                            new HashSet<EntityId>()
                        )
                        .knowledgeBases(
                            Optional.of(
                                new ArrayList<CharterKnowledgeBaseReference>()
                            )
                        )
                        .build()
                )
                .manual("Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.")
                .precondition(
                    Precondition.conversation()
                )
                .type(CharterType.STANDARD)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"charterId\": {\n"
            + "    \"referenceId\": \"cancellation-flow\"\n"
            + "  },\n"
            + "  \"name\": \"Cancellation Flow\",\n"
            + "  \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"type\": \"STANDARD\",\n"
            + "  \"references\": {\n"
            + "    \"knowledgeBases\": [],\n"
            + "    \"actionIds\": []\n"
            + "  }\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"charterId\": {\n"
            + "    \"referenceId\": \"cancellation-flow\",\n"
            + "    \"appId\": \"support-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CHARTER\"\n"
            + "  },\n"
            + "  \"name\": \"Cancellation Flow\",\n"
            + "  \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "  \"childCharterIds\": [],\n"
            + "  \"references\": {\n"
            + "    \"knowledgeBases\": [],\n"
            + "    \"actionIds\": []\n"
            + "  },\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"type\": \"STANDARD\",\n"
            + "  \"userRank\": 0,\n"
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testGet() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"manual\":\"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\"childCharterIds\":[],\"references\":{\"knowledgeBases\":[],\"actionIds\":[]},\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}"));
        CharterResponse response = client.charters().get(
            "cancellation-flow",
            CharterGetRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"charterId\": {\n"
            + "    \"referenceId\": \"cancellation-flow\",\n"
            + "    \"appId\": \"support-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CHARTER\"\n"
            + "  },\n"
            + "  \"name\": \"Cancellation Flow\",\n"
            + "  \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "  \"childCharterIds\": [],\n"
            + "  \"references\": {\n"
            + "    \"knowledgeBases\": [],\n"
            + "    \"actionIds\": []\n"
            + "  },\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"type\": \"STANDARD\",\n"
            + "  \"userRank\": 0,\n"
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testPatch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"manual\":\"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\"childCharterIds\":[],\"references\":{\"knowledgeBases\":[],\"actionIds\":[]},\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}"));
        CharterResponse response = client.charters().patch(
            "cancellation-flow",
            CharterPatchRequest
                .builder()
                .precondition(
                    Precondition.conversation()
                )
                .status(CharterStatus.ACTIVE)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\"\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"charterId\": {\n"
            + "    \"referenceId\": \"cancellation-flow\",\n"
            + "    \"appId\": \"support-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CHARTER\"\n"
            + "  },\n"
            + "  \"name\": \"Cancellation Flow\",\n"
            + "  \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "  \"childCharterIds\": [],\n"
            + "  \"references\": {\n"
            + "    \"knowledgeBases\": [],\n"
            + "    \"actionIds\": []\n"
            + "  },\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"type\": \"STANDARD\",\n"
            + "  \"userRank\": 0,\n"
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testDelete() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"manual\":\"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\"childCharterIds\":[],\"references\":{\"knowledgeBases\":[],\"actionIds\":[]},\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}"));
        CharterResponse response = client.charters().delete(
            "cancellation-flow",
            CharterDeleteRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"charterId\": {\n"
            + "    \"referenceId\": \"cancellation-flow\",\n"
            + "    \"appId\": \"support-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CHARTER\"\n"
            + "  },\n"
            + "  \"name\": \"Cancellation Flow\",\n"
            + "  \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "  \"childCharterIds\": [],\n"
            + "  \"references\": {\n"
            + "    \"knowledgeBases\": [],\n"
            + "    \"actionIds\": []\n"
            + "  },\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"conversation\",\n"
            + "    \"value\": {\n"
            + "      \"conversationPreconditionType\": \"tags\",\n"
            + "      \"tags\": [\n"
            + "        \"cancellation\"\n"
            + "      ]\n"
            + "    }\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"type\": \"STANDARD\",\n"
            + "  \"userRank\": 0,\n"
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testSearch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"number\":0,\"size\":20,\"totalElements\":1,\"totalPages\":1,\"charters\":[{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"parentCharterId\":null,\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"childrenExclusionPolicy\":\"DEFAULT\",\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}]}"));
        CharterSearchResponse response = client.charters().search(
            CharterSearchRequest
                .builder()
                .page(0)
                .size(20)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"page\": 0,\n"
            + "  \"size\": 20\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"number\": 0,\n"
            + "  \"size\": 20,\n"
            + "  \"totalElements\": 1,\n"
            + "  \"totalPages\": 1,\n"
            + "  \"charters\": [\n"
            + "    {\n"
            + "      \"charterId\": {\n"
            + "        \"referenceId\": \"cancellation-flow\",\n"
            + "        \"appId\": \"support-app\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CHARTER\"\n"
            + "      },\n"
            + "      \"name\": \"Cancellation Flow\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"conversation\",\n"
            + "        \"value\": {\n"
            + "          \"conversationPreconditionType\": \"tags\",\n"
            + "          \"tags\": [\n"
            + "            \"cancellation\"\n"
            + "          ]\n"
            + "        }\n"
            + "      },\n"
            + "      \"parentCharterId\": null,\n"
            + "      \"status\": \"ACTIVE\",\n"
            + "      \"type\": \"STANDARD\",\n"
            + "      \"childrenExclusionPolicy\": \"DEFAULT\",\n"
            + "      \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "      \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testGetAncestors() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"charters\":[{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"childCharterIds\":[{\"referenceId\":\"cancellation-step-1\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"}],\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"references\":{\"knowledgeBases\":[{\"knowledgeBaseId\":{\"referenceId\":\"cancellation-policy\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"}}],\"actionIds\":[]},\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"},{\"charterId\":{\"referenceId\":\"cancellation-step-1\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Step 1\",\"parentCharterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"childCharterIds\":[],\"precondition\":{\"preconditionType\":\"user\",\"key\":\"plan\",\"value\":\"premium\"},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"references\":{\"knowledgeBases\":[],\"actionIds\":[{\"referenceId\":\"cancel-subscription\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"ACTION\"}]},\"createdAt\":\"2025-01-02T00:00:00Z\",\"updatedAt\":\"2025-01-16T12:30:00Z\"}]}"));
        CharterAncestorsResponse response = client.charters().getAncestors(
            "cancellation-step-1",
            CharterGetAncestorsRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"charters\": [\n"
            + "    {\n"
            + "      \"charterId\": {\n"
            + "        \"referenceId\": \"cancellation-flow\",\n"
            + "        \"appId\": \"support-app\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CHARTER\"\n"
            + "      },\n"
            + "      \"name\": \"Cancellation Flow\",\n"
            + "      \"childCharterIds\": [\n"
            + "        {\n"
            + "          \"referenceId\": \"cancellation-step-1\",\n"
            + "          \"appId\": \"support-app\",\n"
            + "          \"organizationId\": \"acme\",\n"
            + "          \"agentId\": \"support\",\n"
            + "          \"type\": \"CHARTER\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"status\": \"ACTIVE\",\n"
            + "      \"type\": \"STANDARD\",\n"
            + "      \"userRank\": 0,\n"
            + "      \"references\": {\n"
            + "        \"knowledgeBases\": [\n"
            + "          {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"referenceId\": \"cancellation-policy\",\n"
            + "              \"appId\": \"support-app\",\n"
            + "              \"organizationId\": \"acme\",\n"
            + "              \"agentId\": \"support\",\n"
            + "              \"type\": \"KNOWLEDGE_BASE\"\n"
            + "            }\n"
            + "          }\n"
            + "        ],\n"
            + "        \"actionIds\": []\n"
            + "      },\n"
            + "      \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "      \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"charterId\": {\n"
            + "        \"referenceId\": \"cancellation-step-1\",\n"
            + "        \"appId\": \"support-app\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CHARTER\"\n"
            + "      },\n"
            + "      \"name\": \"Cancellation Step 1\",\n"
            + "      \"parentCharterId\": {\n"
            + "        \"referenceId\": \"cancellation-flow\",\n"
            + "        \"appId\": \"support-app\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CHARTER\"\n"
            + "      },\n"
            + "      \"childCharterIds\": [],\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"plan\",\n"
            + "        \"value\": \"premium\"\n"
            + "      },\n"
            + "      \"status\": \"ACTIVE\",\n"
            + "      \"type\": \"STANDARD\",\n"
            + "      \"userRank\": 0,\n"
            + "      \"references\": {\n"
            + "        \"knowledgeBases\": [],\n"
            + "        \"actionIds\": [\n"
            + "          {\n"
            + "            \"referenceId\": \"cancel-subscription\",\n"
            + "            \"appId\": \"support-app\",\n"
            + "            \"organizationId\": \"acme\",\n"
            + "            \"agentId\": \"support\",\n"
            + "            \"type\": \"ACTION\"\n"
            + "          }\n"
            + "        ]\n"
            + "      },\n"
            + "      \"createdAt\": \"2025-01-02T00:00:00Z\",\n"
            + "      \"updatedAt\": \"2025-01-16T12:30:00Z\"\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testListChildren() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"number\":0,\"size\":500,\"totalElements\":1,\"totalPages\":1,\"results\":[{\"hasMore\":false,\"children\":[{\"charterId\":{\"referenceId\":\"cancellation-flow\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"},\"name\":\"Cancellation Flow\",\"manual\":\"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\"childCharterIds\":[{\"referenceId\":\"cancellation-step-1\",\"appId\":\"support-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CHARTER\"}],\"references\":{\"knowledgeBases\":[],\"actionIds\":[]},\"precondition\":{\"preconditionType\":\"conversation\",\"value\":{\"conversationPreconditionType\":\"tags\",\"tags\":[\"cancellation\"]}},\"status\":\"ACTIVE\",\"type\":\"STANDARD\",\"userRank\":0,\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-01-15T12:30:00Z\"}]}]}"));
        CharterListChildrenResponse response = client.charters().listChildren(
            CharterListChildrenRequest
                .builder()
                .parentIds(
                    new ArrayList<EntityId>()
                )
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"parentIds\": []\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"number\": 0,\n"
            + "  \"size\": 500,\n"
            + "  \"totalElements\": 1,\n"
            + "  \"totalPages\": 1,\n"
            + "  \"results\": [\n"
            + "    {\n"
            + "      \"hasMore\": false,\n"
            + "      \"children\": [\n"
            + "        {\n"
            + "          \"charterId\": {\n"
            + "            \"referenceId\": \"cancellation-flow\",\n"
            + "            \"appId\": \"support-app\",\n"
            + "            \"organizationId\": \"acme\",\n"
            + "            \"agentId\": \"support\",\n"
            + "            \"type\": \"CHARTER\"\n"
            + "          },\n"
            + "          \"name\": \"Cancellation Flow\",\n"
            + "          \"manual\": \"Guide the user through the cancellation process with empathy. Always confirm their intent before proceeding.\",\n"
            + "          \"childCharterIds\": [\n"
            + "            {\n"
            + "              \"referenceId\": \"cancellation-step-1\",\n"
            + "              \"appId\": \"support-app\",\n"
            + "              \"organizationId\": \"acme\",\n"
            + "              \"agentId\": \"support\",\n"
            + "              \"type\": \"CHARTER\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"references\": {\n"
            + "            \"knowledgeBases\": [],\n"
            + "            \"actionIds\": []\n"
            + "          },\n"
            + "          \"precondition\": {\n"
            + "            \"preconditionType\": \"conversation\",\n"
            + "            \"value\": {\n"
            + "              \"conversationPreconditionType\": \"tags\",\n"
            + "              \"tags\": [\n"
            + "                \"cancellation\"\n"
            + "              ]\n"
            + "            }\n"
            + "          },\n"
            + "          \"status\": \"ACTIVE\",\n"
            + "          \"type\": \"STANDARD\",\n"
            + "          \"userRank\": 0,\n"
            + "          \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "          \"updatedAt\": \"2025-01-15T12:30:00Z\"\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
}
