<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-extensions.ftl" as extensions>

<#macro listCollaboration collaboration>

<details>
    <summary><h2>Collaboration</h2></summary>
    <sup>(${collaboration.id})</sup><br/><br/>

    <#if collaboration.documentation?has_content>
        <blockquote>${collaboration.documentation}</blockquote>
    <#else>
        <blockquote>Not documented.</blockquote>
    </#if>

    <#if collaboration.extensions?has_content>
        <@extensions.listExtensions extensions=collaboration.extensions />
    </#if>
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
                <p>Connected to process <a href="#${participant.processRef}"><strong>${participant.processName}</strong></a></p>
                <#if participant.extensions?has_content>
                    <p>
                        <@extensions.listExtensions participant.extensions />
                    </p>
                </#if>
            </#items>
        <#else>
            <p>None.</p>
        </#list>
    </details>
</#if>

</#macro>