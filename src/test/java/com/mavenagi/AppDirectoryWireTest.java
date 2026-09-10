package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.appdirectory.requests.GetAppSettingUploadUrlRequest;
import com.mavenagi.resources.appdirectory.types.DirectoryAppsSearchRequest;
import com.mavenagi.resources.appdirectory.types.GetDirectoryAppSettingDownloadUrlResponse;
import com.mavenagi.resources.appdirectory.types.GetDirectoryAppSettingUploadUrlResponse;
import com.mavenagi.resources.appdirectory.types.InstallDirectoryAppRequest;
import com.mavenagi.resources.developerscommons.types.AppsResponse;
import com.mavenagi.resources.developerscommons.types.MarketplaceAppDetail;
import java.util.HashMap;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppDirectoryWireTest {
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
            .setBody("{\"apps\":[{\"installed\":\"INSTALLED\",\"id\":\"id\",\"name\":\"name\",\"developerOrganizationId\":\"developerOrganizationId\",\"creator\":{\"organizationId\":\"organizationId\",\"name\":\"name\",\"website\":\"website\",\"email\":\"email\",\"phone\":\"phone\"},\"visibility\":\"IN_DEVELOPMENT\",\"description\":\"description\",\"shortDescription\":\"shortDescription\",\"logoUrl\":\"logoUrl\",\"categories\":[\"COMMUNICATION\",\"COMMUNICATION\"],\"capabilities\":[\"ACTIONS\",\"ACTIONS\"],\"isAgentApp\":true},{\"installed\":\"INSTALLED\",\"id\":\"id\",\"name\":\"name\",\"developerOrganizationId\":\"developerOrganizationId\",\"creator\":{\"organizationId\":\"organizationId\",\"name\":\"name\",\"website\":\"website\",\"email\":\"email\",\"phone\":\"phone\"},\"visibility\":\"IN_DEVELOPMENT\",\"description\":\"description\",\"shortDescription\":\"shortDescription\",\"logoUrl\":\"logoUrl\",\"categories\":[\"COMMUNICATION\",\"COMMUNICATION\"],\"capabilities\":[\"ACTIONS\",\"ACTIONS\"],\"isAgentApp\":true}],\"allAppsCountByFilter\":{\"countByVisibility\":{\"IN_DEVELOPMENT\":1},\"countByCategory\":{\"COMMUNICATION\":1}},\"installedAppsCountByFilter\":{\"countByVisibility\":{\"IN_DEVELOPMENT\":1},\"countByCategory\":{\"COMMUNICATION\":1}},\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        AppsResponse response = client.appDirectory().search(
            DirectoryAppsSearchRequest
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
            + "  \"apps\": [\n"
            + "    {\n"
            + "      \"installed\": \"INSTALLED\",\n"
            + "      \"id\": \"id\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"developerOrganizationId\": \"developerOrganizationId\",\n"
            + "      \"creator\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"name\": \"name\",\n"
            + "        \"website\": \"website\",\n"
            + "        \"email\": \"email\",\n"
            + "        \"phone\": \"phone\"\n"
            + "      },\n"
            + "      \"visibility\": \"IN_DEVELOPMENT\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"shortDescription\": \"shortDescription\",\n"
            + "      \"logoUrl\": \"logoUrl\",\n"
            + "      \"categories\": [\n"
            + "        \"COMMUNICATION\",\n"
            + "        \"COMMUNICATION\"\n"
            + "      ],\n"
            + "      \"capabilities\": [\n"
            + "        \"ACTIONS\",\n"
            + "        \"ACTIONS\"\n"
            + "      ],\n"
            + "      \"isAgentApp\": true\n"
            + "    },\n"
            + "    {\n"
            + "      \"installed\": \"INSTALLED\",\n"
            + "      \"id\": \"id\",\n"
            + "      \"name\": \"name\",\n"
            + "      \"developerOrganizationId\": \"developerOrganizationId\",\n"
            + "      \"creator\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"name\": \"name\",\n"
            + "        \"website\": \"website\",\n"
            + "        \"email\": \"email\",\n"
            + "        \"phone\": \"phone\"\n"
            + "      },\n"
            + "      \"visibility\": \"IN_DEVELOPMENT\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"shortDescription\": \"shortDescription\",\n"
            + "      \"logoUrl\": \"logoUrl\",\n"
            + "      \"categories\": [\n"
            + "        \"COMMUNICATION\",\n"
            + "        \"COMMUNICATION\"\n"
            + "      ],\n"
            + "      \"capabilities\": [\n"
            + "        \"ACTIONS\",\n"
            + "        \"ACTIONS\"\n"
            + "      ],\n"
            + "      \"isAgentApp\": true\n"
            + "    }\n"
            + "  ],\n"
            + "  \"allAppsCountByFilter\": {\n"
            + "    \"countByVisibility\": {\n"
            + "      \"IN_DEVELOPMENT\": 1\n"
            + "    },\n"
            + "    \"countByCategory\": {\n"
            + "      \"COMMUNICATION\": 1\n"
            + "    }\n"
            + "  },\n"
            + "  \"installedAppsCountByFilter\": {\n"
            + "    \"countByVisibility\": {\n"
            + "      \"IN_DEVELOPMENT\": 1\n"
            + "    },\n"
            + "    \"countByCategory\": {\n"
            + "      \"COMMUNICATION\": 1\n"
            + "    }\n"
            + "  },\n"
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
            .setBody("{\"installed\":\"INSTALLED\",\"settings\":{\"key\":\"value\"},\"installationInstructions\":\"installationInstructions\",\"instructions\":\"instructions\",\"previewUrl\":\"previewUrl\",\"links\":[{\"title\":\"title\",\"description\":\"description\",\"url\":\"url\"},{\"title\":\"title\",\"description\":\"description\",\"url\":\"url\"}],\"settingsSchema\":[{\"type\":\"text\",\"defaultValue\":\"defaultValue\",\"validation\":{\"pattern\":\"pattern\",\"errorMessage\":\"errorMessage\"},\"key\":\"key\",\"displayName\":\"displayName\",\"description\":\"description\",\"visibility\":\"VISIBLE\",\"required\":true},{\"type\":\"text\",\"defaultValue\":\"defaultValue\",\"validation\":{\"pattern\":\"pattern\",\"errorMessage\":\"errorMessage\"},\"key\":\"key\",\"displayName\":\"displayName\",\"description\":\"description\",\"visibility\":\"VISIBLE\",\"required\":true}],\"id\":\"id\",\"name\":\"name\",\"developerOrganizationId\":\"developerOrganizationId\",\"creator\":{\"organizationId\":\"organizationId\",\"name\":\"name\",\"website\":\"website\",\"email\":\"email\",\"phone\":\"phone\"},\"visibility\":\"IN_DEVELOPMENT\",\"description\":\"description\",\"shortDescription\":\"shortDescription\",\"logoUrl\":\"logoUrl\",\"categories\":[\"COMMUNICATION\",\"COMMUNICATION\"],\"capabilities\":[\"ACTIONS\",\"ACTIONS\"],\"isAgentApp\":true}"));
        MarketplaceAppDetail response = client.appDirectory().get("appId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"installed\": \"INSTALLED\",\n"
            + "  \"settings\": {\n"
            + "    \"key\": \"value\"\n"
            + "  },\n"
            + "  \"installationInstructions\": \"installationInstructions\",\n"
            + "  \"instructions\": \"instructions\",\n"
            + "  \"previewUrl\": \"previewUrl\",\n"
            + "  \"links\": [\n"
            + "    {\n"
            + "      \"title\": \"title\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"url\": \"url\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"title\": \"title\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"url\": \"url\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"settingsSchema\": [\n"
            + "    {\n"
            + "      \"type\": \"text\",\n"
            + "      \"defaultValue\": \"defaultValue\",\n"
            + "      \"validation\": {\n"
            + "        \"pattern\": \"pattern\",\n"
            + "        \"errorMessage\": \"errorMessage\"\n"
            + "      },\n"
            + "      \"key\": \"key\",\n"
            + "      \"displayName\": \"displayName\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"visibility\": \"VISIBLE\",\n"
            + "      \"required\": true\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"text\",\n"
            + "      \"defaultValue\": \"defaultValue\",\n"
            + "      \"validation\": {\n"
            + "        \"pattern\": \"pattern\",\n"
            + "        \"errorMessage\": \"errorMessage\"\n"
            + "      },\n"
            + "      \"key\": \"key\",\n"
            + "      \"displayName\": \"displayName\",\n"
            + "      \"description\": \"description\",\n"
            + "      \"visibility\": \"VISIBLE\",\n"
            + "      \"required\": true\n"
            + "    }\n"
            + "  ],\n"
            + "  \"id\": \"id\",\n"
            + "  \"name\": \"name\",\n"
            + "  \"developerOrganizationId\": \"developerOrganizationId\",\n"
            + "  \"creator\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"name\": \"name\",\n"
            + "    \"website\": \"website\",\n"
            + "    \"email\": \"email\",\n"
            + "    \"phone\": \"phone\"\n"
            + "  },\n"
            + "  \"visibility\": \"IN_DEVELOPMENT\",\n"
            + "  \"description\": \"description\",\n"
            + "  \"shortDescription\": \"shortDescription\",\n"
            + "  \"logoUrl\": \"logoUrl\",\n"
            + "  \"categories\": [\n"
            + "    \"COMMUNICATION\",\n"
            + "    \"COMMUNICATION\"\n"
            + "  ],\n"
            + "  \"capabilities\": [\n"
            + "    \"ACTIONS\",\n"
            + "    \"ACTIONS\"\n"
            + "  ],\n"
            + "  \"isAgentApp\": true\n"
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
    public void testInstall() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.appDirectory().install(
            "appId",
            InstallDirectoryAppRequest
                .builder()
                .settings(new 
                    HashMap<String, Object>() {{put("key", "value");
                    }})
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"settings\": {\n"
            + "    \"key\": \"value\"\n"
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
    public void testUninstall() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.appDirectory().uninstall("appId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
    @Test
    public void testGetSettingDownloadUrl() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"downloadUrl\":\"downloadUrl\"}"));
        GetDirectoryAppSettingDownloadUrlResponse response = client.appDirectory().getSettingDownloadUrl("appId", "settingsKey");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"downloadUrl\": \"downloadUrl\"\n"
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
    public void testGetSettingUploadUrl() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"uploadUrl\":\"uploadUrl\",\"downloadUrl\":\"downloadUrl\"}"));
        GetDirectoryAppSettingUploadUrlResponse response = client.appDirectory().getSettingUploadUrl(
            "appId",
            "settingsKey",
            GetAppSettingUploadUrlRequest
                .builder()
                .contentLength(1)
                .contentType("contentType")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"contentLength\": 1,\n"
            + "  \"contentType\": \"contentType\"\n"
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
            + "  \"uploadUrl\": \"uploadUrl\",\n"
            + "  \"downloadUrl\": \"downloadUrl\"\n"
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
