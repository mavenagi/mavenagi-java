package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.analytics.types.AgentUserColumnDefinition;
import com.mavenagi.resources.analytics.types.AgentUserCount;
import com.mavenagi.resources.analytics.types.AgentUserMetric;
import com.mavenagi.resources.analytics.types.AgentUserTableRequest;
import com.mavenagi.resources.analytics.types.AgentUserTableResponse;
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
import com.mavenagi.resources.analytics.types.EventChartRequest;
import com.mavenagi.resources.analytics.types.EventColumnDefinition;
import com.mavenagi.resources.analytics.types.EventCount;
import com.mavenagi.resources.analytics.types.EventGroupBy;
import com.mavenagi.resources.analytics.types.EventMetric;
import com.mavenagi.resources.analytics.types.EventPieChartRequest;
import com.mavenagi.resources.analytics.types.EventTableRequest;
import com.mavenagi.resources.analytics.types.EventTableResponse;
import com.mavenagi.resources.analytics.types.FeedbackColumnDefinition;
import com.mavenagi.resources.analytics.types.FeedbackCount;
import com.mavenagi.resources.analytics.types.FeedbackGroupBy;
import com.mavenagi.resources.analytics.types.FeedbackMetric;
import com.mavenagi.resources.analytics.types.FeedbackTableRequest;
import com.mavenagi.resources.analytics.types.FeedbackTableResponse;
import com.mavenagi.resources.analytics.types.TimeInterval;
import com.mavenagi.resources.commons.types.EventField;
import com.mavenagi.resources.commons.types.EventFilter;
import com.mavenagi.resources.commons.types.EventType;
import com.mavenagi.resources.commons.types.FeedbackType;
import com.mavenagi.resources.conversation.types.ConversationField;
import com.mavenagi.resources.conversation.types.ConversationFilter;
import com.mavenagi.resources.conversation.types.FeedbackField;
import com.mavenagi.resources.conversation.types.FeedbackFilter;
import com.mavenagi.resources.conversation.types.NumericConversationField;
import com.mavenagi.resources.users.types.AgentUserFilter;
import java.util.Arrays;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnalyticsWireTest {
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
    public void testGetConversationTable() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"headers\":[\"count\",\"avg_first_response_time\",\"percentile_first_response_time\"],\"rows\":[{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Sales\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-01T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":5},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":150},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":110}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Support\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-01T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":10},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":300},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":250}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Sales\",\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-02T00:00:00Z\"}}},\"data\":{\"count\":{\"type\":\"double\",\"value\":7},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":180},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":180}}},{\"identifier\":{\"Category\":{\"type\":\"string\",\"value\":\"Support\"},\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-02T00:00:00Z\"}},\"data\":{\"count\":{\"type\":\"double\",\"value\":8},\"avg_first_response_time\":{\"type\":\"millisecond\",\"value\":320},\"percentile_handle_time\":{\"type\":\"millisecond\",\"value\":220}}}]}"));
        ConversationTableResponse response = client.analytics().getConversationTable(
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
        ChartResponse response = client.analytics().getConversationChart(
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
    @Test
    public void testGetFeedbackTable() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"headers\":[\"feedback_count\",\"unique_users\"],\"rows\":[{\"identifier\":{\"CreatedBy\":{\"type\":\"string\",\"value\":\"John Doe\"}},\"data\":{\"feedback_count\":{\"type\":\"double\",\"value\":5}}},{\"identifier\":{\"CreatedBy\":{\"type\":\"string\",\"value\":\"Jane Smith\"}},\"data\":{\"feedback_count\":{\"type\":\"double\",\"value\":3}}}]}"));
        FeedbackTableResponse response = client.analytics().getFeedbackTable(
            FeedbackTableRequest
                .builder()
                .fieldGroupings(
                    Arrays.asList(
                        FeedbackGroupBy
                            .builder()
                            .field(FeedbackField.CREATED_BY)
                            .build()
                    )
                )
                .columnDefinitions(
                    Arrays.asList(
                        FeedbackColumnDefinition
                            .builder()
                            .header("feedback_count")
                            .metric(
                                FeedbackMetric.count(
                                    FeedbackCount
                                        .builder()
                                        .build()
                                )
                            )
                            .build()
                    )
                )
                .feedbackFilter(
                    FeedbackFilter
                        .builder()
                        .types(
                            Optional.of(
                                Arrays.asList(FeedbackType.THUMBS_UP, FeedbackType.INSERT)
                            )
                        )
                        .build()
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
            + "  \"feedbackFilter\": {\n"
            + "    \"types\": [\n"
            + "      \"THUMBS_UP\",\n"
            + "      \"INSERT\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"fieldGroupings\": [\n"
            + "    {\n"
            + "      \"field\": \"CreatedBy\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"columnDefinitions\": [\n"
            + "    {\n"
            + "      \"header\": \"feedback_count\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"count\"\n"
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
            + "    \"feedback_count\",\n"
            + "    \"unique_users\"\n"
            + "  ],\n"
            + "  \"rows\": [\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"CreatedBy\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"John Doe\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"feedback_count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 5\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"CreatedBy\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"Jane Smith\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"feedback_count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 3\n"
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
    public void testGetAgentUserTable() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"headers\":[\"user_count\"],\"rows\":[{\"identifier\":{\"CreatedAt\":{\"type\":\"dateTime\",\"value\":\"2023-10-01T00:00:00Z\"}},\"data\":{\"user_count\":{\"type\":\"double\",\"value\":5}}}]}"));
        AgentUserTableResponse response = client.analytics().getAgentUserTable(
            AgentUserTableRequest
                .builder()
                .columnDefinitions(
                    Arrays.asList(
                        AgentUserColumnDefinition
                            .builder()
                            .header("user_count")
                            .metric(
                                AgentUserMetric.count(
                                    AgentUserCount
                                        .builder()
                                        .build()
                                )
                            )
                            .build()
                    )
                )
                .agentUserFilter(
                    AgentUserFilter
                        .builder()
                        .search("john")
                        .build()
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
            + "  \"agentUserFilter\": {\n"
            + "    \"search\": \"john\"\n"
            + "  },\n"
            + "  \"columnDefinitions\": [\n"
            + "    {\n"
            + "      \"header\": \"user_count\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"count\"\n"
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
            + "    \"user_count\"\n"
            + "  ],\n"
            + "  \"rows\": [\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"CreatedAt\": {\n"
            + "          \"type\": \"dateTime\",\n"
            + "          \"value\": \"2023-10-01T00:00:00Z\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"user_count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 5\n"
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
    public void testGetEventTable() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"headers\":[\"event_count\"],\"rows\":[{\"identifier\":{\"EVENT_NAME\":{\"type\":\"string\",\"value\":\"CHAT_OPENED\"}},\"data\":{\"event_count\":{\"type\":\"double\",\"value\":50}}},{\"identifier\":{\"EVENT_NAME\":{\"type\":\"string\",\"value\":\"CHAT_CLOSED\"}},\"data\":{\"event_count\":{\"type\":\"double\",\"value\":45}}}]}"));
        EventTableResponse response = client.analytics().getEventTable(
            EventTableRequest
                .builder()
                .fieldGroupings(
                    Arrays.asList(
                        EventGroupBy
                            .builder()
                            .field(EventField.EVENT_NAME)
                            .build()
                    )
                )
                .columnDefinitions(
                    Arrays.asList(
                        EventColumnDefinition
                            .builder()
                            .header("event_count")
                            .metric(
                                EventMetric.count(
                                    EventCount
                                        .builder()
                                        .build()
                                )
                            )
                            .build()
                    )
                )
                .eventFilter(
                    EventFilter
                        .builder()
                        .eventTypes(
                            Optional.of(
                                Arrays.asList(EventType.USER)
                            )
                        )
                        .build()
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
            + "  \"eventFilter\": {\n"
            + "    \"eventTypes\": [\n"
            + "      \"USER\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"fieldGroupings\": [\n"
            + "    {\n"
            + "      \"field\": \"EVENT_NAME\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"columnDefinitions\": [\n"
            + "    {\n"
            + "      \"header\": \"event_count\",\n"
            + "      \"metric\": {\n"
            + "        \"type\": \"count\"\n"
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
            + "    \"event_count\"\n"
            + "  ],\n"
            + "  \"rows\": [\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"EVENT_NAME\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"CHAT_OPENED\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"event_count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 50\n"
            + "        }\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": {\n"
            + "        \"EVENT_NAME\": {\n"
            + "          \"type\": \"string\",\n"
            + "          \"value\": \"CHAT_CLOSED\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"data\": {\n"
            + "        \"event_count\": {\n"
            + "          \"type\": \"double\",\n"
            + "          \"value\": 45\n"
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
    public void testGetEventChart() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"type\":\"pieChart\",\"series\":{\"name\":\"Series\",\"data\":[{\"label\":\"Sales\",\"y\":5},{\"label\":\"Support\",\"y\":10}]}}"));
        ChartResponse response = client.analytics().getEventChart(
            EventChartRequest.pieChart(
                EventPieChartRequest
                    .builder()
                    .groupBy(
                        EventGroupBy
                            .builder()
                            .field(EventField.EVENT_NAME)
                            .build()
                    )
                    .metric(
                        EventMetric.count(
                            EventCount
                                .builder()
                                .build()
                        )
                    )
                    .eventFilter(
                        EventFilter
                            .builder()
                            .eventTypes(
                                Optional.of(
                                    Arrays.asList(EventType.USER)
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
            + "  \"eventFilter\": {\n"
            + "    \"eventTypes\": [\n"
            + "      \"USER\"\n"
            + "    ]\n"
            + "  },\n"
            + "  \"groupBy\": {\n"
            + "    \"field\": \"EVENT_NAME\"\n"
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
