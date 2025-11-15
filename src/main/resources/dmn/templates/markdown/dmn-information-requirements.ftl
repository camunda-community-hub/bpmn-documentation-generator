<#import "../dmn-template-util.ftl" as util />

<#macro listInformationRequirements informationRequirements>

    <#if informationRequirements?has_content>
### Information Requirements
        <#list informationRequirements as informationRequirement>
<@util.showDocumentation informationRequirement.description />
            <#if informationRequirement.requiredDecisionHref?has_content>
* [Decision](${informationRequirement.requiredDecisionHref})
            </#if>
            <#if informationRequirement.requiredInputHref?has_content>
* [Input Data](${informationRequirement.requiredInputHref})
            </#if>
            <#if informationRequirement.requiredinformationHref?has_content>
* [Business Knowledge Model](${informationRequirement.requiredInformationHref})
            </#if>
        </#list>
    </#if>

</#macro>