<#import "../dmn-template-util.ftl" as util />

<#macro listInformationRequirements informationRequirements>

    <#if informationRequirements?has_content>
### Information Requirements
        <#list informationRequirements as informationRequirement>
${informationRequirement.id}
<@util.showDocumentation informationRequirement.description />
            <#if informationRequirement.requiredDecisionHref?has_content>
Decision ${informationRequirement.requiredDecisionHref}
            </#if>
            <#if informationRequirement.requiredInputHref?has_content>
Input ${informationRequirement.requiredInputHref}
            </#if>
            <#if informationRequirement.requiredinformationHref?has_content>
Information ${informationRequirement.requiredInformationHref}
            </#if>
        </#list>
    </#if>

</#macro>