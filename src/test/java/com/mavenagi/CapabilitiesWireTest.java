package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.capabilities.requests.CapabilitiesSearchRequest;
import com.mavenagi.resources.capabilities.requests.DeleteCapabilityByTypeRequest;
import com.mavenagi.resources.capabilities.requests.GetCapabilityByTypeRequest;
import com.mavenagi.resources.capabilities.requests.PatchCapabilityByTypeRequest;
import com.mavenagi.resources.capabilities.types.CapabilitiesSearchResponse;
import com.mavenagi.resources.capabilities.types.CapabilityResponse;
import com.mavenagi.resources.commons.types.CapabilityType;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CapabilitiesWireTest {
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
    public void testGetCapability() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"capabilityType\":\"ACTION\",\"actionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"instructions\":\"instructions\",\"llmInclusionStatus\":\"ALWAYS\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"preconditionExplanation\":\"preconditionExplanation\",\"deleted\":true,\"userInteractionRequired\":true,\"buttonName\":\"buttonName\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"values\":[\"values\",\"values\"],\"operator\":\"NOT\"},\"userFormParameters\":[{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}},{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}}],\"language\":\"language\",\"sideEffects\":\"NONE\",\"name\":\"name\",\"description\":\"description\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"ACTIVE\"}"));
        CapabilityResponse response = client.capabilities().getCapability(
            CapabilityType.ACTION,
            "referenceId",
            GetCapabilityByTypeRequest
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
            + "  \"capabilityType\": \"ACTION\",\n"
            + "  \"actionId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"instructions\": \"instructions\",\n"
            + "  \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "  \"segmentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"preconditionExplanation\": \"preconditionExplanation\",\n"
            + "  \"deleted\": true,\n"
            + "  \"userInteractionRequired\": true,\n"
            + "  \"buttonName\": \"buttonName\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"user\",\n"
            + "    \"key\": \"key\",\n"
            + "    \"value\": \"value\",\n"
            + "    \"values\": [\n"
            + "      \"values\",\n"
            + "      \"values\"\n"
            + "    ],\n"
            + "    \"operator\": \"NOT\"\n"
            + "  },\n"
            + "  \"userFormParameters\": [\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"label\": \"label\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"required\": true,\n"
            + "      \"hidden\": true,\n"
            + "      \"type\": \"STRING\",\n"
            + "      \"enumOptions\": [\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"schema\": \"schema\",\n"
            + "      \"oauthConfiguration\": {\n"
            + "        \"authorizationUrl\": \"authorizationUrl\",\n"
            + "        \"tokenUrl\": \"tokenUrl\",\n"
            + "        \"clientId\": \"clientId\",\n"
            + "        \"clientSecret\": \"clientSecret\",\n"
            + "        \"scopes\": [\n"
            + "          \"scopes\",\n"
            + "          \"scopes\"\n"
            + "        ],\n"
            + "        \"extraAuthParams\": {\n"
            + "          \"extraAuthParams\": \"extraAuthParams\"\n"
            + "        },\n"
            + "        \"extraTokenParams\": {\n"
            + "          \"extraTokenParams\": \"extraTokenParams\"\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"label\": \"label\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"required\": true,\n"
            + "      \"hidden\": true,\n"
            + "      \"type\": \"STRING\",\n"
            + "      \"enumOptions\": [\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"schema\": \"schema\",\n"
            + "      \"oauthConfiguration\": {\n"
            + "        \"authorizationUrl\": \"authorizationUrl\",\n"
            + "        \"tokenUrl\": \"tokenUrl\",\n"
            + "        \"clientId\": \"clientId\",\n"
            + "        \"clientSecret\": \"clientSecret\",\n"
            + "        \"scopes\": [\n"
            + "          \"scopes\",\n"
            + "          \"scopes\"\n"
            + "        ],\n"
            + "        \"extraAuthParams\": {\n"
            + "          \"extraAuthParams\": \"extraAuthParams\"\n"
            + "        },\n"
            + "        \"extraTokenParams\": {\n"
            + "          \"extraTokenParams\": \"extraTokenParams\"\n"
            + "        }\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"language\": \"language\",\n"
            + "  \"sideEffects\": \"NONE\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"status\": \"ACTIVE\"\n"
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
    public void testPatchCapability() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"capabilityType\":\"ACTION\",\"actionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"instructions\":\"instructions\",\"llmInclusionStatus\":\"ALWAYS\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"preconditionExplanation\":\"preconditionExplanation\",\"deleted\":true,\"userInteractionRequired\":true,\"buttonName\":\"buttonName\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"values\":[\"values\",\"values\"],\"operator\":\"NOT\"},\"userFormParameters\":[{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}},{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}}],\"language\":\"language\",\"sideEffects\":\"NONE\",\"name\":\"name\",\"description\":\"description\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"ACTIVE\"}"));
        CapabilityResponse response = client.capabilities().patchCapability(
            CapabilityType.ACTION,
            "referenceId",
            PatchCapabilityByTypeRequest
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
            + "  \"capabilityType\": \"ACTION\",\n"
            + "  \"actionId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"instructions\": \"instructions\",\n"
            + "  \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "  \"segmentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"preconditionExplanation\": \"preconditionExplanation\",\n"
            + "  \"deleted\": true,\n"
            + "  \"userInteractionRequired\": true,\n"
            + "  \"buttonName\": \"buttonName\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"user\",\n"
            + "    \"key\": \"key\",\n"
            + "    \"value\": \"value\",\n"
            + "    \"values\": [\n"
            + "      \"values\",\n"
            + "      \"values\"\n"
            + "    ],\n"
            + "    \"operator\": \"NOT\"\n"
            + "  },\n"
            + "  \"userFormParameters\": [\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"label\": \"label\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"required\": true,\n"
            + "      \"hidden\": true,\n"
            + "      \"type\": \"STRING\",\n"
            + "      \"enumOptions\": [\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"schema\": \"schema\",\n"
            + "      \"oauthConfiguration\": {\n"
            + "        \"authorizationUrl\": \"authorizationUrl\",\n"
            + "        \"tokenUrl\": \"tokenUrl\",\n"
            + "        \"clientId\": \"clientId\",\n"
            + "        \"clientSecret\": \"clientSecret\",\n"
            + "        \"scopes\": [\n"
            + "          \"scopes\",\n"
            + "          \"scopes\"\n"
            + "        ],\n"
            + "        \"extraAuthParams\": {\n"
            + "          \"extraAuthParams\": \"extraAuthParams\"\n"
            + "        },\n"
            + "        \"extraTokenParams\": {\n"
            + "          \"extraTokenParams\": \"extraTokenParams\"\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"id\": \"id\",\n"
            + "      \"label\": \"label\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"required\": true,\n"
            + "      \"hidden\": true,\n"
            + "      \"type\": \"STRING\",\n"
            + "      \"enumOptions\": [\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"label\": \"label\",\n"
            + "          \"value\": {\n"
            + "            \"key\": \"value\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"schema\": \"schema\",\n"
            + "      \"oauthConfiguration\": {\n"
            + "        \"authorizationUrl\": \"authorizationUrl\",\n"
            + "        \"tokenUrl\": \"tokenUrl\",\n"
            + "        \"clientId\": \"clientId\",\n"
            + "        \"clientSecret\": \"clientSecret\",\n"
            + "        \"scopes\": [\n"
            + "          \"scopes\",\n"
            + "          \"scopes\"\n"
            + "        ],\n"
            + "        \"extraAuthParams\": {\n"
            + "          \"extraAuthParams\": \"extraAuthParams\"\n"
            + "        },\n"
            + "        \"extraTokenParams\": {\n"
            + "          \"extraTokenParams\": \"extraTokenParams\"\n"
            + "        }\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"language\": \"language\",\n"
            + "  \"sideEffects\": \"NONE\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"status\": \"ACTIVE\"\n"
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
    public void testDeleteCapability() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.capabilities().deleteCapability(
            CapabilityType.ACTION,
            "referenceId",
            DeleteCapabilityByTypeRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
    @Test
    public void testSearchCapabilities() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"capabilities\":[{\"capabilityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"capabilityType\":\"ACTION\",\"name\":\"name\",\"description\":\"description\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"ACTIVE\"},{\"capabilityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"capabilityType\":\"ACTION\",\"name\":\"name\",\"description\":\"description\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"ACTIVE\"}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        CapabilitiesSearchResponse response = client.capabilities().searchCapabilities(
            CapabilitiesSearchRequest
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
            + "  \"capabilities\": [\n"
            + "    {\n"
            + "      \"capabilityId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"capabilityType\": \"ACTION\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"status\": \"ACTIVE\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"capabilityId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"capabilityType\": \"ACTION\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"status\": \"ACTIVE\"\n"
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
}
