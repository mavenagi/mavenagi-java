package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.InboxItem;
import com.mavenagi.resources.commons.types.InboxItemFix;
import com.mavenagi.resources.inbox.requests.InboxItemFixRequest;
import com.mavenagi.resources.inbox.requests.InboxItemIgnoreRequest;
import com.mavenagi.resources.inbox.requests.InboxItemRequest;
import com.mavenagi.resources.inbox.types.ApplyFixesRequest;
import com.mavenagi.resources.inbox.types.InboxSearchRequest;
import com.mavenagi.resources.inbox.types.InboxSearchResponse;
import java.util.Arrays;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InboxWireTest {
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
            .setBody("{\"items\":[{\"type\":\"duplicateDocuments\",\"recommendedFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"otherFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"sourceDocument\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"documents\":[{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"}],\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"OPEN\",\"severity\":\"LOW\"},{\"type\":\"duplicateDocuments\",\"recommendedFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"otherFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"sourceDocument\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"documents\":[{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"}],\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"OPEN\",\"severity\":\"LOW\"}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        InboxSearchResponse response = client.inbox().search(
            InboxSearchRequest
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
            + "  \"items\": [\n"
            + "    {\n"
            + "      \"type\": \"duplicateDocuments\",\n"
            + "      \"recommendedFixes\": [\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"otherFixes\": [\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"sourceDocument\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"documents\": [\n"
            + "        {\n"
            + "          \"knowledgeBaseId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"documentId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"title\": \"title\",\n"
            + "          \"snippet\": \"snippet\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"knowledgeBaseId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"documentId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"title\": \"title\",\n"
            + "          \"snippet\": \"snippet\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"status\": \"OPEN\",\n"
            + "      \"severity\": \"LOW\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"duplicateDocuments\",\n"
            + "      \"recommendedFixes\": [\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"otherFixes\": [\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        },\n"
            + "        {\n"
            + "          \"documentInformation\": {\n"
            + "            \"knowledgeBaseId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"documentId\": {\n"
            + "              \"type\": \"AGENT\",\n"
            + "              \"appId\": \"appId\",\n"
            + "              \"referenceId\": \"referenceId\"\n"
            + "            },\n"
            + "            \"title\": \"title\",\n"
            + "            \"snippet\": \"snippet\"\n"
            + "          },\n"
            + "          \"id\": {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        }\n"
            + "      ],\n"
            + "      \"sourceDocument\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"documents\": [\n"
            + "        {\n"
            + "          \"knowledgeBaseId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"documentId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"title\": \"title\",\n"
            + "          \"snippet\": \"snippet\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"knowledgeBaseId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"documentId\": {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          \"title\": \"title\",\n"
            + "          \"snippet\": \"snippet\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"status\": \"OPEN\",\n"
            + "      \"severity\": \"LOW\"\n"
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
            .setBody("{\"type\":\"duplicateDocuments\",\"recommendedFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"otherFixes\":[{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}},{\"documentInformation\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}],\"sourceDocument\":{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},\"documents\":[{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"},{\"knowledgeBaseId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"documentId\":{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"title\":\"title\",\"snippet\":\"snippet\"}],\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"status\":\"OPEN\",\"severity\":\"LOW\"}"));
        InboxItem response = client.inbox().get(
            "inboxItemId",
            InboxItemRequest
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
            + "  \"type\": \"duplicateDocuments\",\n"
            + "  \"recommendedFixes\": [\n"
            + "    {\n"
            + "      \"documentInformation\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"documentInformation\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"otherFixes\": [\n"
            + "    {\n"
            + "      \"documentInformation\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"documentInformation\": {\n"
            + "        \"knowledgeBaseId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"documentId\": {\n"
            + "          \"type\": \"AGENT\",\n"
            + "          \"appId\": \"appId\",\n"
            + "          \"referenceId\": \"referenceId\"\n"
            + "        },\n"
            + "        \"title\": \"title\",\n"
            + "        \"snippet\": \"snippet\"\n"
            + "      },\n"
            + "      \"id\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"sourceDocument\": {\n"
            + "    \"knowledgeBaseId\": {\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"documentId\": {\n"
            + "      \"type\": \"AGENT\",\n"
            + "      \"appId\": \"appId\",\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"title\": \"title\",\n"
            + "    \"snippet\": \"snippet\"\n"
            + "  },\n"
            + "  \"documents\": [\n"
            + "    {\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"documentId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"title\": \"title\",\n"
            + "      \"snippet\": \"snippet\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"knowledgeBaseId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"documentId\": {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"title\": \"title\",\n"
            + "      \"snippet\": \"snippet\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"id\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"status\": \"OPEN\",\n"
            + "  \"severity\": \"LOW\"\n"
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
    public void testGetFix() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"type\":\"addDocument\",\"suggestedTextTitle\":\"suggestedTextTitle\",\"suggestedText\":\"suggestedText\",\"id\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}}"));
        InboxItemFix response = client.inbox().getFix(
            "inboxItemFixId",
            InboxItemFixRequest
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
            + "  \"type\": \"addDocument\",\n"
            + "  \"suggestedTextTitle\": \"suggestedTextTitle\",\n"
            + "  \"suggestedText\": \"suggestedText\",\n"
            + "  \"id\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
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
    public void testApplyFixes() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.inbox().applyFixes(
            "inboxItemId",
            ApplyFixesRequest
                .builder()
                .appId("appId")
                .fixReferenceIds(
                    Arrays.asList("fixReferenceIds", "fixReferenceIds")
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
            + "  \"appId\": \"appId\",\n"
            + "  \"fixReferenceIds\": [\n"
            + "    \"fixReferenceIds\",\n"
            + "    \"fixReferenceIds\"\n"
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
    }
    @Test
    public void testIgnore() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.inbox().ignore(
            "inboxItemId",
            InboxItemIgnoreRequest
                .builder()
                .appId("appId")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
    }
}
