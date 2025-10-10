package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.MetadataPrecondition;
import com.mavenagi.resources.commons.types.Precondition;
import com.mavenagi.resources.commons.types.PreconditionGroup;
import com.mavenagi.resources.commons.types.PreconditionGroupOperator;
import com.mavenagi.resources.segments.requests.SegmentGetRequest;
import com.mavenagi.resources.segments.types.SegmentPatchRequest;
import com.mavenagi.resources.segments.types.SegmentRequest;
import com.mavenagi.resources.segments.types.SegmentResponse;
import com.mavenagi.resources.segments.types.SegmentsSearchRequest;
import com.mavenagi.resources.segments.types.SegmentsSearchResponse;
import java.util.Arrays;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SegmentsWireTest {
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
            .setBody("{\"segments\":[{\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"status\":\"ACTIVE\",\"name\":\"name\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"operator\":\"NOT\"}},{\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"status\":\"ACTIVE\",\"name\":\"name\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"operator\":\"NOT\"}}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        SegmentsSearchResponse response = client.segments().search(
            SegmentsSearchRequest
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
            + "  \"segments\": [\n"
            + "    {\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"status\": \"ACTIVE\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"key\",\n"
            + "        \"value\": \"value\",\n"
            + "        \"operator\": \"NOT\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"status\": \"ACTIVE\",\n"
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
    public void testCreateOrUpdate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"segmentId\":{\"referenceId\":\"admin-users\",\"appId\":\"auth-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"},\"name\":\"Admin users\",\"status\":\"ACTIVE\",\"precondition\":{\"preconditionType\":\"group\",\"operator\":\"AND\",\"preconditions\":[{\"preconditionType\":\"user\",\"key\":\"userKey\"},{\"preconditionType\":\"user\",\"key\":\"userKey2\"}]}}"));
        SegmentResponse response = client.segments().createOrUpdate(
            SegmentRequest
                .builder()
                .segmentId(
                    EntityIdBase
                        .builder()
                        .referenceId("admin-users")
                        .build()
                )
                .name("Admin users")
                .precondition(
                    Precondition.group(
                        PreconditionGroup
                            .builder()
                            .operator(PreconditionGroupOperator.AND)
                            .preconditions(
                                Arrays.asList(
                                    Precondition.user(
                                        MetadataPrecondition
                                            .builder()
                                            .key("userKey")
                                            .build()
                                    ),
                                    Precondition.user(
                                        MetadataPrecondition
                                            .builder()
                                            .key("userKey2")
                                            .build()
                                    )
                                )
                            )
                            .build()
                    )
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
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"admin-users\"\n"
            + "  },\n"
            + "  \"name\": \"Admin users\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"group\",\n"
            + "    \"operator\": \"AND\",\n"
            + "    \"preconditions\": [\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey2\"\n"
            + "      }\n"
            + "    ]\n"
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
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"admin-users\",\n"
            + "    \"appId\": \"auth-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
            + "  },\n"
            + "  \"name\": \"Admin users\",\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"group\",\n"
            + "    \"operator\": \"AND\",\n"
            + "    \"preconditions\": [\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey2\"\n"
            + "      }\n"
            + "    ]\n"
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
            .setBody("{\"segmentId\":{\"referenceId\":\"admin-users\",\"appId\":\"auth-app\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"},\"name\":\"Admin users\",\"status\":\"ACTIVE\",\"precondition\":{\"preconditionType\":\"group\",\"operator\":\"AND\",\"preconditions\":[{\"preconditionType\":\"user\",\"key\":\"userKey\"},{\"preconditionType\":\"user\",\"key\":\"userKey2\"}]}}"));
        SegmentResponse response = client.segments().get(
            "admin-users",
            SegmentGetRequest
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
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"admin-users\",\n"
            + "    \"appId\": \"auth-app\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
            + "  },\n"
            + "  \"name\": \"Admin users\",\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"group\",\n"
            + "    \"operator\": \"AND\",\n"
            + "    \"preconditions\": [\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"userKey2\"\n"
            + "      }\n"
            + "    ]\n"
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
            .setBody("{\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"status\":\"ACTIVE\",\"name\":\"name\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"operator\":\"NOT\"}}"));
        SegmentResponse response = client.segments().patch(
            "segmentReferenceId",
            SegmentPatchRequest
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
            + "  \"segmentId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"status\": \"ACTIVE\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"precondition\": {\n"
            + "    \"preconditionType\": \"user\",\n"
            + "    \"key\": \"key\",\n"
            + "    \"value\": \"value\",\n"
            + "    \"operator\": \"NOT\"\n"
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
}
