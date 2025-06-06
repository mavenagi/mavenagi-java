/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analytics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mavenagi.api.core.ClientOptions;
import com.mavenagi.api.core.MavenAGIApiException;
import com.mavenagi.api.core.MavenAGIException;
import com.mavenagi.api.core.MediaTypes;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.core.RequestOptions;
import com.mavenagi.api.resources.analytics.types.ChartResponse;
import com.mavenagi.api.resources.analytics.types.ConversationChartRequest;
import com.mavenagi.api.resources.analytics.types.ConversationTableRequest;
import com.mavenagi.api.resources.analytics.types.ConversationTableResponse;
import com.mavenagi.api.resources.analytics.types.FeedbackTableRequest;
import com.mavenagi.api.resources.analytics.types.FeedbackTableResponse;
import com.mavenagi.api.resources.commons.errors.BadRequestError;
import com.mavenagi.api.resources.commons.errors.NotFoundError;
import com.mavenagi.api.resources.commons.errors.ServerError;
import com.mavenagi.api.resources.commons.types.ErrorMessage;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AnalyticsClient {
    protected final ClientOptions clientOptions;

    public AnalyticsClient(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
    }

    /**
     * Retrieves structured conversation data formatted as a table, allowing users to group, filter, and define specific metrics to display as columns.
     */
    public ConversationTableResponse getConversationTable(ConversationTableRequest request) {
        return getConversationTable(request, null);
    }

    /**
     * Retrieves structured conversation data formatted as a table, allowing users to group, filter, and define specific metrics to display as columns.
     */
    public ConversationTableResponse getConversationTable(
            ConversationTableRequest request, RequestOptions requestOptions) {
        HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1")
                .addPathSegments("tables/conversations")
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
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), ConversationTableResponse.class);
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
     * Fetches conversation data visualized in a chart format. Supported chart types include pie chart, date histogram, and stacked bar charts.
     */
    public ChartResponse getConversationChart(ConversationChartRequest request) {
        return getConversationChart(request, null);
    }

    /**
     * Fetches conversation data visualized in a chart format. Supported chart types include pie chart, date histogram, and stacked bar charts.
     */
    public ChartResponse getConversationChart(ConversationChartRequest request, RequestOptions requestOptions) {
        HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1")
                .addPathSegments("charts/conversations")
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
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), ChartResponse.class);
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
     * Retrieves structured feedback data formatted as a table, allowing users to group, filter,  and define specific metrics to display as columns.
     */
    public FeedbackTableResponse getFeedbackTable(FeedbackTableRequest request) {
        return getFeedbackTable(request, null);
    }

    /**
     * Retrieves structured feedback data formatted as a table, allowing users to group, filter,  and define specific metrics to display as columns.
     */
    public FeedbackTableResponse getFeedbackTable(FeedbackTableRequest request, RequestOptions requestOptions) {
        HttpUrl httpUrl = HttpUrl.parse(this.clientOptions.environment().getUrl())
                .newBuilder()
                .addPathSegments("v1")
                .addPathSegments("tables/feedback")
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
                return ObjectMappers.JSON_MAPPER.readValue(responseBody.string(), FeedbackTableResponse.class);
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
