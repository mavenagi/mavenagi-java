package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.EntityId;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.EntityType;
import com.mavenagi.resources.intelligentfields.requests.IntelligentFieldDeleteRequest;
import com.mavenagi.resources.intelligentfields.requests.IntelligentFieldGetRequest;
import com.mavenagi.resources.intelligentfields.requests.IntelligentFieldPatchRequest;
import com.mavenagi.resources.intelligentfields.types.EnumOption;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldDetailResponse;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldRequest;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldResponse;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldType;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldValueEntityFilter;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldValueFieldFilter;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldValueSearchRequest;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldValueSearchResponse;
import com.mavenagi.resources.intelligentfields.types.IntelligentFieldValueSortField;
import java.util.Arrays;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntelligentFieldsWireTest {
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
            .setBody("{\"fieldId\":{\"referenceId\":\"ticket-priority\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"INTELLIGENT_FIELD\"},\"name\":\"Ticket Priority\",\"description\":\"The priority of the conversation based on urgency\",\"status\":\"INACTIVE\",\"entityType\":\"CONVERSATION\",\"validationType\":\"STRING\",\"enumOptions\":[{\"value\":\"HIGH\",\"label\":\"High Priority\"},{\"value\":\"MEDIUM\",\"label\":\"Medium Priority\"},{\"value\":\"LOW\",\"label\":\"Low Priority\"}],\"definition\":\"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\"createdAt\":\"2024-01-15T10:30:00Z\",\"updatedAt\":\"2024-01-15T10:30:00Z\"}"));
        IntelligentFieldResponse response = client.intelligentFields().createOrUpdate(
            IntelligentFieldRequest
                .builder()
                .fieldId(
                    EntityIdBase
                        .builder()
                        .referenceId("ticket-priority")
                        .build()
                )
                .name("Ticket Priority")
                .entityType(EntityType.CONVERSATION)
                .validationType(IntelligentFieldType.STRING)
                .definition("The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.")
                .description("The priority of the conversation based on urgency")
                .enumOptions(
                    Optional.of(
                        Arrays.asList(
                            EnumOption
                                .builder()
                                .value("HIGH")
                                .label("High Priority")
                                .build(),
                            EnumOption
                                .builder()
                                .value("MEDIUM")
                                .label("Medium Priority")
                                .build(),
                            EnumOption
                                .builder()
                                .value("LOW")
                                .label("Low Priority")
                                .build()
                        )
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
            + "  \"fieldId\": {\n"
            + "    \"referenceId\": \"ticket-priority\"\n"
            + "  },\n"
            + "  \"name\": \"Ticket Priority\",\n"
            + "  \"description\": \"The priority of the conversation based on urgency\",\n"
            + "  \"entityType\": \"CONVERSATION\",\n"
            + "  \"validationType\": \"STRING\",\n"
            + "  \"enumOptions\": [\n"
            + "    {\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"label\": \"High Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"MEDIUM\",\n"
            + "      \"label\": \"Medium Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"LOW\",\n"
            + "      \"label\": \"Low Priority\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\"\n"
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
            + "  \"fieldId\": {\n"
            + "    \"referenceId\": \"ticket-priority\",\n"
            + "    \"appId\": \"zendesk\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"INTELLIGENT_FIELD\"\n"
            + "  },\n"
            + "  \"name\": \"Ticket Priority\",\n"
            + "  \"description\": \"The priority of the conversation based on urgency\",\n"
            + "  \"status\": \"INACTIVE\",\n"
            + "  \"entityType\": \"CONVERSATION\",\n"
            + "  \"validationType\": \"STRING\",\n"
            + "  \"enumOptions\": [\n"
            + "    {\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"label\": \"High Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"MEDIUM\",\n"
            + "      \"label\": \"Medium Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"LOW\",\n"
            + "      \"label\": \"Low Priority\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\n"
            + "  \"createdAt\": \"2024-01-15T10:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T10:30:00Z\"\n"
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
            .setBody("{\"fieldId\":{\"referenceId\":\"ticket-priority\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"INTELLIGENT_FIELD\"},\"name\":\"Ticket Priority\",\"description\":\"The priority of the conversation based on urgency\",\"status\":\"INACTIVE\",\"entityType\":\"CONVERSATION\",\"validationType\":\"STRING\",\"enumOptions\":[{\"value\":\"HIGH\",\"label\":\"High Priority\"},{\"value\":\"MEDIUM\",\"label\":\"Medium Priority\"},{\"value\":\"LOW\",\"label\":\"Low Priority\"}],\"definition\":\"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\"createdAt\":\"2024-01-15T10:30:00Z\",\"updatedAt\":\"2024-01-15T10:30:00Z\",\"referencingCharters\":[]}"));
        IntelligentFieldDetailResponse response = client.intelligentFields().get(
            "ticket-priority",
            IntelligentFieldGetRequest
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
            + "  \"fieldId\": {\n"
            + "    \"referenceId\": \"ticket-priority\",\n"
            + "    \"appId\": \"zendesk\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"INTELLIGENT_FIELD\"\n"
            + "  },\n"
            + "  \"name\": \"Ticket Priority\",\n"
            + "  \"description\": \"The priority of the conversation based on urgency\",\n"
            + "  \"status\": \"INACTIVE\",\n"
            + "  \"entityType\": \"CONVERSATION\",\n"
            + "  \"validationType\": \"STRING\",\n"
            + "  \"enumOptions\": [\n"
            + "    {\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"label\": \"High Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"MEDIUM\",\n"
            + "      \"label\": \"Medium Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"LOW\",\n"
            + "      \"label\": \"Low Priority\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\n"
            + "  \"createdAt\": \"2024-01-15T10:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T10:30:00Z\",\n"
            + "  \"referencingCharters\": []\n"
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
            .setBody("{\"fieldId\":{\"referenceId\":\"ticket-priority\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"INTELLIGENT_FIELD\"},\"name\":\"Ticket Priority\",\"description\":\"The priority of the conversation based on urgency\",\"status\":\"INACTIVE\",\"entityType\":\"CONVERSATION\",\"validationType\":\"STRING\",\"enumOptions\":[{\"value\":\"HIGH\",\"label\":\"High Priority\"},{\"value\":\"MEDIUM\",\"label\":\"Medium Priority\"},{\"value\":\"LOW\",\"label\":\"Low Priority\"}],\"definition\":\"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\"createdAt\":\"2024-01-15T10:30:00Z\",\"updatedAt\":\"2024-01-15T10:30:00Z\"}"));
        IntelligentFieldResponse response = client.intelligentFields().patch(
            "ticket-priority",
            IntelligentFieldPatchRequest
                .builder()
                .definition("The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\"\n"
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
            + "  \"fieldId\": {\n"
            + "    \"referenceId\": \"ticket-priority\",\n"
            + "    \"appId\": \"zendesk\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"INTELLIGENT_FIELD\"\n"
            + "  },\n"
            + "  \"name\": \"Ticket Priority\",\n"
            + "  \"description\": \"The priority of the conversation based on urgency\",\n"
            + "  \"status\": \"INACTIVE\",\n"
            + "  \"entityType\": \"CONVERSATION\",\n"
            + "  \"validationType\": \"STRING\",\n"
            + "  \"enumOptions\": [\n"
            + "    {\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"label\": \"High Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"MEDIUM\",\n"
            + "      \"label\": \"Medium Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"LOW\",\n"
            + "      \"label\": \"Low Priority\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\n"
            + "  \"createdAt\": \"2024-01-15T10:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T10:30:00Z\"\n"
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
            .setBody("{\"fieldId\":{\"referenceId\":\"ticket-priority\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"INTELLIGENT_FIELD\"},\"name\":\"Ticket Priority\",\"description\":\"The priority of the conversation based on urgency\",\"status\":\"INACTIVE\",\"entityType\":\"CONVERSATION\",\"validationType\":\"STRING\",\"enumOptions\":[{\"value\":\"HIGH\",\"label\":\"High Priority\"},{\"value\":\"MEDIUM\",\"label\":\"Medium Priority\"},{\"value\":\"LOW\",\"label\":\"Low Priority\"}],\"definition\":\"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\"createdAt\":\"2024-01-15T10:30:00Z\",\"updatedAt\":\"2024-01-15T10:30:00Z\"}"));
        IntelligentFieldResponse response = client.intelligentFields().delete(
            "ticket-priority",
            IntelligentFieldDeleteRequest
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
            + "  \"fieldId\": {\n"
            + "    \"referenceId\": \"ticket-priority\",\n"
            + "    \"appId\": \"zendesk\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"INTELLIGENT_FIELD\"\n"
            + "  },\n"
            + "  \"name\": \"Ticket Priority\",\n"
            + "  \"description\": \"The priority of the conversation based on urgency\",\n"
            + "  \"status\": \"INACTIVE\",\n"
            + "  \"entityType\": \"CONVERSATION\",\n"
            + "  \"validationType\": \"STRING\",\n"
            + "  \"enumOptions\": [\n"
            + "    {\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"label\": \"High Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"MEDIUM\",\n"
            + "      \"label\": \"Medium Priority\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"value\": \"LOW\",\n"
            + "      \"label\": \"Low Priority\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"definition\": \"The priority of the conversation based on the urgency and importance; draw from the content / messages in the conversation; must be one of HIGH, MEDIUM, or LOW.\",\n"
            + "  \"createdAt\": \"2024-01-15T10:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T10:30:00Z\"\n"
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
    public void testSearchValues() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"values\":[{\"fieldId\":{\"referenceId\":\"ticket-priority\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"INTELLIGENT_FIELD\"},\"name\":\"Ticket Priority\",\"entityId\":{\"referenceId\":\"ticket-123\",\"appId\":\"zendesk\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION\"},\"value\":\"HIGH\",\"confidence\":0.95,\"rationale\":\"The conversation mentions a production outage affecting multiple customers, indicating high urgency and impact.\",\"createdAt\":\"2024-01-15T10:30:00Z\"}],\"totalElements\":1,\"totalPages\":1,\"size\":20,\"number\":0}"));
        IntelligentFieldValueSearchResponse response = client.intelligentFields().searchValues(
            IntelligentFieldValueSearchRequest
                .builder()
                .fieldFilter(
                    IntelligentFieldValueFieldFilter
                        .builder()
                        .fieldIds(
                            Optional.of(
                                Arrays.asList(
                                    EntityId
                                        .builder()
                                        .referenceId("ticket-priority")
                                        .appId("zendesk")
                                        .type(EntityType.INTELLIGENT_FIELD)
                                        .organizationId("acme")
                                        .agentId("support")
                                        .build()
                                )
                            )
                        )
                        .build()
                )
                .entityFilter(
                    IntelligentFieldValueEntityFilter
                        .builder()
                        .entityIds(
                            Optional.of(
                                Arrays.asList(
                                    EntityId
                                        .builder()
                                        .referenceId("conv-123")
                                        .appId("zendesk")
                                        .type(EntityType.CONVERSATION)
                                        .organizationId("acme")
                                        .agentId("support")
                                        .build()
                                )
                            )
                        )
                        .build()
                )
                .page(0)
                .size(20)
                .sort(IntelligentFieldValueSortField.CREATED_AT)
                .sortDesc(true)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"fieldFilter\": {\n"
            + "    \"fieldIds\": [\n"
            + "      {\n"
            + "        \"referenceId\": \"ticket-priority\",\n"
            + "        \"appId\": \"zendesk\",\n"
            + "        \"type\": \"INTELLIGENT_FIELD\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\"\n"
            + "      }\n"
            + "    ]\n"
            + "  },\n"
            + "  \"entityFilter\": {\n"
            + "    \"entityIds\": [\n"
            + "      {\n"
            + "        \"referenceId\": \"conv-123\",\n"
            + "        \"appId\": \"zendesk\",\n"
            + "        \"type\": \"CONVERSATION\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\"\n"
            + "      }\n"
            + "    ]\n"
            + "  },\n"
            + "  \"page\": 0,\n"
            + "  \"size\": 20,\n"
            + "  \"sort\": \"CREATED_AT\",\n"
            + "  \"sortDesc\": true\n"
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
            + "  \"values\": [\n"
            + "    {\n"
            + "      \"fieldId\": {\n"
            + "        \"referenceId\": \"ticket-priority\",\n"
            + "        \"appId\": \"zendesk\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"INTELLIGENT_FIELD\"\n"
            + "      },\n"
            + "      \"name\": \"Ticket Priority\",\n"
            + "      \"entityId\": {\n"
            + "        \"referenceId\": \"ticket-123\",\n"
            + "        \"appId\": \"zendesk\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CONVERSATION\"\n"
            + "      },\n"
            + "      \"value\": \"HIGH\",\n"
            + "      \"confidence\": 0.95,\n"
            + "      \"rationale\": \"The conversation mentions a production outage affecting multiple customers, indicating high urgency and impact.\",\n"
            + "      \"createdAt\": \"2024-01-15T10:30:00Z\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"totalElements\": 1,\n"
            + "  \"totalPages\": 1,\n"
            + "  \"size\": 20,\n"
            + "  \"number\": 0\n"
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
