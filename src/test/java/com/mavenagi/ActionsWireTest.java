package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.actions.requests.ActionGetRequest;
import com.mavenagi.resources.actions.requests.ActionPatchRequest;
import com.mavenagi.resources.actions.types.ActionRequest;
import com.mavenagi.resources.actions.types.ActionsResponse;
import com.mavenagi.resources.actions.types.ActionsSearchRequest;
import com.mavenagi.resources.commons.types.ActionParameter;
import com.mavenagi.resources.commons.types.ActionResponse;
import com.mavenagi.resources.commons.types.EntityId;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.EntityType;
import com.mavenagi.resources.commons.types.LlmInclusionStatus;
import com.mavenagi.resources.commons.types.MetadataPrecondition;
import com.mavenagi.resources.commons.types.Precondition;
import com.mavenagi.resources.commons.types.PreconditionGroup;
import com.mavenagi.resources.commons.types.PreconditionGroupOperator;
import java.util.Arrays;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionsWireTest {
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
            .setBody("{\"actions\":[{\"actionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"instructions\":\"instructions\",\"llmInclusionStatus\":\"ALWAYS\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"preconditionExplanation\":\"preconditionExplanation\",\"deleted\":true,\"name\":\"name\",\"description\":\"description\",\"userInteractionRequired\":true,\"buttonName\":\"buttonName\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"values\":[\"values\",\"values\"],\"operator\":\"NOT\"},\"userFormParameters\":[{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}},{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}}],\"language\":\"language\"},{\"actionId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"instructions\":\"instructions\",\"llmInclusionStatus\":\"ALWAYS\",\"segmentId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"x\"},\"preconditionExplanation\":\"preconditionExplanation\",\"deleted\":true,\"name\":\"name\",\"description\":\"description\",\"userInteractionRequired\":true,\"buttonName\":\"buttonName\",\"precondition\":{\"preconditionType\":\"user\",\"key\":\"key\",\"value\":\"value\",\"values\":[\"values\",\"values\"],\"operator\":\"NOT\"},\"userFormParameters\":[{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}},{\"id\":\"id\",\"label\":\"label\",\"description\":\"description\",\"required\":true,\"hidden\":true,\"type\":\"STRING\",\"enumOptions\":[{\"label\":\"label\",\"value\":{\"key\":\"value\"}},{\"label\":\"label\",\"value\":{\"key\":\"value\"}}],\"schema\":\"schema\",\"oauthConfiguration\":{\"authorizationUrl\":\"authorizationUrl\",\"tokenUrl\":\"tokenUrl\",\"clientId\":\"clientId\",\"clientSecret\":\"clientSecret\",\"scopes\":[\"scopes\",\"scopes\"],\"extraAuthParams\":{\"extraAuthParams\":\"extraAuthParams\"},\"extraTokenParams\":{\"extraTokenParams\":\"extraTokenParams\"}}}],\"language\":\"language\"}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        ActionsResponse response = client.actions().search(
            ActionsSearchRequest
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
            + "  \"actions\": [\n"
            + "    {\n"
            + "      \"actionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"instructions\": \"instructions\",\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"preconditionExplanation\": \"preconditionExplanation\",\n"
            + "      \"deleted\": true,\n"
            + "      \"name\": \"name\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"userInteractionRequired\": true,\n"
            + "      \"buttonName\": \"buttonName\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"key\",\n"
            + "        \"value\": \"value\",\n"
            + "        \"values\": [\n"
            + "          \"values\",\n"
            + "          \"values\"\n"
            + "        ],\n"
            + "        \"operator\": \"NOT\"\n"
            + "      },\n"
            + "      \"userFormParameters\": [\n"
            + "        {\n"
            + "          \"id\": \"id\",\n"
            + "          \"label\": \"label\",\n"
            + "          \"description\": \"description\",\n"
            + "          \"required\": true,\n"
            + "          \"hidden\": true,\n"
            + "          \"type\": \"STRING\",\n"
            + "          \"enumOptions\": [\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            },\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            }\n"
            + "          ],\n"
            + "          \"schema\": \"schema\",\n"
            + "          \"oauthConfiguration\": {\n"
            + "            \"authorizationUrl\": \"authorizationUrl\",\n"
            + "            \"tokenUrl\": \"tokenUrl\",\n"
            + "            \"clientId\": \"clientId\",\n"
            + "            \"clientSecret\": \"clientSecret\",\n"
            + "            \"scopes\": [\n"
            + "              \"scopes\",\n"
            + "              \"scopes\"\n"
            + "            ],\n"
            + "            \"extraAuthParams\": {\n"
            + "              \"extraAuthParams\": \"extraAuthParams\"\n"
            + "            },\n"
            + "            \"extraTokenParams\": {\n"
            + "              \"extraTokenParams\": \"extraTokenParams\"\n"
            + "            }\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"id\": \"id\",\n"
            + "          \"label\": \"label\",\n"
            + "          \"description\": \"description\",\n"
            + "          \"required\": true,\n"
            + "          \"hidden\": true,\n"
            + "          \"type\": \"STRING\",\n"
            + "          \"enumOptions\": [\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            },\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            }\n"
            + "          ],\n"
            + "          \"schema\": \"schema\",\n"
            + "          \"oauthConfiguration\": {\n"
            + "            \"authorizationUrl\": \"authorizationUrl\",\n"
            + "            \"tokenUrl\": \"tokenUrl\",\n"
            + "            \"clientId\": \"clientId\",\n"
            + "            \"clientSecret\": \"clientSecret\",\n"
            + "            \"scopes\": [\n"
            + "              \"scopes\",\n"
            + "              \"scopes\"\n"
            + "            ],\n"
            + "            \"extraAuthParams\": {\n"
            + "              \"extraAuthParams\": \"extraAuthParams\"\n"
            + "            },\n"
            + "            \"extraTokenParams\": {\n"
            + "              \"extraTokenParams\": \"extraTokenParams\"\n"
            + "            }\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"language\": \"language\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"actionId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"instructions\": \"instructions\",\n"
            + "      \"llmInclusionStatus\": \"ALWAYS\",\n"
            + "      \"segmentId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"x\"\n"
            + "      },\n"
            + "      \"preconditionExplanation\": \"preconditionExplanation\",\n"
            + "      \"deleted\": true,\n"
            + "      \"name\": \"name\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"userInteractionRequired\": true,\n"
            + "      \"buttonName\": \"buttonName\",\n"
            + "      \"precondition\": {\n"
            + "        \"preconditionType\": \"user\",\n"
            + "        \"key\": \"key\",\n"
            + "        \"value\": \"value\",\n"
            + "        \"values\": [\n"
            + "          \"values\",\n"
            + "          \"values\"\n"
            + "        ],\n"
            + "        \"operator\": \"NOT\"\n"
            + "      },\n"
            + "      \"userFormParameters\": [\n"
            + "        {\n"
            + "          \"id\": \"id\",\n"
            + "          \"label\": \"label\",\n"
            + "          \"description\": \"description\",\n"
            + "          \"required\": true,\n"
            + "          \"hidden\": true,\n"
            + "          \"type\": \"STRING\",\n"
            + "          \"enumOptions\": [\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            },\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            }\n"
            + "          ],\n"
            + "          \"schema\": \"schema\",\n"
            + "          \"oauthConfiguration\": {\n"
            + "            \"authorizationUrl\": \"authorizationUrl\",\n"
            + "            \"tokenUrl\": \"tokenUrl\",\n"
            + "            \"clientId\": \"clientId\",\n"
            + "            \"clientSecret\": \"clientSecret\",\n"
            + "            \"scopes\": [\n"
            + "              \"scopes\",\n"
            + "              \"scopes\"\n"
            + "            ],\n"
            + "            \"extraAuthParams\": {\n"
            + "              \"extraAuthParams\": \"extraAuthParams\"\n"
            + "            },\n"
            + "            \"extraTokenParams\": {\n"
            + "              \"extraTokenParams\": \"extraTokenParams\"\n"
            + "            }\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"id\": \"id\",\n"
            + "          \"label\": \"label\",\n"
            + "          \"description\": \"description\",\n"
            + "          \"required\": true,\n"
            + "          \"hidden\": true,\n"
            + "          \"type\": \"STRING\",\n"
            + "          \"enumOptions\": [\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            },\n"
            + "            {\n"
            + "              \"label\": \"label\",\n"
            + "              \"value\": {\n"
            + "                \"key\": \"value\"\n"
            + "              }\n"
            + "            }\n"
            + "          ],\n"
            + "          \"schema\": \"schema\",\n"
            + "          \"oauthConfiguration\": {\n"
            + "            \"authorizationUrl\": \"authorizationUrl\",\n"
            + "            \"tokenUrl\": \"tokenUrl\",\n"
            + "            \"clientId\": \"clientId\",\n"
            + "            \"clientSecret\": \"clientSecret\",\n"
            + "            \"scopes\": [\n"
            + "              \"scopes\",\n"
            + "              \"scopes\"\n"
            + "            ],\n"
            + "            \"extraAuthParams\": {\n"
            + "              \"extraAuthParams\": \"extraAuthParams\"\n"
            + "            },\n"
            + "            \"extraTokenParams\": {\n"
            + "              \"extraTokenParams\": \"extraTokenParams\"\n"
            + "            }\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"language\": \"language\"\n"
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
            .setBody("{\"actionId\":{\"referenceId\":\"get-balance\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"ACTION\"},\"name\":\"Get the user's balance\",\"description\":\"This action calls an API to get the user's current balance.\",\"instructions\":\"This action calls an API to get the user's current balance.\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"userInteractionRequired\":false,\"userFormParameters\":[],\"precondition\":{\"preconditionType\":\"group\",\"operator\":\"AND\",\"preconditions\":[{\"preconditionType\":\"user\",\"key\":\"userKey\"},{\"preconditionType\":\"user\",\"key\":\"userKey2\"}]},\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"},\"language\":\"en\",\"deleted\":false}"));
        ActionResponse response = client.actions().createOrUpdate(
            ActionRequest
                .builder()
                .actionId(
                    EntityIdBase
                        .builder()
                        .referenceId("get-balance")
                        .build()
                )
                .name("Get the user's balance")
                .description("This action calls an API to get the user's current balance.")
                .userInteractionRequired(false)
                .userFormParameters(
                    new ArrayList<ActionParameter>()
                )
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
                .language("en")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"actionId\": {\n"
            + "    \"referenceId\": \"get-balance\"\n"
            + "  },\n"
            + "  \"name\": \"Get the user's balance\",\n"
            + "  \"description\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"userInteractionRequired\": false,\n"
            + "  \"userFormParameters\": [],\n"
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
            + "  },\n"
            + "  \"language\": \"en\"\n"
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
            + "  \"actionId\": {\n"
            + "    \"referenceId\": \"get-balance\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"ACTION\"\n"
            + "  },\n"
            + "  \"name\": \"Get the user's balance\",\n"
            + "  \"description\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"instructions\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"userInteractionRequired\": false,\n"
            + "  \"userFormParameters\": [],\n"
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
            + "  },\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
            + "  },\n"
            + "  \"language\": \"en\",\n"
            + "  \"deleted\": false\n"
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
            .setBody("{\"actionId\":{\"referenceId\":\"get-balance\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"ACTION\"},\"name\":\"Get the user's balance\",\"description\":\"This action calls an API to get the user's current balance.\",\"instructions\":\"This action calls an API to get the user's current balance.\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"userInteractionRequired\":false,\"userFormParameters\":[],\"precondition\":{\"preconditionType\":\"group\",\"operator\":\"AND\",\"preconditions\":[{\"preconditionType\":\"user\",\"key\":\"userKey\"},{\"preconditionType\":\"user\",\"key\":\"userKey2\"}]},\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"},\"language\":\"en\",\"deleted\":false}"));
        ActionResponse response = client.actions().get(
            "get-balance",
            ActionGetRequest
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
            + "  \"actionId\": {\n"
            + "    \"referenceId\": \"get-balance\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"ACTION\"\n"
            + "  },\n"
            + "  \"name\": \"Get the user's balance\",\n"
            + "  \"description\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"instructions\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"userInteractionRequired\": false,\n"
            + "  \"userFormParameters\": [],\n"
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
            + "  },\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
            + "  },\n"
            + "  \"language\": \"en\",\n"
            + "  \"deleted\": false\n"
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
            .setBody("{\"actionId\":{\"referenceId\":\"get-balance\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"ACTION\"},\"name\":\"Get the user's balance\",\"description\":\"This action calls an API to get the user's current balance.\",\"instructions\":\"This action calls an API to get the user's current balance.\",\"llmInclusionStatus\":\"WHEN_RELEVANT\",\"userInteractionRequired\":false,\"userFormParameters\":[],\"precondition\":{\"preconditionType\":\"group\",\"operator\":\"AND\",\"preconditions\":[{\"preconditionType\":\"user\",\"key\":\"userKey\"},{\"preconditionType\":\"user\",\"key\":\"userKey2\"}]},\"segmentId\":{\"referenceId\":\"premium-users\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"SEGMENT\"},\"language\":\"en\",\"deleted\":false}"));
        ActionResponse response = client.actions().patch(
            "get-balance",
            ActionPatchRequest
                .builder()
                .instructions("Use this action when the user asks about their account balance or remaining credits.")
                .llmInclusionStatus(LlmInclusionStatus.WHEN_RELEVANT)
                .segmentId(
                    EntityId
                        .builder()
                        .referenceId("premium-users")
                        .appId("my-billing-system")
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
            + "  \"instructions\": \"Use this action when the user asks about their account balance or remaining credits.\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
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
            + "  \"actionId\": {\n"
            + "    \"referenceId\": \"get-balance\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"ACTION\"\n"
            + "  },\n"
            + "  \"name\": \"Get the user's balance\",\n"
            + "  \"description\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"instructions\": \"This action calls an API to get the user's current balance.\",\n"
            + "  \"llmInclusionStatus\": \"WHEN_RELEVANT\",\n"
            + "  \"userInteractionRequired\": false,\n"
            + "  \"userFormParameters\": [],\n"
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
            + "  },\n"
            + "  \"segmentId\": {\n"
            + "    \"referenceId\": \"premium-users\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"SEGMENT\"\n"
            + "  },\n"
            + "  \"language\": \"en\",\n"
            + "  \"deleted\": false\n"
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
        client.actions().delete("get-balance");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
}
