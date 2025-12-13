package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.EntityId;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.EntityIdWithoutAgent;
import com.mavenagi.resources.commons.types.EntityType;
import com.mavenagi.resources.commons.types.LlmInclusionStatus;
import com.mavenagi.resources.knowledge.requests.KnowledgeBaseGetRequest;
import com.mavenagi.resources.knowledge.requests.KnowledgeBasePatchRequest;
import com.mavenagi.resources.knowledge.requests.KnowledgeBaseVersionsListRequest;
import com.mavenagi.resources.knowledge.requests.KnowledgeDocumentGetRequest;
import com.mavenagi.resources.knowledge.requests.KnowledgeDocumentPatchRequest;
import com.mavenagi.resources.knowledge.types.FinalizeKnowledgeBaseVersionRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseRefreshRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseResponse;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseSearchRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseVersion;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseVersionFinalizeStatus;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseVersionRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseVersionType;
import com.mavenagi.resources.knowledge.types.KnowledgeBaseVersionsListResponse;
import com.mavenagi.resources.knowledge.types.KnowledgeBasesResponse;
import com.mavenagi.resources.knowledge.types.KnowledgeDeleteRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeDocumentContentType;
import com.mavenagi.resources.knowledge.types.KnowledgeDocumentRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeDocumentResponse;
import com.mavenagi.resources.knowledge.types.KnowledgeDocumentSearchRequest;
import com.mavenagi.resources.knowledge.types.KnowledgeDocumentsResponse;
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

public class KnowledgeWireTest {
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
    public void testSearchKnowledgeBases() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"knowledgeBases\":[{\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"knowledgeBaseId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"activeVersionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"mostRecentVersionStatus\":\"SUCCEEDED\",\"type\":\"API\",\"metadata\":{\"metadata\":\"metadata\"},\"tags\":[\"tags\"],\"llmInclusionStatus\":\"ALWAYS\",\"refreshFrequency\":\"NONE\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"name\":\"name\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"operator\":\"NOT\"}},{\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"knowledgeBaseId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"activeVersionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"mostRecentVersionStatus\":\"SUCCEEDED\",\"type\":\"API\",\"metadata\":{\"metadata\":\"metadata\"},\"tags\":[\"tags\"],\"llmInclusionStatus\":\"ALWAYS\",\"refreshFrequency\":\"NONE\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"name\":\"name\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"operator\":\"NOT\"}}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        KnowledgeBasesResponse response = client.knowledge().searchKnowledgeBases(
            KnowledgeBaseSearchRequest
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
            + "  \"knowledgeBases\": [\n"
            + "    {\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"activeVersionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"mostRecentVersionStatus\": \"SUCCEEDED\",\n"
            + "      \"type\": \"API\",\n"
            + "      \"metadata\": {\n"
            + "        \"metadata\": \"metadata\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"tags\"\n"
            + "      ],\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"refreshFrequency\": \"NONE\",\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"name\": \"name\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"key\",\n"
            + "        \"value\": \"value\",\n"
            + "        \"operator\": \"NOT\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"activeVersionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"mostRecentVersionStatus\": \"SUCCEEDED\",\n"
            + "      \"type\": \"API\",\n"
            + "      \"metadata\": {\n"
            + "        \"metadata\": \"metadata\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"tags\"\n"
            + "      ],\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"refreshFrequency\": \"NONE\",\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"name\": \"name\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"key\",\n"
            + "        \"value\": \"value\",\n"
            + "        \"operator\": \"NOT\"\n"
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
    public void testCreateOrUpdateKnowledgeBase() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-02-02T00:00:00Z\",\"knowledgeBaseId\":{\"referenceId\":\"help-center\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"},\"activeVersionId\":{\"referenceId\":\"version-1\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE_VERSION\"},\"mostRecentVersionStatus\":\"SUCCEEDED\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"name\":\"Help center\",\"type\":\"API\",\"metadata\":{\"key\":\"value\"},\"tags\":[\"tag1\",\"tag2\"],\"refreshFrequency\":\"DAILY\",\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"}}"));
        KnowledgeBaseResponse response = client.knowledge().createOrUpdateKnowledgeBase(
            KnowledgeBaseRequest
                .builder()
                .knowledgeBaseId(
                    EntityIdBase
                        .builder()
                        .referenceId("help-center")
                        .build()
                )
                .name("Help center")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-center\"\n"
            + "  },\n"
            + "  \"name\": \"Help center\"\n"
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
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-02-02T00:00:00Z\",\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-center\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE\"\n"
            + "  },\n"
            + "  \"activeVersionId\": {\n"
            + "    \"referenceId\": \"version-1\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\"\n"
            + "  },\n"
            + "  \"mostRecentVersionStatus\": \"SUCCEEDED\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"name\": \"Help center\",\n"
            + "  \"type\": \"API\",\n"
            + "  \"metadata\": {\n"
            + "    \"key\": \"value\"\n"
            + "  },\n"
            + "  \"tags\": [\n"
            + "    \"tag1\",\n"
            + "    \"tag2\"\n"
            + "  ],\n"
            + "  \"refreshFrequency\": \"DAILY\",\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
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
    public void testGetKnowledgeBase() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-02-02T00:00:00Z\",\"knowledgeBaseId\":{\"referenceId\":\"help-center\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"},\"activeVersionId\":{\"referenceId\":\"version-1\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE_VERSION\"},\"mostRecentVersionStatus\":\"SUCCEEDED\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"name\":\"Help center\",\"type\":\"API\",\"metadata\":{\"key\":\"value\"},\"tags\":[\"tag1\",\"tag2\"],\"refreshFrequency\":\"DAILY\",\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"}}"));
        KnowledgeBaseResponse response = client.knowledge().getKnowledgeBase(
            "help-center",
            KnowledgeBaseGetRequest
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
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-02-02T00:00:00Z\",\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-center\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE\"\n"
            + "  },\n"
            + "  \"activeVersionId\": {\n"
            + "    \"referenceId\": \"version-1\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\"\n"
            + "  },\n"
            + "  \"mostRecentVersionStatus\": \"SUCCEEDED\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"name\": \"Help center\",\n"
            + "  \"type\": \"API\",\n"
            + "  \"metadata\": {\n"
            + "    \"key\": \"value\"\n"
            + "  },\n"
            + "  \"tags\": [\n"
            + "    \"tag1\",\n"
            + "    \"tag2\"\n"
            + "  ],\n"
            + "  \"refreshFrequency\": \"DAILY\",\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
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
    public void testRefreshKnowledgeBase() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.knowledge().refreshKnowledgeBase(
            "help-center",
            KnowledgeBaseRefreshRequest
                .builder()
                .appId("readme")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"appId\": \"readme\"\n"
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
    }
    @Test
    public void testPatchKnowledgeBase() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"createdAt\":\"2025-01-01T00:00:00Z\",\"updatedAt\":\"2025-02-02T00:00:00Z\",\"knowledgeBaseId\":{\"referenceId\":\"help-center\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"},\"activeVersionId\":{\"referenceId\":\"version-1\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE_VERSION\"},\"mostRecentVersionStatus\":\"SUCCEEDED\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"name\":\"Help center\",\"type\":\"API\",\"metadata\":{\"key\":\"value\"},\"tags\":[\"tag1\",\"tag2\"],\"refreshFrequency\":\"DAILY\",\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"}}"));
        KnowledgeBaseResponse response = client.knowledge().patchKnowledgeBase(
            "help-center",
            KnowledgeBasePatchRequest
                .builder()
                .name("Updated Help Center")
                .tags(
                    new HashSet<String>(
                        Arrays.asList("tag1", "tag2", "tag3")
                    )
                )
                .segmentId(
                    EntityId
                        .builder()
                        .referenceId("premium-users")
                        .appId("readme")
                        .organizationId("acme")
                        .agentId("support")
                        .type(EntityType.SEGMENT)
                        .build()
                )
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"name\": \"Updated Help Center\",\n"
            + "  \"tags\": [\n"
            + "    \"tag1\",\n"
            + "    \"tag2\",\n"
            + "    \"tag3\"\n"
            + "  ],\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
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
            + "  \"createdAt\": \"2025-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2025-02-02T00:00:00Z\",\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-center\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE\"\n"
            + "  },\n"
            + "  \"activeVersionId\": {\n"
            + "    \"referenceId\": \"version-1\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\"\n"
            + "  },\n"
            + "  \"mostRecentVersionStatus\": \"SUCCEEDED\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"name\": \"Help center\",\n"
            + "  \"type\": \"API\",\n"
            + "  \"metadata\": {\n"
            + "    \"key\": \"value\"\n"
            + "  },\n"
            + "  \"tags\": [\n"
            + "    \"tag1\",\n"
            + "    \"tag2\"\n"
            + "  ],\n"
            + "  \"refreshFrequency\": \"DAILY\",\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
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
    public void testCreateKnowledgeBaseVersion() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"versionId\":{\"type\":\"KNOWLEDGE_BASE_VERSION\",\"referenceId\":\"versionId\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\"},\"type\":\"FULL\",\"status\":\"IN_PROGRESS\",\"createdAt\":\"2024-01-01T00:00:00Z\",\"updatedAt\":\"2024-02-02T00:00:00Z\"}"));
        KnowledgeBaseVersion response = client.knowledge().createKnowledgeBaseVersion(
            "help-center",
            KnowledgeBaseVersionRequest
                .builder()
                .type(KnowledgeBaseVersionType.FULL)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"type\": \"FULL\"\n"
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
            + "  \"versionId\": {\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\",\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\"\n"
            + "  },\n"
            + "  \"type\": \"FULL\",\n"
            + "  \"status\": \"IN_PROGRESS\",\n"
            + "  \"createdAt\": \"2024-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2024-02-02T00:00:00Z\"\n"
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
    public void testFinalizeKnowledgeBaseVersion() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"versionId\":{\"type\":\"KNOWLEDGE_BASE_VERSION\",\"referenceId\":\"versionId\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\"},\"type\":\"FULL\",\"status\":\"IN_PROGRESS\",\"createdAt\":\"2024-01-01T00:00:00Z\",\"updatedAt\":\"2024-02-02T00:00:00Z\"}"));
        KnowledgeBaseVersion response = client.knowledge().finalizeKnowledgeBaseVersion(
            "help-center",
            FinalizeKnowledgeBaseVersionRequest
                .builder()
                .versionId(
                    EntityIdWithoutAgent
                        .builder()
                        .type(EntityType.KNOWLEDGE_BASE_VERSION)
                        .referenceId("versionId")
                        .appId("maven")
                        .build()
                )
                .status(KnowledgeBaseVersionFinalizeStatus.SUCCEEDED)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"versionId\": {\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\",\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\"\n"
            + "  },\n"
            + "  \"status\": \"SUCCEEDED\"\n"
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
            + "  \"versionId\": {\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\",\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\"\n"
            + "  },\n"
            + "  \"type\": \"FULL\",\n"
            + "  \"status\": \"IN_PROGRESS\",\n"
            + "  \"createdAt\": \"2024-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2024-02-02T00:00:00Z\"\n"
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
    public void testListKnowledgeBaseVersions() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"knowledgeBaseVersions\":[{\"versionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"status\":\"SUCCEEDED\",\"errorMessage\":\"errorMessage\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"type\":\"FULL\"},{\"versionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"status\":\"SUCCEEDED\",\"errorMessage\":\"errorMessage\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"type\":\"FULL\"}]}"));
        KnowledgeBaseVersionsListResponse response = client.knowledge().listKnowledgeBaseVersions(
            "knowledgeBaseReferenceId",
            KnowledgeBaseVersionsListRequest
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
            + "  \"knowledgeBaseVersions\": [\n"
            + "    {\n"
            + "      \"versionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"status\": \"SUCCEEDED\",\n"
            + "      \"errorMessage\": \"errorMessage\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"type\": \"FULL\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"versionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"status\": \"SUCCEEDED\",\n"
            + "      \"errorMessage\": \"errorMessage\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"type\": \"FULL\"\n"
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
    public void testSearchKnowledgeDocuments() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"knowledgeDocuments\":[{\"knowledgeDocumentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseVersionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"title\":\"title\",\"llmInclusionStatus\":\"ALWAYS\",\"knowledgeBaseLlmInclusionStatus\":\"ALWAYS\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"url\":\"url\",\"language\":\"language\",\"author\":\"author\"},{\"knowledgeDocumentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseVersionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"title\":\"title\",\"llmInclusionStatus\":\"ALWAYS\",\"knowledgeBaseLlmInclusionStatus\":\"ALWAYS\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"url\":\"url\",\"language\":\"language\",\"author\":\"author\"}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        KnowledgeDocumentsResponse response = client.knowledge().searchKnowledgeDocuments(
            KnowledgeDocumentSearchRequest
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
            + "  \"knowledgeDocuments\": [\n"
            + "    {\n"
            + "      \"knowledgeDocumentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"knowledgeBaseVersionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"title\": \"title\",\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"knowledgeBaseLlmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"url\": \"url\",\n"
            + "      \"language\": \"language\",\n"
            + "      \"author\": \"author\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"knowledgeDocumentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"knowledgeBaseVersionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"title\": \"title\",\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"knowledgeBaseLlmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"url\": \"url\",\n"
            + "      \"language\": \"language\",\n"
            + "      \"author\": \"author\"\n"
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
    public void testCreateKnowledgeDocument() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"knowledgeDocumentId\":{\"referenceId\":\"getting-started\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_DOCUMENT\"},\"knowledgeBaseVersionId\":{\"referenceId\":\"versionId\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE_VERSION\"},\"knowledgeBaseId\":{\"referenceId\":\"help-docs\",\"appId\":\"help-center\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"},\"content\":\"## Getting started This is a getting started guide for the help center.\",\"title\":\"Getting started\",\"metadata\":{\"category\":\"getting-started\"},\"createdAt\":\"2024-01-01T00:00:00Z\",\"updatedAt\":\"2024-02-02T00:00:00Z\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"relevantEntities\":[{\"entityId\":{\"type\":\"CUSTOMER\",\"appId\":\"crm\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"customer-42\"},\"scopeEntityId\":{\"type\":\"AGENT\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"support\"}}],\"knowledgeBaseLlmInclusionStatus\":\"WHEN_RELEVANT\"}"));
        KnowledgeDocumentResponse response = client.knowledge().createKnowledgeDocument(
            "help-center",
            KnowledgeDocumentRequest
                .builder()
                .knowledgeDocumentId(
                    EntityIdBase
                        .builder()
                        .referenceId("getting-started")
                        .build()
                )
                .contentType(KnowledgeDocumentContentType.MARKDOWN)
                .title("Getting started")
                .versionId(
                    EntityIdWithoutAgent
                        .builder()
                        .type(EntityType.KNOWLEDGE_BASE_VERSION)
                        .referenceId("versionId")
                        .appId("maven")
                        .build()
                )
                .content("## Getting started\\nThis is a getting started guide for the help center.")
                .metadata(
                    new HashMap<String, String>() {{
                        put("category", "getting-started");
                    }}
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
            + "  \"knowledgeDocumentId\": {\n"
            + "    \"referenceId\": \"getting-started\"\n"
            + "  },\n"
            + "  \"versionId\": {\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\",\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\"\n"
            + "  },\n"
            + "  \"contentType\": \"MARKDOWN\",\n"
            + "  \"content\": \"## Getting started\\\\nThis is a getting started guide for the help center.\",\n"
            + "  \"title\": \"Getting started\",\n"
            + "  \"metadata\": {\n"
            + "    \"category\": \"getting-started\"\n"
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
            + "  \"knowledgeDocumentId\": {\n"
            + "    \"referenceId\": \"getting-started\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_DOCUMENT\"\n"
            + "  },\n"
            + "  \"knowledgeBaseVersionId\": {\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\"\n"
            + "  },\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-docs\",\n"
            + "    \"appId\": \"help-center\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE\"\n"
            + "  },\n"
            + "  \"content\": \"## Getting started This is a getting started guide for the help center.\",\n"
            + "  \"title\": \"Getting started\",\n"
            + "  \"metadata\": {\n"
            + "    \"category\": \"getting-started\"\n"
            + "  },\n"
            + "  \"createdAt\": \"2024-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2024-02-02T00:00:00Z\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"relevantEntities\": [\n"
            + "    {\n"
            + "      \"entityId\": {\n"
            + "        \"type\": \"CUSTOMER\",\n"
            + "        \"appId\": \"crm\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"customer-42\"\n"
            + "      },\n"
            + "      \"scopeEntityId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"maven\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"support\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"knowledgeBaseLlmInclusionStatus\": \"WHEN_RELEVANT\"\n"
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
    public void testDeleteKnowledgeDocument() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.knowledge().deleteKnowledgeDocument(
            "help-center",
            "getting-started",
            KnowledgeDeleteRequest
                .builder()
                .versionId(
                    EntityIdWithoutAgent
                        .builder()
                        .type(EntityType.KNOWLEDGE_BASE_VERSION)
                        .appId("maven")
                        .referenceId("versionId")
                        .build()
                )
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"versionId\": {\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\",\n"
            + "    \"appId\": \"maven\",\n"
            + "    \"referenceId\": \"versionId\"\n"
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
    }
    @Test
    public void testGetKnowledgeDocument() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"processingStatus\":\"SUCCEEDED\",\"content\":\"content\",\"asset\":{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},\"metadata\":{\"metadata\":\"metadata\"},\"relevantEntities\":[{\"entityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"scopeEntityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"}}],\"knowledgeDocumentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseVersionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"knowledgeBaseId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"title\":\"title\",\"llmInclusionStatus\":\"ALWAYS\",\"knowledgeBaseLlmInclusionStatus\":\"ALWAYS\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"url\":\"url\",\"language\":\"language\",\"author\":\"author\"}"));
        KnowledgeDocumentResponse response = client.knowledge().getKnowledgeDocument(
            "knowledgeBaseVersionReferenceId",
            "knowledgeDocumentReferenceId",
            KnowledgeDocumentGetRequest
                .builder()
                .knowledgeBaseVersionAppId("knowledgeBaseVersionAppId")
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
            + "  \"processingStatus\": \"SUCCEEDED\",\n"
            + "  \"content\": \"content\",\n"
            + "  \"asset\": {\n"
            + "    \"url\": \"url\",\n"
            + "    \"sizeBytes\": 1000000,\n"
            + "    \"status\": \"PENDING\",\n"
            + "    \"type\": \"type\",\n"
            + "    \"name\": \"name\"\n"
            + "  },\n"
            + "  \"metadata\": {\n"
            + "    \"metadata\": \"metadata\"\n"
            + "  },\n"
            + "  \"relevantEntities\": [\n"
            + "    {\n"
            + "      \"entityId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"scopeEntityId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"knowledgeDocumentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"knowledgeBaseVersionId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"title\": \"title\",\n"
            + "  \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "  \"knowledgeBaseLlmInclusionStatus\": \"ALWAYS\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"url\": \"url\",\n"
            + "  \"language\": \"language\",\n"
            + "  \"author\": \"author\"\n"
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
    public void testPatchKnowledgeDocument() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"knowledgeDocumentId\":{\"referenceId\":\"getting-started\",\"appId\":\"readme\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_DOCUMENT\"},\"knowledgeBaseVersionId\":{\"referenceId\":\"versionId\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE_VERSION\"},\"knowledgeBaseId\":{\"referenceId\":\"help-docs\",\"appId\":\"help-center\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"KNOWLEDGE_BASE\"},\"content\":\"## Getting started This is a getting started guide for the help center.\",\"title\":\"Getting started\",\"metadata\":{\"category\":\"getting-started\"},\"createdAt\":\"2024-01-01T00:00:00Z\",\"updatedAt\":\"2024-02-02T00:00:00Z\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"relevantEntities\":[{\"entityId\":{\"type\":\"CUSTOMER\",\"appId\":\"crm\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"customer-42\"},\"scopeEntityId\":{\"type\":\"AGENT\",\"appId\":\"maven\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"referenceId\":\"support\"}}],\"knowledgeBaseLlmInclusionStatus\":\"WHEN_RELEVANT\"}"));
        KnowledgeDocumentResponse response = client.knowledge().patchKnowledgeDocument(
            "help-center",
            "how-it-works",
            KnowledgeDocumentPatchRequest
                .builder()
                .llmInclusionStatus(LlmInclusionStatus.ALWAYS)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"llmInclusionStatus\": \"ALWAYS\"\n"
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
            + "  \"knowledgeDocumentId\": {\n"
            + "    \"referenceId\": \"getting-started\",\n"
            + "    \"appId\": \"readme\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_DOCUMENT\"\n"
            + "  },\n"
            + "  \"knowledgeBaseVersionId\": {\n"
            + "    \"referenceId\": \"versionId\",\n"
            + "    \"appId\": \"maven\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE_VERSION\"\n"
            + "  },\n"
            + "  \"knowledgeBaseId\": {\n"
            + "    \"referenceId\": \"help-docs\",\n"
            + "    \"appId\": \"help-center\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"KNOWLEDGE_BASE\"\n"
            + "  },\n"
            + "  \"content\": \"## Getting started This is a getting started guide for the help center.\",\n"
            + "  \"title\": \"Getting started\",\n"
            + "  \"metadata\": {\n"
            + "    \"category\": \"getting-started\"\n"
            + "  },\n"
            + "  \"createdAt\": \"2024-01-01T00:00:00Z\",\n"
            + "  \"updatedAt\": \"2024-02-02T00:00:00Z\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"relevantEntities\": [\n"
            + "    {\n"
            + "      \"entityId\": {\n"
            + "        \"type\": \"CUSTOMER\",\n"
            + "        \"appId\": \"crm\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"customer-42\"\n"
            + "      },\n"
            + "      \"scopeEntityId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"maven\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"referenceId\": \"support\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"knowledgeBaseLlmInclusionStatus\": \"WHEN_RELEVANT\"\n"
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
