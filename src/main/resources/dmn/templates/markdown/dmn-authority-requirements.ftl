<#import "../dmn-template-util.ftl" as util />

<#macro listAuthorityRequirements authorityRequirements>

    <#if authorityRequirements?has_content>
### Authority Requirements
        <#list authorityRequirements as authorityRequirement>
${authorityRequirement.id}
<@util.showDocumentation authorityRequirement.description />
            <#if authorityRequirement.requiredDecisionHref?has_content>
Decision ${authorityRequirement.requiredDecisionHref}
            </#if>
            <#if authorityRequirement.requiredInputHref?has_content>
Input ${authorityRequirement.requiredInputHref}
            </#if>
            <#if authorityRequirement.requiredAuthorityHref?has_content>
Authority ${authorityRequirement.requiredAuthorityHref}
            </#if>
        </#list>
    </#if>

</#macro>