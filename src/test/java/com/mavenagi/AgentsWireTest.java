package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.agents.requests.AgentPatchRequest;
import com.mavenagi.resources.agents.types.Agent;
import com.mavenagi.resources.agents.types.AgentEnvironment;
import com.mavenagi.resources.agents.types.AgentsSearchRequest;
import com.mavenagi.resources.agents.types.AgentsSearchResponse;
import com.mavenagi.resources.agents.types.CreateAgentRequest;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgentsWireTest {
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
            .setBody("{\"agents\":[{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}},{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        AgentsSearchResponse response = client.agents().search(
            AgentsSearchRequest
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
            + "  \"agents\": [\n"
            + "    {\n"
            + "      \"agentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"name\": \"name\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"environment\": \"DEMO\",\n"
            + "      \"defaultTimezone\": \"defaultTimezone\",\n"
            + "      \"enabledPiiCategories\": [\n"
            + "        \"Name\"\n"
            + "      ],\n"
            + "      \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "      \"prompting\": {\n"
            + "        \"persona\": \"CASUAL_BUDDY\",\n"
            + "        \"additionalPromptText\": \"additionalPromptText\",\n"
            + "        \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "        \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "        \"rejectQuestionsWithoutKnowledge\": true\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"agentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"name\": \"name\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"environment\": \"DEMO\",\n"
            + "      \"defaultTimezone\": \"defaultTimezone\",\n"
            + "      \"enabledPiiCategories\": [\n"
            + "        \"Name\"\n"
            + "      ],\n"
            + "      \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "      \"prompting\": {\n"
            + "        \"persona\": \"CASUAL_BUDDY\",\n"
            + "        \"additionalPromptText\": \"additionalPromptText\",\n"
            + "        \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "        \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "        \"rejectQuestionsWithoutKnowledge\": true\n"
            + "      }\n"
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
    public void testList() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("[{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}},{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}}]"));
        List<Agent> response = client.agents().list("organizationReferenceId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "[\n"
            + "  {\n"
            + "    \"agentId\": {\n"
            + "      \"organizationId\": \"organizationId\",\n"
            + "      \"agentId\": \"agentId\",\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"name\": \"name\",\n"
            + "    \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"environment\": \"DEMO\",\n"
            + "    \"defaultTimezone\": \"defaultTimezone\",\n"
            + "    \"enabledPiiCategories\": [\n"
            + "      \"Name\"\n"
            + "    ],\n"
            + "    \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "    \"prompting\": {\n"
            + "      \"persona\": \"CASUAL_BUDDY\",\n"
            + "      \"additionalPromptText\": \"additionalPromptText\",\n"
            + "      \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "      \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "      \"rejectQuestionsWithoutKnowledge\": true\n"
            + "    }\n"
            + "  },\n"
            + "  {\n"
            + "    \"agentId\": {\n"
            + "      \"organizationId\": \"organizationId\",\n"
            + "      \"agentId\": \"agentId\",\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"name\": \"name\",\n"
            + "    \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"environment\": \"DEMO\",\n"
            + "    \"defaultTimezone\": \"defaultTimezone\",\n"
            + "    \"enabledPiiCategories\": [\n"
            + "      \"Name\"\n"
            + "    ],\n"
            + "    \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "    \"prompting\": {\n"
            + "      \"persona\": \"CASUAL_BUDDY\",\n"
            + "      \"additionalPromptText\": \"additionalPromptText\",\n"
            + "      \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "      \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "      \"rejectQuestionsWithoutKnowledge\": true\n"
            + "    }\n"
            + "  }\n"
            + "]";
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
    public void testCreate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}}"));
        Agent response = client.agents().create(
            "organizationReferenceId",
            "agentReferenceId",
            CreateAgentRequest
                .builder()
                .name("name")
                .environment(AgentEnvironment.DEMO)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"name\": \"name\",\n"
            + "  \"environment\": \"DEMO\"\n"
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
            + "  \"agentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"environment\": \"DEMO\",\n"
            + "  \"defaultTimezone\": \"defaultTimezone\",\n"
            + "  \"enabledPiiCategories\": [\n"
            + "    \"Name\"\n"
            + "  ],\n"
            + "  \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "  \"prompting\": {\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "    \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "    \"rejectQuestionsWithoutKnowledge\": true\n"
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
            .setBody("{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}}"));
        Agent response = client.agents().get("organizationReferenceId", "agentReferenceId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"agentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"environment\": \"DEMO\",\n"
            + "  \"defaultTimezone\": \"defaultTimezone\",\n"
            + "  \"enabledPiiCategories\": [\n"
            + "    \"Name\"\n"
            + "  ],\n"
            + "  \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "  \"prompting\": {\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "    \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "    \"rejectQuestionsWithoutKnowledge\": true\n"
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
    public void testPatch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"agentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"name\":\"name\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"environment\":\"DEMO\",\"defaultTimezone\":\"defaultTimezone\",\"enabledPiiCategories\":[\"Name\"],\"systemFallbackMessage\":\"systemFallbackMessage\",\"prompting\":{\"persona\":\"CASUAL_BUDDY\",\"additionalPromptText\":\"additionalPromptText\",\"categoryGenerationPromptText\":\"categoryGenerationPromptText\",\"contentSafetyViolationResponsePromptText\":\"contentSafetyViolationResponsePromptText\",\"rejectQuestionsWithoutKnowledge\":true}}"));
        Agent response = client.agents().patch(
            "organizationReferenceId",
            "agentReferenceId",
            AgentPatchRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
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
            + "  \"agentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"environment\": \"DEMO\",\n"
            + "  \"defaultTimezone\": \"defaultTimezone\",\n"
            + "  \"enabledPiiCategories\": [\n"
            + "    \"Name\"\n"
            + "  ],\n"
            + "  \"systemFallbackMessage\": \"systemFallbackMessage\",\n"
            + "  \"prompting\": {\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"categoryGenerationPromptText\": \"categoryGenerationPromptText\",\n"
            + "    \"contentSafetyViolationResponsePromptText\": \"contentSafetyViolationResponsePromptText\",\n"
            + "    \"rejectQuestionsWithoutKnowledge\": true\n"
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
        client.agents().delete("organizationReferenceId", "agentReferenceId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
}
