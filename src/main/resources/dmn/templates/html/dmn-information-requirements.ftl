<#import "../dmn-template-util.ftl" as util />

<#macro listInformationRequirements informationRequirements>
    <#if informationRequirements?has_content>
        <h3>Information Requirements</h3>
        <#list informationRequirements as informationRequirement>
            <p>
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
            </p>
        </#list>
    </#if>

</#macro>