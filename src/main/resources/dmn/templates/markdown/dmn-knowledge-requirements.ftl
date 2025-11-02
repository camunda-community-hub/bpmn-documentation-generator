<#import "../dmn-template-util.ftl" as util />

<#macro listKnowledgeRequirements knowledgeRequirements>

    <#if knowledgeRequirements?has_content>
### Knowledge Requirements
        <#list knowledgeRequirements as knowledgeRequirement>
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
        </#list>
    </#if>

</#macro>