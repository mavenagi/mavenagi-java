package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.EventRequest;
import com.mavenagi.resources.commons.types.EventResponse;
import com.mavenagi.resources.commons.types.EventUserInfoBase;
import com.mavenagi.resources.commons.types.EventsSearchRequest;
import com.mavenagi.resources.commons.types.EventsSearchResponse;
import com.mavenagi.resources.commons.types.NovelUserEvent;
import com.mavenagi.resources.commons.types.UserEventName;
import com.mavenagi.resources.events.requests.EventGetRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventsWireTest {
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
    public void testCreate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"eventType\":\"userEvent\",\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"eventName\":\"BUTTON_CLICKED\",\"userInfo\":{\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"userDisplayName\":\"userDisplayName\"},\"feedbackInfo\":[{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}},{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}}],\"pageInfo\":{\"pageName\":\"pageName\",\"pageUrl\":\"pageUrl\",\"pageTitle\":\"pageTitle\",\"linkUrl\":\"linkUrl\",\"elementId\":\"elementId\"},\"timestamp\":\"2024-01-15T09:30:00Z\",\"references\":[{\"entityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"scopeEntityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"}}],\"sourceInfo\":{\"type\":\"WEB\",\"deviceInfo\":{\"type\":\"DESKTOP\",\"name\":\"name\",\"version\":\"version\",\"osInfo\":{\"type\":\"WINDOWS\",\"name\":\"name\",\"version\":\"version\"}},\"browserInfo\":{\"type\":\"CHROME\",\"name\":\"name\",\"version\":\"version\",\"userAgent\":\"userAgent\"},\"geoInfo\":{\"city\":\"city\",\"state\":\"state\",\"country\":\"country\",\"region\":\"region\",\"latitude\":1.1,\"longitude\":1.1},\"ipInfo\":{\"ip\":\"ip\"},\"languageInfo\":{\"code\":\"code\"}},\"sessionInfo\":{\"id\":\"id\",\"start\":\"2024-01-15T09:30:00Z\",\"end\":\"2024-01-15T09:30:00Z\",\"duration\":1000000},\"contextInfo\":{\"additionalData\":{\"additionalData\":\"additionalData\"}}}"));
        EventResponse response = client.events().create(
            EventRequest.userEvent(
                NovelUserEvent
                    .builder()
                    .id(
                        EntityIdBase
                            .builder()
                            .referenceId("x")
                            .build()
                    )
                    .eventName(UserEventName.BUTTON_CLICKED)
                    .userInfo(
                        EventUserInfoBase
                            .builder()
                            .id(
                                EntityIdBase
                                    .builder()
                                    .referenceId("x")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"eventType\": \"userEvent\",\n"
            + "  \"id\": {\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"eventName\": \"BUTTON_CLICKED\",\n"
            + "  \"userInfo\": {\n"
            + "    \"id\": {\n"
            + "      \"referenceId\": \"x\"\n"
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
            + "  \"eventType\": \"userEvent\",\n"
            + "  \"id\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"eventName\": \"BUTTON_CLICKED\",\n"
            + "  \"userInfo\": {\n"
            + "    \"id\": {\n"
            + "      \"organizationId\": \"organizationId\",\n"
            + "      \"agentId\": \"agentId\",\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"x\"\n"
            + "    },\n"
            + "    \"userDisplayName\": \"userDisplayName\"\n"
            + "  },\n"
            + "  \"feedbackInfo\": [\n"
            + "    {\n"
            + "      \"rating\": 1.1,\n"
            + "      \"thumbUp\": true,\n"
            + "      \"survey\": {\n"
            + "        \"surveyQuestion\": \"surveyQuestion\",\n"
            + "        \"surveyAnswer\": \"surveyAnswer\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": 1.1,\n"
            + "      \"thumbUp\": true,\n"
            + "      \"survey\": {\n"
            + "        \"surveyQuestion\": \"surveyQuestion\",\n"
            + "        \"surveyAnswer\": \"surveyAnswer\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"pageInfo\": {\n"
            + "    \"pageName\": \"pageName\",\n"
            + "    \"pageUrl\": \"pageUrl\",\n"
            + "    \"pageTitle\": \"pageTitle\",\n"
            + "    \"linkUrl\": \"linkUrl\",\n"
            + "    \"elementId\": \"elementId\"\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"references\": [\n"
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
            + "  \"sourceInfo\": {\n"
            + "    \"type\": \"WEB\",\n"
            + "    \"deviceInfo\": {\n"
            + "      \"type\": \"DESKTOP\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"version\": \"version\",\n"
            + "      \"osInfo\": {\n"
            + "        \"type\": \"WINDOWS\",\n"
            + "        \"name\": \"name\",\n"
            + "        \"version\": \"version\"\n"
            + "      }\n"
            + "    },\n"
            + "    \"browserInfo\": {\n"
            + "      \"type\": \"CHROME\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"version\": \"version\",\n"
            + "      \"userAgent\": \"userAgent\"\n"
            + "    },\n"
            + "    \"geoInfo\": {\n"
            + "      \"city\": \"city\",\n"
            + "      \"state\": \"state\",\n"
            + "      \"country\": \"country\",\n"
            + "      \"region\": \"region\",\n"
            + "      \"latitude\": 1.1,\n"
            + "      \"longitude\": 1.1\n"
            + "    },\n"
            + "    \"ipInfo\": {\n"
            + "      \"ip\": \"ip\"\n"
            + "    },\n"
            + "    \"languageInfo\": {\n"
            + "      \"code\": \"code\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"sessionInfo\": {\n"
            + "    \"id\": \"id\",\n"
            + "    \"start\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"end\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"duration\": 1000000\n"
            + "  },\n"
            + "  \"contextInfo\": {\n"
            + "    \"additionalData\": {\n"
            + "      \"additionalData\": \"additionalData\"\n"
            + "    }\n"
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
    public void testSearch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"events\":[{\"eventType\":\"userEvent\",\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"eventName\":\"BUTTON_CLICKED\",\"userInfo\":{\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"userDisplayName\":\"userDisplayName\"},\"feedbackInfo\":[{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}},{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}}],\"pageInfo\":{\"pageName\":\"pageName\",\"pageUrl\":\"pageUrl\",\"pageTitle\":\"pageTitle\",\"linkUrl\":\"linkUrl\",\"elementId\":\"elementId\"},\"timestamp\":\"2024-01-15T09:30:00Z\",\"references\":[{\"entityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"scopeEntityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"}}],\"sourceInfo\":{\"type\":\"WEB\",\"deviceInfo\":{\"type\":\"DESKTOP\",\"name\":\"name\",\"version\":\"version\",\"osInfo\":{\"type\":\"WINDOWS\",\"name\":\"name\",\"version\":\"version\"}},\"browserInfo\":{\"type\":\"CHROME\",\"name\":\"name\",\"version\":\"version\",\"userAgent\":\"userAgent\"},\"geoInfo\":{\"city\":\"city\",\"state\":\"state\",\"country\":\"country\",\"region\":\"region\",\"latitude\":1.1,\"longitude\":1.1},\"ipInfo\":{\"ip\":\"ip\"},\"languageInfo\":{\"code\":\"code\"}},\"sessionInfo\":{\"id\":\"id\",\"start\":\"2024-01-15T09:30:00Z\",\"end\":\"2024-01-15T09:30:00Z\",\"duration\":1000000},\"contextInfo\":{\"additionalData\":{\"additionalData\":\"additionalData\"}}},{\"eventType\":\"userEvent\",\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"eventName\":\"BUTTON_CLICKED\",\"userInfo\":{\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"userDisplayName\":\"userDisplayName\"},\"feedbackInfo\":[{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}},{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}}],\"pageInfo\":{\"pageName\":\"pageName\",\"pageUrl\":\"pageUrl\",\"pageTitle\":\"pageTitle\",\"linkUrl\":\"linkUrl\",\"elementId\":\"elementId\"},\"timestamp\":\"2024-01-15T09:30:00Z\",\"references\":[{\"entityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"scopeEntityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"}}],\"sourceInfo\":{\"type\":\"WEB\",\"deviceInfo\":{\"type\":\"DESKTOP\",\"name\":\"name\",\"version\":\"version\",\"osInfo\":{\"type\":\"WINDOWS\",\"name\":\"name\",\"version\":\"version\"}},\"browserInfo\":{\"type\":\"CHROME\",\"name\":\"name\",\"version\":\"version\",\"userAgent\":\"userAgent\"},\"geoInfo\":{\"city\":\"city\",\"state\":\"state\",\"country\":\"country\",\"region\":\"region\",\"latitude\":1.1,\"longitude\":1.1},\"ipInfo\":{\"ip\":\"ip\"},\"languageInfo\":{\"code\":\"code\"}},\"sessionInfo\":{\"id\":\"id\",\"start\":\"2024-01-15T09:30:00Z\",\"end\":\"2024-01-15T09:30:00Z\",\"duration\":1000000},\"contextInfo\":{\"additionalData\":{\"additionalData\":\"additionalData\"}}}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        EventsSearchResponse response = client.events().search(
            EventsSearchRequest
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
            + "  \"events\": [\n"
            + "    {\n"
            + "      \"eventType\": \"userEvent\",\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"eventName\": \"BUTTON_CLICKED\",\n"
            + "      \"userInfo\": {\n"
            + "        \"id\": {\n"
            + "          \"organizationId\": \"organizationId\",\n"
            + "          \"agentId\": \"agentId\",\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"x\"\n"
            + "        },\n"
            + "        \"userDisplayName\": \"userDisplayName\"\n"
            + "      },\n"
            + "      \"feedbackInfo\": [\n"
            + "        {\n"
            + "          \"rating\": 1.1,\n"
            + "          \"thumbUp\": true,\n"
            + "          \"survey\": {\n"
            + "            \"surveyQuestion\": \"surveyQuestion\",\n"
            + "            \"surveyAnswer\": \"surveyAnswer\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"rating\": 1.1,\n"
            + "          \"thumbUp\": true,\n"
            + "          \"survey\": {\n"
            + "            \"surveyQuestion\": \"surveyQuestion\",\n"
            + "            \"surveyAnswer\": \"surveyAnswer\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"pageInfo\": {\n"
            + "        \"pageName\": \"pageName\",\n"
            + "        \"pageUrl\": \"pageUrl\",\n"
            + "        \"pageTitle\": \"pageTitle\",\n"
            + "        \"linkUrl\": \"linkUrl\",\n"
            + "        \"elementId\": \"elementId\"\n"
            + "      },\n"
            + "      \"timestamp\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"references\": [\n"
            + "        {\n"
            + "          \"entityId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"x\"\n"
            + "          },\n"
            + "          \"scopeEntityId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"x\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"sourceInfo\": {\n"
            + "        \"type\": \"WEB\",\n"
            + "        \"deviceInfo\": {\n"
            + "          \"type\": \"DESKTOP\",\n"
            + "          \"name\": \"name\",\n"
            + "          \"version\": \"version\",\n"
            + "          \"osInfo\": {\n"
            + "            \"type\": \"WINDOWS\",\n"
            + "            \"name\": \"name\",\n"
            + "            \"version\": \"version\"\n"
            + "          }\n"
            + "        },\n"
            + "        \"browserInfo\": {\n"
            + "          \"type\": \"CHROME\",\n"
            + "          \"name\": \"name\",\n"
            + "          \"version\": \"version\",\n"
            + "          \"userAgent\": \"userAgent\"\n"
            + "        },\n"
            + "        \"geoInfo\": {\n"
            + "          \"city\": \"city\",\n"
            + "          \"state\": \"state\",\n"
            + "          \"country\": \"country\",\n"
            + "          \"region\": \"region\",\n"
            + "          \"latitude\": 1.1,\n"
            + "          \"longitude\": 1.1\n"
            + "        },\n"
            + "        \"ipInfo\": {\n"
            + "          \"ip\": \"ip\"\n"
            + "        },\n"
            + "        \"languageInfo\": {\n"
            + "          \"code\": \"code\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"sessionInfo\": {\n"
            + "        \"id\": \"id\",\n"
            + "        \"start\": \"2024-01-15T09:30:00Z\",\n"
            + "        \"end\": \"2024-01-15T09:30:00Z\",\n"
            + "        \"duration\": 1000000\n"
            + "      },\n"
            + "      \"contextInfo\": {\n"
            + "        \"additionalData\": {\n"
            + "          \"additionalData\": \"additionalData\"\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"eventType\": \"userEvent\",\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"eventName\": \"BUTTON_CLICKED\",\n"
            + "      \"userInfo\": {\n"
            + "        \"id\": {\n"
            + "          \"organizationId\": \"organizationId\",\n"
            + "          \"agentId\": \"agentId\",\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"x\"\n"
            + "        },\n"
            + "        \"userDisplayName\": \"userDisplayName\"\n"
            + "      },\n"
            + "      \"feedbackInfo\": [\n"
            + "        {\n"
            + "          \"rating\": 1.1,\n"
            + "          \"thumbUp\": true,\n"
            + "          \"survey\": {\n"
            + "            \"surveyQuestion\": \"surveyQuestion\",\n"
            + "            \"surveyAnswer\": \"surveyAnswer\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"rating\": 1.1,\n"
            + "          \"thumbUp\": true,\n"
            + "          \"survey\": {\n"
            + "            \"surveyQuestion\": \"surveyQuestion\",\n"
            + "            \"surveyAnswer\": \"surveyAnswer\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"pageInfo\": {\n"
            + "        \"pageName\": \"pageName\",\n"
            + "        \"pageUrl\": \"pageUrl\",\n"
            + "        \"pageTitle\": \"pageTitle\",\n"
            + "        \"linkUrl\": \"linkUrl\",\n"
            + "        \"elementId\": \"elementId\"\n"
            + "      },\n"
            + "      \"timestamp\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"references\": [\n"
            + "        {\n"
            + "          \"entityId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"x\"\n"
            + "          },\n"
            + "          \"scopeEntityId\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"x\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"sourceInfo\": {\n"
            + "        \"type\": \"WEB\",\n"
            + "        \"deviceInfo\": {\n"
            + "          \"type\": \"DESKTOP\",\n"
            + "          \"name\": \"name\",\n"
            + "          \"version\": \"version\",\n"
            + "          \"osInfo\": {\n"
            + "            \"type\": \"WINDOWS\",\n"
            + "            \"name\": \"name\",\n"
            + "            \"version\": \"version\"\n"
            + "          }\n"
            + "        },\n"
            + "        \"browserInfo\": {\n"
            + "          \"type\": \"CHROME\",\n"
            + "          \"name\": \"name\",\n"
            + "          \"version\": \"version\",\n"
            + "          \"userAgent\": \"userAgent\"\n"
            + "        },\n"
            + "        \"geoInfo\": {\n"
            + "          \"city\": \"city\",\n"
            + "          \"state\": \"state\",\n"
            + "          \"country\": \"country\",\n"
            + "          \"region\": \"region\",\n"
            + "          \"latitude\": 1.1,\n"
            + "          \"longitude\": 1.1\n"
            + "        },\n"
            + "        \"ipInfo\": {\n"
            + "          \"ip\": \"ip\"\n"
            + "        },\n"
            + "        \"languageInfo\": {\n"
            + "          \"code\": \"code\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"sessionInfo\": {\n"
            + "        \"id\": \"id\",\n"
            + "        \"start\": \"2024-01-15T09:30:00Z\",\n"
            + "        \"end\": \"2024-01-15T09:30:00Z\",\n"
            + "        \"duration\": 1000000\n"
            + "      },\n"
            + "      \"contextInfo\": {\n"
            + "        \"additionalData\": {\n"
            + "          \"additionalData\": \"additionalData\"\n"
            + "        }\n"
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
    public void testGet() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"eventType\":\"userEvent\",\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"eventName\":\"BUTTON_CLICKED\",\"userInfo\":{\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"userDisplayName\":\"userDisplayName\"},\"feedbackInfo\":[{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}},{\"rating\":1.1,\"thumbUp\":true,\"survey\":{\"surveyQuestion\":\"surveyQuestion\",\"surveyAnswer\":\"surveyAnswer\"}}],\"pageInfo\":{\"pageName\":\"pageName\",\"pageUrl\":\"pageUrl\",\"pageTitle\":\"pageTitle\",\"linkUrl\":\"linkUrl\",\"elementId\":\"elementId\"},\"timestamp\":\"2024-01-15T09:30:00Z\",\"references\":[{\"entityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"scopeEntityId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"}}],\"sourceInfo\":{\"type\":\"WEB\",\"deviceInfo\":{\"type\":\"DESKTOP\",\"name\":\"name\",\"version\":\"version\",\"osInfo\":{\"type\":\"WINDOWS\",\"name\":\"name\",\"version\":\"version\"}},\"browserInfo\":{\"type\":\"CHROME\",\"name\":\"name\",\"version\":\"version\",\"userAgent\":\"userAgent\"},\"geoInfo\":{\"city\":\"city\",\"state\":\"state\",\"country\":\"country\",\"region\":\"region\",\"latitude\":1.1,\"longitude\":1.1},\"ipInfo\":{\"ip\":\"ip\"},\"languageInfo\":{\"code\":\"code\"}},\"sessionInfo\":{\"id\":\"id\",\"start\":\"2024-01-15T09:30:00Z\",\"end\":\"2024-01-15T09:30:00Z\",\"duration\":1000000},\"contextInfo\":{\"additionalData\":{\"additionalData\":\"additionalData\"}}}"));
        EventResponse response = client.events().get(
            "eventId",
            EventGetRequest
                .builder()
                .appId("appId")
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
            + "  \"eventType\": \"userEvent\",\n"
            + "  \"id\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"eventName\": \"BUTTON_CLICKED\",\n"
            + "  \"userInfo\": {\n"
            + "    \"id\": {\n"
            + "      \"organizationId\": \"organizationId\",\n"
            + "      \"agentId\": \"agentId\",\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"x\"\n"
            + "    },\n"
            + "    \"userDisplayName\": \"userDisplayName\"\n"
            + "  },\n"
            + "  \"feedbackInfo\": [\n"
            + "    {\n"
            + "      \"rating\": 1.1,\n"
            + "      \"thumbUp\": true,\n"
            + "      \"survey\": {\n"
            + "        \"surveyQuestion\": \"surveyQuestion\",\n"
            + "        \"surveyAnswer\": \"surveyAnswer\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": 1.1,\n"
            + "      \"thumbUp\": true,\n"
            + "      \"survey\": {\n"
            + "        \"surveyQuestion\": \"surveyQuestion\",\n"
            + "        \"surveyAnswer\": \"surveyAnswer\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"pageInfo\": {\n"
            + "    \"pageName\": \"pageName\",\n"
            + "    \"pageUrl\": \"pageUrl\",\n"
            + "    \"pageTitle\": \"pageTitle\",\n"
            + "    \"linkUrl\": \"linkUrl\",\n"
            + "    \"elementId\": \"elementId\"\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"references\": [\n"
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
            + "  \"sourceInfo\": {\n"
            + "    \"type\": \"WEB\",\n"
            + "    \"deviceInfo\": {\n"
            + "      \"type\": \"DESKTOP\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"version\": \"version\",\n"
            + "      \"osInfo\": {\n"
            + "        \"type\": \"WINDOWS\",\n"
            + "        \"name\": \"name\",\n"
            + "        \"version\": \"version\"\n"
            + "      }\n"
            + "    },\n"
            + "    \"browserInfo\": {\n"
            + "      \"type\": \"CHROME\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"version\": \"version\",\n"
            + "      \"userAgent\": \"userAgent\"\n"
            + "    },\n"
            + "    \"geoInfo\": {\n"
            + "      \"city\": \"city\",\n"
            + "      \"state\": \"state\",\n"
            + "      \"country\": \"country\",\n"
            + "      \"region\": \"region\",\n"
            + "      \"latitude\": 1.1,\n"
            + "      \"longitude\": 1.1\n"
            + "    },\n"
            + "    \"ipInfo\": {\n"
            + "      \"ip\": \"ip\"\n"
            + "    },\n"
            + "    \"languageInfo\": {\n"
            + "      \"code\": \"code\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"sessionInfo\": {\n"
            + "    \"id\": \"id\",\n"
            + "    \"start\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"end\": \"2024-01-15T09:30:00Z\",\n"
            + "    \"duration\": 1000000\n"
            + "  },\n"
            + "  \"contextInfo\": {\n"
            + "    \"additionalData\": {\n"
            + "      \"additionalData\": \"additionalData\"\n"
            + "    }\n"
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
