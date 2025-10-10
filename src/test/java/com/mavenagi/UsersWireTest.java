package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.AppUserIdentifier;
import com.mavenagi.resources.commons.types.AppUserIdentifyingPropertyType;
import com.mavenagi.resources.commons.types.AppUserRequest;
import com.mavenagi.resources.commons.types.AppUserResponse;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.UserData;
import com.mavenagi.resources.commons.types.VisibilityType;
import com.mavenagi.resources.users.requests.UserDeleteRequest;
import com.mavenagi.resources.users.requests.UserGetRequest;
import com.mavenagi.resources.users.types.AgentUser;
import com.mavenagi.resources.users.types.AgentUserSearchRequest;
import com.mavenagi.resources.users.types.AgentUserSearchResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersWireTest {
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
    public void testSearch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"agentUsers\":[{\"id\":\"id\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"defaultName\":\"defaultName\",\"users\":[{\"userId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"visibleData\":{\"visibleData\":\"visibleData\"}},{\"userId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"visibleData\":{\"visibleData\":\"visibleData\"}}]},{\"id\":\"id\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"defaultName\":\"defaultName\",\"users\":[{\"userId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"visibleData\":{\"visibleData\":\"visibleData\"}},{\"userId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"identifiers\":[{\"value\":\"value\",\"type\":\"EMAIL\"}],\"visibleData\":{\"visibleData\":\"visibleData\"}}]}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        AgentUserSearchResponse response = client.users().search(
            AgentUserSearchRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{}";
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
            + "  \"agentUsers\": [\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"identifiers\": [\n"
            + "        {\n"
            + "          \"value\": \"value\",\n"
            + "          \"type\": \"EMAIL\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"defaultName\": \"defaultName\",\n"
            + "      \"users\": [\n"
            + "        {\n"
            + "          \"userId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"identifiers\": [\n"
            + "            {\n"
            + "              \"value\": \"value\",\n"
            + "              \"type\": \"EMAIL\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"visibleData\": {\n"
            + "            \"visibleData\": \"visibleData\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"userId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"identifiers\": [\n"
            + "            {\n"
            + "              \"value\": \"value\",\n"
            + "              \"type\": \"EMAIL\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"visibleData\": {\n"
            + "            \"visibleData\": \"visibleData\"\n"
            + "          }\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"identifiers\": [\n"
            + "        {\n"
            + "          \"value\": \"value\",\n"
            + "          \"type\": \"EMAIL\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"defaultName\": \"defaultName\",\n"
            + "      \"users\": [\n"
            + "        {\n"
            + "          \"userId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"identifiers\": [\n"
            + "            {\n"
            + "              \"value\": \"value\",\n"
            + "              \"type\": \"EMAIL\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"visibleData\": {\n"
            + "            \"visibleData\": \"visibleData\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"userId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"identifiers\": [\n"
            + "            {\n"
            + "              \"value\": \"value\",\n"
            + "              \"type\": \"EMAIL\"\n"
            + "            }\n"
            + "          ],\n"
            + "          \"visibleData\": {\n"
            + "            \"visibleData\": \"visibleData\"\n"
            + "          }\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ],\n"
            + "  \"number\": 1,\n"
            + "  \"size\": 1,\n"
            + "  \"totalElements\": 1000000,\n"
            + "  \"totalPages\": 1\n"
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
    public void testGetAgentUser() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"id\":\"aus_1234567890\",\"createdAt\":\"2021-01-01T00:00:00Z\",\"updatedAt\":\"2021-01-01T00:00:00Z\",\"identifiers\":[{\"value\":\"joe@myapp.com\",\"type\":\"EMAIL\"}],\"defaultName\":\"Joe\",\"users\":[{\"userId\":{\"type\":\"USER_PROFILE\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"user-0\"},\"identifiers\":[],\"visibleData\":{\"name\":\"Joe\"}},{\"userId\":{\"type\":\"USER_PROFILE\",\"appId\":\"myapp2\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"user-abc\"},\"identifiers\":[{\"value\":\"joe@myapp2.com\",\"type\":\"EMAIL\"}],\"visibleData\":{\"name\":\"Joe Doe\"}}]}"));
        AgentUser response = client.users().getAgentUser("aus_1234567890");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"id\": \"aus_1234567890\",\n"
            + "  \"createdAt\": \"2021-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2021-01-01T00:00:00Z\",\n"
            + "  \"identifiers\": [\n"
            + "    {\n"
            + "      \"value\": \"joe@myapp.com\",\n"
            + "      \"type\": \"EMAIL\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"defaultName\": \"Joe\",\n"
            + "  \"users\": [\n"
            + "    {\n"
            + "      \"userId\": {\n"
            + "        \"type\": \"USER_PROFILE\",\n"
            + "        \"appId\": \"myapp\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"user-0\"\n"
            + "      },\n"
            + "      \"identifiers\": [],\n"
            + "      \"visibleData\": {\n"
            + "        \"name\": \"Joe\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"userId\": {\n"
            + "        \"type\": \"USER_PROFILE\",\n"
            + "        \"appId\": \"myapp2\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"user-abc\"\n"
            + "      },\n"
            + "      \"identifiers\": [\n"
            + "        {\n"
            + "          \"value\": \"joe@myapp2.com\",\n"
            + "          \"type\": \"EMAIL\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"visibleData\": {\n"
            + "        \"name\": \"Joe Doe\"\n"
            + "      }\n"
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
    public void testCreateOrUpdate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"userId\":{\"referenceId\":\"user-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"USER_PROFILE\"},\"agentUserId\":\"aus_1234567890\",\"identifiers\":[{\"value\":\"joe@myapp.com\",\"type\":\"EMAIL\"}],\"data\":{\"name\":{\"value\":\"Joe\",\"visibility\":\"VISIBLE\"}},\"allUserData\":{\"myapp\":{\"name\":\"Joe\"}},\"defaultUserData\":{\"name\":\"Joe\"}}"));
        AppUserResponse response = client.users().createOrUpdate(
            AppUserRequest
                .builder()
                .userId(
                    EntityIdBase
                        .builder()
                        .referenceId("user-0")
                        .build()
                )
                .identifiers(
                    new HashSet<AppUserIdentifier>(
                        Arrays.asList(
                            AppUserIdentifier
                                .builder()
                                .value("joe@myapp.com")
                                .type(AppUserIdentifyingPropertyType.EMAIL)
                                .build()
                        )
                    )
                )
                .data(
                    new HashMap<String, UserData>() {{
                        put("name", UserData
                            .builder()
                            .value("Joe")
                            .visibility(VisibilityType.VISIBLE)
                            .build());
                    }}
                )
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\"\n"
            + "  },\n"
            + "  \"identifiers\": [\n"
            + "    {\n"
            + "      \"value\": \"joe@myapp.com\",\n"
            + "      \"type\": \"EMAIL\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"data\": {\n"
            + "    \"name\": {\n"
            + "      \"value\": \"Joe\",\n"
            + "      \"visibility\": \"VISIBLE\"\n"
            + "    }\n"
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
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"USER_PROFILE\"\n"
            + "  },\n"
            + "  \"agentUserId\": \"aus_1234567890\",\n"
            + "  \"identifiers\": [\n"
            + "    {\n"
            + "      \"value\": \"joe@myapp.com\",\n"
            + "      \"type\": \"EMAIL\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"data\": {\n"
            + "    \"name\": {\n"
            + "      \"value\": \"Joe\",\n"
            + "      \"visibility\": \"VISIBLE\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"allUserData\": {\n"
            + "    \"myapp\": {\n"
            + "      \"name\": \"Joe\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"defaultUserData\": {\n"
            + "    \"name\": \"Joe\"\n"
            + "  }\n"
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
            .setBody("{\"userId\":{\"referenceId\":\"user-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"USER_PROFILE\"},\"agentUserId\":\"aus_1234567890\",\"identifiers\":[{\"value\":\"joe@myapp.com\",\"type\":\"EMAIL\"}],\"data\":{\"name\":{\"value\":\"Joe\",\"visibility\":\"VISIBLE\"}},\"allUserData\":{\"myapp\":{\"name\":\"Joe\"}},\"defaultUserData\":{\"name\":\"Joe\"}}"));
        AppUserResponse response = client.users().get(
            "user-0",
            UserGetRequest
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
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"USER_PROFILE\"\n"
            + "  },\n"
            + "  \"agentUserId\": \"aus_1234567890\",\n"
            + "  \"identifiers\": [\n"
            + "    {\n"
            + "      \"value\": \"joe@myapp.com\",\n"
            + "      \"type\": \"EMAIL\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"data\": {\n"
            + "    \"name\": {\n"
            + "      \"value\": \"Joe\",\n"
            + "      \"visibility\": \"VISIBLE\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"allUserData\": {\n"
            + "    \"myapp\": {\n"
            + "      \"name\": \"Joe\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"defaultUserData\": {\n"
            + "    \"name\": \"Joe\"\n"
            + "  }\n"
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
            .setBody("{}"));
        client.users().delete(
            "user-0",
            UserDeleteRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
}
