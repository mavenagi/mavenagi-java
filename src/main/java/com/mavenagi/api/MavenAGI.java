/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api;

import com.mavenagi.api.core.ClientOptions;
import com.mavenagi.api.core.Suppliers;
import com.mavenagi.api.resources.actions.ActionsClient;
import com.mavenagi.api.resources.appsettings.AppSettingsClient;
import com.mavenagi.api.resources.conversation.ConversationClient;
import com.mavenagi.api.resources.knowledge.KnowledgeClient;
import com.mavenagi.api.resources.translations.TranslationsClient;
import com.mavenagi.api.resources.triggers.TriggersClient;
import com.mavenagi.api.resources.users.UsersClient;
import java.util.function.Supplier;

public class MavenAGI {
    protected final ClientOptions clientOptions;

    protected final Supplier<ActionsClient> actionsClient;

    protected final Supplier<AppSettingsClient> appSettingsClient;

    protected final Supplier<ConversationClient> conversationClient;

    protected final Supplier<KnowledgeClient> knowledgeClient;

    protected final Supplier<TranslationsClient> translationsClient;

    protected final Supplier<TriggersClient> triggersClient;

    protected final Supplier<UsersClient> usersClient;

    public MavenAGI(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        this.actionsClient = Suppliers.memoize(() -> new ActionsClient(clientOptions));
        this.appSettingsClient = Suppliers.memoize(() -> new AppSettingsClient(clientOptions));
        this.conversationClient = Suppliers.memoize(() -> new ConversationClient(clientOptions));
        this.knowledgeClient = Suppliers.memoize(() -> new KnowledgeClient(clientOptions));
        this.translationsClient = Suppliers.memoize(() -> new TranslationsClient(clientOptions));
        this.triggersClient = Suppliers.memoize(() -> new TriggersClient(clientOptions));
        this.usersClient = Suppliers.memoize(() -> new UsersClient(clientOptions));
    }

    public ActionsClient actions() {
        return this.actionsClient.get();
    }

    public AppSettingsClient appSettings() {
        return this.appSettingsClient.get();
    }

    public ConversationClient conversation() {
        return this.conversationClient.get();
    }

    public KnowledgeClient knowledge() {
        return this.knowledgeClient.get();
    }

    public TranslationsClient translations() {
        return this.translationsClient.get();
    }

    public TriggersClient triggers() {
        return this.triggersClient.get();
    }

    public UsersClient users() {
        return this.usersClient.get();
    }

    public static MavenAGIBuilder builder() {
        return new MavenAGIBuilder();
    }
}
