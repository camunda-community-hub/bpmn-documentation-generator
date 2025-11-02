<#import "../dmn-template-util.ftl" as util />

<#macro listKnowledgeRequirements knowledgeRequirements>

    <#if knowledgeRequirements?has_content>
        <h3>Knowledge Requirements</h3>
        <#list knowledgeRequirements as knowledgeRequirement>
            <p>
                ${knowledgeRequirement.id}
                <@util.showDocumentation knowledgeRequirement.description />
                <#if knowledgeRequirement.requiredDecisionHref?has_content>
                    Decision ${knowledgeRequirement.requiredDecisionHref}
                </#if>
                <#if knowledgeRequirement.requiredInputHref?has_content>
                    Input ${knowledgeRequirement.requiredInputHref}
                </#if>
                <#if knowledgeRequirement.requiredKnowledgeHref?has_content>
                    Knowledge ${knowledgeRequirement.requiredKnowledgeHref}
                </#if>
            </p>
        </#list>
    </#if>

</#macro>