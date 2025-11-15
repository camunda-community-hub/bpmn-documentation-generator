<#import "../dmn-template-util.ftl" as util />

<#macro listAuthorityRequirements authorityRequirements>

    <#if authorityRequirements?has_content>
### Authority Requirements

        <#list authorityRequirements as authorityRequirement>
<a id="${authorityRequirement.id}"></a>
            <#if authorityRequirement.requiredDecisionHref?has_content>
* [Decision](${authorityRequirement.requiredDecisionHref})
<@util.showDocumentation authorityRequirement.description />
            </#if>
            <#if authorityRequirement.requiredInputHref?has_content>
* [Input Data](${authorityRequirement.requiredInputHref})
<@util.showDocumentation authorityRequirement.description />
            </#if>
            <#if authorityRequirement.requiredAuthorityHref?has_content>
* [Knowledge Source](${authorityRequirement.requiredAuthorityHref})
<@util.showDocumentation authorityRequirement.description />
            </#if>
        </#list>
    </#if>

</#macro>