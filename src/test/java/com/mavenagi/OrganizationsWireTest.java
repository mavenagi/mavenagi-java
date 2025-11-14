package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.analytics.types.ChartResponse;
import com.mavenagi.resources.analytics.types.ConversationAverage;
import com.mavenagi.resources.analytics.types.ConversationChartRequest;
import com.mavenagi.resources.analytics.types.ConversationColumnDefinition;
import com.mavenagi.resources.analytics.types.ConversationCount;
import com.mavenagi.resources.analytics.types.ConversationGroupBy;
import com.mavenagi.resources.analytics.types.ConversationMetric;
import com.mavenagi.resources.analytics.types.ConversationPercentile;
import com.mavenagi.resources.analytics.types.ConversationPieChartRequest;
import com.mavenagi.resources.analytics.types.ConversationTableRequest;
import com.mavenagi.resources.analytics.types.ConversationTableResponse;
import com.mavenagi.resources.analytics.types.TimeInterval;
import com.mavenagi.resources.conversation.types.ConversationField;
import com.mavenagi.resources.conversation.types.ConversationFilter;
import com.mavenagi.resources.conversation.types.NumericConversationField;
import com.mavenagi.resources.organizations.types.CreateOrganizationRequest;
import com.mavenagi.resources.organizations.types.Organization;
import com.mavenagi.resources.organizations.types.OrganizationPatchRequest;
import java.util.Arrays;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrganizationsWireTest {
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
            .setBody("{\"organizationId\":{\"referenceId\":\"x\"},\"name\":\"name\",\"defaultLanguage\":\"defaultLanguage\"}"));
        Organization response = client.organizations().create(
            "organizationReferenceId",
            CreateOrganizationRequest
                .builder()
                .name("name")
                .defaultLanguage("defaultLanguage")
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
            + "  \"defaultLanguage\": \"defaultLanguage\"\n"
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
            + "  \"organizationId\": {\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"defaultLanguage\": \"defaultLanguage\"\n"
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
            .setBody("{\"organizationId\":{\"referenceId\":\"x\"},\"name\":\"name\",\"defaultLanguage\":\"defaultLanguage\"}"));
        Organization response = client.organizations().get("organizationReferenceId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"organizationId\": {\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"defaultLanguage\": \"defaultLanguage\"\n"
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
            .setBody("{\"organizationId\":{\"referenceId\":\"x\"},\"name\":\"name\",\"defaultLanguage\":\"defaultLanguage\"}"));
        Organization response = client.organizations().patch(
            "organizationReferenceId",
            OrganizationPatchRequest
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
            + "  \"organizationId\": {\n"
            + "    \"referenceId\": \"x\"\n"
            + "  },\n"
            + "  \"name\": \"name\",\n"
            + "  \"defaultLanguage\": \"defaultLanguage\"\n"
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
        client.organizations().delete("organizationReferenceId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
    @Test
    public void testGetConversationTable() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"headers\":[\"count\",\"avg_first_response_time\",\"percentile_first_response_time\"],\"rows\":[{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Sales\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-01T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":5},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":150},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":110}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Support\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-01T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":10},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":300},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":250}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Sales\",\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-02T00:00:00Z\"}}},\"data\":{\"count\":{\"type\":\"double\",\"value\":7},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":180},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":180}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Support\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-02T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":8},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":320},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":220}}}]}"));
        ConversationTableResponse response = client.organizations().getConversationTable(
            ConversationTableRequest
                .builder()
                .fieldGroupings(
                    Arrays.asList(
                        ConversationGroupBy
                            .builder()
                            .field(ConversationField.CATEGORY)
                            .build()
                    )
                )
                .columnDefinitions(
                    Arrays.asList(
                        ConversationColumnDefinition
                            .builder()
                            .header("count")
                            .metric(
                                ConversationMetric.count(
                                    ConversationCount
                                        .builder()
                                        .build()
                                )
                            )
                            .build(),
                        ConversationColumnDefinition
                            .builder()
                            .header("avg_first_response_time")
                            .metric(
                                ConversationMetric.average(
                                    ConversationAverage
                                        .builder()
                                        .targetField(NumericConversationField.FIRST_RESPONSE_TIME)
                                        .build()
                                )
                            )
                            .build(),
                        ConversationColumnDefinition
                            .builder()
                            .header("percentile_handle_time")
                            .metric(
                                ConversationMetric.percentile(
                                    ConversationPercentile
                                        .builder()
                                        .targetField(NumericConversationField.HANDLE_TIME)
                                        .percentile(25.0)
                                        .build()
                                )
                            )
                            .build()
                    )
                )
                .conversationFilter(
                    ConversationFilter
                        .builder()
                        .languages(
                            Optional.of(
                                Arrays.asList("en", "es")
                            )
                        )
                        .build()
                )
                .timeGrouping(TimeInterval.DAY)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"conversationFilter\": {\n"
            + "    \"languages\": [\n"
            + "      \"en\",\n"
            + "      \"es\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"timeGrouping\": \"DAY\",\n"
            + "  \"fieldGroupings\": [\n"
            + "    {\n"
            + "      \"field\": \"Category\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"columnDefinitions\": [\n"
            + "    {\n"
            + "      \"header\": \"count\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"count\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"header\": \"avg_first_response_time\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"average\",\n"
            + "        \"targetField\": \"FirstResponseTime\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"header\": \"percentile_handle_time\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"percentile\",\n"
            + "        \"targetField\": \"HandleTime\",\n"
            + "        \"percentile\": 25\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
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
            + "  \"headers\": [\n"
            + "    \"count\",\n"
            + "    \"avg_first_response_time\",\n"
            + "    \"percentile_first_response_time\"\n"
            + "  ],\n"
            + "  \"rows\": [\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"Category\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"Sales\"\n"
            + "        },\n"
            + "        \"CreatedAt\": {\n"
            + "          \"type\": \"dateTime\",\n"
            + "          \"value\": \"2023-10-01T00:00:00Z\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 5\n"
            + "        },\n"
            + "        \"avg_first_response_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 150\n"
            + "        },\n"
            + "        \"percentile_handle_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 110\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"Category\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"Support\"\n"
            + "        },\n"
            + "        \"CreatedAt\": {\n"
            + "          \"type\": \"dateTime\",\n"
            + "          \"value\": \"2023-10-01T00:00:00Z\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 10\n"
            + "        },\n"
            + "        \"avg_first_response_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 300\n"
            + "        },\n"
            + "        \"percentile_handle_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 250\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"Category\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"Sales\",\n"
            + "          \"CreatedAt\": {\n"
            + "            \"type\": \"dateTime\",\n"
            + "            \"value\": \"2023-10-02T00:00:00Z\"\n"
            + "          }\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 7\n"
            + "        },\n"
            + "        \"avg_first_response_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 180\n"
            + "        },\n"
            + "        \"percentile_handle_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 180\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"Category\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"Support\"\n"
            + "        },\n"
            + "        \"CreatedAt\": {\n"
            + "          \"type\": \"dateTime\",\n"
            + "          \"value\": \"2023-10-02T00:00:00Z\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 8\n"
            + "        },\n"
            + "        \"avg_first_response_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 320\n"
            + "        },\n"
            + "        \"percentile_handle_time\": {\n"
            + "          \"type\": \"millisecond\",\n"
            + "          \"value\": 220\n"
            + "        }\n"
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
    public void testGetConversationChart() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"type\":\"pieChart\",\"series\":{\"name\":\"Series\",\"data\":[{\"label\":\"Sales\",\"y\":5},{\"label\":\"Support\",\"y\":10}]}}"));
        ChartResponse response = client.organizations().getConversationChart(
            ConversationChartRequest.pieChart(
                ConversationPieChartRequest
                    .builder()
                    .groupBy(
                        ConversationGroupBy
                            .builder()
                            .field(ConversationField.CATEGORY)
                            .build()
                    )
                    .metric(
                        ConversationMetric.count(
                            ConversationCount
                                .builder()
                                .build()
                        )
                    )
                    .conversationFilter(
                        ConversationFilter
                            .builder()
                            .languages(
                                Optional.of(
                                    Arrays.asList("en", "es")
                                )
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
            + "  \"type\": \"pieChart\",\n"
            + "  \"conversationFilter\": {\n"
            + "    \"languages\": [\n"
            + "      \"en\",\n"
            + "      \"es\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"groupBy\": {\n"
            + "    \"field\": \"Category\"\n"
            + "  },\n"
            + "  \"metric\": {\n"
            + "    \"type\": \"count\"\n"
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
            + "  \"type\": \"pieChart\",\n"
            + "  \"series\": {\n"
            + "    \"name\": \"Series\",\n"
            + "    \"data\": [\n"
            + "      {\n"
            + "        \"label\": \"Sales\",\n"
            + "        \"y\": 5\n"
            + "      },\n"
            + "      {\n"
            + "        \"label\": \"Support\",\n"
            + "        \"y\": 10\n"
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
}
