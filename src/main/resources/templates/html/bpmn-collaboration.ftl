<#import "../bpmn-template-util.ftl" as util/>

<#macro listCollaboration collaboration>

<details>
    <summary><h2>Collaboration</h2></summary>
    <sup>(${collaboration.id})</sup><br/><br/>

    <#if collaboration.documentation?has_content>
        <blockquote>${collaboration.documentation}</blockquote>
    <#else>
        <blockquote>Not documented.</blockquote>
    </#if>

    <#list collaboration.extensions>
        <#items as extensionKey, extensionValue>
            <#if extensionKey == "properties">
                <h4><em>Properties</em></h4>
                <table>
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Value</td>
                    </tr>
                    </thead>
                    <tbody>
                    <#list extensionValue>
                        <#items as itemKey, itemValue>
                            <tr>
                                <td>${itemKey}</td>
                                <td>${itemValue}</td>
                            </tr>
                        </#items>
                    </#list>
                    </tbody>
                </table>
            </#if>
        </#items>
    </#list>

    <#list collaboration.extensions>
        <#items as extensionKey, extensionValue>
            <#if extensionKey == "exampleData">
                <h4><em>Example Data</em></h4>
                <#list extensionValue>

                    <#items as itemKey, itemValue>
                        <pre>
                        <code class="language-json">
                            ${itemValue}
                        </code>
                    </pre>
                    </#items>
                </#list>
            </#if>
        </#items>
    </#list>
</details>

<#if collaboration.participants?has_content>
    <details>
        <summary><h2>Participants</h2></summary>

        <#list collaboration.participants>
            <ul>
                <#items as participant>
                    <li><a href="#${participant.id}"><@util.emptyOrNull participant.name "participant" /></a></li>
                </#items>
            </ul>
        </#list>

        <#list collaboration.participants>
            <#items as participant>
                <h3 id="${participant.id}"><@util.emptyOrNull participant.name "participant" /></h3>
                <sup>(id: ${participant.id})</sup><br/><br/>
                <#if participant.documentation?has_content>
                    <blockquote>${participant.documentation}</blockquote>
                <#else>
                    <blockquote>Not documented.</blockquote>
                </#if>
                <p>process <a href="#${participant.processRef}">(<strong>${participant.processRef}</strong>)</a></p>

                <#list collaboration.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "properties">
                            <h4><em>Properties</em></h4>
                            <table>
                                <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Value</td>
                                </tr>
                                </thead>
                                <tbody>
                                <#list extensionValue>
                                    <#items as itemKey, itemValue>
                                        <tr>
                                            <td>${itemKey}</td>
                                            <td>${itemValue}</td>
                                        </tr>
                                    </#items>
                                </#list>
                                </tbody>
                            </table>
                        </#if>
                    </#items>
                </#list>

                <#list participant.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "exampleData">
                            <h4><em>Example Data</em></h4>

                            <#list extensionValue>
                                <#items as itemKey, itemValue>
                                    <pre>
                                    <code class="language-json">
                                        ${itemValue}
                                    </code>
                                </pre>
                                </#items>
                            </#list>
                        </#if>
                    </#items>
                </#list>
            </#items>
        <#else>
            <p>None.</p>
        </#list>
    </details>
</#if>

</#macro>