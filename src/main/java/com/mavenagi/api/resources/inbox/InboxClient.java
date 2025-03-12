/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.inbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mavenagi.api.core.ClientOptions;
import com.mavenagi.api.core.MavenAGIApiException;
import com.mavenagi.api.core.MavenAGIException;
import com.mavenagi.api.core.MediaTypes;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.core.RequestOptions;
import com.mavenagi.api.resources.commons.errors.BadRequestError;
import com.mavenagi.api.resources.commons.errors.NotFoundError;
import com.mavenagi.api.resources.commons.errors.ServerError;
import com.mavenagi.api.resources.commons.types.ErrorMessage;
import com.mavenagi.api.resources.commons.types.InboxItem;
import com.mavenagi.api.resources.commons.types.InboxItemFix;
import com.mavenagi.api.resources.inbox.requests.InboxItemFixRequest;
import com.mavenagi.api.resources.inbox.requests.InboxItemIgnoreRequest;
import com.mavenagi.api.resources.inbox.requests.InboxItemRequest;
import com.mavenagi.api.resources.inbox.types.ApplyInboxItemFixRequest;
import com.mavenagi.api.resources.inbox.types.InboxSearchRequest;
import com.mavenagi.api.resources.inbox.types.InboxSearchResponse;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InboxClient {
    protected final ClientOptions clientOptions;

    public InboxClient(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
    }

    /**
     * Retrieve a paginated list of inbox items for an agent.
     */
    public InboxSearchResponse search() {
        return search(InboxSearchRequest.builder().build());
    }

    /**
     * Retrieve a paginated list of inbox items for an agent.
     */
    public InboxSearchResponse search(InboxSearchRequest request) {
        return search(request, null);
    }

    /**
     * Retrieve a paginated list of inbox items for an agent.
     */
    public InboxSearchResponse search(InboxSearchRequest request, RequestOptions requestOptions) {
        HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1/inbox")
                .addPathSegments("search")
                .build();
        RequestBody body;
        try {
            body = RequestBody.create(
                    ObjectMappers.JSON_MAPPER.writeValueAsBytes(request), MediaTypes.APPLICATION_JSON);
        } catch (JsonProcessingException e) {
            throw new MavenAGIException("Failed to serialize request", e);
        }
        Request okhttpRequest = new Request.Builder()
                .url(httpUrl)
                .method("POST", body)
                .headers(Headers.of(clientOptions.headers(requestOptions)))
                .addHeader("Content-Type", "application/json")
                .build();
        OkHttpClient client = clientOptions.httpClient();
        if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
            client = clientOptions.httpClientWithTimeout(requestOptions);
        }
        try (Response response = client.newCall(okhttpRequest).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), InboxSearchResponse.class);
            }
            String responseBodyString = responseBody != null ? responseBody.string() : "{}";
            try {
                switch (response.code()) {
                    case 400:
                        throw new BadRequestError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 404:
                        throw new NotFoundError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 500:
                        throw new ServerError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                }
            } catch (JsonProcessingException ignored) {
                // unable to map error response, throwing generic error
            }
            throw new MavenAGIApiException(
                    "Error with status code " + response.code(),
                    response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        } catch (IOException e) {
            throw new MavenAGIException("Network error executing HTTP request", e);
        }
    }

    /**
     * Retrieve details of a specific inbox item by its ID.
     */
    public InboxItem get(String inboxItemId, InboxItemRequest request) {
        return get(inboxItemId, request, null);
    }

    /**
     * Retrieve details of a specific inbox item by its ID.
     */
    public InboxItem get(String inboxItemId, InboxItemRequest request, RequestOptions requestOptions) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1/inbox")
                .addPathSegment(inboxItemId);
        httpUrl.addQueryParameter("appId", request.getAppId());
        Request.Builder _requestBuilder = new Request.Builder()
                .url(httpUrl.build())
                .method("GET", null)
                .headers(Headers.of(clientOptions.headers(requestOptions)))
                .addHeader("Content-Type", "application/json");
        Request okhttpRequest = _requestBuilder.build();
        OkHttpClient client = clientOptions.httpClient();
        if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
            client = clientOptions.httpClientWithTimeout(requestOptions);
        }
        try (Response response = client.newCall(okhttpRequest).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), InboxItem.class);
            }
            String responseBodyString = responseBody != null ? responseBody.string() : "{}";
            try {
                switch (response.code()) {
                    case 400:
                        throw new BadRequestError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 404:
                        throw new NotFoundError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 500:
                        throw new ServerError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                }
            } catch (JsonProcessingException ignored) {
                // unable to map error response, throwing generic error
            }
            throw new MavenAGIApiException(
                    "Error with status code " + response.code(),
                    response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        } catch (IOException e) {
            throw new MavenAGIException("Network error executing HTTP request", e);
        }
    }

    /**
     * Retrieve a suggested fix. Includes document information if the fix is a Missing Knowledge suggestion.
     */
    public InboxItemFix getFix(String inboxItemFixId, InboxItemFixRequest request) {
        return getFix(inboxItemFixId, request, null);
    }

    /**
     * Retrieve a suggested fix. Includes document information if the fix is a Missing Knowledge suggestion.
     */
    public InboxItemFix getFix(String inboxItemFixId, InboxItemFixRequest request, RequestOptions requestOptions) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1/inbox")
                .addPathSegments("fix")
                .addPathSegment(inboxItemFixId);
        httpUrl.addQueryParameter("appId", request.getAppId());
        Request.Builder _requestBuilder = new Request.Builder()
                .url(httpUrl.build())
                .method("GET", null)
                .headers(Headers.of(clientOptions.headers(requestOptions)))
                .addHeader("Content-Type", "application/json");
        Request okhttpRequest = _requestBuilder.build();
        OkHttpClient client = clientOptions.httpClient();
        if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
            client = clientOptions.httpClientWithTimeout(requestOptions);
        }
        try (Response response = client.newCall(okhttpRequest).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), InboxItemFix.class);
            }
            String responseBodyString = responseBody != null ? responseBody.string() : "{}";
            try {
                switch (response.code()) {
                    case 400:
                        throw new BadRequestError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 404:
                        throw new NotFoundError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 500:
                        throw new ServerError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                }
            } catch (JsonProcessingException ignored) {
                // unable to map error response, throwing generic error
            }
            throw new MavenAGIApiException(
                    "Error with status code " + response.code(),
                    response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        } catch (IOException e) {
            throw new MavenAGIException("Network error executing HTTP request", e);
        }
    }

    /**
     * Apply a fix to an inbox item with a specific document.
     */
    public void applyFix(String inboxItemFixId, ApplyInboxItemFixRequest request) {
        applyFix(inboxItemFixId, request, null);
    }

    /**
     * Apply a fix to an inbox item with a specific document.
     */
    public void applyFix(String inboxItemFixId, ApplyInboxItemFixRequest request, RequestOptions requestOptions) {
        HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1/inbox")
                .addPathSegments("fix")
                .addPathSegment(inboxItemFixId)
                .addPathSegments("apply")
                .build();
        RequestBody body;
        try {
            body = RequestBody.create(
                    ObjectMappers.JSON_MAPPER.writeValueAsBytes(request), MediaTypes.APPLICATION_JSON);
        } catch (JsonProcessingException e) {
            throw new MavenAGIException("Failed to serialize request", e);
        }
        Request okhttpRequest = new Request.Builder()
                .url(httpUrl)
                .method("POST", body)
                .headers(Headers.of(clientOptions.headers(requestOptions)))
                .addHeader("Content-Type", "application/json")
                .build();
        OkHttpClient client = clientOptions.httpClient();
        if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
            client = clientOptions.httpClientWithTimeout(requestOptions);
        }
        try (Response response = client.newCall(okhttpRequest).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                return;
            }
            String responseBodyString = responseBody != null ? responseBody.string() : "{}";
            try {
                switch (response.code()) {
                    case 400:
                        throw new BadRequestError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 404:
                        throw new NotFoundError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 500:
                        throw new ServerError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                }
            } catch (JsonProcessingException ignored) {
                // unable to map error response, throwing generic error
            }
            throw new MavenAGIApiException(
                    "Error with status code " + response.code(),
                    response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        } catch (IOException e) {
            throw new MavenAGIException("Network error executing HTTP request", e);
        }
    }

    /**
     * Ignore a specific inbox item by its ID.
     */
    public void ignore(String inboxItemId, InboxItemIgnoreRequest request) {
        ignore(inboxItemId, request, null);
    }

    /**
     * Ignore a specific inbox item by its ID.
     */
    public void ignore(String inboxItemId, InboxItemIgnoreRequest request, RequestOptions requestOptions) {
        HttpUrl.Builder httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1/inbox")
                .addPathSegment(inboxItemId)
                .addPathSegments("ignore");
        httpUrl.addQueryParameter("appId", request.getAppId());
        Request.Builder _requestBuilder = new Request.Builder()
                .url(httpUrl.build())
                .method("POST", RequestBody.create("", null))
                .headers(Headers.of(clientOptions.headers(requestOptions)));
        Request okhttpRequest = _requestBuilder.build();
        OkHttpClient client = clientOptions.httpClient();
        if (requestOptions != null && requestOptions.getTimeout().isPresent()) {
            client = clientOptions.httpClientWithTimeout(requestOptions);
        }
        try (Response response = client.newCall(okhttpRequest).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                return;
            }
            String responseBodyString = responseBody != null ? responseBody.string() : "{}";
            try {
                switch (response.code()) {
                    case 400:
                        throw new BadRequestError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 404:
                        throw new NotFoundError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                    case 500:
                        throw new ServerError(
                                ObjectMappers.JSON_MAPPER.readValue(responseBodyString, ErrorMessage.class));
                }
            } catch (JsonProcessingException ignored) {
                // unable to map error response, throwing generic error
            }
            throw new MavenAGIApiException(
                    "Error with status code " + response.code(),
                    response.code(),
                    ObjectMappers.JSON_MAPPER.readValue(responseBodyString, Object.class));
        } catch (IOException e) {
            throw new MavenAGIException("Network error executing HTTP request", e);
        }
    }
}
