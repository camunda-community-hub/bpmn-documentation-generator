<#import "../dmn-template-util.ftl" as util />

<#macro listKnowledgeRequirements knowledgeRequirements>

    <#if knowledgeRequirements?has_content>
### Knowledge Requirements

        <#list knowledgeRequirements as knowledgeRequirement>
            <#if knowledgeRequirement.requiredDecisionHref?has_content>
* [Decision](${knowledgeRequirement.requiredDecisionHref})
<@util.showDocumentation knowledgeRequirement.description />
            </#if>
            <#if knowledgeRequirement.requiredInputHref?has_content>
* [Input Data](${knowledgeRequirement.requiredInputHref})
<@util.showDocumentation knowledgeRequirement.description />
            </#if>
            <#if knowledgeRequirement.requiredKnowledgeHref?has_content>
* [Business Knowledge Model](${knowledgeRequirement.requiredKnowledgeHref})
<@util.showDocumentation knowledgeRequirement.description />
            </#if>
        </#list>
    </#if>

</#macro>